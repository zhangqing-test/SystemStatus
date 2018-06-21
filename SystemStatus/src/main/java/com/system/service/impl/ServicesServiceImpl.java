package com.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.system.entity.Services;
import com.system.mapper.ServicesMapper;
import com.system.service.IServicesService;
import com.system.web.form.DatatableForm;

/**
 * <p>
 * ${table.comment} 服务实现类
 * </p>
 *
 * @author Yang Yang
 * @since 2018-01-12
 */
@Service
@Transactional
public class ServicesServiceImpl extends ServiceImpl<ServicesMapper, Services> implements IServicesService {
	private static final Logger LOG = LoggerFactory.getLogger(ServicesServiceImpl.class);

	@Autowired
	private ServicesMapper mapper;

	public String selectInfluenceNames(Integer id, int type) {
		List<Services> list = mapper.selectInfluenceNames(id, type);
		if (null != list && list.size() > 0) {
			String[] arr = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				arr[i] = list.get(i).getName();
			}
			String str = StringUtils.join(arr, ",");
			return str;
		} else {
			return null;
		}
	}

	public List<Services> paginationQuery(DatatableForm form,EntityWrapper<Services> ew) {
		List<Services> list = new ArrayList<Services>();
		try {
			Page<Services> page = new Page<Services>(form.getStart() / form.getLength() + 1, form.getLength());
			page.setOrderByField("id");
			page.setAsc(true);
			page.setSearchCount(false);
			list = mapper.selectPage(page, ew);
		} catch (Exception e) {
			LOG.error("分页查询失败", e);
		}
		return list;
	}

	public List<Map<String, Object>> selectAffectServices(Integer id) {
		List<Map<String, Object>> list = mapper.selectAffectServices(id);
		return list;
	}

}
