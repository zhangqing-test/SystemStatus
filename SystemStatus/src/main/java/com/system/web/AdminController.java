package com.system.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.system.common.constants.StatusConstants;
import com.system.common.controller.BaseController;
import com.system.common.util.WebServiceUtil;
import com.system.entity.Account;
import com.system.entity.AffectServices;
import com.system.entity.Hardware;
import com.system.entity.Manager;
import com.system.entity.PingAndPort;
import com.system.entity.ProcessMsg;
import com.system.entity.Services;
import com.system.entity.Users;
import com.system.service.IAccountService;
import com.system.service.IAffectServicesService;
import com.system.service.IHardwareService;
import com.system.service.IManagerService;
import com.system.service.IPingAndPortService;
import com.system.service.IProcessInfoService;
import com.system.service.IServicesService;
import com.system.web.form.DatatableForm;
import com.system.web.vo.ProcessInfo;

import net.sf.json.JSONArray;

/**
 * 后台页面 前端控制器
 */
@Controller
public class AdminController extends BaseController {

	@Autowired
	private IHardwareService hardwareService;

	@Autowired
	private IServicesService servicesService;

	@Autowired
	private IAffectServicesService affectServicesService;

	@Autowired
	private IPingAndPortService netService;

	@Autowired
	private IManagerService managerService;

	@Autowired
	private IProcessInfoService processInfoService;

	@Autowired
	private IAccountService accountService;

	@RequestMapping("/OA/account/query")
	@ResponseBody
	public String accountList(DatatableForm form, HttpServletRequest request, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		/*
		 * String hardwareName = request.getParameter("fwqmc"); String
		 * serviceName = request.getParameter("rjfwmc");
		 */
		String hardwareAccountName = request.getParameter("yjzhm");
		String serviceAccountName = request.getParameter("rjzhm");

		EntityWrapper<Account> ew = new EntityWrapper<Account>();
		/*
		 * if (!"".equals(hardwareName)) { ew.eq("fwqmc", hardwareName); } if
		 * (!"".equals(serviceName)) { ew.eq("rjfwmc", serviceName); }
		 */
		if (!"".equals(hardwareAccountName) && null != hardwareAccountName) {
			ew.eq("hardware_account", hardwareAccountName);
		}
		if (!"".equals(serviceAccountName) && null != serviceAccountName) {
			ew.eq("service_account", serviceAccountName);
		}
		Integer count = accountService.selectCount(ew);
		if (count != 0) {
			SimpleDateFormat sdf = new SimpleDateFormat(StatusConstants.SDF);
			List<Account> list = accountService.paginationQuery(form, ew);
			List<List<String>> data = new ArrayList<List<String>>();
			for (Account a : list) {
				List<String> detail = new ArrayList<String>();
				List<Hardware> hd = hardwareService.selectList(new EntityWrapper<Hardware>().eq("id", a.getName()));
				detail.add(hd.get(0).getName());
				List<Services> sc = servicesService.selectList(new EntityWrapper<Services>().eq("id", a.getServices()));
				detail.add(sc.get(0).getName());
				detail.add(a.getHardware_account());
				detail.add(a.getService_account());
				detail.add(a.getDay().toString());
				detail.add(sdf.format(a.getTime()).toString());
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

	@RequestMapping("/OA/account")
	public String account(HttpServletRequest request, Model model) {
		return "accountList";
	}

	@RequestMapping("/OA/services/processget")
	@ResponseBody
	public String processGet(@RequestParam("id") Integer id, @RequestParam("ip") String ip) {
		EntityWrapper<ProcessMsg> ew = new EntityWrapper<ProcessMsg>();
		ew.eq("sid", id);
		List<ProcessMsg> pcmsg = processInfoService.selectList(ew);
		String url = "http://" + ip + ":9090/SystemControl/services/SystemService";
		String space = "http://webService.system.com";
		String method = "getProcessInfos";
		Map<String, String> param = new HashMap<String, String>();
		String result = WebServiceUtil.callAxis(url, space, method, param);
		List<ProcessInfo> lis = JSON.parseArray(result, ProcessInfo.class);
		for (ProcessMsg msg : pcmsg) {
			boolean flag = false;
			Integer pid = msg.getPid();
			String name = msg.getName();
			for (ProcessInfo p : lis) {
				if (name.equals(p.getName())) {
					ProcessMsg newmsg = new ProcessMsg(id, Integer.parseInt(p.getPid()), p.getName(), p.getUser(), p.getMemSize(), p.getMemUse());
					processInfoService.update(newmsg, new EntityWrapper<ProcessMsg>().eq("sid", id).eq("name", name));
					flag = true;
					break;
				}
			}
			if (!flag) {
				processInfoService.delete(new EntityWrapper<ProcessMsg>().eq("sid", id).eq("pid", pid));
			}
		}

		List<ProcessMsg> res = processInfoService.selectList(ew);
		JSONArray js = JSONArray.fromObject(res);
		return js.toString();
	}

	/**
	 * 每次调用需要更新原有key对应的值
	 * 
	 */
	@RequestMapping("/OA/services/processmap")
	@ResponseBody
	public String changeMap(@RequestParam("info") String info, @RequestParam("id") Integer id, Model model) {

		List<ProcessMsg> list = new ArrayList<ProcessMsg>();
		String str[] = info.split(",");
		for (int i = 0; i < str.length; i++) {
			String pinfo[] = str[i].split("&&");
			ProcessMsg pc = new ProcessMsg(id, Integer.parseInt(pinfo[1]), pinfo[2].replaceAll("%25", "%"), pinfo[3], pinfo[4], pinfo[5]);
			list.add(pc);
		}

		EntityWrapper<ProcessMsg> ew = new EntityWrapper<ProcessMsg>();
		ew.eq("sid", id);
		processInfoService.delete(ew);
		processInfoService.insertBatch(list);
		return "success";
	}

	/**
	 * 硬件入口
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/OA/hardware")
	public String hardware(HttpServletRequest request, Model model) {
		return "hardwareList";
	}

	/**
	 * 硬件list分页
	 * 
	 * @param form
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/OA/hardware/query")
	@ResponseBody
	public String hardwareList(HttpSession session, DatatableForm form, HttpServletRequest request, Model model) {
		Users u = (Users) session.getAttribute("loginUser");
		String pingButton = "";
		if (u.getAuth().equals("admin")) {
			pingButton = "<button type=\"button\" onClick=\"hardwareClick(ID)\" class=\"btn btn-outline btn-primary btn-xs\">detail</button><button type=\"button\" onClick=\"hardwareDelete(ID)\" class=\"btn btn-outline btn-danger btn-xs\">delete</button>";
		} else {
			pingButton = "<button type=\"button\" onClick=\"hardwareClick(ID)\" class=\"btn btn-outline btn-primary btn-xs\">detail</button>";
		}

		Map<String, Object> result = new HashMap<String, Object>();
		String ip = request.getParameter("hIp");
		String name = request.getParameter("hName");
		String sx = request.getParameter("hSx");
		EntityWrapper<Hardware> ew = new EntityWrapper<Hardware>();
		if (!"".equals(name)) {
			ew.like("name", name);
		}
		if (!"".equals(ip)) {
			ew.eq("ip", ip);
		}
		if (!"".equals(sx)) {
			ew.eq("fwqsx", sx);
		}
		Integer count = hardwareService.selectCount(ew);
		if (count != 0) {
			List<Hardware> list = hardwareService.paginationQuery(form, ew);
			List<List<String>> data = new ArrayList<List<String>>();
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd
			// HH:mm:ss");

			for (Hardware hardware : list) {
				List<String> detail = new ArrayList<String>();
				detail.add(hardware.getName());

				detail.add(hardware.getFwqsx() == 0 ? "虚拟机" : "实体机");
				detail.add(hardware.getIp());
				detail.add(hardware.getSzjip());
				detail.add(hardware.getYxxt());
				detail.add(hardware.getMac());
				detail.add(pingButton.replaceAll("ID", hardware.getId().toString()));
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
	 * 硬件详情
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/OA/hardware/detail")
	public String hardwareDetail(@RequestParam("id") Integer id, Model model) {
		Hardware hardware = hardwareService.selectById(id);
		model.addAttribute("data", hardware);
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(hardware.getFirstManager());
		ids.add(hardware.getSecondManager());
		if (null != hardware.getThirdManager()) {
			ids.add(hardware.getThirdManager());
		}
		List<Manager> managers = managerService.selectBatchIds(ids);
		if (null != managers) {
			for (Manager manager : managers) {
				if (manager.getId().equals(hardware.getFirstManager())) {
					model.addAttribute("one", manager);
				}
				if (manager.getId().equals(hardware.getSecondManager())) {
					model.addAttribute("two", manager);
				}
				if (manager.getId().equals(hardware.getThirdManager())) {
					model.addAttribute("three", manager);
				}
			}
		}
		return "hardwareDetail";
	}

	/**
	 * 根据id 删除hardware
	 * 
	 * @param id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/OA/hardware/delete")
	@ResponseBody
	public String hardwareDelete(@RequestParam("id") Integer id, HttpServletRequest request, Model model) {
		try {
			boolean flag = false;
			EntityWrapper<Services> sEw = new EntityWrapper<Services>();
			sEw.eq("hardware_id", id);
			List<Services> slist = servicesService.selectList(sEw);
			if (slist.isEmpty()) {
				flag = hardwareService.deleteById(id);
			}
			return flag ? "success" : "false";

		} catch (Exception e) {
			return "false";
		}
	}

	/**
	 * 服务入口
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/OA/services")
	public String services(HttpServletRequest request, Model model) {
		return "servicesList";
	}

	/**
	 * 服务list分页
	 * 
	 * @param form
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/OA/services/query")
	@ResponseBody
	public String servicesList(HttpSession session, DatatableForm form, HttpServletRequest request, Model model) {
		Users u = (Users) session.getAttribute("loginUser");
		String pingButton = "";
		if (u.getAuth().equals("admin")) {
			pingButton = "<button type=\"button\" onClick=\"servicesClick(ID)\" class=\"btn btn-outline btn-primary btn-xs\">detail</button><button type=\"button\" onClick=\"servicesDelete(ID)\" class=\"btn btn-outline btn-danger btn-xs\">delete</button>";
		} else {
			pingButton = "<button type=\"button\" onClick=\"servicesClick(ID)\" class=\"btn btn-outline btn-primary btn-xs\">detail</button>";
		}

		Map<String, Object> result = new HashMap<String, Object>();
		String fwName = request.getParameter("fwName");
		String sfjk = request.getParameter("sfjk");
		String fwType = request.getParameter("fwType");
		String fwip = request.getParameter("fwip");
		String fwport = request.getParameter("fwport");
		EntityWrapper<Services> ew = new EntityWrapper<Services>();
		if (!"".equals(fwName) && null != fwName) {
			ew.like("name", fwName.trim());
		}
		if (!"".equals(sfjk) && null != sfjk) {
			ew.eq("jk", sfjk);
		}
		if (!"".equals(fwType) && null != fwType) {
			ew.eq("type", fwType);
		}
		if (!"".equals(fwport) && null != fwport) {
			ew.eq("port", fwport.trim());
		}

		EntityWrapper<Hardware> ewh = new EntityWrapper<Hardware>();
		List<Hardware> hardwarelist = null;
		if (!"".equals(fwip) && null != fwip) {
			ewh.eq("ip", fwip.trim());
			hardwarelist = hardwareService.selectList(ewh);
		}
		if (hardwarelist != null) {
			ew.eq("hardware_id", hardwarelist.get(0).getId());
		}
		Integer count = servicesService.selectCount(ew);

		if (count != 0) {
			List<Services> list = servicesService.paginationQuery(form, ew);
			List<List<String>> data = new ArrayList<List<String>>();

			for (Services services : list) {
				List<String> detail = new ArrayList<String>();
				Hardware hardware = hardwareService.selectById(services.getHardwareId());
				detail.add(hardware.getIp());
				detail.add(services.getName());
				detail.add(hardware.getName());
				detail.add(services.getPort());
				detail.add(services.isJk() ? "是" : "否");
				detail.add(services.getType() == 1 ? "应用服务" : "数据库");
				detail.add(pingButton.replaceAll("ID", services.getId().toString()));
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
	 * 服务详情
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/OA/services/detail")
	public String servicesDetail(@RequestParam("id") Integer id, Model model) {
		Services services = servicesService.selectById(id);
		model.addAttribute("data", services);

		Hardware hardware = hardwareService.selectById(services.getHardwareId());
		model.addAttribute("hardware", hardware);
		model.addAttribute("jk", services.isJk());

		List<Map<String, Object>> list = servicesService.selectAffectServices(services.getId());
		model.addAttribute("affect", list);

		List<Integer> ids = new ArrayList<Integer>();
		ids.add(services.getFirstManager());
		ids.add(services.getSecondManager());
		if (null != services.getThirdManager()) {
			ids.add(services.getThirdManager());
		}
		List<Manager> managers = managerService.selectBatchIds(ids);
		if (null != managers) {
			for (Manager manager : managers) {
				if (manager.getId().equals(services.getFirstManager())) {
					model.addAttribute("one", manager);
				}
				if (manager.getId().equals(services.getSecondManager())) {
					model.addAttribute("two", manager);
				}
				if (manager.getId().equals(services.getThirdManager())) {
					model.addAttribute("three", manager);
				}
			}
		}
		return "servicesDetail";
	}

	/** 前端页面name点击 */
	@RequestMapping("/OA/services/detailinfo")
	public String servicesDetailInfo(@RequestParam("id") Integer id, Model model) {
		Services services = servicesService.selectById(id);
		model.addAttribute("data", services);

		Hardware hardware = hardwareService.selectById(services.getHardwareId());
		model.addAttribute("hardware", hardware);
		model.addAttribute("jk", services.isJk());

		List<Map<String, Object>> list = servicesService.selectAffectServices(services.getId());
		model.addAttribute("affect", list);

		List<Integer> ids = new ArrayList<Integer>();
		ids.add(services.getFirstManager());
		ids.add(services.getSecondManager());
		if (null != services.getThirdManager()) {
			ids.add(services.getThirdManager());
		}
		List<Manager> managers = managerService.selectBatchIds(ids);
		if (null != managers) {
			for (Manager manager : managers) {
				if (manager.getId().equals(services.getFirstManager())) {
					model.addAttribute("one", manager);
				}
				if (manager.getId().equals(services.getSecondManager())) {
					model.addAttribute("two", manager);
				}
				if (manager.getId().equals(services.getThirdManager())) {
					model.addAttribute("three", manager);
				}
			}
		}
		return "showDetail";
	}

	/**
	 * 根据id 删除services
	 * 
	 * @param id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/OA/services/delete")
	@ResponseBody
	public String servicesDelete(@RequestParam("id") Integer id, HttpServletRequest request, Model model) {
		try {
			affectServicesService.delete(new EntityWrapper<AffectServices>().eq("services_id", id));
			boolean flag = servicesService.deleteById(id);
			return flag ? "success" : "false";
		} catch (Exception e) {
			return "false";
		}
	}

	/**
	 * 网络入口
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/OA/net")
	public String net(HttpServletRequest request, Model model) {
		return "netList";
	}

	/**
	 * 网络list分页
	 * 
	 * @param form
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/OA/net/query")
	@ResponseBody
	public String netList(HttpSession session, DatatableForm form, HttpServletRequest request, Model model) {
		Users u = (Users) session.getAttribute("loginUser");
		String pingButton = "";
		if (u.getAuth().equals("admin")) {
			pingButton = "<button type=\"button\" onClick=\"netClick(ID)\" class=\"btn btn-outline btn-primary btn-xs\">detail</button><button type=\"button\" onClick=\"netDelete(ID)\" class=\"btn btn-outline btn-danger btn-xs\">delete</button>";
		} else {
			pingButton = "<button type=\"button\" onClick=\"netClick(ID)\" class=\"btn btn-outline btn-primary btn-xs\">detail</button>";
		}

		Map<String, Object> result = new HashMap<String, Object>();
		String netName = request.getParameter("netName");
		String netIp = request.getParameter("netIp");
		String netType = request.getParameter("netType");
		EntityWrapper<PingAndPort> ew = new EntityWrapper<PingAndPort>();
		if (!"".equals(netName)) {
			ew.like("ip_name", netName);
		}
		if (!"".equals(netIp)) {
			ew.eq("ip", netIp);
		}
		if (!"".equals(netType)) {
			ew.eq("type", netType);
		}
		Integer count = netService.selectCount(ew);

		if (count != 0) {
			List<PingAndPort> list = netService.paginationQuery(form, ew);
			List<List<String>> data = new ArrayList<List<String>>();

			for (PingAndPort net : list) {
				List<String> detail = new ArrayList<String>();
				detail.add(net.getNetName());
				detail.add(net.getIpName());
				detail.add(net.getIp());
				detail.add(net.getTargetIpName());
				detail.add(net.getTargetIp());
				switch (net.getType()) {
				case 1:
					detail.add("双向");
					break;
				case 2:
					detail.add("主到副");
					break;
				case 3:
					detail.add("副到主");
					break;
				default:
					break;
				}
				detail.add(pingButton.replaceAll("ID", net.getId().toString()));
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
	 * 网络详情
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/OA/net/detail")
	public String netDetail(@RequestParam("id") Integer id, Model model) {
		PingAndPort net = netService.selectById(id);
		model.addAttribute("data", net);

		List<Integer> ids = new ArrayList<Integer>();
		ids.add(net.getFirstManager());
		ids.add(net.getSecondManager());
		if (null != net.getThirdManager()) {
			ids.add(net.getThirdManager());
		}
		List<Manager> managers = managerService.selectBatchIds(ids);
		if (null != managers) {
			for (Manager manager : managers) {
				if (manager.getId().equals(net.getFirstManager())) {
					model.addAttribute("one", manager);
				}
				if (manager.getId().equals(net.getSecondManager())) {
					model.addAttribute("two", manager);
				}
				if (manager.getId().equals(net.getThirdManager())) {
					model.addAttribute("three", manager);
				}
			}
		}
		return "netDetail";
	}

	/**
	 * 根据id 删除net
	 * 
	 * @param id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/OA/net/delete")
	@ResponseBody
	public String netDelete(@RequestParam("id") Integer id, HttpServletRequest request, Model model) {
		try {
			boolean flag = netService.deleteById(id);
			return flag ? "success" : "false";
		} catch (Exception e) {
			return "false";
		}
	}

}
