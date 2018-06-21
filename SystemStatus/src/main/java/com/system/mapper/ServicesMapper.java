package com.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.system.common.mapper.BaseMapper;
import com.system.entity.Services;

/**
 * <p>
 * ${table.comment} Mapper 接口
 * </p>
 *
 * @author Yang Yang
 * @since 2018-01-12
 */
public interface ServicesMapper extends BaseMapper<Services> {

	List<Services> selectInfluenceNames(@Param("id") Integer id, @Param("type") int type);

	List<Map<String, Object>> selectAffectServices(@Param("id") Integer id);

}