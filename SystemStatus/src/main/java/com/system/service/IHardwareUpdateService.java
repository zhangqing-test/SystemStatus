package com.system.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.system.entity.HardwareUpdate;

/**
 * <p>
 * 硬件更新历史记录 服务类
 * </p>
 *
 * @author Yang Yang
 * @since 2018-01-13
 */
public interface IHardwareUpdateService extends IService<HardwareUpdate> {

	/**
	 * 根据简称模糊查询记录list
	 * 
	 * @param name
	 * @return
	 */
	List<HardwareUpdate> queryPageHistory(String name);

	/**
	 * 自动补全简称名(插件使用)
	 * 
	 * @param name
	 * @return
	 */
	List<String> queryAutoCompleteName(String name);

}
