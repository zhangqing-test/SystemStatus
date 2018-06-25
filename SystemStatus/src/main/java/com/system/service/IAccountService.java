package com.system.service;

import java.util.List;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;
import com.system.entity.Account;
import com.system.web.form.DatatableForm;

public interface IAccountService extends IService<Account> {

	List<Account> paginationQuery(DatatableForm form, EntityWrapper<Account> ew);

}
