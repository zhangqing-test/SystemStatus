package com.system.webService.form;

import java.io.Serializable;

import com.system.entity.Manager;
import com.system.entity.PingAndPort;

/**
 * 网络表单
 */
public class NetForm implements Serializable {

	private static final long serialVersionUID = 6743721247305272912L;

	/** 标识id */
	private Integer id;
	/** 线路名称 */
	private String netName;
	/** 主ip */
	private String ip;
	/** 主ip名称 */
	private String ipName;
	/** 副ip */
	private String targetIp;
	/** 副ip名称 */
	private String targetIpName;
	/** 网络策略 */
	private Integer type;
	/** 主ip端口 */
	private String ipPort;
	/** 副ip端口 */
	private String targetIpPort;
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

	public NetForm() {
		super();
	}

	@Override
	public String toString() {
		return "NetForm [id=" + id + ", netName=" + netName + ", ip=" + ip + ", ipName=" + ipName + ", targetIp=" + targetIp + ", targetIpName=" + targetIpName + ", type=" + type + ", ipPort=" + ipPort + ", targetIpPort=" + targetIpPort + ", firstManagerName=" + firstManagerName + ", firstManagerJob=" + firstManagerJob + ", firstManagerPhone=" + firstManagerPhone + ", secondManagerName=" + secondManagerName + ", secondManagerJob=" + secondManagerJob + ", secondManagerPhone=" + secondManagerPhone + ", thirdManagerName=" + thirdManagerName + ", thirdManagerJob=" + thirdManagerJob + ", thirdManagerPhone=" + thirdManagerPhone + ", comments=" + comments + "]";
	}

	public NetForm(Integer id, String netName, String ip, String ipName, String targetIp, String targetIpName, Integer type, String ipPort, String targetIpPort, String firstManagerName, String firstManagerJob, String firstManagerPhone, String secondManagerName, String secondManagerJob, String secondManagerPhone, String thirdManagerName, String thirdManagerJob, String thirdManagerPhone, String comments) {
		super();
		this.id = id;
		this.netName = netName;
		this.ip = ip;
		this.ipName = ipName;
		this.targetIp = targetIp;
		this.targetIpName = targetIpName;
		this.type = type;
		this.ipPort = ipPort;
		this.targetIpPort = targetIpPort;
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
	}

	public String getNetName() {
		return netName;
	}

	public void setNetName(String netName) {
		this.netName = netName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getTargetIp() {
		return targetIp;
	}

	public void setTargetIp(String targetIp) {
		this.targetIp = targetIp;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getIpPort() {
		return ipPort;
	}

	public void setIpPort(String ipPort) {
		this.ipPort = ipPort;
	}

	public String getTargetIpPort() {
		return targetIpPort;
	}

	public void setTargetIpPort(String targetIpPort) {
		this.targetIpPort = targetIpPort;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIpName() {
		return ipName;
	}

	public void setIpName(String ipName) {
		this.ipName = ipName;
	}

	public String getTargetIpName() {
		return targetIpName;
	}

	public void setTargetIpName(String targetIpName) {
		this.targetIpName = targetIpName;
	}

	/**
	 * 创建pingAndPort对象
	 * 
	 * @param one
	 * @param two
	 * @param third
	 * @return
	 */
	public PingAndPort createNet(Manager one, Manager two, Manager third) {
		PingAndPort pingAndPort = new PingAndPort();
		pingAndPort.setIp(ip);
		pingAndPort.setTargetIp(targetIp);
		pingAndPort.setPort(ipPort);
		pingAndPort.setDatetime(null);
		pingAndPort.setLifecycle(1);
		pingAndPort.setIpName(ipName);
		pingAndPort.setTargetIpName(targetIpName);
		pingAndPort.setMessage(true);
		pingAndPort.setNetName(netName);
		pingAndPort.setType(type + 1);
		pingAndPort.setTargetPort(targetIpPort);
		pingAndPort.setFirstManager(one.getId());
		pingAndPort.setSecondManager(two.getId());
		if (null != third) {
			pingAndPort.setThirdManager(third.getId());
		} else {
			pingAndPort.setThirdManager(null);
		}
		pingAndPort.setComments(comments);
		pingAndPort.setRequestId(id);
		return pingAndPort;
	}

	public void update(PingAndPort old, Manager one, Manager two, Manager third) {
		old.setIp(ip);
		old.setTargetIp(targetIp);
		old.setPort(ipPort);
		old.setLifecycle(1);
		old.setIpName(ipName);
		old.setTargetIpName(targetIpName);
		old.setMessage(true);
		old.setNetName(netName);
		old.setType(type + 1);
		old.setTargetPort(targetIpPort);
		old.setFirstManager(one.getId());
		old.setSecondManager(two.getId());
		if (null != third) {
			old.setThirdManager(third.getId());
		} else {
			old.setThirdManager(null);
		}
		old.setComments(comments);
	}

}
