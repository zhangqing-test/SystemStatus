package com.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.system.common.mapper.BaseMapper;
import com.system.entity.Hardware;

/**
 * <p>
 * ${table.comment} Mapper 接口
 * </p>
 *
 * @author Yang Yang
 * @since 2018-01-12
 */
public interface HardwareMapper extends BaseMapper<Hardware> {

	List<Hardware> selectInfluenceNames(@Param("id") Integer id, @Param("type") int type);

}