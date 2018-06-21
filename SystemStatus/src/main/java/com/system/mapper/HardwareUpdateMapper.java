package com.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.system.common.mapper.BaseMapper;
import com.system.entity.HardwareUpdate;

/**
 * <p>
 * HardwareUpdate Mapper 接口
 * </p>
 *
 * @author Yang Yang
 * @since 2018-01-13
 */
public interface HardwareUpdateMapper extends BaseMapper<HardwareUpdate> {

	/**
	 * 自动补全简称名(插件使用)<br>
	 * top 8<br>
	 * 日期降序
	 * 
	 * @param name
	 * @return
	 */
	List<String> queryAutoCompleteName(@Param("name") String name);

}