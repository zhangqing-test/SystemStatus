package com.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.system.entity.PingAndPort;
import com.system.entity.PingAndPortInfo;
import com.system.mapper.PingAndPortInfoMapper;
import com.system.service.IPingAndPortInfoService;
import com.system.web.form.NetQueryForm;

/**
 * <p>
 * ${table.comment} 服务实现类
 * </p>
 *
 * @author Yang Yang
 * @since 2017-12-12
 */
@Service
@Transactional
public class PingAndPortInfoServiceImpl extends ServiceImpl<PingAndPortInfoMapper, PingAndPortInfo> implements IPingAndPortInfoService {

	@Autowired
	private PingAndPortInfoMapper pingAndPortInfoMapper;

	public List<PingAndPort> selectErrorNet(NetQueryForm form) {
		return pingAndPortInfoMapper.selectErrorNet(form);
	}

}
