package com.system.service;

import java.util.List;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;
import com.system.entity.Hardware;
import com.system.web.form.DatatableForm;

/**
 * <p>
 * 硬件服务器serviec 服务类
 * </p>
 *
 * @author Yang Yang
 * @since 2018-01-12
 */
public interface IHardwareService extends IService<Hardware> {

	String selectInfluenceNames(Integer id, int i);

	List<Hardware> paginationQuery(DatatableForm form,EntityWrapper<Hardware> ew);

}
