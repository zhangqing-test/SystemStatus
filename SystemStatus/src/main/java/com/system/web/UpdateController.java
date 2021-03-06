package com.system.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.system.common.controller.BaseController;
import com.system.entity.Hardware;
import com.system.entity.HardwareUpdate;
import com.system.entity.Manager;
import com.system.entity.NetUpdate;
import com.system.entity.PingAndPort;
import com.system.entity.Services;
import com.system.entity.ServicesUpdate;
import com.system.service.IHardwareService;
import com.system.service.IHardwareUpdateService;
import com.system.service.IManagerService;
import com.system.service.INetInfluenceService;
import com.system.service.INetUpdateService;
import com.system.service.IPingAndPortService;
import com.system.service.IServicesService;
import com.system.service.IServicesUpdateService;
import com.system.web.form.UpdateHistoryForm;
import com.system.web.vo.UpdateHistoryVo;
import com.system.web.vo.UpdateInfoVo;

/**
 * <p>
 * 更新表单记录 前端控制器
 * </p>
 *
 * @author Yang Yang
 * @since 2017-12-21
 */
@Controller
public class UpdateController extends BaseController {
	private static final Logger LOG = LoggerFactory.getLogger(UpdateController.class);

	@Autowired
	private IHardwareUpdateService hardwareUpdateService;

	@Autowired
	private IHardwareService hardwareService;

	@Autowired
	private IServicesUpdateService servicesUpdateService;

	@Autowired
	private IServicesService servicesService;

	@Autowired
	private INetUpdateService netUpdateService;

	@Autowired
	private IPingAndPortService pingAndPortService;

	@Autowired
	private INetInfluenceService nInfluenceService;

	@Autowired
	private IManagerService iManagerService;

	/**
	 * 更新历史记录查询 controller入口
	 * 
	 * @param form
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/updateHistory")
	public String updateHistory(UpdateHistoryForm form, Model model) throws UnsupportedEncodingException {
		LOG.debug("查询更新历史记录");
		Integer type = form.getType();
		String fname = form.getName();
		String fip = form.getIp();
		String name = null;
		if (fname != null) {
			name = new String(fname.getBytes("iso-8859-1"), "utf-8");
		}
		// String ip = form.getIp();
		model.addAttribute("type", type);
		if (StringUtils.isNotEmpty(name)) {
			model.addAttribute("name", name);
		}
		if (StringUtils.isNotEmpty(fip)) {
			model.addAttribute("ip", fip);
		}

		// 获取历史记录
		List<UpdateHistoryVo> list = new ArrayList<UpdateHistoryVo>();
		if (type != null) {
			if (type == 1) {
				// 硬件服务器更新
				List<HardwareUpdate> huList = hardwareUpdateService.queryPageHistory(name);
				if (null != huList) {
					for (HardwareUpdate hardwareUpdate : huList) {
						Hardware hardware = hardwareService.selectById(hardwareUpdate.getHardwareId());
						if (fip != null && fip != hardware.getIp()) {
							continue;
						}
						String his = hardwareService.selectInfluenceNames(hardwareUpdate.getId(), 0);
						String sis = servicesService.selectInfluenceNames(hardwareUpdate.getId(), 0);
						String nis = nInfluenceService.selectInfluenceNames(hardwareUpdate.getId(), 0);

						UpdateHistoryVo temp = new UpdateHistoryVo();
						temp.init(hardwareUpdate, hardware.getName(), hardware.getIp(), his, sis, nis);
						list.add(temp);
					}
				}
			} else if (type == 2) {
				// 软件服务更新
				List<ServicesUpdate> suList = servicesUpdateService.queryPageHistory(name);
				if (null != suList) {
					for (ServicesUpdate servicesUpdate : suList) {
						Services services = servicesService.selectById(servicesUpdate.getServicesId());
						Hardware hardware = hardwareService.selectById(services.getHardwareId());

						if (fip != null && fip.equals(hardware.getIp())) {
							continue;
						}
						String his = hardwareService.selectInfluenceNames(servicesUpdate.getId(), 1);
						String sis = servicesService.selectInfluenceNames(servicesUpdate.getId(), 1);
						String nis = nInfluenceService.selectInfluenceNames(servicesUpdate.getId(), 1);

						UpdateHistoryVo temp = new UpdateHistoryVo();
						temp.init(servicesUpdate, services.getName(), hardware.getIp(), his, sis, nis);
						list.add(temp);
					}
				}
			} else {
				// 网络更新
				List<NetUpdate> nuList = netUpdateService.queryPageHistory(name);
				if (null != nuList) {
					for (NetUpdate netUpdate : nuList) {
						PingAndPort pingAndPort = pingAndPortService.selectById(netUpdate.getNetId());

						if (fip != null && fip != pingAndPort.getIp()) {
							continue;
						}
						String his = hardwareService.selectInfluenceNames(netUpdate.getId(), 2);
						String sis = servicesService.selectInfluenceNames(netUpdate.getId(), 2);
						String nis = nInfluenceService.selectInfluenceNames(netUpdate.getId(), 2);

						UpdateHistoryVo temp = new UpdateHistoryVo();
						temp.init(netUpdate, pingAndPort.getNetName(), pingAndPort.getIp() + "," + pingAndPort.getTargetIp(), his, sis, nis);
						list.add(temp);
					}
				}
			}
		}
		model.addAttribute("list", list);
		return "updateHistory";
	}

	/**
	 * 自动补全硬件记录名称
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping("/autoComplete/hname")
	@ResponseBody
	public List<String> autoCompleteHName(@RequestParam("name") String name) {
		List<String> result = new ArrayList<String>();
		List<String> list = hardwareUpdateService.queryAutoCompleteName(name);
		if (null != list) {
			result.addAll(list);
		}
		return result;
	}

	/**
	 * 自动补全服务记录名称
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping("/autoComplete/sname")
	@ResponseBody
	public List<String> autoCompleteSName(@RequestParam("name") String name) {
		List<String> result = new ArrayList<String>();
		List<String> list = servicesUpdateService.queryAutoCompleteName(name);
		if (null != list) {
			result.addAll(list);
		}
		return result;
	}

	/**
	 * 自动补全网络记录名称
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping("/autoComplete/nname")
	@ResponseBody
	public List<String> autoCompleteNName(@RequestParam("name") String name) {
		List<String> result = new ArrayList<String>();
		List<String> list = netUpdateService.queryAutoCompleteName(name);
		if (null != list) {
			result.addAll(list);
		}
		return result;
	}

	/**
	 * 根据id和类型获取更新信息
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping("/queryTypeUpdateInfo")
	@ResponseBody
	public List<UpdateInfoVo> queryTypeUpdateInfo(@RequestParam("id") Integer id, @RequestParam("type") Integer type) {
		List<UpdateInfoVo> list = new ArrayList<UpdateInfoVo>(10);
		if (type != null) {
			if (type == 1) {
				List<ServicesUpdate> suList = servicesUpdateService.queryHistoryBySid(id);
				Services services = servicesService.selectById(id);
				Hardware hardware = hardwareService.selectById(services.getHardwareId());
				Manager manager = iManagerService.selectById(hardware.getFirstManager());
				String sis;
				UpdateInfoVo uiv;
				int i = 0;
				if (null != suList) {
					for (ServicesUpdate servicesUpdate : suList) {
						if (i == 5) {
							break;
						}
						sis = servicesService.selectInfluenceNames(servicesUpdate.getId(), 1);
						uiv = new UpdateInfoVo(servicesUpdate.getId(), manager.getManagerName(), servicesUpdate.getDatetime(), servicesUpdate.getUpdateContent(), sis);
						list.add(uiv);
						i++;
					}
				}
			} else if (type == 2) {
				List<NetUpdate> nuList = netUpdateService.queryHistoryBynid(id);
				PingAndPort pingAndPort = pingAndPortService.selectById(id);
				Manager manager = iManagerService.selectById(pingAndPort.getFirstManager());
				String sis;
				UpdateInfoVo uiv;
				int i = 0;
				if (null != nuList) {
					for (NetUpdate netUpdate : nuList) {
						if (i == 5) {
							break;
						}
						sis = servicesService.selectInfluenceNames(netUpdate.getId(), 2);
						uiv = new UpdateInfoVo(netUpdate.getId(), manager.getManagerName(), netUpdate.getDatetime(), netUpdate.getUpdateReason(), sis);
						list.add(uiv);
					}
				}
			}
		}
		return list;
	}

}
