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
import com.system.entity.Users;
import com.system.mapper.UsersMapper;
import com.system.service.UsersService;
import com.system.web.form.DatatableForm;


/**
 * 
 * @author zhangyou
 *
 */
@Service
@Transactional
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {
	private static final Logger LOG = LoggerFactory.getLogger(UsersServiceImpl.class);
	@Autowired
	private UsersMapper usersMapper;



	@Override
	public List<Users> selectUserId(String id) {
		
		return null;
	}

	@Override
	public Users selectUserByPwd(String userCode, String userPwd) {
		EntityWrapper<Users> ew = new EntityWrapper<Users>();
		ew.eq("user_code", userCode);
		ew.eq("user_pwd", userPwd);
		List<Users> u= usersMapper.selectList(ew);
		if(u!=null&&u.size()==1){
			return u.get(0);
		}
		return null;
	}

	@Override
	public List<Users> paginationQuery(DatatableForm form,
			EntityWrapper<Users> ew) {
		List<Users> list = new ArrayList<Users>();
		try {
			Page<Users> page = new Page<Users>(form.getStart() / form.getLength() + 1, form.getLength());
			page.setOrderByField("id");
			page.setAsc(true);
			page.setSearchCount(false);						
			list = usersMapper.selectPage(page, ew);
		} catch (Exception e) {
			LOG.error("分页查询失败", e);
		}
		return list;

	}





}
