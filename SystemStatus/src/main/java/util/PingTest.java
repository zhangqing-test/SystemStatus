package util;

import java.io.IOException;
import java.io.StringReader;

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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.alibaba.fastjson.JSONObject;

public class PingTest {

	public static void main(String[] args) {
		getDataFromClient();
		// try {
		//
		// String data =
		// "{\"id\":\"1858\",\"rjfwmc\":\"18\",\"yjzhm\":\"sa\",\"rjzhm\":\"sa\",\"fwqmc\":\"18\",\"xzzhlx\":0,\"xzzhsm\":\"555\",\"xzzhrq\":\"2018-06-22\",\"zq\":\"100\",\"sfxyjrcjglyz\":0,\"sfxyktycfwqx\":0,\"syqx\":\"100\",\"yjzrr\":\"李亮\",\"yjzrrzw\":\"运维工程师\",\"yjzrrdh\":\"18862611677\",\"ejzrr\":\"李亮\",\"ejzrrzw\":\"运维工程师\",\"ejzrrdh\":\"18862611677\",\"sjzrr\":\"李亮\",\"sjzrrzw\":\"运维工程师\",\"sjzrrdh\":\"18862611677\",\"bzsm\":\"10000\"}";
		// Service service = new Service();
		// Call call = (Call) service.createCall();
		// // 取得新增信息
		// call.setTargetEndpointAddress("http://localhost:8080/SystemStatus/services/MonitorService?wsdl");
		// QName qn = new QName("http://webService.system.com",
		// "createAccount");
		// call.setOperationName(qn);
		//
		// call.addParameter("arg0", XMLType.XSD_STRING, ParameterMode.IN);
		//
		// Object[] object = new Object[] { data };
		//
		// // 设置参数类型
		// call.setReturnType(XMLType.XSD_BOOLEAN);
		// Boolean res = (Boolean) call.invoke(object);
		//
		// System.out.println("result>>>>>>>>>>>" + res);
		// call.clearOperation();
		// call.removeAllParameters();
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	private static JSONObject getDataFromClient() {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://localhost:8080/SystemStatus/services/MonitorService?wsdl");
		String data = "{\"rjfwmc\":\"18\",\"yjzhm\":\"sa\",\"rjzhm\":\"sa\",\"fwqmc\":\"18\",\"xzzhlx\":0,\"xzzhsm\":\"555\",\"xzzhrq\":\"2018-06-22\",\"zq\":\"100\",\"id\":\"1121\",\"sfxyjrcjglyz\":0,\"sfxyktycfwqx\":0,\"syqx\":\"100\",\"yjzrr\":\"李亮\",\"yjzrrzw\":\"运维工程师\",\"yjzrrdh\":\"18862611677\",\"ejzrr\":\"李亮\",\"ejzrrzw\":\"运维工程师\",\"ejzrrdh\":\"18862611677\",\"sjzrr\":\"李亮\",\"sjzrrzw\":\"运维工程师\",\"sjzrrdh\":\"18862611677\",\"bzsm\":\"10000\"}";
		String wsdlData = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://webService.system.com\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soapenv:Body> <ser:createAccount><str>" + data + "</str></ser:createAccount> </soapenv:Body> </soapenv:Envelope>";

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
