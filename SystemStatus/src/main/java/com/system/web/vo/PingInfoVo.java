package com.system.web.vo;

import java.io.Serializable;
import java.util.Date;

import com.system.entity.PingAndPort;
import com.system.entity.PingAndPortInfo;

public class PingInfoVo implements Serializable {

	private static final long serialVersionUID = 6100752416295353312L;

	private Integer id;
	private String targetIp;
	private String ip;
	private Integer lifecycle;
	private Date datetime;
	private String ipName;
	private String targetIpName;
	private Boolean message;
	private Integer type;

	private Boolean one_pingStatus;
	private String one_portStatus;
	private Integer one_pingMiss;
	private Integer one_min;
	private Integer one_max;
	private Integer one_avg;

	private Boolean two_pingStatus;
	private String two_portStatus;
	private Integer two_pingMiss;
	private Integer two_min;
	private Integer two_max;
	private Integer two_avg;

	public PingInfoVo() {
		super();
	}

	public PingInfoVo(PingAndPort pingAndPort) {
		this.id = pingAndPort.getId();
		this.ip = pingAndPort.getIp();
		this.targetIp = pingAndPort.getTargetIp();
		this.lifecycle = pingAndPort.getLifecycle();
		this.datetime = pingAndPort.getDatetime();
		this.ipName = pingAndPort.getIpName();
		this.targetIpName = pingAndPort.getTargetIpName();
		this.message = pingAndPort.isMessage();
		this.type = pingAndPort.getType();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTargetIp() {
		return targetIp;
	}

	public void setTargetIp(String targetIp) {
		this.targetIp = targetIp;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getLifecycle() {
		return lifecycle;
	}

	public void setLifecycle(Integer lifecycle) {
		this.lifecycle = lifecycle;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
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

	public Boolean getMessage() {
		return message;
	}

	public void setMessage(Boolean message) {
		this.message = message;
	}

	public Boolean getOne_pingStatus() {
		return one_pingStatus;
	}

	public void setOne_pingStatus(Boolean one_pingStatus) {
		this.one_pingStatus = one_pingStatus;
	}

	public String getOne_portStatus() {
		return one_portStatus;
	}

	public void setOne_portStatus(String one_portStatus) {
		this.one_portStatus = one_portStatus;
	}

	public Integer getOne_pingMiss() {
		return one_pingMiss;
	}

	public void setOne_pingMiss(Integer one_pingMiss) {
		this.one_pingMiss = one_pingMiss;
	}

	public Integer getOne_min() {
		return one_min;
	}

	public void setOne_min(Integer one_min) {
		this.one_min = one_min;
	}

	public Integer getOne_max() {
		return one_max;
	}

	public void setOne_max(Integer one_max) {
		this.one_max = one_max;
	}

	public Integer getOne_avg() {
		return one_avg;
	}

	public void setOne_avg(Integer one_avg) {
		this.one_avg = one_avg;
	}

	public Boolean getTwo_pingStatus() {
		return two_pingStatus;
	}

	public void setTwo_pingStatus(Boolean two_pingStatus) {
		this.two_pingStatus = two_pingStatus;
	}

	public String getTwo_portStatus() {
		return two_portStatus;
	}

	public void setTwo_portStatus(String two_portStatus) {
		this.two_portStatus = two_portStatus;
	}

	public Integer getTwo_pingMiss() {
		return two_pingMiss;
	}

	public void setTwo_pingMiss(Integer two_pingMiss) {
		this.two_pingMiss = two_pingMiss;
	}

	public Integer getTwo_min() {
		return two_min;
	}

	public void setTwo_min(Integer two_min) {
		this.two_min = two_min;
	}

	public Integer getTwo_max() {
		return two_max;
	}

	public void setTwo_max(Integer two_max) {
		this.two_max = two_max;
	}

	public Integer getTwo_avg() {
		return two_avg;
	}

	public void setTwo_avg(Integer two_avg) {
		this.two_avg = two_avg;
	}

	public void getInfo(PingAndPortInfo pingAndPortInfo) {
		if (pingAndPortInfo.getType() == 0) {
			this.one_pingStatus = pingAndPortInfo.isPingStatus();
			String portStatus = pingAndPortInfo.getPortStatus();
			if (portStatus != null) {
				this.one_portStatus = portStatus.replaceAll("\"", "'");
			}
			this.one_pingMiss = pingAndPortInfo.getPingMiss();
			this.one_min = pingAndPortInfo.getPingMin();
			this.one_max = pingAndPortInfo.getPingMax();
			this.one_avg = pingAndPortInfo.getPingAvg();
		} else {
			this.two_pingStatus = pingAndPortInfo.isPingStatus();
			String portStatus = pingAndPortInfo.getPortStatus();
			if (portStatus != null) {
				this.two_portStatus = portStatus.replaceAll("\"", "'");
			}
			this.two_pingMiss = pingAndPortInfo.getPingMiss();
			this.two_min = pingAndPortInfo.getPingMin();
			this.two_max = pingAndPortInfo.getPingMax();
			this.two_avg = pingAndPortInfo.getPingAvg();
		}
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
