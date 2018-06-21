package com.system.common.constants;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemConfig {
	private static final Logger LOG = LoggerFactory.getLogger(SystemConfig.class);

	private static Properties props;

	static {
		loadProps();
	}

	synchronized private static void loadProps() {
		props = new Properties();
		InputStream in = null;
		try {
			in = SystemConfig.class.getClassLoader().getResourceAsStream("config.properties");
			props.load(in);
		} catch (FileNotFoundException e) {
			LOG.error("config.properties文件未找到");
		} catch (IOException e) {
			LOG.error("出现IOException");
		} finally {
			try {
				if (null != in) {
					in.close();
				}
			} catch (IOException e) {
				LOG.error("config.properties文件流关闭出现异常");
			}
		}
	}

	public static String getProperty(String key) {
		if (null == props) {
			loadProps();
		}
		return props.getProperty(key);
	}

	public static String getProperty(String key, String defaultValue) {
		if (null == props) {
			loadProps();
		}
		return props.getProperty(key, defaultValue);
	}
}
