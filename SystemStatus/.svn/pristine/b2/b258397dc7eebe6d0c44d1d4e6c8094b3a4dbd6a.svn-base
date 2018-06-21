package com.system.common.util;

import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

public class SendMsg {
	private static final String URL = "http://128.1.10.82:9999/RichWebService/services/WebService?wsdl";

	public static boolean sendMsg(String mobile, String content) {
		try {
			Service service = new Service();
			Call call = (Call) service.createCall();
			// 取得新增信息
			call.setTargetEndpointAddress(new java.net.URL(URL));
			call.setOperationName("sendMsg");
			call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);// 指定方法名
			call.addParameter("mobile", XMLType.XSD_STRING, ParameterMode.IN); // 设置参数类型
			call.addParameter("content", XMLType.XSD_STRING, ParameterMode.IN); // 设置参数类型
			call.addParameter("source", XMLType.XSD_STRING, ParameterMode.IN); // 设置参数类型
			call.addParameter("ch", XMLType.XSD_STRING, ParameterMode.IN); // 设置参数类型

			call.setReturnType(XMLType.XSD_STRING);
			String res = (String) call.invoke(new Object[] { mobile, content, "监控", "22" });

			call.clearOperation();
			call.removeAllParameters();

			if (res.indexOf("发送成功") >= 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
