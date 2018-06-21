package com.system.service;

public interface OAService {

	/**
	 * 创建服务器监控表单
	 * 
	 * @param str
	 * @return
	 */
	public Boolean createHardware(String str);

	/**
	 * 创建服务监控表单
	 * 
	 * @param str
	 * @return
	 */
	public Boolean createService(String str);

	/**
	 * 创建网络监控表单
	 * 
	 * @param str
	 * @return
	 */
	public Boolean createNet(String str);

	/**
	 * 服务器更新表单
	 * 
	 * @param str
	 * @return
	 */
	public Boolean updateHardware(String str);

	/**
	 * 更新服务器内容
	 * 
	 * @param str
	 * @param id
	 * @return
	 */
	public Boolean refreshHardware(String str, Integer id);

	/**
	 * 服务更新表单
	 * 
	 * @param str
	 * @return
	 */
	public Boolean updateServices(String str);

	/**
	 * 更新服务内容
	 * 
	 * @param str
	 * @param id
	 * @return
	 */
	public Boolean refreshService(String str, Integer id);

	/**
	 * 网络更新表单
	 * 
	 * @param str
	 * @return
	 */
	public Boolean updateNet(String str);

	/**
	 * 刷新网络内容
	 * 
	 * @param str
	 * @param id
	 * @return
	 */
	public Boolean refreshNet(String str, Integer id);

	/**
	 * 创建开发人员账号
	 * 
	 * @param str
	 */
	public Boolean createAccount(String str);

}
