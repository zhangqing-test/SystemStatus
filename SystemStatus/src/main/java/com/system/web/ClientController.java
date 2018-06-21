package com.system.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.system.common.controller.BaseController;
import com.system.entity.ServerClient;
import com.system.service.IServerClientService;

/**
 * 客户端入口
 */
@Controller
@RequestMapping("client")
public class ClientController extends BaseController {

	private static final Logger LOG = LoggerFactory.getLogger(ClientController.class);

	private static final String PROJECT_PATH = "E:\\yang\\SystemControl && E:";// 更新客户端只能在本地，此为本地项目路径(指令~)

	// private static final String PROJECT_PATH = "D:\\workspace\\SystemControl
	// && D:";//更新客户端只能在本地，此为本地项目路径

	@Autowired
	private IServerClientService serverClientService;

	@RequestMapping("toclient")
	public String toClient(HttpServletRequest request, Model model) {
		return "updateClient";
	}

	/**
	 * 批量更新客户端
	 * 
	 * @param clientIp
	 * @param flag
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/update")
	public String batchUpdateClient(String clientIp, Integer flag) {
		// 指定服务器更新时加条件
		EntityWrapper<ServerClient> ew = new EntityWrapper<ServerClient>();
		if (flag == 0) {
			ew.eq("ip", clientIp);
		}
		// ew.eq("server_status", "2").or().eq("build_status",
		// false).or().eq("restart_status", false);
		List<ServerClient> list = serverClientService.selectList(ew);
		for (ServerClient serverClient : list) {
			// 1 获取需要更新的客户端ip
			String ip = serverClient.getIp();

			// 2 更新客户端状态标识， 调用mvn热部署脚本 更新
			serverClient.setServerStatus(2);
			serverClientService.updateById(serverClient);

			try {
				String cmd = "cmd /c \"cd " + PROJECT_PATH + "&&" + "call mvn tomcat:redeploy -Dmaven.test.skip=true -Dip=" + ip + "\"";
				System.err.println(cmd);
				LOG.info("Start>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				Process ps = Runtime.getRuntime().exec(cmd);
				BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream(), "UTF-8"));// 注意中文编码问题
				String line;
				int isok = 0;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
					LOG.info("runBat info=========>" + line);
					if (line.indexOf("BUILD SUCCESS") > 0) {
						LOG.info("*******成功********");
						serverClient.setBuildStatus(true);
					}

					if (line.indexOf("BUILD FAILURE") > 0) {
						LOG.info("*******失败********");
						serverClient.setBuildStatus(false);
						serverClientService.updateById(serverClient);
						if (flag == 0) {
							return "BUILD FAILURE!";
						}
						isok = 1;
						break;
					}
				}
				br.close();
				if (isok == 1) {
					continue;
				}

			} catch (IOException e) {
				e.printStackTrace();
				serverClient.setBuildStatus(false);
				serverClientService.updateById(serverClient);
				if (flag == 0) {
					return "BUILD FAILURE!";
				}
				continue;
			}

			// 3 调用重启脚本接口
			try {
				Thread.currentThread().sleep(5000);
			} catch (InterruptedException e2) {
				e2.printStackTrace();
			}
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost("http://" + ip + ":9090/SystemControl/services/SystemService?wsdl");
			String wsdlData = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://webService.system.com\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" + "<soapenv:Body> <ser:restartService></ser:restartService> </soapenv:Body> </soapenv:Envelope>";
			System.out.println(wsdlData);
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
				LOG.info("********restartService:" + returnCont);
				if ("success".equals(returnCont)) {
					serverClient.setRestartStatus(true);
				} else {
					serverClient.setRestartStatus(false);
					serverClientService.updateById(serverClient);
					if (flag == 0) {
						return "Restart Flase!";
					}
					continue;
				}

			} catch (Exception e) {
				LOG.error("serverInfo调用SystemControl接口异常", e);
				serverClient.setRestartStatus(false);
				serverClientService.updateById(serverClient);
				if (flag == 0) {
					return "Restart Flase!";
				}
				continue;
			}
			// 4 sleep检查getStatus接口
			try {
				Thread.currentThread().sleep(15000);
			} catch (InterruptedException e2) {
				e2.printStackTrace();
			}

			String encode = "";
			try {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("1", true);
				jsonObject.put("2", true);
				jsonObject.put("3", "C");
				String string = jsonObject.toString();
				encode = URLEncoder.encode(string, "UTF-8");
			} catch (Exception e1) {
				LOG.error("参数编码异常", e1);
			}

			CloseableHttpClient httpclient2 = HttpClients.createDefault();
			HttpPost httpPost2 = new HttpPost("http://" + ip + ":9090/SystemControl/services/SystemService?wsdl");
			String wsdlData2 = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://webService.system.com\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" + "<soapenv:Body> <ser:getStatus><query>" + encode + "</query></ser:getStatus> </soapenv:Body> </soapenv:Envelope>";
			System.out.println(wsdlData2);
			StringEntity myEntity2 = new StringEntity(wsdlData2, ContentType.create("text/xml", "UTF-8"));
			httpPost2.setEntity(myEntity2);

			// 发送请求
			ResponseHandler<String> responseHandler2 = new ResponseHandler<String>() {
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
				String responseBody2 = httpclient2.execute(httpPost2, responseHandler2);
				httpclient2.close();

				// 解析xml，获得return信息
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				StringReader sr = new StringReader(responseBody2);
				InputSource is = new InputSource(sr);
				Document document = db.parse(is);
				Element root = document.getDocumentElement();
				NodeList nodelist_return = root.getElementsByTagName("ns:return");
				String returnCont = nodelist_return.item(0).getTextContent();

				// 解析返回信息
				LOG.info("********querySuccess:" + returnCont);
				serverClient.setServerStatus(1);

			} catch (Exception e) {
				LOG.error("serverInfo调用SystemControl接口异常", e);
				serverClient.setServerStatus(0);
				serverClientService.updateById(serverClient);
				if (flag == 0) {
					return "Query False!";
				}
				continue;
			}

			// 5 更新客户端状态标识
			serverClientService.updateById(serverClient);

		}
		if (flag == 1) {
			return "Update Over!";
		}

		return "Client:" + clientIp + " Update Success!";
	}

	/**
	 * 更新失败的客户端
	 * 
	 * @param clientIp
	 * @param flag
	 * @return
	 */
	@ResponseBody
	@RequestMapping("bad")
	public List<ServerClient> badClient(String clientIp, Integer flag) {
		EntityWrapper<ServerClient> ew = new EntityWrapper<ServerClient>();
		ew.eq("server_status", "2").or().eq("build_status", false).or().eq("restart_status", false);
		List<ServerClient> lists = serverClientService.selectList(ew);
		return lists;
	}
}
