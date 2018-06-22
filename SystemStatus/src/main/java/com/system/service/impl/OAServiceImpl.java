package com.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.system.common.annotation.SystemServiceLog;
import com.system.common.constants.StatusConstants;
import com.system.entity.Account;
import com.system.entity.AffectServices;
import com.system.entity.Hardware;
import com.system.entity.HardwareInfluence;
import com.system.entity.HardwareUpdate;
import com.system.entity.Manager;
import com.system.entity.NetInfluence;
import com.system.entity.NetUpdate;
import com.system.entity.PingAndPort;
import com.system.entity.ServerClient;
import com.system.entity.Services;
import com.system.entity.ServicesInfluence;
import com.system.entity.ServicesUpdate;
import com.system.service.IAccountService;
import com.system.service.IAffectServicesService;
import com.system.service.IHardwareInfluenceService;
import com.system.service.IHardwareService;
import com.system.service.IHardwareUpdateService;
import com.system.service.IManagerService;
import com.system.service.INetInfluenceService;
import com.system.service.INetUpdateService;
import com.system.service.IPingAndPortService;
import com.system.service.IServerClientService;
import com.system.service.IServicesInfluenceService;
import com.system.service.IServicesService;
import com.system.service.IServicesUpdateService;
import com.system.service.OAService;
import com.system.webService.form.AccountForm;
import com.system.webService.form.HardwareForm;
import com.system.webService.form.HardwareUpdateForm;
import com.system.webService.form.NetForm;
import com.system.webService.form.NetUpdateForm;
import com.system.webService.form.ServiceForm;
import com.system.webService.form.ServicesUpdateForm;

@Service
@Transactional
public class OAServiceImpl implements OAService {
	private static final Logger LOG = LoggerFactory.getLogger(OAServiceImpl.class);

	@Autowired
	private IManagerService managerService;

	@Autowired
	private IServerClientService serverClientService;

	@Autowired
	private IHardwareService hardwareService;

	@Autowired
	private IServicesService servicesService;

	@Autowired
	private IPingAndPortService pingAndPortService;

	@Autowired
	private IAffectServicesService affectServicesService;

	@Autowired
	private IHardwareInfluenceService hardwareInfluenceService;

	@Autowired
	private IServicesInfluenceService servicesInfluenceService;

	@Autowired
	private INetInfluenceService netInfluenceService;

	@Autowired
	private IHardwareUpdateService hardwareUpdateService;

	@Autowired
	private IServicesUpdateService servicesUpdateService;

	@Autowired
	private INetUpdateService netUpdateService;

	@Autowired
	private IAccountService accountService;

	/**
	 * 创建服务器监控表单
	 * 
	 * @param str
	 * @return
	 */
	@SystemServiceLog(description = StatusConstants.OA_HARDWARE_CREATE, tableType = StatusConstants.TABLE_HARDWARE)
	public Boolean createHardware(String str) {
		try {
			LOG.info("创建服务器监控表单" + str);
			HardwareForm hardwareForm = JSONObject.parseObject(str, HardwareForm.class);
			// 处理联系人
			Manager one = checkManager(hardwareForm.getFirstManagerName(), hardwareForm.getFirstManagerPhone(), hardwareForm.getFirstManagerJob());
			Manager two = checkManager(hardwareForm.getSecondManagerName(), hardwareForm.getSecondManagerPhone(), hardwareForm.getSecondManagerJob());
			Manager third = checkManager(hardwareForm.getThirdManagerName(), hardwareForm.getThirdManagerPhone(), hardwareForm.getThirdManagerJob());

			// 处理server数据入库
			Hardware hardware = hardwareForm.createHardware(one, two, third);
			if (null != hardware) {
				boolean insert = hardwareService.insert(hardware);
				if (!insert) {
					LOG.error("服务器表单创建失败:" + hardware.toString());
					return false;
				}
			}

			// 返回接口调用状态
			return true;
		} catch (Exception e) {
			LOG.error("服务器表单创建失败" + str, e);
			return false;
		}

	}

	/**
	 * 创建服务监控表单
	 * 
	 * @param str
	 * @return
	 */
	@SystemServiceLog(description = StatusConstants.OA_SERVICE_CREATE, tableType = StatusConstants.TABLE_SERVICE)
	public Boolean createService(String str) {
		try {
			LOG.info("创建服务监控表单" + str);
			ServiceForm serviceForm = JSONObject.parseObject(str, ServiceForm.class);
			// 处理联系人
			Manager one = checkManager(serviceForm.getFirstManagerName(), serviceForm.getFirstManagerPhone(), serviceForm.getFirstManagerJob());
			Manager two = checkManager(serviceForm.getSecondManagerName(), serviceForm.getSecondManagerPhone(), serviceForm.getSecondManagerJob());
			Manager third = checkManager(serviceForm.getThirdManagerName(), serviceForm.getThirdManagerPhone(), serviceForm.getThirdManagerJob());

			// 处理server数据
			Services services = serviceForm.createServices(one, two, third);
			Hardware hardware = hardwareService.selectOne(new EntityWrapper<Hardware>().eq("id", services.getHardwareId()));
			if (null != hardware) {
				// 硬件服务器存在
				boolean client = true;
				if (services.isJk()) {
					// 需要监控
					client = checkServerClient(hardware.getIp(), hardware.getName());
				}

				if (client) {
					// 客户端程序存在
					boolean insert = servicesService.insert(services);
					if (insert) {
						// 监控服务数据入库成功
						// 保存中间表数据
						List<String> list = serviceForm.getList();
						if (null != list) {
							for (String string : list) {
								String[] split = string.split("#");
								AffectServices affectServices = new AffectServices();
								affectServices.setServicesId(services.getId());
								affectServices.setAffectServicesId(Integer.valueOf(split[0]));
								affectServices.setName(split[1]);
								affectServices.setContent(split[2]);
								boolean insert2 = affectServicesService.insert(affectServices);
								if (!insert2) {
									LOG.error("受影响的服务入库失败:" + affectServices.toString());
								}
							}
						}
					} else {
						LOG.error("服务表单创建失败:" + services.toString());
						return false;
					}
				} else {
					LOG.error("客户端不存在:" + services.toString());
					return false;
				}
			} else {
				return false;
			}
			// 返回接口调用状态
			return true;
		} catch (Exception e) {
			LOG.error("服务表单创建失败" + str, e);
			return false;
		}
	}

	/**
	 * 创建网络监控表单
	 * 
	 * @param str
	 * @return
	 */
	@SystemServiceLog(description = StatusConstants.OA_NET_CREATE, tableType = StatusConstants.TABLE_NET)
	public Boolean createNet(String str) {
		try {
			LOG.info("创建网络监控表单" + str);
			NetForm netForm = JSONObject.parseObject(str, NetForm.class);
			// 处理联系人
			Manager one = checkManager(netForm.getFirstManagerName(), netForm.getFirstManagerPhone(), netForm.getFirstManagerJob());
			Manager two = checkManager(netForm.getSecondManagerName(), netForm.getSecondManagerPhone(), netForm.getSecondManagerJob());
			Manager third = checkManager(netForm.getThirdManagerName(), netForm.getThirdManagerPhone(), netForm.getThirdManagerJob());

			// 处理网络监控数据入库
			PingAndPort pingAndPort = netForm.createNet(one, two, third);

			if (null != pingAndPort) {
				// 根据双向还是单向处理客户端数据
				switch (pingAndPort.getType()) {
				case 1:
					checkServerClient(pingAndPort.getIp(), pingAndPort.getIpName());
					checkServerClient(pingAndPort.getTargetIp(), pingAndPort.getTargetIpName());
					break;
				case 2:
					checkServerClient(pingAndPort.getIp(), pingAndPort.getIpName());
					break;
				case 3:
					checkServerClient(pingAndPort.getTargetIp(), pingAndPort.getTargetIpName());
					break;
				default:
					break;
				}

				boolean insert = pingAndPortService.insert(pingAndPort);
				if (!insert) {
					LOG.error("服务器表单创建失败:" + pingAndPort.toString());
					return false;
				}
			}

			// 返回接口调用状态
			return true;
		} catch (Exception e) {
			LOG.error("创建网络监控表单失败" + str, e);
			return false;
		}
	}

	/**
	 * 服务器更新表单
	 * 
	 * @param str
	 * @return
	 */
	@SystemServiceLog(description = StatusConstants.OA_HARDWARE_UPDATE, tableType = StatusConstants.TABLE_HARDWARE_UPDATE)
	public Boolean updateHardware(String str) {
		try {
			LOG.info("服务器更新表单" + str);
			// 解析json 根据id查询记录
			HardwareUpdateForm form = JSONObject.parseObject(str, HardwareUpdateForm.class);
			Hardware hardware = hardwareService.selectById(form.getHardwareId());
			if (null == hardware) {
				LOG.error("服务器记录不存在，更新失败:" + form.getHardwareId());
				return false;
			}

			// 根据request_id查询表单记录，如果有为第二次调用，更新处理；如果没有则为第一次调用，新增记录
			EntityWrapper<HardwareUpdate> ew = new EntityWrapper<HardwareUpdate>();
			ew.eq("request_id", form.getId());
			HardwareUpdate hardwareUpdate = hardwareUpdateService.selectOne(ew);
			if (null == hardwareUpdate) {
				hardwareUpdate = form.getHardwareUpdate();
				boolean insert = hardwareUpdateService.insert(hardwareUpdate);
				if (!insert) {
					LOG.error("新增服务器更新表单失败");
					return false;
				}
				// 处理三个影响的中间表，硬件，软件，ip
				String hInfluences = form.getHinfluences();
				if (StringUtils.isNotEmpty(hInfluences)) {
					String[] split = hInfluences.split(",");
					for (String string : split) {
						HardwareInfluence hardwareInfluence = new HardwareInfluence();
						hardwareInfluence.setRelationId(hardwareUpdate.getId());
						hardwareInfluence.setHardwareId(Integer.valueOf(string));
						hardwareInfluence.setType(0);
						hardwareInfluenceService.insert(hardwareInfluence);
					}
				}

				String sInfluences = form.getSinfluences();
				if (StringUtils.isNotEmpty(sInfluences)) {
					String[] split = sInfluences.split(",");
					for (String string : split) {
						ServicesInfluence servicesInfluence = new ServicesInfluence();
						servicesInfluence.setRelationId(hardwareUpdate.getId());
						servicesInfluence.setServicesId(Integer.valueOf(string));
						servicesInfluence.setType(0);
						servicesInfluenceService.insert(servicesInfluence);
					}
				}

				String nInfluences = form.getNinfluences();
				if (StringUtils.isNotEmpty(nInfluences)) {
					String[] split = nInfluences.split(",");
					for (String string : split) {
						NetInfluence netInfluence = new NetInfluence();
						netInfluence.setRelationId(hardwareUpdate.getId());
						netInfluence.setIp(string);
						netInfluence.setType(0);
						netInfluenceService.insert(netInfluence);
					}
				}
			} else {
				form.update(hardwareUpdate);
				boolean update = hardwareUpdateService.updateById(hardwareUpdate);
				if (!update) {
					LOG.error("更新服务器更新表单失败");
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			LOG.error("服务器更新表单创建失败" + str, e);
			return false;
		}
	}

	@SystemServiceLog(description = StatusConstants.OA_HARDWARE_UPDATE, tableType = StatusConstants.TABLE_HARDWARE)
	public Boolean refreshHardware(String str, Integer id) {
		// 1 根据id查出hardware表记录
		Hardware old = hardwareService.selectById(id);
		if (null == old) {
			return false;
		}
		// 2 转化json覆盖记录
		LOG.info("刷新服务器数据" + str);
		HardwareForm hardwareForm = JSONObject.parseObject(str, HardwareForm.class);
		// 3 处理联系人
		Manager one = checkManager(hardwareForm.getFirstManagerName(), hardwareForm.getFirstManagerPhone(), hardwareForm.getFirstManagerJob());
		Manager two = checkManager(hardwareForm.getSecondManagerName(), hardwareForm.getSecondManagerPhone(), hardwareForm.getSecondManagerJob());
		Manager third = checkManager(hardwareForm.getThirdManagerName(), hardwareForm.getThirdManagerPhone(), hardwareForm.getThirdManagerJob());

		hardwareForm.update(old, one, two, third);
		// 4 更新记录
		boolean flag = hardwareService.updateById(old);
		if (!flag) {
			LOG.error("根据id更新服务器数据失败:" + hardwareForm.toString());
			return false;
		}
		checkServerClient(old.getIp(), old.getName());
		return flag;
	}

	/**
	 * 服务器更新表单
	 * 
	 * @param str
	 * @return
	 */
	@SystemServiceLog(description = StatusConstants.OA_SERVICE_UPDATE, tableType = StatusConstants.TABLE_SERVICE_UPDATE)
	public Boolean updateServices(String str) {
		try {
			LOG.info("服务更新表单" + str);
			ServicesUpdateForm form = JSONObject.parseObject(str, ServicesUpdateForm.class);
			Services services = servicesService.selectById(form.getServicesId());
			if (null == services) {
				LOG.error("服务记录不存在，更新失败:" + form.getServicesId());
				return false;
			}

			EntityWrapper<ServicesUpdate> ew = new EntityWrapper<ServicesUpdate>();
			ew.eq("request_id", form.getId());
			ServicesUpdate servicesUpdate = servicesUpdateService.selectOne(ew);
			if (null == servicesUpdate) {
				servicesUpdate = form.getServicesUpdate();
				boolean insert = servicesUpdateService.insert(servicesUpdate);
				if (!insert) {
					LOG.error("新增服务更新表单失败");
					return false;
				}
				// 处理三个中间表
				String hInfluences = form.getHinfluences();
				if (StringUtils.isNotEmpty(hInfluences)) {
					String[] split = hInfluences.split(",");
					for (String string : split) {
						HardwareInfluence hardwareInfluence = new HardwareInfluence();
						hardwareInfluence.setRelationId(servicesUpdate.getId());
						hardwareInfluence.setHardwareId(Integer.valueOf(string));
						hardwareInfluence.setType(1);
						hardwareInfluenceService.insert(hardwareInfluence);
					}
				}

				String sInfluences = form.getSinfluences();
				if (StringUtils.isNotEmpty(sInfluences)) {
					String[] split = sInfluences.split(",");
					for (String string : split) {
						ServicesInfluence servicesInfluence = new ServicesInfluence();
						servicesInfluence.setRelationId(servicesUpdate.getId());
						servicesInfluence.setServicesId(Integer.valueOf(string));
						servicesInfluence.setType(1);
						servicesInfluenceService.insert(servicesInfluence);
					}
				}

				String nInfluences = form.getNinfluences();
				if (StringUtils.isNotEmpty(nInfluences)) {
					String[] split = nInfluences.split(",");
					for (String string : split) {
						NetInfluence netInfluence = new NetInfluence();
						netInfluence.setRelationId(servicesUpdate.getId());
						netInfluence.setIp(string);
						netInfluence.setType(1);
						netInfluenceService.insert(netInfluence);
					}
				}
			} else {
				form.update(servicesUpdate);
				boolean update = servicesUpdateService.updateById(servicesUpdate);
				if (!update) {
					LOG.error("更新服务更新表单失败");
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			LOG.error("服务更新表单异常" + str, e);
			return false;
		}
	}

	@SystemServiceLog(description = StatusConstants.OA_SERVICE_UPDATE, tableType = StatusConstants.TABLE_SERVICE)
	public Boolean refreshService(String str, Integer id) {
		try {
			// 1 根据id查出services表记录
			Services old = servicesService.selectById(id);
			if (null == old) {
				return false;
			}
			// 2 转化json覆盖记录
			LOG.info("刷新服务数据" + str);
			ServiceForm serviceForm = JSONObject.parseObject(str, ServiceForm.class);

			// 3 根据id查出hardware记录
			Hardware hardware = hardwareService.selectById(serviceForm.getHardwareId());
			if (null == hardware) {
				return false;
			}

			// 4 处理联系人
			Manager one = checkManager(serviceForm.getFirstManagerName(), serviceForm.getFirstManagerPhone(), serviceForm.getFirstManagerJob());
			Manager two = checkManager(serviceForm.getSecondManagerName(), serviceForm.getSecondManagerPhone(), serviceForm.getSecondManagerJob());
			Manager third = checkManager(serviceForm.getThirdManagerName(), serviceForm.getThirdManagerPhone(), serviceForm.getThirdManagerJob());

			serviceForm.update(old, one, two, third);
			// 5 更新记录
			List<String> list = serviceForm.getList();
			List<Integer> ids = null;
			if (list != null) {
				// 外调接口不为空
				ids = new ArrayList<Integer>();
				for (String string : list) {
					String[] split = string.split("#");
					AffectServices affectServices = affectServicesService.selectOne(new EntityWrapper<AffectServices>().eq("services_id", id).eq("affect_services_id", Integer.valueOf(split[0])));
					if (null == affectServices) {
						// 新增
						affectServices = new AffectServices();
						affectServices.setServicesId(id);
						affectServices.setAffectServicesId(Integer.valueOf(split[0]));
						affectServices.setName(split[1]);
						affectServices.setContent(split[2]);
						boolean insert2 = affectServicesService.insert(affectServices);
						if (!insert2) {
							LOG.error("受影响的服务入库失败:" + affectServices.toString());
						} else {
							ids.add(affectServices.getAffectServicesId());
						}

					} else {
						// 更新
						affectServices.setName(split[1]);
						affectServices.setContent(split[2]);
						boolean update = affectServicesService.updateById(affectServices);
						if (!update) {
							LOG.error("受影响的服务更新失败:" + affectServices.toString());
						}
						ids.add(affectServices.getAffectServicesId());
					}
				}
			}

			// 清楚可能改变后的残留数据
			Wrapper<AffectServices> ew = new EntityWrapper<AffectServices>().eq("services_id", id);
			if (null != ids) {
				ew.notIn("affect_services_id", ids);
			}

			affectServicesService.delete(ew);

			boolean flag = servicesService.updateById(old);
			if (!flag) {
				LOG.error("根据id更新服务器数据失败:" + serviceForm.toString());
				return false;
			}
			// 6 检查客户端记录是否存在
			if (old.isJk()) {
				checkServerClient(hardware.getIp(), hardware.getName());
			}
			return flag;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 网络更新表单
	 * 
	 * @param str
	 * @return
	 */
	@SystemServiceLog(description = StatusConstants.OA_NET_UPDATE, tableType = StatusConstants.TABLE_NET_UPDATE)
	public Boolean updateNet(String str) {
		try {
			LOG.info("网络更新表单" + str);
			NetUpdateForm form = JSONObject.parseObject(str, NetUpdateForm.class);
			PingAndPort pingAndPort = pingAndPortService.selectById(form.getNetId());
			if (null == pingAndPort) {
				LOG.error("网络记录不存在，更新失败:" + form.getNetId());
				return false;
			}

			EntityWrapper<NetUpdate> ew = new EntityWrapper<NetUpdate>();
			ew.eq("request_id", form.getId());
			NetUpdate netUpdate = netUpdateService.selectOne(ew);
			if (null == netUpdate) {
				netUpdate = form.getNetUpdate();
				boolean insert = netUpdateService.insert(netUpdate);
				if (!insert) {
					LOG.error("新增网络更新表单失败");
					return false;
				}
				// 处理三个中间表
				String hInfluences = form.getHinfluences();
				if (StringUtils.isNotEmpty(hInfluences)) {
					String[] split = hInfluences.split(",");
					for (String string : split) {
						HardwareInfluence hardwareInfluence = new HardwareInfluence();
						hardwareInfluence.setRelationId(netUpdate.getId());
						hardwareInfluence.setHardwareId(Integer.valueOf(string));
						hardwareInfluence.setType(2);
						hardwareInfluenceService.insert(hardwareInfluence);
					}
				}

				String sInfluences = form.getSinfluences();
				if (StringUtils.isNotEmpty(sInfluences)) {
					String[] split = sInfluences.split(",");
					for (String string : split) {
						ServicesInfluence servicesInfluence = new ServicesInfluence();
						servicesInfluence.setRelationId(netUpdate.getId());
						servicesInfluence.setServicesId(Integer.valueOf(string));
						servicesInfluence.setType(2);
						servicesInfluenceService.insert(servicesInfluence);
					}
				}

				String nInfluences = form.getNinfluences();
				if (StringUtils.isNotEmpty(nInfluences)) {
					String[] split = nInfluences.split(",");
					for (String string : split) {
						NetInfluence netInfluence = new NetInfluence();
						netInfluence.setRelationId(netUpdate.getId());
						netInfluence.setIp(string);
						netInfluence.setType(2);
						netInfluenceService.insert(netInfluence);
					}
				}
			} else {
				form.update(netUpdate);
				boolean update = netUpdateService.updateById(netUpdate);
				if (!update) {
					LOG.error("更新网络更新表单失败");
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			LOG.error("网络更新表单异常" + str, e);
			return false;
		}
	}

	/**
	 * 刷新网络内容
	 * 
	 * @param str
	 * @param id
	 * @return
	 */
	@SystemServiceLog(description = StatusConstants.OA_NET_UPDATE, tableType = StatusConstants.TABLE_NET)
	public Boolean refreshNet(String str, Integer id) {
		// 1 根据id查出pingAndPor表记录
		PingAndPort old = pingAndPortService.selectById(id);
		if (null == old) {
			return false;
		}
		// 2 转化json覆盖记录
		LOG.info("刷新网络数据" + str);
		NetForm netForm = JSONObject.parseObject(str, NetForm.class);

		// 3 处理联系人
		Manager one = checkManager(netForm.getFirstManagerName(), netForm.getFirstManagerPhone(), netForm.getFirstManagerJob());
		Manager two = checkManager(netForm.getSecondManagerName(), netForm.getSecondManagerPhone(), netForm.getSecondManagerJob());
		Manager third = checkManager(netForm.getThirdManagerName(), netForm.getThirdManagerPhone(), netForm.getThirdManagerJob());

		netForm.update(old, one, two, third);
		// 4 更新记录
		boolean flag = pingAndPortService.updateById(old);
		if (!flag) {
			LOG.error("根据id更新服务器数据失败:" + netForm.toString());
			return false;
		}
		// 5 检查客户端记录是否存在
		switch (old.getType()) {
		case 1:
			checkServerClient(old.getIp(), old.getIpName());
			checkServerClient(old.getTargetIp(), old.getTargetIpName());
			break;
		case 2:
			checkServerClient(old.getIp(), old.getIpName());
			break;
		case 3:
			checkServerClient(old.getTargetIp(), old.getTargetIpName());
			break;
		default:
			break;
		}
		return flag;
	}

	/**
	 * 处理客户端
	 * 
	 * @param ip
	 * @param ipName
	 * @return
	 */
	private boolean checkServerClient(String ip, String ipName) {
		EntityWrapper<ServerClient> scew = new EntityWrapper<ServerClient>();
		scew.eq("ip", ip);
		ServerClient serverClient = serverClientService.selectOne(scew);
		if (null == serverClient) {
			serverClient = new ServerClient();
			serverClient.setIp(ip);
			serverClient.setName(ipName);
			serverClient.setServerStatus(1);
			serverClient.setRestartStatus(true);
			serverClient.setBuildStatus(true);
			boolean insert = serverClientService.insert(serverClient);
			if (!insert) {
				LOG.error("serverClient保存失败:" + serverClient.toString());
			}
			return insert;
		} else {
			return true;
		}
	}

	/**
	 * 处理联系人 如果号码为空 返回null<br>
	 * 
	 * @param name
	 * @param phone
	 * @param job
	 * @return
	 */
	private Manager checkManager(String name, String phone, String job) {
		if (StringUtils.isEmpty(phone)) {
			return null;
		}
		EntityWrapper<Manager> mew = new EntityWrapper<Manager>();
		mew.eq("manager_name", name).eq("manager_phone", phone);
		Manager manager = managerService.selectOne(mew);
		if (null == manager) {
			manager = new Manager();
			manager.setManagerName(name);
			manager.setManagerPhone(phone);
			manager.setManagerJob(job);
			boolean insert = managerService.insert(manager);
			if (!insert) {
				LOG.error("联系人保存失败:" + name + "||" + phone + "||" + job);
				return null;
			}
		}
		return manager;
	}

	@SystemServiceLog(description = StatusConstants.ACCOUNT_CREATE, tableType = StatusConstants.TABLE_ACCOUNT)
	public Boolean createAccount(String str) {

		AccountForm accountForm = JSONObject.parseObject(str, AccountForm.class);
		Manager one = checkManager(accountForm.getYjzrr(), accountForm.getYjzrrdh(), accountForm.getYjzrrzw());
		Manager two = checkManager(accountForm.getEjzrr(), accountForm.getEjzrrdh(), accountForm.getEjzrrzw());
		Manager third = checkManager(accountForm.getSjzrr(), accountForm.getSjzrrdh(), accountForm.getSjzrrzw());
		Account account = accountForm.createAccount(one, two, third);
		boolean insert = accountService.insert(account);
		if (!insert) {
			LOG.error("新增开发人员账号失败:" + account.toString());
			return false;
		} else {
			return true;
		}
	}

}
