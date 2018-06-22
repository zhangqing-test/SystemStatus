package com.system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.system.entity.Account;
import com.system.mapper.AccountMapper;
import com.system.service.IAccountService;

@Service
@Transactional
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

}
