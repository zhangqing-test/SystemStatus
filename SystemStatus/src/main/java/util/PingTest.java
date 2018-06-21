package util;

import java.io.IOException;
import java.io.StringReader;
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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.alibaba.fastjson.JSONObject;
import com.system.entity.PingAndPortInfo;

public class PingTest {

	public static void main(String[] args) {

		// clientToTarget("128.1.10.145", "180.1.13.25", null, 0);
		String test = "{\"1433\",false}";
		System.out.println(test.replaceAll("\"", "\\\\\""));

	}

	private static PingAndPortInfo clientToTarget(String ip, String targetIp, String port, Integer type) {
		PingAndPortInfo pingAndPortInfo = new PingAndPortInfo();
		pingAndPortInfo.setUid(UUID.randomUUID().toString().replaceAll("-", ""));
		// pingAndPortInfo.setPingAndPortId(pingAndPort.getId());
		// pingAndPortInfo.setDatetime(date);
		pingAndPortInfo.setType(type);
		// 调用接口获取ping状态
		JSONObject pingJson = getDataFromClient(ip, targetIp, 4, null);
		System.out.println(pingJson);
		if (null != pingJson) {
			JSONObject pingData = pingJson.getJSONObject("4");
			// pingAndPortInfo.setPingStatus(pingData.getBoolean(CONNECT));
			// pingAndPortInfo.setPingMiss(pingData.getInteger(MISS));
			// pingAndPortInfo.setPingMin(pingData.getInteger(MIN));
			// pingAndPortInfo.setPingMax(pingData.getInteger(MAX));
			// pingAndPortInfo.setPingAvg(pingData.getInteger(AVG));
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

	private static JSONObject getDataFromClient(String ip, String targetIp, Integer command, String port) {
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
			return null;
		}

	}
}
