package com.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.system.entity.Hardware;
import com.system.mapper.HardwareMapper;
import com.system.service.IHardwareService;
import com.system.web.form.DatatableForm;

/**
 * <p>
 * 硬件服务器service 服务实现类
 * </p>
 *
 * @author Yang Yang
 * @since 2018-01-12
 */
@Service
@Transactional
public class HardwareServiceImpl extends ServiceImpl<HardwareMapper, Hardware> implements IHardwareService {
	private static final Logger LOG = LoggerFactory.getLogger(HardwareServiceImpl.class);

	@Autowired
	private HardwareMapper mapper;

	public String selectInfluenceNames(Integer id, int type) {
		List<Hardware> list = mapper.selectInfluenceNames(id, type);
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

	public List<Hardware> paginationQuery(DatatableForm form,EntityWrapper<Hardware> ew) {
		List<Hardware> list = new ArrayList<Hardware>();
		try {
			Page<Hardware> page = new Page<Hardware>(form.getStart() / form.getLength() + 1, form.getLength());
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
