package com.system.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.net.telnet.TelnetClient;
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
import org.json.JSONObject;

import com.system.common.constants.StatusConstants;

/**
 * 检查ip-端口连接状态
 */
public class IPUtil {

	/**
	 * 检测ip状态
	 * 
	 * @param ip
	 * @return
	 */
	public static String checkPing(String ip) {
		JSONObject jb = new JSONObject();
		BufferedReader in = null;
		// 将要执行的ping命令,此命令是windows格式的命令
		Runtime r = Runtime.getRuntime();
		String pingCommand = "ping " + ip + " -n " + 5 + " -w " + 1000;
		try { // 执行命令并获取输出
			Process p = r.exec(pingCommand);
			if (p == null) {
				jb.put("connect", false);
				jb.put("miss", 100);
				return jb.toString();
			}
			in = new BufferedReader(new InputStreamReader(p.getInputStream(), Charset.forName("GBK")));
			String line = null;
			boolean flag = false;
			Integer missCount = null;
			Integer[] arr = null;
			while ((line = in.readLine()) != null) {
				if (getCheckResult(line) == 1) {
					flag = true;
				}

				if (null != getMissPackage(line)) {
					missCount = getMissPackage(line);
				}

				if (flag) {
					arr = getPingTime(line);
				}
			}
			jb.put("connect", flag);
			jb.put("miss", missCount);
			if (arr != null) {
				jb.put("min", arr[0]);
				jb.put("max", arr[1]);
				jb.put("avg", arr[2]);
			} else {
				jb.put("min", 0);
				jb.put("max", 0);
				jb.put("avg", 0);
			}
			return jb.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			// 出现异常则返回假
			jb.put("connect", false);
			jb.put("miss", 100);
			jb.put("min", 0);
			jb.put("max", 0);
			jb.put("avg", 0);
			return jb.toString();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 匹配连接成功正则
	 * 
	 * @param line
	 * @return
	 */
	private static int getCheckResult(String line) {
		Pattern pattern = Pattern.compile("(\\d+ms)(\\s+)(TTL=\\d+)", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(line);
		while (matcher.find()) {
			return 1;
		}
		return 0;
	}

	/**
	 * 获取丢失率正则
	 * 
	 * @param line
	 * @return
	 */
	private static Integer getMissPackage(String line) {
		Pattern pattern = Pattern.compile("(\\()([0-9]+)(\\%)", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(line);
		while (matcher.find()) {
			String match = matcher.group(0);
			return Integer.valueOf(match.substring(1, match.length() - 1));
		}
		return null;
	}

	/**
	 * 获取ping时间
	 * 
	 * @param line
	 * @return
	 */
	private static Integer[] getPingTime(String line) {
		Integer[] arr = new Integer[3];
		if (line.indexOf("最短") > 0) {
			String[] split = line.split("，");
			for (int i = 0; i < split.length; i++) {
				String string = split[i];
				Pattern pattern = Pattern.compile("\\d+", Pattern.CASE_INSENSITIVE);
				Matcher matcher = pattern.matcher(string);
				while (matcher.find()) {
					String match = matcher.group(0);
					arr[i] = Integer.valueOf(match);
				}
			}
		}
		return arr;
	}

	/**
	 * apache telnet检查端口连通
	 * 
	 * @param ip
	 * @param port
	 * @return
	 */
	public static boolean checkPort(String ip, Integer port) {
		TelnetClient client = new TelnetClient();
		try {
			client.setConnectTimeout(StatusConstants.TIME_OUT);
			client.connect(ip, port);
		} catch (IOException e) {

		}
		boolean available = client.isAvailable();
		try {
			client.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return available;
	}

	public static void main(String[] args) {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://172.4.1.20:9090/SystemControl/services/SystemService?wsdl");
		String wsdlData = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://webService.system.com\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" + "<soapenv:Body> <ser:ipCheck><command>4</command><ip>128.1.10.153</ip><port></port></ser:ipCheck> </soapenv:Body> </soapenv:Envelope>";

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
						System.out.println(result);
						return result;
					} else {
						return null;
					}
				} else {
					throw new ClientProtocolException("Unexpected response status: " + status);
				}
			}
		};

	}
}
