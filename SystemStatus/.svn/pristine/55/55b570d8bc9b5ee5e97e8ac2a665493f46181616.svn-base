package com.system.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * OA接口aop日志记录
 * </p>
 * <ul>
 * <li>description:接口日志内容描述</li>
 * <li>tableType:处理的相关表</li>
 * </ul>
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemServiceLog {

	/**
	 * 日志描述
	 */
	String description() default "";

	/**
	 * 操作表类型
	 */
	int tableType() default 0;
}
