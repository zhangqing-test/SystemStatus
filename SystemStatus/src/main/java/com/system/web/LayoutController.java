package com.system.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.system.common.controller.BaseController;
import com.system.entity.Users;

/**
 * 通用布局前端控制器
 */
@Controller
public class LayoutController extends BaseController {

	/**
	 * 数据展示 入口
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		return "index";
	}

	/**
	 * 后台管理 入口（未实现）
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/control")
	public String control(HttpSession session, Model model) {
		Users u = (Users) session.getAttribute("loginUser");
		if (u.getAuth().equals("admin")) {
			model.addAttribute("flag", true);
		}
		return "admin";
	}
}
