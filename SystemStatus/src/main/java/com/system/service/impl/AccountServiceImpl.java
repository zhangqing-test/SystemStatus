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
import com.system.entity.Account;
import com.system.mapper.AccountMapper;
import com.system.service.IAccountService;
import com.system.web.form.DatatableForm;

@Service
@Transactional
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

	private static final Logger LOG = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
	private AccountMapper accountMapper;

	@Override
	public List<Account> paginationQuery(DatatableForm form, EntityWrapper<Account> ew) {

		List<Account> list = new ArrayList<Account>();
		try {
			Page<Account> page = new Page<Account>(form.getStart() / form.getLength() + 1, form.getLength());
			page.setOrderByField("create_time");
			page.setAsc(true);
			page.setSearchCount(false);
			list = accountMapper.selectPage(page, ew);
		} catch (Exception e) {
			LOG.error("分页查询失败", e);
		}
		return list;
	}

}
