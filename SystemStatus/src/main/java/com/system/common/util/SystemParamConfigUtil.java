package com.system.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

/**
 * DeadlockParam工具类
 */
public final class SystemParamConfigUtil {

	private static Map<String, String> sysParamMap = null;
	private static List<String> organCodeList = null;
	private static Map<String, String> organNameMap = null;

	public static String getParamValueByParam(String param) {
		String result = null;
		result = (String) sysParamMap.get(param.toUpperCase());
		return result;
	}

	public synchronized static List<String> getOrganCode() {
		if (organCodeList == null) {
			organCodeList = new ArrayList<String>();
			Set<Entry<String, String>> entrySet = sysParamMap.entrySet();
			for (Entry<String, String> entry : entrySet) {
				String key = entry.getKey();
				if (key.startsWith("JDBC.URL")) {
					organCodeList.add(key.replaceAll("JDBC.URL", ""));
				}
			}
		}
		return organCodeList;
	}

	public static String getName(String code) {
		String name = null;
		String key = "NAME." + code;
		name = sysParamMap.get(key);
		return name;
	}

	public synchronized static Map<String, String> getOrganNameMap() {
		if (organNameMap == null) {
			organNameMap = new LinkedHashMap<String, String>();
			List<String> organCode = getOrganCode();
			for (String code : organCode) {
				String name = getName(code);
				organNameMap.put(code, name);
			}
		}
		return organNameMap;
	}

	static {
		InputStream in = SystemParamConfigUtil.class.getClassLoader().getResourceAsStream("deadlock.properties");
		Properties pps = new Properties();

		try {
			InputStreamReader ir = new InputStreamReader(in, "UTF-8");
			pps.load(ir);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Set<Entry<Object, Object>> entrySet = pps.entrySet();
		sysParamMap = new LinkedHashMap<String, String>();
		for (Entry<Object, Object> entry : entrySet) {
			sysParamMap.put(entry.getKey().toString().toUpperCase(), entry.getValue().toString().trim());
		}
	}

}
