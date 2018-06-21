package com.system.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.system.entity.NetUpdate;

/**
 * <p>
 * 网络更新记录 服务类
 * </p>
 *
 * @author Yang Yang
 * @since 2018-01-13
 */
public interface INetUpdateService extends IService<NetUpdate> {

	/**
	 * 根据简称模糊查询记录list
	 * 
	 * @param name
	 * @return
	 */
	List<NetUpdate> queryPageHistory(String name);

	/**
	 * 自动补全简称名(插件使用)
	 * 
	 * @param name
	 * @return
	 */
	List<String> queryAutoCompleteName(String name);
	
	
	/**
	 * 根据netid获取更新记录
	 * 
	 * @param ID
	 * @return
	 */
	public List<NetUpdate> queryHistoryBynid(Integer id);

}
