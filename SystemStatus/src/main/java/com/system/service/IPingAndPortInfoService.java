package com.system.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.system.entity.PingAndPort;
import com.system.entity.PingAndPortInfo;
import com.system.web.form.NetQueryForm;

/**
 * <p>
 * ${table.comment} 服务类
 * </p>
 *
 * @author Yang Yang
 * @since 2017-12-12
 */
public interface IPingAndPortInfoService extends IService<PingAndPortInfo> {

	/**
	 * 获取异常监控网络list
	 * 
	 * @return
	 */
	List<PingAndPort> selectErrorNet(NetQueryForm form);

}
