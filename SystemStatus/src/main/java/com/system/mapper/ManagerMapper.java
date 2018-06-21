package com.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.system.common.mapper.BaseMapper;
import com.system.entity.Manager;

/**
 * <p>
 * ${table.comment} Mapper 接口
 * </p>
 *
 * @author Yang Yang
 * @since 2017-12-18
 */
public interface ManagerMapper extends BaseMapper<Manager> {

	List<Manager> selectListByRelationId(@Param("relationId") String relationId);

}