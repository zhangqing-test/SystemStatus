package com.system.common.converter;

import org.springframework.core.convert.converter.Converter;

/**
 * 自定义去掉前后空格<br>
 * S：页面传递参数的类型<br>
 * T：转换后的类型<br>
 * Created by yang on 2017/6/27.
 */
public class TrimConverter implements Converter<String, String> {

	public String convert(String source) {
		try {
			if (null != source) {
				source = source.trim();
				if (!"".equals(source)) {
					return source;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
