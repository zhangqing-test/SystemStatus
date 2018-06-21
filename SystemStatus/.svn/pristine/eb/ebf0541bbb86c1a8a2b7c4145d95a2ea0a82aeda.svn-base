package com.system.common.aop;

import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.system.common.annotation.SystemServiceLog;
import com.system.entity.OaInterfaceLog;
import com.system.mapper.OaInterfaceLogMapper;

/**
 * oa接口调用日志记录
 */
@Aspect
@Component
@SuppressWarnings("rawtypes")
public class WebServiceLogAspect {
	private static final Logger LOG = LoggerFactory.getLogger(WebServiceLogAspect.class);

	@Autowired
	private OaInterfaceLogMapper LogMapper;

	/**
	 * 切点
	 */
	@Pointcut("@annotation(com.system.common.annotation.SystemServiceLog)")
	public void serviceAspect() {

	}

	@After("serviceAspect()")
	public void doServiceLog(JoinPoint joinPoint) {
		LOG.debug("OA接口日志记录");
		try {
			int requestId = getRequestId(joinPoint);
			int methodTableType = getMethodTableType(joinPoint);
			String methodDescription = getMethodDescription(joinPoint);
			String methodParams = getMethodParams(joinPoint);
			OaInterfaceLog log = new OaInterfaceLog();
			log.setRequestId(requestId);
			log.setTable(methodTableType);
			log.setDescription(methodDescription);
			log.setContent(methodParams);
			log.setDatetime(new Date());
			LogMapper.insert(log);
		} catch (Exception e) {
			LOG.error("异常信息:{}", e);
		}
	}

	/**
	 * getMethodDescription:获取注解中对方法的描述信息. <br/>
	 * 
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 */
	private String getMethodDescription(JoinPoint joinPoint) throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(SystemServiceLog.class).description();
					break;
				}
			}
		}
		return description;
	}

	/**
	 * getMethodTableType:获取注解中对方法的数据表类型 . <br/>
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Exception
	 */
	private int getMethodTableType(JoinPoint joinPoint) throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		int tableType = 0;
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					tableType = method.getAnnotation(SystemServiceLog.class).tableType();
					break;
				}
			}
		}
		return tableType;
	}

	/**
	 * getMethodParams:获取json格式的参数. <br/>
	 * 
	 * @param joinPoint
	 * @return
	 */
	private String getMethodParams(JoinPoint joinPoint) {
		Object[] arguments = joinPoint.getArgs();
		String params = JSONObject.toJSONString(arguments);
		return params;
	}

	/**
	 * getRequestId:获取requestId. <br/>
	 * 
	 * @param joinPoint
	 * @return
	 */
	private int getRequestId(JoinPoint joinPoint) {
		Object[] arguments = joinPoint.getArgs();
		JSONObject jb = JSONObject.parseObject((String) arguments[0]);
		Integer id = jb.getInteger("id");
		return id;
	}
}
