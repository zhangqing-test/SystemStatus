package com.system.web;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.system.common.controller.BaseController;
import com.system.entity.ServerInfo;
import com.system.service.IServerInfoService;

/**
 * <p>
 * 网络数据 前端控制器
 * </p>
 *
 * @author Yang Yang
 * @since 2017-12-12
 */
@Controller
@RequestMapping("/pingAndPortInfo")
public class PingAndPortInfoController extends BaseController {

	@Autowired
	private IServerInfoService serverInfoService;

	/**
	 * 测试 echarts报表入口
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/echarts")
	public String echarts(Model model) {
		Page<ServerInfo> page = new Page<ServerInfo>();
		page.setCurrent(1);
		page.setSize(20);
		page.setSearchCount(false);
		EntityWrapper<ServerInfo> ew = new EntityWrapper<ServerInfo>();
		ew.eq("server_id", "22a7cbede05d422fb486bc445e02fab9").orderBy("datetime", false);

		Page<ServerInfo> selectPage = serverInfoService.selectPage(page, ew);
		List<ServerInfo> records = selectPage.getRecords();
		JSONArray ja = new JSONArray();
		JSONArray atime = new JSONArray();
		for (ServerInfo serverInfo : records) {
			ja.add(serverInfo.getCpu());
			atime.add(new SimpleDateFormat("HH:mm:ss").format(serverInfo.getDatetime()));
		}
		model.addAttribute("data", ja.toString());
		model.addAttribute("time", atime.toString());
		return "charts";
	}
}
