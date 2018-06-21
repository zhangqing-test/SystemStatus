package com.system.web.vo;

import java.io.Serializable;

public class InfoPageVo implements Serializable {

	private static final long serialVersionUID = -8917627178437669795L;

	private Integer id;

	private String ip;

	private String name;

	private Integer status;

	private Integer cpu;

	private Integer memory;

	private Integer spaceMax;

	private Integer memoryUsed;

	private Integer memoryTotal;

	private String space;

	private Integer order;

	private Boolean message;
	
	private String portStatus;

	public InfoPageVo() {
		super();
	}

	public InfoPageVo(Integer id, String ip, String name, Integer status, Integer cpu, Integer memory, Integer spaceMax, Integer memoryUsed, Integer memoryTotal, String space, Integer order, boolean message,String portStatus) {
		super();
		this.id = id;
		this.ip = ip;
		this.name = name;
		this.status = status;
		this.cpu = cpu;
		this.memory = memory;
		this.spaceMax = spaceMax;
		this.memoryUsed = memoryUsed;
		this.memoryTotal = memoryTotal;
		this.space = space;
		this.order = order;
		this.message = message;
		this.portStatus=portStatus;
	}

	@Override
	public String toString() {
		return "InfoPageVo [id=" + id + ", ip=" + ip + ", name=" + name + ", status=" + status + ", cpu=" + cpu + ", memory=" + memory + ", spaceMax=" + spaceMax + ", memoryUsed=" + memoryUsed + ", memoryTotal=" + memoryTotal + ", space=" + space + ", order=" + order + ", message=" + message + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCpu() {
		return cpu;
	}

	public void setCpu(Integer cpu) {
		this.cpu = cpu;
	}

	public Integer getMemory() {
		return memory;
	}

	public void setMemory(Integer memory) {
		this.memory = memory;
	}

	public Integer getSpaceMax() {
		return spaceMax;
	}

	public void setSpaceMax(Integer spaceMax) {
		this.spaceMax = spaceMax;
	}

	public Integer getMemoryUsed() {
		return memoryUsed;
	}

	public void setMemoryUsed(Integer memoryUsed) {
		this.memoryUsed = memoryUsed;
	}

	public Integer getMemoryTotal() {
		return memoryTotal;
	}

	public void setMemoryTotal(Integer memoryTotal) {
		this.memoryTotal = memoryTotal;
	}

	public String getSpace() {
		return space;
	}

	public void setSpace(String space) {
		this.space = space;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public boolean isMessage() {
		return message;
	}

	public void setMessage(Boolean message) {
		this.message = message;
	}
	
	

	public Boolean getMessage() {
		return message;
	}

	public String getPortStatus() {
		return portStatus;
	}

	public void setPortStatus(String portStatus) {
		this.portStatus = portStatus;
	}
	
	

}
