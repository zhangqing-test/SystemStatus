package com.system.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.system.entity.HardwareUpdate;
import com.system.mapper.HardwareUpdateMapper;
import com.system.service.IHardwareUpdateService;

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
public class HardwareUpdateServiceImpl extends ServiceImpl<HardwareUpdateMapper, HardwareUpdate> implements IHardwareUpdateService {
	private static final Logger LOG = LoggerFactory.getLogger(HardwareUpdateServiceImpl.class);

	@Autowired
	private HardwareUpdateMapper mapper;

	public List<HardwareUpdate> queryPageHistory(String name) {
		try {
			EntityWrapper<HardwareUpdate> ew = new EntityWrapper<HardwareUpdate>();
			ew.like("name", name);
			List<HardwareUpdate> list = mapper.selectList(ew);
			return list;
		} catch (Exception e) {
			LOG.error("", e);
			return null;
		}
	}

	public List<String> queryAutoCompleteName(String name) {
		return mapper.queryAutoCompleteName(name);
	}

}
