package com.system.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.system.common.constants.StatusConstants;
import com.system.common.controller.BaseController;
import com.system.common.util.ComputeUtil;
import com.system.common.util.WebServiceUtil;
import com.system.entity.AffectServices;
import com.system.entity.Hardware;
import com.system.entity.ServerInfo;
import com.system.entity.Services;
import com.system.service.IAffectServicesService;
import com.system.service.IHardwareService;
import com.system.service.IServerInfoService;
import com.system.service.IServicesService;
import com.system.web.form.ServerQueryForm;
import com.system.web.vo.InfoPageVo;
import com.system.web.vo.ProcessInfo;

/**
 * <p>
 * 服务所在服务器业务数据 前端控制器
 * </p>
 *
 * @author Yang Yang
 * @since 2017-11-27
 */
@Controller
@RequestMapping("/serverInfo")
public class ServerInfoController extends BaseController {
	private static final Logger LOG = LoggerFactory.getLogger(ServerInfoController.class);

	@Autowired
	private IServerInfoService serverInfoService;

	@Autowired
	private IServicesService servicesService;

	@Autowired
	private IHardwareService hardwareService;

	@Autowired
	private IAffectServicesService affectServicesService;

	/**
	 * 服务器数据信息入口
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/query")
	public String query(HttpServletRequest request, Model model, ServerQueryForm form) {
		model.addAttribute("form", form);
		// 1 查出需要监控的服务list
		EntityWrapper<Services> sEw = new EntityWrapper<Services>();
		sEw.eq("jk", true);
		if (!"".equals(form.getName()) && null != form.getName()) {
			sEw.like("name", form.getName());
		}
		List<Services> slist = servicesService.selectList(sEw);
		List<Services> sip = new ArrayList<Services>();
		for (Services services : slist) {
			Hardware hardware = hardwareService.selectById(services.getHardwareId());
			if (!"".equals(form.getIp()) && null != form.getIp() && !form.getIp().equals(hardware.getIp())) {
				sip.add(services);
			}
		}
		slist.removeAll(sip);

		// 2 获取记录抓取时间
		Date date = slist.get(0).getDatetime();
		SimpleDateFormat sdf = new SimpleDateFormat(StatusConstants.SDF);
		String time = sdf.format(date).toString();
		model.addAttribute("time", time);

		// 3 根据记录抓取时间获取服务器信息
		// TODO(优化使用mapperjoin后返回)
		EntityWrapper<ServerInfo> ew = new EntityWrapper<ServerInfo>();
		if (null != form.getCpuMin() && null != form.getCpuMax()) {
			ew.between("cpu", form.getCpuMin(), form.getCpuMax());
		}
		if (null != form.getSpaceMin() && null != form.getSpaceMax()) {
			ew.between("space_max", form.getSpaceMin(), form.getSpaceMax());
		}

		ew.eq("datetime", date);
		ew.orderBy("orders", false);
		List<ServerInfo> serverInfos = serverInfoService.selectList(ew);

		// 4 拼装返回信息
		List<InfoPageVo> list = getInfoPageList(slist, serverInfos, form.getMemoryMin(), form.getMemoryMax());
		model.addAttribute("list", list);

		return "server";
	}

	@RequestMapping("/changeServerMessage")
	@ResponseBody
	public String changeMessage(@RequestParam("id") String id, @RequestParam("message") Boolean flag, Model model) {
		Services services = servicesService.selectById(id);
		if (services != null) {
			services.setMessage(flag);
			Boolean update = servicesService.updateById(services);
			return update.toString();
		} else {
			return "false";
		}
	}

	/**
	 * 处理分页展示数据
	 * 
	 * @param servers
	 * @param serverInfos
	 * @return
	 */
	// private List<InfoPageVo> getInfoPageList(List<Services> slist,
	// List<ServerInfo> serverInfos, String ip, Integer memoryMin, Integer
	// memoryMax) {
	// List<InfoPageVo> list = new ArrayList<InfoPageVo>();
	// try {
	// for (ServerInfo serverInfo : serverInfos) {
	// Services services = null;
	// Integer memory = ComputeUtil.div(serverInfo.getMemoryUsed(),
	// serverInfo.getMemoryTotal(), 2);
	// if (null != memoryMin && null != memoryMax) {
	// if (memory <= memoryMin || memory >= memoryMax) {
	// continue;
	// }
	// }
	// for (Services temp : slist) {
	// if (temp.getId().equals(serverInfo.getServicesId())) {
	// services = temp;
	// break;
	// }
	// }
	//
	// Hardware hardware = hardwareService.selectById(services.getHardwareId());
	// if (hardware == null) {
	// System.out.println(services.getHardwareId());
	// }
	// if (!"".equals(ip) && null != ip && !ip.equals(hardware.getIp())) {
	// continue;
	// }
	//
	// InfoPageVo infoPageVo = new InfoPageVo();
	// Integer id = services.getId();
	// infoPageVo.setId(id);
	// infoPageVo.setIp(hardware.getIp());
	// infoPageVo.setName(services.getName());
	// infoPageVo.setStatus(services.getStatus());
	// infoPageVo.setCpu(serverInfo.getCpu());
	// infoPageVo.setMemory(memory);
	// infoPageVo.setMemoryUsed(serverInfo.getMemoryUsed());
	// infoPageVo.setMemoryTotal(serverInfo.getMemoryTotal());
	// String spaceJStr = serverInfo.getSpace();
	// infoPageVo.setSpaceMax(serverInfo.getSpaceMax());
	// infoPageVo.setSpace(spaceJStr);
	// infoPageVo.setMessage(services.isMessage());
	// String portStatus = serverInfo.getPortStatus();
	// if (portStatus != null) {
	//
	// Map<String, Boolean> map = JSON.parseObject(portStatus, Map.class);
	// String ports = "";
	// for (String port : map.keySet()) {
	// if (map.get(port)) {
	// ports += "<span class=\"label label-success\">" + port + "</span>&nbsp;";
	// } else {
	// ports += "<span class=\"label label-danger\">" + port + "</span>&nbsp;";
	// }
	// }
	// infoPageVo.setPortStatus(ports);
	// }
	// list.add(infoPageVo);
	// }
	// } catch (Exception e) {
	// LOG.error("处理分页展示数据异常", e);
	// }
	// return list;
	// }

	/**
	 * 处理分页展示数据
	 * 
	 * @param servers
	 * @param serverInfos
	 * @return
	 */
	private List<InfoPageVo> getInfoPageList(List<Services> slist, List<ServerInfo> serverInfos, Integer memmin, Integer memmax) {
		List<InfoPageVo> list = new ArrayList<InfoPageVo>();
		try {
			for (Services services : slist) {
				for (ServerInfo serverInfo : serverInfos) {
					Integer memory = ComputeUtil.div(serverInfo.getMemoryUsed(), serverInfo.getMemoryTotal(), 2);
					if (null != memmin && null != memmax) {
						if (memory <= memmin || memory >= memmax) {
							continue;
						}
					}

					if (services.getId().equals(serverInfo.getServicesId())) {
						Hardware hardware = hardwareService.selectById(services.getHardwareId());

						InfoPageVo infoPageVo = new InfoPageVo();
						Integer id = services.getId();
						infoPageVo.setId(id);
						infoPageVo.setIp(hardware.getIp());
						infoPageVo.setName(services.getName());
						infoPageVo.setStatus(services.getStatus());
						infoPageVo.setCpu(serverInfo.getCpu());
						infoPageVo.setMemory(memory);
						infoPageVo.setMemoryUsed(serverInfo.getMemoryUsed());
						infoPageVo.setMemoryTotal(serverInfo.getMemoryTotal());
						String spaceJStr = serverInfo.getSpace();
						infoPageVo.setSpaceMax(serverInfo.getSpaceMax());
						infoPageVo.setSpace(spaceJStr);
						infoPageVo.setMessage(services.isMessage());
						String portStatus = serverInfo.getPortStatus();
						if (portStatus != null) {
							Map<String, Boolean> map = JSON.parseObject(portStatus, Map.class);
							String ports = "";
							for (String port : map.keySet()) {
								if (map.get(port)) {
									ports += "<span class=\"label label-success\">" + port + "</span>&nbsp;";
								} else {
									ports += "<span class=\"label label-danger\">" + port + "</span>&nbsp;";
								}
							}
							infoPageVo.setPortStatus(ports);
						}
						list.add(infoPageVo);
						break;
					}
				}
			}
		} catch (Exception e) {
			LOG.error("处理分页展示数据异常", e);
		}
		return list;
	}

	@RequestMapping("/getServiceRelationList")
	@ResponseBody
	public List<Map<String, String>> getServiceRelationList(@RequestParam(value = "ids[]") Integer[] ids) {
		List<Map<String, String>> lis = new ArrayList<Map<String, String>>();
		EntityWrapper<AffectServices> ew = new EntityWrapper<AffectServices>();
		ew.in("services_id", ids);
		ew.in("affect_services_id", ids);
		List<AffectServices> aslist = affectServicesService.selectList(ew);
		for (AffectServices affectServices : aslist) {
			String name = servicesService.selectById(affectServices.getServicesId()).getName();
			String name_ = servicesService.selectById(affectServices.getAffectServicesId()).getName();
			Map<String, String> map = new HashMap<String, String>();
			map.put(name, name_);
			lis.add(map);
		}
		return lis;
	}

	@RequestMapping("/getAllProcess")
	@ResponseBody
	public List<ProcessInfo> getAllProcess(String ip) {

		String url = "http://" + ip + ":9090/SystemControl/services/SystemService";
		String space = "http://webService.system.com";
		String method = "getProcessInfos";
		Map<String, String> param = new HashMap<String, String>();
		String result = WebServiceUtil.callAxis(url, space, method, param);
		List<ProcessInfo> lis = JSON.parseArray(result, ProcessInfo.class);
		return lis;
	}

	@RequestMapping("/getMemoryList")
	@ResponseBody
	public List<ProcessInfo> getMemoryList(String ip) {
		String url = "http://" + ip + ":9090/SystemControl/services/SystemService";
		String space = "http://webService.system.com";
		String method = "getProcessInfos";
		Map<String, String> param = new HashMap<String, String>();
		String result = WebServiceUtil.callAxis(url, space, method, param);
		List<ProcessInfo> lis = JSON.parseArray(result, ProcessInfo.class);
		Collections.sort(lis, new Comparator<ProcessInfo>() {
			/*
			 * 返回负数表示：p1 小于p2， 返回0 表示：p1和p2相等， 返回正数表示：p1大于p2
			 */
			public int compare(ProcessInfo p1, ProcessInfo p2) {
				if (p1.getMemUse().contains("K") && p2.getMemUse().contains("K")) {
					Double k1 = Double.valueOf(p1.getMemUse().substring(0, p1.getMemUse().indexOf("K")));
					Double k2 = Double.valueOf(p2.getMemUse().substring(0, p2.getMemUse().indexOf("K")));
					if (k1 > k2) {
						return -1;
					} else if (k1 < k2) {
						return 1;
					} else {
						return 0;
					}

				} else if (p1.getMemUse().contains("M") && p2.getMemUse().contains("M")) {
					Double M1 = Double.valueOf(p1.getMemUse().substring(0, p1.getMemUse().indexOf("M")));
					Double M2 = Double.valueOf(p2.getMemUse().substring(0, p2.getMemUse().indexOf("M")));
					if (M1 > M2) {
						return -1;
					} else if (M1 < M2) {
						return 1;
					} else {
						return 0;
					}
				} else if (p1.getMemUse().contains("K") && p2.getMemUse().contains("M")) {
					return 1;
				} else {
					return -1;
				}

			}
		});
		return lis;

	}

	/**
	 * 获取指定service的所有监控数据（系统保留一天）
	 * 
	 * @param server的id号
	 * @return 插件data需要的数据格式是数组（0表示时间，1表示数值）
	 * 
	 */
	@RequestMapping("/getServiceAllMsg")
	@ResponseBody
	public Map<String, Object> getServiceAllMsg(@RequestParam(value = "id") int id) {

		List<String[]> cpulis = new ArrayList<String[]>();
		List<String[]> nclis = new ArrayList<String[]>();
		List<String[]> cplis = new ArrayList<String[]>();

		EntityWrapper<ServerInfo> ew = new EntityWrapper<ServerInfo>();
		ew.eq("services_id", id);
		ew.orderBy("datetime", true);
		List<ServerInfo> sinfolist = serverInfoService.selectList(ew);
		for (ServerInfo info : sinfolist) {
			long dt = info.getDatetime().getTime();
			// cpu时间，使用率
			String[] cpuinfo = new String[] { String.valueOf(dt), info.getCpu().toString() };
			// 内存时间，使用率
			String[] ncinfo = new String[] { String.valueOf(dt), String.valueOf(ComputeUtil.div(info.getMemoryUsed(), info.getMemoryTotal(), 2)) };
			// 磁盘时间，使用率
			String[] cpinfo = new String[] { String.valueOf(dt), String.valueOf(info.getSpaceMax()) };

			cpulis.add(cpuinfo);
			nclis.add(ncinfo);
			cplis.add(cpinfo);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (sinfolist.isEmpty()) {
			map.put("code", 0);
			map.put("msg", "操作失败！");
			map.put("data1", null);
			map.put("data2", null);
			map.put("data3", null);
		} else {
			map.put("code", 1);
			map.put("msg", "操作成功！");
			map.put("data1", cpulis);
			map.put("data2", nclis);
			map.put("data3", cplis);
		}
		return map;
	}
}
