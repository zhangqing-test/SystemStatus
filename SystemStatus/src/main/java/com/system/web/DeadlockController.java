package com.system.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.system.common.controller.BaseController;
import com.system.common.util.SystemParamConfigUtil;
import com.system.quartz.bean.SpidEntity;
import com.system.service.DeadlockService;
import com.system.web.vo.DeadlockVo;

/**
 * 死锁页面 前端控制器
 */
@Controller
public class DeadlockController extends BaseController {

	@Autowired
	private DeadlockService deadlockService;

	/**
	 * 死锁页面入口
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/deadlock")
	public String index(HttpServletRequest request, Model model) {
		Map<String, String> nameMap = SystemParamConfigUtil.getOrganNameMap();
		model.addAttribute("orgs", nameMap);
		return "deadlock";
	}

	/**
	 * 查询按钮
	 * 
	 * @param code
	 * @param model
	 * @return
	 */
	@RequestMapping("/deadlock/query")
	public String query(@RequestParam("code") String code, Model model) {
		String thisurl = SystemParamConfigUtil.getParamValueByParam("jdbc.url" + code);
		thisurl = thisurl.replace("HealthCIS", "master");

		Map<String, String> processMap = new HashMap<String, String>();
		Map<String, SpidEntity> spidMap = new HashMap<String, SpidEntity>();
		Map<String, String> blockedMap = new HashMap<String, String>();
		deadlockService.queryDeadlock(processMap, spidMap, blockedMap, thisurl, "cis", "cis", code);
		String[] spids = deadlockService.queryNeededKillSpids(processMap);

		List<DeadlockVo> list = new ArrayList<DeadlockVo>();
		if (spids != null && spids.length > 0) {
			for (String spid : spids) {
				if (StringUtils.isNotEmpty(spid)) {
					DeadlockVo vo = new DeadlockVo(spid, spidMap.get(spid).toString(), blockedMap.get(spid), code);
					list.add(vo);
				}
			}
		}

		model.addAttribute("list", list);
		return "deadlockAjaxList";
	}

	/**
	 * 杀进程按钮
	 * 
	 * @param spid
	 * @param code
	 * @return
	 */
	@RequestMapping("/deadlock/kill")
	@ResponseBody
	public String kill(@RequestParam("spid") String spid, @RequestParam("code") String code) {
		if (StringUtils.isNotEmpty(spid) && StringUtils.isNotEmpty(code)) {
			return deadlockService.kill(spid, code);
		} else {
			return "false";
		}
	}
}
