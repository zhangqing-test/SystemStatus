package com.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.system.entity.PingAndPort;
import com.system.mapper.PingAndPortMapper;
import com.system.service.IPingAndPortService;
import com.system.web.form.DatatableForm;

/**
 * <p>
 * ${table.comment} 服务实现类
 * </p>
 *
 * @author Yang Yang
 * @since 2017-12-04
 */
@Service
@Transactional
public class PingAndPortServiceImpl extends ServiceImpl<PingAndPortMapper, PingAndPort> implements IPingAndPortService {
	private static final Logger LOG = LoggerFactory.getLogger(PingAndPortServiceImpl.class);

	@Autowired
	private PingAndPortMapper mapper;

	public List<PingAndPort> paginationQuery(DatatableForm form,EntityWrapper<PingAndPort> ew) {
		List<PingAndPort> list = new ArrayList<PingAndPort>();
		try {
			Page<PingAndPort> page = new Page<PingAndPort>(form.getStart() / form.getLength() + 1, form.getLength());
			page.setOrderByField("id");
			page.setAsc(true);
			page.setSearchCount(false);
			list = mapper.selectPage(page, ew);
		} catch (Exception e) {
			LOG.error("分页查询失败", e);
		}
		return list;
	}

}
