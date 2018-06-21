package com.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.system.entity.Manager;
import com.system.mapper.ManagerMapper;
import com.system.service.IManagerService;

/**
 * <p>
 * ${table.comment} 服务实现类
 * </p>
 *
 * @author Yang Yang
 * @since 2017-12-18
 */
@Service
@Transactional
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements IManagerService {

	@Autowired
	private ManagerMapper managerMapper;

	public List<Manager> selectListByRelationId(String relationId) {
		return managerMapper.selectListByRelationId(relationId);
	}



}
