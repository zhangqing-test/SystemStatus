package com.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.system.common.mapper.BaseMapper;
import com.system.entity.PingAndPort;
import com.system.entity.PingAndPortInfo;
import com.system.web.form.NetQueryForm;

/**
 * <p>
 * ${table.comment} Mapper 接口
 * </p>
 *
 * @author Yang Yang
 * @since 2017-12-12
 */
public interface PingAndPortInfoMapper extends BaseMapper<PingAndPortInfo> {

	/**
	 * 获取异常监控网络list
	 * 
	 * @return
	 */
	List<PingAndPort> selectErrorNet(@Param("form")NetQueryForm form);

}