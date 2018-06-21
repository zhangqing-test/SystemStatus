package com.system.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.system.common.controller.BaseController;
import com.system.entity.Users;
import com.system.service.UsersService;



/**
 * 登录入口
 */
@Controller
public class LoginController extends BaseController {
	
	@Autowired
	private UsersService usersService;
	
	
	@RequestMapping("login")
	public String Login(String userCode,String userPwd,Model m,HttpServletRequest request){
		Users u=usersService.selectUserByPwd(userCode, userPwd);
		if(u!=null){
			HttpSession session=request.getSession();
			session.setAttribute("loginUser", u);
			session.setMaxInactiveInterval(60*60);
		}else{
			m.addAttribute("msg","用户名或密码不正确！");
			return "login";
		}
		return "index";				
	}
	
	@RequestMapping("tologin")
	public String toLogin(){
		return "login";
	}
	@RequestMapping(value = "offLogin")
	public String offLogin(HttpSession session) {
		session.invalidate();
		return "login";
	}
}
