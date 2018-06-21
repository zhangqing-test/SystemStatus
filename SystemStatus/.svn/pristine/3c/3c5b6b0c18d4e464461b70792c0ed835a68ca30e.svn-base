package com.system.service;

import java.util.List;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;
import com.system.entity.Users;
import com.system.web.form.DatatableForm;

/**
 * 
 * @author zhangyou
 *
 */
public interface UsersService extends IService<Users> {

	List<Users> selectUserId(String id);
	
	Users selectUserByPwd(String userCode,String userPwd);
	
	List<Users> paginationQuery(DatatableForm form,EntityWrapper<Users> ew);
}
