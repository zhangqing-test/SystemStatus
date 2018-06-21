package com.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.system.common.mapper.BaseMapper;

import com.system.entity.Users;

/**
 * 
 * @author zhangyou
 * @since
 */
public interface UsersMapper extends BaseMapper<Users> {

	List<Users> selectListByRelationId(@Param("relationId") String relationId);

}