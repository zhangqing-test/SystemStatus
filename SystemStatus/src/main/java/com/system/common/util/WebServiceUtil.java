package com.system.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

import com.alibaba.fastjson.JSON;
import com.system.web.vo.ProcessInfo;

public class WebServiceUtil {
	public static void main(String[] args) {

		String address = "http://128.1.10.152:9090/SystemControl/services/SystemService?wsdl";
		String space = "http://webService.system.com";
		String method = "getProcessInfos";
		Map<String, String> param = new HashMap<String, String>();

		String result = callAxis(address, space, method, param);
		List<ProcessInfo> lis = JSON.parseArray(result, ProcessInfo.class);
		for (ProcessInfo processInfo : lis) {
			System.out.println(processInfo.toString());
		}

		System.out.println("CallWebService:" + result);

	}

	public static String callAxis(String address, String space, String method, Map<String, String> params) {
		Service service = new Service();
		String result = "";
		try {
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(address);
			call.setOperationName(new QName(space, method));
			call.setSOAPActionURI(space + method);
			Object[] objs = new Object[params.size()];
			int i = 0;
			for (String str : params.keySet()) {
				call.addParameter(str, XMLType.XSD_STRING, ParameterMode.IN);
				objs[i] = params.get(str);
				i++;
			}
			call.setReturnClass(String.class);
			result = (String) call.invoke(objs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
