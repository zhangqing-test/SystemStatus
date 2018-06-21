package com.system.webService.form;

import java.io.Serializable;
import java.util.Date;

import com.system.entity.Hardware;
import com.system.entity.Manager;

/**
 * 硬件表单
 */
public class HardwareForm implements Serializable {

	private static final long serialVersionUID = 1822870796808615194L;
	/** 标识id */
	private Integer id;
	/** 服务器名称 */
	private String name;
	/** 主机名 */
	private String zjm;
	/** 服务器属性 0-虚拟机 1-实体机 */
	private Integer fwqsx;
	/** 用途 */
	private String yt;
	/** 域 */
	private String yu;
	/** 服务器型号 */
	private String fwqxh;
	/** 所属机房 */
	private String ssjf;
	/** 机柜 */
	private String jg;
	/** U位 */
	private String uw;
	/** cpu配置 */
	private String cpuConfig;
	/** 内存配置 */
	private String memoryConfig;
	/** 硬盘 */
	private String yp;
	/** 网卡 */
	private String wk;
	/** 光纤卡 */
	private String gxk;
	/** 电源 */
	private String dy;
	/** 存储 */
	private String cc;
	/** 序列号 */
	private String xlh;
	/** 购买时间 */
	private Date gmsj;
	/** 过保时间 */
	private Date gbsj;
	/** 使用年限 */
	private Integer synx;
	/** 杀毒软件 */
	private String sdrj;
	/** 所属机构 */
	private String ssjg;
	/** 宿主机ip */
	private String szjip;
	/** 运行系统 */
	private String yxxt;
	/** ip */
	private String ip;
	/** 一级联系人名称 */
	private String firstManagerName;
	/** 一级联系人职位 */
	private String firstManagerJob;
	/** 一级联系人号码 */
	private String firstManagerPhone;
	private String secondManagerName;
	private String secondManagerJob;
	private String secondManagerPhone;
	private String thirdManagerName;
	private String thirdManagerJob;
	private String thirdManagerPhone;
	/** 备注 */
	private String comments;
	
	
	private String mac;

	public HardwareForm() {
		super();
	}
	
	

	public String getMac() {
		return mac;
	}



	public void setMac(String mac) {
		this.mac = mac;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZjm() {
		return zjm;
	}

	public void setZjm(String zjm) {
		this.zjm = zjm;
	}

	public Integer getFwqsx() {
		return fwqsx;
	}

	public void setFwqsx(Integer fwqsx) {
		this.fwqsx = fwqsx;
	}

	public String getYt() {
		return yt;
	}

	public void setYt(String yt) {
		this.yt = yt;
	}

	public String getYu() {
		return yu;
	}

	public void setYu(String yu) {
		this.yu = yu;
	}

	public String getFwqxh() {
		return fwqxh;
	}

	public void setFwqxh(String fwqxh) {
		this.fwqxh = fwqxh;
	}

	public String getSsjf() {
		return ssjf;
	}

	public void setSsjf(String ssjf) {
		this.ssjf = ssjf;
	}

	public String getJg() {
		return jg;
	}

	public void setJg(String jg) {
		this.jg = jg;
	}

	public String getUw() {
		return uw;
	}

	public void setUw(String uw) {
		this.uw = uw;
	}

	public String getCpuConfig() {
		return cpuConfig;
	}

	public void setCpuConfig(String cpuConfig) {
		this.cpuConfig = cpuConfig;
	}

	public String getMemoryConfig() {
		return memoryConfig;
	}

	public void setMemoryConfig(String memoryConfig) {
		this.memoryConfig = memoryConfig;
	}

	public String getYp() {
		return yp;
	}

	public void setYp(String yp) {
		this.yp = yp;
	}

	public String getWk() {
		return wk;
	}

	public void setWk(String wk) {
		this.wk = wk;
	}

	public String getGxk() {
		return gxk;
	}

	public void setGxk(String gxk) {
		this.gxk = gxk;
	}

	public String getDy() {
		return dy;
	}

	public void setDy(String dy) {
		this.dy = dy;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getXlh() {
		return xlh;
	}

	public void setXlh(String xlh) {
		this.xlh = xlh;
	}

	public Date getGmsj() {
		return gmsj;
	}

	public void setGmsj(Date gmsj) {
		this.gmsj = gmsj;
	}

	public Date getGbsj() {
		return gbsj;
	}

	public void setGbsj(Date gbsj) {
		this.gbsj = gbsj;
	}

	public Integer getSynx() {
		return synx;
	}

	public void setSynx(Integer synx) {
		this.synx = synx;
	}

	public String getSdrj() {
		return sdrj;
	}

	public void setSdrj(String sdrj) {
		this.sdrj = sdrj;
	}

	public String getSsjg() {
		return ssjg;
	}

	public void setSsjg(String ssjg) {
		this.ssjg = ssjg;
	}

	public String getSzjip() {
		return szjip;
	}

	public void setSzjip(String szjip) {
		this.szjip = szjip;
	}

	public String getYxxt() {
		return yxxt;
	}

	public void setYxxt(String yxxt) {
		this.yxxt = yxxt;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getFirstManagerName() {
		return firstManagerName;
	}

	public void setFirstManagerName(String firstManagerName) {
		this.firstManagerName = firstManagerName;
	}

	public String getFirstManagerJob() {
		return firstManagerJob;
	}

	public void setFirstManagerJob(String firstManagerJob) {
		this.firstManagerJob = firstManagerJob;
	}

	public String getFirstManagerPhone() {
		return firstManagerPhone;
	}

	public void setFirstManagerPhone(String firstManagerPhone) {
		this.firstManagerPhone = firstManagerPhone;
	}

	public String getSecondManagerName() {
		return secondManagerName;
	}

	public void setSecondManagerName(String secondManagerName) {
		this.secondManagerName = secondManagerName;
	}

	public String getSecondManagerJob() {
		return secondManagerJob;
	}

	public void setSecondManagerJob(String secondManagerJob) {
		this.secondManagerJob = secondManagerJob;
	}

	public String getSecondManagerPhone() {
		return secondManagerPhone;
	}

	public void setSecondManagerPhone(String secondManagerPhone) {
		this.secondManagerPhone = secondManagerPhone;
	}

	public String getThirdManagerName() {
		return thirdManagerName;
	}

	public void setThirdManagerName(String thirdManagerName) {
		this.thirdManagerName = thirdManagerName;
	}

	public String getThirdManagerJob() {
		return thirdManagerJob;
	}

	public void setThirdManagerJob(String thirdManagerJob) {
		this.thirdManagerJob = thirdManagerJob;
	}

	public String getThirdManagerPhone() {
		return thirdManagerPhone;
	}

	public void setThirdManagerPhone(String thirdManagerPhone) {
		this.thirdManagerPhone = thirdManagerPhone;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public HardwareForm(Integer id, String name, String zjm, Integer fwqsx, String yt, String yu, String fwqxh, String ssjf, String jg, String uw, String cpuConfig, String memoryConfig, String yp, String wk, String gxk, String dy, String cc, String xlh, Date gmsj, Date gbsj, Integer synx, String sdrj, String ssjg, String szjip, String yxxt, String ip, String firstManagerName, String firstManagerJob, String firstManagerPhone, String secondManagerName, String secondManagerJob, String secondManagerPhone, String thirdManagerName, String thirdManagerJob, String thirdManagerPhone, String comments,String mac) {
		super();
		this.id = id;
		this.name = name;
		this.zjm = zjm;
		this.fwqsx = fwqsx;
		this.yt = yt;
		this.yu = yu;
		this.fwqxh = fwqxh;
		this.ssjf = ssjf;
		this.jg = jg;
		this.uw = uw;
		this.cpuConfig = cpuConfig;
		this.memoryConfig = memoryConfig;
		this.yp = yp;
		this.wk = wk;
		this.gxk = gxk;
		this.dy = dy;
		this.cc = cc;
		this.xlh = xlh;
		this.gmsj = gmsj;
		this.gbsj = gbsj;
		this.synx = synx;
		this.sdrj = sdrj;
		this.ssjg = ssjg;
		this.szjip = szjip;
		this.yxxt = yxxt;
		this.ip = ip;
		this.firstManagerName = firstManagerName;
		this.firstManagerJob = firstManagerJob;
		this.firstManagerPhone = firstManagerPhone;
		this.secondManagerName = secondManagerName;
		this.secondManagerJob = secondManagerJob;
		this.secondManagerPhone = secondManagerPhone;
		this.thirdManagerName = thirdManagerName;
		this.thirdManagerJob = thirdManagerJob;
		this.thirdManagerPhone = thirdManagerPhone;
		this.comments = comments;
		this.mac=mac;
	}

	public Hardware createHardware(Manager one, Manager two, Manager third) {
		Hardware hardware = new Hardware();
		hardware.setRequestId(id);
		hardware.setName(name);
		hardware.setZjm(zjm);
		hardware.setFwqsx(fwqsx);
		hardware.setYt(yt);
		hardware.setYu(yu);
		hardware.setFwqxh(fwqxh);
		hardware.setSsjf(ssjf);
		hardware.setJg(jg);
		hardware.setUw(uw);
		hardware.setCpuConfig(cpuConfig);
		hardware.setMemoryConfig(memoryConfig);
		hardware.setYp(yp);
		hardware.setWk(wk);
		hardware.setGxk(gxk);
		hardware.setDy(dy);
		hardware.setCc(cc);
		hardware.setXlh(xlh);
		hardware.setGmsj(gmsj);
		hardware.setGbsj(gbsj);
		hardware.setSynx(synx);
		hardware.setSdrj(sdrj);
		hardware.setSsjg(ssjg);
		hardware.setSzjip(szjip);
		hardware.setYxxt(yxxt);
		hardware.setIp(ip);
		hardware.setFirstManager(one.getId());
		hardware.setSecondManager(two.getId());
		hardware.setMac(mac);
		if (null != third) {
			hardware.setThirdManager(third.getId());
		} else {
			hardware.setThirdManager(null);
		}
		hardware.setComments(comments);
		return hardware;
	}

	public void update(Hardware old, Manager one, Manager two, Manager third) {
		old.setName(name);
		old.setZjm(zjm);
		old.setFwqsx(fwqsx);
		old.setYt(yt);
		old.setYu(yu);
		old.setFwqxh(fwqxh);
		old.setSsjf(ssjf);
		old.setJg(jg);
		old.setUw(uw);
		old.setCpuConfig(cpuConfig);
		old.setMemoryConfig(memoryConfig);
		old.setYp(yp);
		old.setWk(wk);
		old.setGxk(gxk);
		old.setDy(dy);
		old.setCc(cc);
		old.setXlh(xlh);
		old.setGmsj(gmsj);
		old.setGbsj(gbsj);
		old.setSynx(synx);
		old.setSdrj(sdrj);
		old.setSsjg(ssjg);
		old.setSzjip(szjip);
		old.setYxxt(yxxt);
		old.setIp(ip);
		old.setFirstManager(one.getId());
		old.setSecondManager(two.getId());
		old.setMac(mac);
		if (null != third) {
			old.setThirdManager(third.getId());
		} else {
			old.setThirdManager(null);
		}
		old.setComments(comments);
	}

}
