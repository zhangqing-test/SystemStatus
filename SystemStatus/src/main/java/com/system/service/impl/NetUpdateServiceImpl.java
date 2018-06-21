package com.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.system.entity.NetUpdate;
import com.system.entity.ServicesUpdate;
import com.system.mapper.NetUpdateMapper;
import com.system.service.INetUpdateService;

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
public class NetUpdateServiceImpl extends ServiceImpl<NetUpdateMapper, NetUpdate> implements INetUpdateService {

	@Autowired
	private NetUpdateMapper mapper;

	public List<NetUpdate> queryPageHistory(String name) {
		return mapper.selectList(new EntityWrapper<NetUpdate>().like("name", name));
	}

	public List<String> queryAutoCompleteName(String name) {
		return mapper.queryAutoCompleteName(name);
	}


	public List<NetUpdate> queryHistoryBynid(Integer id) {
		return mapper.selectList(new EntityWrapper<NetUpdate>().eq("net_id", id).orderBy("datetime", false));
	}

}
