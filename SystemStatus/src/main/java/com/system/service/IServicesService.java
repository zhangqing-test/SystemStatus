package com.system.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;
import com.system.entity.Services;
import com.system.web.form.DatatableForm;

/**
 * <p>
 * 软件服务 服务类
 * </p>
 *
 * @author Yang Yang
 * @since 2018-01-12
 */
public interface IServicesService extends IService<Services> {

	String selectInfluenceNames(Integer id, int i);

	List<Services> paginationQuery(DatatableForm form,EntityWrapper<Services> ew);

	List<Map<String, Object>> selectAffectServices(Integer id);

}
