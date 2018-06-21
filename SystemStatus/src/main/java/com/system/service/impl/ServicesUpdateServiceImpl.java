package com.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.system.entity.ServicesUpdate;
import com.system.mapper.ServicesUpdateMapper;
import com.system.service.IServicesUpdateService;

/**
 * <p>
 * ${table.comment} 服务实现类
 * </p>
 *
 * @author Yang Yang
 * @since 2018-01-13
 */
@Service
@Transactional
public class ServicesUpdateServiceImpl extends ServiceImpl<ServicesUpdateMapper, ServicesUpdate> implements IServicesUpdateService {
	@Autowired
	private ServicesUpdateMapper mapper;

	public List<ServicesUpdate> queryPageHistory(String name) {
		return mapper.selectList(new EntityWrapper<ServicesUpdate>().like("name", name));
	}
	
	public List<String> queryAutoCompleteName(String name) {
		return mapper.queryAutoCompleteName(name);
	}
	
	public List<ServicesUpdate> queryHistoryBySid(Integer id) {
		return mapper.selectList(new EntityWrapper<ServicesUpdate>().eq("services_id", id).orderBy("datetime", false));
	}

}
