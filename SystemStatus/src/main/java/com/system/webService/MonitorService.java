package com.system.webService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.system.service.OAService;

@Component("monitorService")
@SuppressWarnings("unused")
public class MonitorService {
	private static final Logger LOG = LoggerFactory.getLogger(MonitorService.class);

	@Autowired
	private OAService oaService;

	/**
	 * 创建服务器监控表单
	 * 
	 * @param str
	 * @return
	 */
	public Boolean createHardware(String str) {
		return oaService.createHardware(str);
	}

	/**
	 * 创建服务监控表单
	 * 
	 * @param str
	 * @return
	 */
	public Boolean createService(String str) {
		return oaService.createService(str);
	}

	/**
	 * 创建网络监控表单
	 * 
	 * @param str
	 * @return
	 */
	public Boolean createNet(String str) {
		return oaService.createNet(str);
	}

	/**
	 * 服务器更新表单
	 * 
	 * @param str
	 * @return
	 */
	public Boolean updateHardware(String str) {
		return oaService.updateHardware(str);
	}

	/**
	 * 更新服务器内容
	 * 
	 * @param str
	 * @param id
	 * @return
	 */
	public Boolean refreshHardware(String str, Integer id) {
		return oaService.refreshHardware(str, id);
	}

	/**
	 * 服务更新表单
	 * 
	 * @param str
	 * @return
	 */
	public Boolean updateService(String str) {
		return oaService.updateServices(str);
	}

	/**
	 * 刷新服务内容
	 * 
	 * @param str
	 * @param id
	 * @return
	 */
	public Boolean refreshService(String str, Integer id) {
		return oaService.refreshService(str, id);
	}

	/**
	 * 网络更新表单
	 * 
	 * @param str
	 * @return
	 */
	public Boolean updateNet(String str) {
		return oaService.updateNet(str);
	}

	/**
	 * 刷新网络内容
	 * 
	 * @param str
	 * @param id
	 * @return
	 */
	public Boolean refreshNet(String str, Integer id) {
		return oaService.refreshNet(str, id);
	}

	/**
	 * 创建开发人员账号
	 * 
	 * @param str
	 * @return
	 */
	public Boolean createAccount(String str) {
		return oaService.createAccount(str);
	}

}
