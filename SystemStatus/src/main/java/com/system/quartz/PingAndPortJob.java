package com.system.quartz;

import java.io.IOException;
import java.io.StringReader;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.system.common.constants.StatusConstants;
import com.system.common.constants.SystemConfig;
import com.system.common.util.IPUtil;
import com.system.common.util.SendMsg;
import com.system.entity.Manager;
import com.system.entity.MonitorAlarmLog;
import com.system.entity.PingAndPort;
import com.system.entity.PingAndPortInfo;
import com.system.entity.ServerClient;
import com.system.service.IManagerService;
import com.system.service.IMonitorAlarmLogService;
import com.system.service.IPingAndPortInfoService;
import com.system.service.IPingAndPortService;
import com.system.service.IServerClientService;

@SuppressWarnings("unused")
public class PingAndPortJob extends QuartzJobBean {
	private static final Logger LOG = LoggerFactory.getLogger(PingAndPortJob.class);

	protected static final String LOCAL = "176.1.19.10";
	protected static final String CONNECT = "connect";
	protected static final String MISS = "miss";
	protected static final String MIN = "min";
	protected static final String MAX = "max";
	protected static final String AVG = "avg";

	private int timeout;
	private static int i = 0;

	// 调度工厂实例化后，经过timeout时间开始执行调度
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	private IPingAndPortService service;

	private IPingAndPortInfoService infoService;

	private IManagerService managerService;

	private IServerClientService serverClientService;

	private IMonitorAlarmLogService monitorAlarmLogService;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		service = webApplicationContext.getBean(IPingAndPortService.class);
		infoService = webApplicationContext.getBean(IPingAndPortInfoService.class);
		managerService = webApplicationContext.getBean(IManagerService.class);
		serverClientService = webApplicationContext.getBean(IServerClientService.class);
		monitorAlarmLogService = webApplicationContext.getBean(IMonitorAlarmLogService.class);

		LOG.info("获取网络连通状态开始>>>>>>");
		final Date date = new Date();
		// 1 获取所有配置
		List<PingAndPort> list = service.selectList(null);
		if (null != list) {
			for (PingAndPort pingAndPort : list) {
				// 2 循环处理
				if (pingAndPort.getLifecycle().equals(1)) {
					new Thread(new PingAndPortTask(pingAndPort, date)).start();
				}
			}
		}
	}

	class PingAndPortTask implements Runnable {
		private PingAndPort pingAndPort;
		private Date date;

		public PingAndPortTask(PingAndPort pingAndPort, Date date) {
			this.pingAndPort = pingAndPort;
			this.date = date;
		}

		public void run() {
			// 1 根据ip判断是不是服务器本机
			String ip = pingAndPort.getIp();
			String targetIp = pingAndPort.getTargetIp();
			pingAndPort.setDatetime(date);

			// 主到副 info
			PingAndPortInfo one = null;
			// 副到主 info
			PingAndPortInfo two = null;

			// 网络策略
			Integer type = pingAndPort.getType();
			switch (type) {
			case 1:
				// 双向
				// 主到副
				if (LOCAL.equals(ip)) {
					one = localToTarget(targetIp, pingAndPort.getTargetPort(), 0);
				} else {
					one = clientToTarget(ip, targetIp, pingAndPort.getTargetPort(), 0);
				}
				// 副到主
				if (LOCAL.equals(targetIp)) {
					two = localToTarget(ip, pingAndPort.getPort(), 1);
				} else {
					two = clientToTarget(targetIp, ip, pingAndPort.getPort(), 1);
				}
				break;
			case 2:
				// 单向-主到副
				if (LOCAL.equals(ip)) {
					one = localToTarget(targetIp, pingAndPort.getTargetPort(), 0);
				} else {
					one = clientToTarget(ip, targetIp, pingAndPort.getTargetPort(), 0);
				}
				break;
			case 3:
				// 单向-副到主
				if (LOCAL.equals(targetIp)) {
					two = localToTarget(ip, pingAndPort.getPort(), 1);
				} else {
					two = clientToTarget(targetIp, ip, pingAndPort.getPort(), 1);
				}
				break;
			default:
				break;
			}

			service.updateById(pingAndPort);
			if (null != one) {
				infoService.insert(one);
			}
			if (null != two) {
				infoService.insert(two);
			}

			// 判断客户端是否在更新状态不报警
			EntityWrapper<ServerClient> scew = new EntityWrapper<ServerClient>();
			scew.in("ip", new String[] { pingAndPort.getIp(), pingAndPort.getTargetIp() }).eq("server_status", 2);
			List<ServerClient> clients = serverClientService.selectList(scew);
			boolean msgFlag = true;
			if (null != clients && clients.size() > 0) {
				msgFlag = false;
			}

			if (msgFlag) {
				// 异常告警次数
				Integer limit = Integer.valueOf(SystemConfig.getProperty(StatusConstants.PING_WARN_COUNT));
				// 是否告警标识
				Boolean[] flag = new Boolean[] { false };
				// 告警内容
				StringBuffer sb = new StringBuffer();
				// 分析info
				if (null != one) {
					analysisInfo(one, limit, flag, sb);
				}

				if (null != two) {
					analysisInfo(two, limit, flag, sb);
				}

				if (flag[0]) {
					// 有ping或者port不通告警
					LOG.error(sb.toString());
					MonitorAlarmLog monitorAlarmLog = new MonitorAlarmLog();
					monitorAlarmLog.setRelationId(pingAndPort.getId());
					monitorAlarmLog.setAlarmTime(date);
					monitorAlarmLog.setStatus(1);
					monitorAlarmLog.setType(2);
					monitorAlarmLog.setAlarmContent(sb.toString());
					boolean insert = monitorAlarmLogService.insert(monitorAlarmLog);
					if (!insert) {
						LOG.error("报错日志入库失败" + monitorAlarmLog.toString());
					}

					sb.append("详情和修改通知地址:").append(StatusConstants.NET_LINK);
					if (pingAndPort.isMessage()) {
						String msg = sb.toString();

						EntityWrapper<MonitorAlarmLog> malew = new EntityWrapper<MonitorAlarmLog>();
						malew.eq("relation_id", pingAndPort.getId());
						malew.eq("status", 1);
						List<MonitorAlarmLog> lists = monitorAlarmLogService.selectList(malew);
						if (lists == null || (null != lists && lists.size() < Integer.valueOf(SystemConfig.getProperty(StatusConstants.NET_SECOND_LEVEL)))) {
							Integer firstManagerId = pingAndPort.getFirstManager();
							Manager firstManager = managerService.selectOne(new EntityWrapper<Manager>().eq("id", firstManagerId));
							if (null != firstManager) {
								SendMsg.sendMsg(firstManager.getManagerPhone(), msg);
								LOG.info("发送短信>>>联系人" + firstManager.getManagerName() + "内容:" + msg);
							}
						} else if (lists.size() < Integer.valueOf(SystemConfig.getProperty(StatusConstants.NET_THIRD_LEVEL))) {
							Integer firstManagerId = pingAndPort.getFirstManager();
							Integer secondManagerId = pingAndPort.getSecondManager();
							List<Manager> managers = managerService.selectList(new EntityWrapper<Manager>().in("id", new Object[] { firstManagerId, secondManagerId }));
							if (null != managers) {
								for (Manager manager : managers) {
									SendMsg.sendMsg(manager.getManagerPhone(), msg);
									LOG.info("发送短信>>>联系人" + manager.getManagerName() + "内容:" + msg);
								}
							}
						} else {
							Integer firstManagerId = pingAndPort.getFirstManager();
							Integer secondManagerId = pingAndPort.getSecondManager();
							Integer thirdManager = pingAndPort.getThirdManager();
							Object[] object = null;
							if (null != thirdManager) {
								object = new Object[] { firstManagerId, secondManagerId, thirdManager };
							} else {
								object = new Object[] { firstManagerId, secondManagerId };
							}
							List<Manager> managers = managerService.selectList(new EntityWrapper<Manager>().in("id", object));
							if (null != managers) {
								for (Manager manager : managers) {
									SendMsg.sendMsg(manager.getManagerPhone(), msg);
									LOG.info("发送短信>>>联系人" + manager.getManagerName() + "内容:" + msg);
								}
							}
						}

						// 如果是客户端问题通知开发
						StringBuffer sbdev = new StringBuffer();
						if (one.getPingAvg() == 1000) {
							sbdev.append(pingAndPort.getIpName()).append(pingAndPort.getIp()).append(">").append(pingAndPort.getTargetIpName()).append(pingAndPort.getTargetIp()).append("调用客户端接口异常;");
						}

						if (two.getPingAvg() == 1000) {
							sbdev.append(pingAndPort.getTargetIpName()).append(pingAndPort.getTargetIp()).append(">").append(pingAndPort.getIpName()).append(pingAndPort.getIp()).append("调用客户端接口异常;");
						}
						String content = sbdev.toString();
						if (StringUtils.isNotBlank(content)) {
							LOG.error(content);
							/*
							 * SendMsg.sendMsg(SystemConfig.getProperty(
							 * StatusConstants.DEV_TEL), content);
							 * LOG.info("发送短信>>>联系人-开发工程师" + "内容:" + content);
							 */
						}
					}
				}

				if (!flag[0]) {
					// net并且没告警 修改告警log标识
					EntityWrapper<MonitorAlarmLog> malew = new EntityWrapper<MonitorAlarmLog>();
					malew.eq("relation_id", pingAndPort.getId()).eq("status", 1).eq("type", 2);
					List<MonitorAlarmLog> alarmLogs = monitorAlarmLogService.selectList(malew);
					if (null != alarmLogs) {
						for (MonitorAlarmLog monitorAlarmLog : alarmLogs) {
							monitorAlarmLog.setStatus(0);
							monitorAlarmLogService.update(monitorAlarmLog, new EntityWrapper<MonitorAlarmLog>().eq("relation_id", monitorAlarmLog.getRelationId()).eq("alarm_time", monitorAlarmLog.getAlarmTime()).eq("type", monitorAlarmLog.getType()));
						}
					}
				}
			}
		}

		/**
		 * 176.1.19.10服务器本机ping其他ip/端口
		 * 
		 * @param ip
		 *            目标ip
		 * @param port
		 *            端口
		 * @param type
		 *            0-主到副 1-副到主
		 * @return
		 */
		private PingAndPortInfo localToTarget(String ip, String port, Integer type) {
			PingAndPortInfo pingAndPortInfo = new PingAndPortInfo();
			pingAndPortInfo.setUid(UUID.randomUUID().toString().replaceAll("-", ""));
			pingAndPortInfo.setPingAndPortId(pingAndPort.getId());
			pingAndPortInfo.setDatetime(date);
			pingAndPortInfo.setType(type);
			// 2 本机ping目标ip
			String result = IPUtil.checkPing(ip);
			JSONObject jb = JSONObject.parseObject(result);
			Boolean connect = jb.getBoolean(CONNECT);
			Integer miss = jb.getInteger(MISS);
			Integer min = jb.getInteger(MIN);
			Integer max = jb.getInteger(MAX);
			Integer avg = jb.getInteger(AVG);
			// 3 本机telnet目标端口
			if (null != port) {
				String[] ports = port.split(",");
				JSONObject jports = new JSONObject();
				for (String temp : ports) {
					boolean flag = IPUtil.checkPort(ip, Integer.valueOf(temp));
					jports.put(temp, flag);
				}
				String portResult = jports.toString();
				pingAndPortInfo.setPortStatus(portResult);
			}
			pingAndPortInfo.setPingStatus(connect);
			pingAndPortInfo.setPingMiss(miss);
			pingAndPortInfo.setPingMin(min);
			pingAndPortInfo.setPingMax(max);
			pingAndPortInfo.setPingAvg(avg);
			return pingAndPortInfo;
		}

		/**
		 * 调用客户端ping目标ip
		 * 
		 * @param ip
		 *            源ip
		 * @param targetIp
		 *            目标ip
		 * @param port
		 *            端口
		 * @param type
		 *            0-主到副 1-副到主
		 * @return
		 */
		private PingAndPortInfo clientToTarget(String ip, String targetIp, String port, Integer type) {
			PingAndPortInfo pingAndPortInfo = new PingAndPortInfo();
			pingAndPortInfo.setUid(UUID.randomUUID().toString().replaceAll("-", ""));
			pingAndPortInfo.setPingAndPortId(pingAndPort.getId());
			pingAndPortInfo.setDatetime(date);
			pingAndPortInfo.setType(type);
			// 调用接口获取ping状态
			JSONObject pingJson = getDataFromClient(ip, targetIp, 4, null);
			if (null != pingJson) {
				JSONObject pingData = pingJson.getJSONObject("4");
				pingAndPortInfo.setPingStatus(pingData.getBoolean(CONNECT));
				pingAndPortInfo.setPingMiss(pingData.getInteger(MISS));
				pingAndPortInfo.setPingMin(pingData.getInteger(MIN));
				pingAndPortInfo.setPingMax(pingData.getInteger(MAX));
				pingAndPortInfo.setPingAvg(pingData.getInteger(AVG));
			} else {
				pingAndPortInfo.setPingStatus(false);
				pingAndPortInfo.setPingMiss(100);
				pingAndPortInfo.setPingMin(1000);
				pingAndPortInfo.setPingMax(1000);
				pingAndPortInfo.setPingAvg(1000);
			}

			// 调用接口获取port状态
			if (StringUtils.isNotEmpty(port)) {
				JSONObject portJson = getDataFromClient(ip, targetIp, 5, port);
				if (null != portJson) {
					JSONObject portData = portJson.getJSONObject("5");
					pingAndPortInfo.setPortStatus(portData.toString());
				} else {
					JSONObject jb = new JSONObject();
					String[] split = port.split(",");
					for (String portTemp : split) {
						jb.put(portTemp, false);
					}
					pingAndPortInfo.setPortStatus(jb.toString());
				}
			}
			return pingAndPortInfo;
		}

		/**
		 * 调用客户端接口获取ip/port
		 * 
		 * @param ip
		 *            源ip
		 * @param targetIp
		 *            目标ip
		 * @param command
		 *            客户端指令 4-ping/5-port
		 * @param port
		 *            端口
		 * @return
		 */
		private JSONObject getDataFromClient(String ip, String targetIp, Integer command, String port) {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost("http://" + ip + ":9090/SystemControl/services/SystemService?wsdl");
			String wsdlData = null;
			if (4 == command) {
				wsdlData = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://webService.system.com\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" + "<soapenv:Body> <ser:ipCheck><command>4</command><ip>" + targetIp + "</ip><port></port></ser:ipCheck> </soapenv:Body> </soapenv:Envelope>";
			} else if (5 == command) {
				wsdlData = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://webService.system.com\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" + "<soapenv:Body> <ser:ipCheck><command>5</command><ip>" + targetIp + "</ip><port>" + port + "</port></ser:ipCheck> </soapenv:Body> </soapenv:Envelope>";
			}
			StringEntity myEntity = new StringEntity(wsdlData, ContentType.create("text/xml", "UTF-8"));
			httpPost.setEntity(myEntity);

			// 发送请求
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
				public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						if (null != entity) {
							String result = EntityUtils.toString(entity);
							return result;
						} else {
							return null;
						}
					} else {
						throw new ClientProtocolException("Unexpected response status: " + status);
					}
				}
			};

			try {
				// 返回的json对象
				String responseBody = httpclient.execute(httpPost, responseHandler);
				httpclient.close();

				// 解析xml，获得return信息
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				StringReader sr = new StringReader(responseBody);
				InputSource is = new InputSource(sr);
				Document document = db.parse(is);
				Element root = document.getDocumentElement();
				NodeList nodelist_return = root.getElementsByTagName("ns:return");
				String returnCont = nodelist_return.item(0).getTextContent();
				// 解析返回信息
				JSONObject jsonObject = JSONObject.parseObject(returnCont);
				return jsonObject;
			} catch (Exception e) {
				LOG.error("调用SystemControl接口异常,httpPost=" + httpPost + "wsdlData=" + wsdlData, e);
				return null;
			}

		}

		/**
		 * PingAndPortInfo通知报警
		 * 
		 * @param pingAndPortInfo
		 * @param limit
		 * @param flag
		 * @param sb
		 */
		private void analysisInfo(PingAndPortInfo pingAndPortInfo, Integer limit, Boolean[] flag, StringBuffer sb) {
			// 报错查询历史记录list
			List<PingAndPortInfo> tempList = null;
			if (pingAndPortInfo.isPingStatus()) {
				// 2 如果ping的通 检查端口
				String temp = pingAndPortInfo.getPortStatus();
				if (StringUtils.isNotEmpty(temp)) {
					JSONObject jb = JSONObject.parseObject(temp);
					Set<String> keySet = jb.keySet();
					// 端口不通 告警
					StringBuffer sbtemp = new StringBuffer();
					boolean portflag = false;

					if (pingAndPortInfo.getType() == 0) {
						// 主到副
						sbtemp.append(pingAndPort.getIpName()).append(pingAndPort.getIp()).append(">").append(pingAndPort.getTargetIpName()).append(pingAndPort.getTargetIp());
					} else {
						// 副到主
						sbtemp.append(pingAndPort.getTargetIpName()).append(pingAndPort.getTargetIp()).append(">").append(pingAndPort.getIpName()).append(pingAndPort.getIp());
					}

					for (String key : keySet) {
						Boolean value = jb.getBoolean(key);
						if (!value) {
							portflag = true;
							sbtemp.append("端口:").append(key).append("不通;");
						}
					}

					if (portflag) {
						if (null == tempList) {
							Page<PingAndPortInfo> page = new Page<PingAndPortInfo>();
							page.setCurrent(1);
							page.setSize(limit);
							page.setSearchCount(false);
							EntityWrapper<PingAndPortInfo> ew = new EntityWrapper<PingAndPortInfo>();
							ew.orderBy("datetime", false).eq("ping_and_port_id", pingAndPortInfo.getPingAndPortId()).eq("type", pingAndPortInfo.getType());

							Page<PingAndPortInfo> selectPage = infoService.selectPage(page, ew);
							tempList = selectPage.getRecords();
						}

						int count = 0;
						for (PingAndPortInfo pingAndPortInfoTemp : tempList) {
							String ports = pingAndPortInfoTemp.getPortStatus();
							if (StringUtils.isNotBlank(ports)) {
								// 不详细判断，只要历史有检测的端口不通就表示此次监测的端口有不通，计数+1
								/*
								 * JSONObject jb2 =
								 * JSONObject.parseObject(ports); Set<String>
								 * keySet2 = jb2.keySet(); for (String key2 :
								 * keySet2) { Boolean value2 =
								 * jb2.getBoolean(key2); if (!value2) { count++;
								 * break; } }
								 */
								if (StringUtils.containsIgnoreCase(ports, "false")) {
									count++;
								}

							}
						}

						if (count == limit) {
							// 确认告警
							flag[0] = true;
							sb.append(sbtemp.toString());
						}
					}

				}
			} else {
				// 1 如果长时间ping不通告警
				if (null == tempList) {
					Page<PingAndPortInfo> page = new Page<PingAndPortInfo>();
					page.setCurrent(1);
					page.setSize(limit);
					page.setSearchCount(false);
					EntityWrapper<PingAndPortInfo> ew = new EntityWrapper<PingAndPortInfo>();
					ew.orderBy("datetime", false).eq("ping_and_port_id", pingAndPortInfo.getPingAndPortId()).eq("type", pingAndPortInfo.getType());

					Page<PingAndPortInfo> selectPage = infoService.selectPage(page, ew);
					tempList = selectPage.getRecords();
				}

				int count = 0;
				for (PingAndPortInfo pingAndPortInfoTemp : tempList) {
					if (!pingAndPortInfoTemp.isPingStatus()) {
						count++;
					}
				}

				if (count == limit) {
					// 确认告警
					flag[0] = true;
					if (pingAndPortInfo.getType() == 0) {
						sb.append(pingAndPort.getIpName()).append(pingAndPort.getIp()).append(">").append(pingAndPort.getTargetIpName()).append(pingAndPort.getTargetIp()).append(":ping状态异常,丢包率:").append(pingAndPortInfo.getPingMiss()).append(";");
					} else {
						sb.append(pingAndPort.getTargetIpName()).append(pingAndPort.getTargetIp()).append(">").append(pingAndPort.getIpName()).append(pingAndPort.getIp()).append(":ping状态异常,丢包率:").append(pingAndPortInfo.getPingMiss()).append(";");
					}

				}
			}
		}
	}
}
