package com.system.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.system.entity.ServicesUpdate;

/**
 * <p>
 * 应用服务更新记录 服务类
 * </p>
 *
 * @author Yang Yang
 * @since 2018-01-13
 */
public interface IServicesUpdateService extends IService<ServicesUpdate> {

	/**
	 * 根据简称模糊查询记录list
	 * 
	 * @param name
	 * @return
	 */
	List<ServicesUpdate> queryPageHistory(String name);

	/**
	 * 自动补全简称名(插件使用)
	 * 
	 * @param name
	 * @return
	 */
	List<String> queryAutoCompleteName(String name);
	
	/**
	 * 根据serviceid获取更新记录
	 * 
	 * @param ID
	 * @return
	 */
	public List<ServicesUpdate> queryHistoryBySid(Integer id);

}
