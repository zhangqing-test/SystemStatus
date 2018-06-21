package com.system.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.system.common.constants.StatusConstants;
import com.system.common.controller.BaseController;
import com.system.entity.PingAndPort;
import com.system.entity.PingAndPortInfo;
import com.system.service.IPingAndPortInfoService;
import com.system.service.IPingAndPortService;
import com.system.web.form.NetQueryForm;
import com.system.web.vo.PingInfoVo;

/**
 * <p>
 * 网络 前端控制器
 * </p>
 *
 * @author Yang Yang
 * @since 2017-12-04
 */
@Controller
@SuppressWarnings("unused")
public class PingAndPortController extends BaseController {
	private static final Logger LOG = LoggerFactory.getLogger(PingAndPortController.class);

	@Autowired
	private IPingAndPortService pingAndPortService;

	@Autowired
	private IPingAndPortInfoService pingAndPortInfoService;

	/**
	 * 网络监控数据 前段入口
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/net")
	public String query(HttpServletRequest request, Model model,NetQueryForm form) {
		List<PingAndPort> list = pingAndPortInfoService.selectErrorNet(form);
		List<PingInfoVo> listVo = new ArrayList<PingInfoVo>();
		if (list != null && list.size() > 0) {
			PingAndPort pingAndPort = list.get(0);
			Date date = pingAndPort.getDatetime();
			SimpleDateFormat sdf = new SimpleDateFormat(StatusConstants.SDF);
			String time = sdf.format(date);
			model.addAttribute("time", time);
			for (PingAndPort temp : list) {
				EntityWrapper<PingAndPortInfo> ew = new EntityWrapper<PingAndPortInfo>();
				ew.where("ping_and_port_id={0}", temp.getId()).and("datetime={0}", date);
				List<PingAndPortInfo> infoList = pingAndPortInfoService.selectList(ew);
				if (infoList != null && infoList.size() > 0) {
					PingInfoVo pingInfoVo = new PingInfoVo(temp);
					for (PingAndPortInfo pingAndPortInfo : infoList) {
						pingInfoVo.getInfo(pingAndPortInfo);
					}
					listVo.add(pingInfoVo);
				}
			}
		}
		model.addAttribute("form", form);
		model.addAttribute("list", listVo);
		return "net";
	}

	/**
	 * 修改网络监控短信通知
	 * 
	 * @param id
	 * @param flag
	 * @param model
	 * @return
	 */
	@RequestMapping("/net/changeNetMessage")
	@ResponseBody
	public String changeNetMessage(@RequestParam("id") String id, @RequestParam("message") Boolean flag, Model model) {
		EntityWrapper<PingAndPort> ew = new EntityWrapper<PingAndPort>();
		ew.eq("id", id);
		PingAndPort pingAndPort = pingAndPortService.selectOne(ew);
		if (pingAndPort != null) {
			pingAndPort.setMessage(flag);
			Boolean update = pingAndPortService.updateById(pingAndPort);
			return update.toString();
		} else {
			return "false";
		}
	}
}
