package com.system.web;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.system.common.constants.StatusConstants;
import com.system.common.controller.BaseController;
import com.system.entity.Users;
import com.system.service.UsersService;
import com.system.web.form.DatatableForm;

/**
 * 账号管理
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {

	@Autowired
	private UsersService usersService;

	@RequestMapping("users")
	public String users(HttpServletRequest request, Model model) {
		return "userList";
	}

	/**
	 * 用户list分页（auth）
	 * 
	 * @param form
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("query")
	@ResponseBody
	public String userList(DatatableForm form, HttpServletRequest request, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		String userCode = request.getParameter("userCode");
		String userName = request.getParameter("userName");
		EntityWrapper<Users> ew = new EntityWrapper<Users>();
		if (!"".equals(userCode)) {
			ew.eq("user_code", userCode);
		}
		if (!"".equals(userName)) {
			ew.eq("user_name", userName);
		}
		Integer count = usersService.selectCount(ew);
		if (count != 0) {
			SimpleDateFormat sdf = new SimpleDateFormat(StatusConstants.SDF);
			List<Users> list = usersService.paginationQuery(form, ew);
			List<List<String>> data = new ArrayList<List<String>>();
			for (Users u : list) {
				List<String> detail = new ArrayList<String>();
				detail.add(u.getUserCode());
				detail.add(u.getUserName());
				if (u.getAuth().equals("admin")) {
					detail.add("管理员");
				} else {
					detail.add("普通用户");
				}
				detail.add(u.getUserDept());
				detail.add(u.getPhone());
				detail.add(u.getUserEmail());

				String time = sdf.format(u.getDatetime()).toString();
				detail.add(time);
				detail.add("<button type=\"button\" onClick=\"userUpdate(" + u.getId() + ")\" class=\"btn btn-outline btn-primary btn-xs\">detail</button>&nbsp;<button type=\"button\" onClick=\"userDelete(" + u.getId() + ")\" class=\"btn btn-outline btn-danger btn-xs\">delete</button>");
				data.add(detail);
			}
			result.put("data", data);
		} else {
			result.put("data", "");
		}
		result.put("draw", form.getDraw());
		result.put("recordsTotal", count);
		result.put("recordsFiltered", count);
		JSONObject jsonObject = new JSONObject(result);
		String jsonString = jsonObject.toJSONString();
		return jsonString;
	}

	/**
	 * 根据id 删除user
	 * 
	 * @param id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public String userDelete(@RequestParam("id") Integer id, HttpServletRequest request, Model model) {
		try {
			boolean flag = usersService.deleteById(id);
			return flag ? "success" : "false";
		} catch (Exception e) {
			return "false";
		}
	}

	/**
	 * 添加用户
	 * 
	 * @param u
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	@Consumes(MediaType.TEXT_PLAIN)
	public String saveUser(Users u) {
		u.setDatetime(new Date());
		u.setUserPwd("0000");
		try {
			boolean flag = usersService.insert(u);
			return flag ? "success" : "false";
		} catch (Exception e) {
			return "false";
		}

	}

	/**
	 * 修改用户
	 * 
	 * @param u
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("update")
	@ResponseBody
	public String updateUser(Users u) throws UnsupportedEncodingException {
		u.setUserName(new String(u.getUserName().getBytes("iso-8859-1"), "utf-8"));
		u.setUserDept(new String(u.getUserDept().getBytes("iso-8859-1"), "utf-8"));
		System.out.println(u.getUserName());
		u.setDatetime(new Date());
		try {
			boolean flag = usersService.updateById(u);
			return flag ? "success" : "false";
		} catch (Exception e) {
			return "false";
		}

	}

	/**
	 * 用户详情
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("detail")
	@ResponseBody
	public Users userdetail(Integer id) {

		return usersService.selectById(id);

	}

	@RequestMapping("sessiondetail")
	@ResponseBody
	public Users sessionDetail(HttpSession session) {

		return (Users) session.getAttribute("loginUser");

	}

	@RequestMapping("updatepwd")
	public String updatePwd(HttpServletRequest request, Model model) {
		return "updatePwd";
	}

}
