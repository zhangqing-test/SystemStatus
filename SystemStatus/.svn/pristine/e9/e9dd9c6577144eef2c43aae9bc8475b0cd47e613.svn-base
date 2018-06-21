package com.system.quartz.task;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.alibaba.fastjson.JSONObject;
import com.system.common.constants.StatusConstants;
import com.system.common.util.ComputeUtil;
import com.system.common.util.IPUtil;
import com.system.entity.Hardware;
import com.system.entity.ServerInfo;
import com.system.entity.Services;

@Component
@Scope("prototype")
public class LoadSystemStatusTask implements Runnable, Serializable {

	private static final Logger LOG = LoggerFactory.getLogger(LoadSystemStatusTask.class);

	private static final long serialVersionUID = -5155368502231023838L;

	private Services services;

	private Hardware hardware;

	private Date datetime;

	private List<ServerInfo> serverInfos;

	public void init(Services services, Hardware hardware, Date datetime, List<ServerInfo> serverInfos) {
		this.services = services;
		this.hardware = hardware;
		this.datetime = datetime;
		this.serverInfos = serverInfos;
	}

	public void run() {
		// 1 根据server类型 区分获取数据方式
		String encode = "";
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("1", true);
			jsonObject.put("2", true);
			jsonObject.put("3", services.getSpaceName());
			String string = jsonObject.toString();
			encode = URLEncoder.encode(string, "UTF-8");
		} catch (Exception e1) {
			LOG.error("参数编码异常", e1);
		}
		// System.out.println(encode);

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://" + hardware.getIp() + ":9090/SystemControl/services/SystemService?wsdl");
		String wsdlData = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://webService.system.com\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" + "<soapenv:Body> <ser:getStatus><query>" + encode + "</query></ser:getStatus> </soapenv:Body> </soapenv:Envelope>";
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
			JSONObject jsonObject = JSONObject.parseObject(returnCont);
			Integer cpu = jsonObject.getInteger(StatusConstants.CPU);
			JSONObject mjb = jsonObject.getJSONObject(StatusConstants.MEMORY);
			Integer freeMemory = mjb.getInteger(StatusConstants.FREE_MEMORY);
			Integer usedMemory = mjb.getInteger(StatusConstants.USED_MEMORY);
			Integer totalMemory = mjb.getInteger(StatusConstants.TOTAL_MEMORY);
			JSONObject sjb = jsonObject.getJSONObject(StatusConstants.SPACE);
			String space = sjb.toString();

			// 计算最大磁盘利用率
			String spaceName = services.getSpaceName();
			String[] spaceNames = spaceName.split(",");
			Integer max = null;
			for (String key : spaceNames) {
				JSONObject value = sjb.getJSONObject(key);
				Integer used = value.getInteger(StatusConstants.USED_SPACE);
				Integer total = value.getInteger(StatusConstants.TOTAL_SPACE);
				int temp = ComputeUtil.div(used, total, 2);
				if (null == max) {
					max = temp;
				} else {
					max = max > temp ? max : temp;
				}
			}
			// 服务器信息保存
			ServerInfo serverInfo = new ServerInfo();

			String port=services.getPort();
			if (null != port&&!"".equals(port)) {
				String[] ports = port.split(",");
				JSONObject jports = new JSONObject();
				for (String temp : ports) {
					if(isInteger(temp.trim())){
						boolean flag = IPUtil.checkPort(hardware.getIp(), Integer.valueOf(temp.trim()));
						jports.put(temp, flag);
					}
				}
				String portResult = jports.toString();
				serverInfo.setPortStatus(portResult);
			}
			String id = UUID.randomUUID().toString().replaceAll("-", "");
			serverInfo.setCpu(cpu);
			serverInfo.setDatetime(datetime);
			serverInfo.setUid(id);
			serverInfo.setMemoryFree(freeMemory);
			serverInfo.setMemoryTotal(totalMemory);
			serverInfo.setMemoryUsed(usedMemory);
			serverInfo.setServicesId(services.getId());
			serverInfo.setSpace(space);
			serverInfo.setSpaceMax(max);
			
			// 计算显示排序
			Integer order = 0;
			String calcType = services.getCalcType();
			String[] split = calcType.split(",");
			int div = split.length;
			for (String calc : split) {
				if (calc.equals("1")) {
					order += cpu;
				} else if (calc.equals("2")) {
					int temp = ComputeUtil.div(usedMemory, totalMemory, 2);
					order += temp;
				} else if (calc.equals("3")) {
					order += max;
				}
			}
			int order2 = order / div;

			serverInfo.setOrders(order2);

			serverInfos.add(serverInfo);
			services.setStatus(1);
		} catch (Exception e) {
			LOG.error("serverInfo调用SystemControl接口异常", e);
			// 服务器信息保存
			ServerInfo serverInfo = new ServerInfo();
			String id = UUID.randomUUID().toString().replaceAll("-", "");
			serverInfo.setCpu(0);
			serverInfo.setDatetime(datetime);
			serverInfo.setUid(id);
			serverInfo.setMemoryFree(1);
			serverInfo.setMemoryTotal(1);
			serverInfo.setMemoryUsed(0);
			serverInfo.setServicesId(services.getId());
			String spaceName = services.getSpaceName();
			JSONObject jb = new JSONObject();
			if (StringUtils.isNotBlank(spaceName)) {
				String[] split = spaceName.split(",");
				for (String space : split) {
					JSONObject temp = new JSONObject();
					temp.put("freeSpace", Integer.MAX_VALUE);
					temp.put("usedSpace", 0);
					temp.put("totalSpace", Integer.MAX_VALUE);
					jb.put(space, temp);
				}

			}
			serverInfo.setSpace(jb.toJSONString());
			serverInfo.setSpaceMax(0);
			serverInfo.setOrders(0);
			serverInfos.add(serverInfo);
			services.setStatus(-1);
		}
	}
	  public static boolean isInteger(String str) {  
	        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
	        return pattern.matcher(str).matches();  
	  }
}
