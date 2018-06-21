package com.system.web.form;

import java.io.Serializable;

public class ServerQueryForm implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private String ip;
	private String name;
	private Integer cpuMin;
	private Integer cpuMax;
	private Integer memoryMin;
	private Integer memoryMax;
	private Integer spaceMin;
	private Integer spaceMax;
	
	
	
	public ServerQueryForm() {
		super();
	}
	public ServerQueryForm(String ip, String name, Integer cpuMin,
			Integer cpuMax, Integer memoryMin, Integer memoryMax,
			Integer spaceMin, Integer spaceMax) {
		super();
		this.ip = ip;
		this.name = name;
		this.cpuMin = cpuMin;
		this.cpuMax = cpuMax;
		this.memoryMin = memoryMin;
		this.memoryMax = memoryMax;
		this.spaceMin = spaceMin;
		this.spaceMax = spaceMax;
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
	public Integer getCpuMin() {
		return cpuMin;
	}
	public void setCpuMin(Integer cpuMin) {
		this.cpuMin = cpuMin;
	}
	public Integer getCpuMax() {
		return cpuMax;
	}
	public void setCpuMax(Integer cpuMax) {
		this.cpuMax = cpuMax;
	}
	public Integer getMemoryMin() {
		return memoryMin;
	}
	public void setMemoryMin(Integer memoryMin) {
		this.memoryMin = memoryMin;
	}
	public Integer getMemoryMax() {
		return memoryMax;
	}
	public void setMemoryMax(Integer memoryMax) {
		this.memoryMax = memoryMax;
	}
	public Integer getSpaceMin() {
		return spaceMin;
	}
	public void setSpaceMin(Integer spaceMin) {
		this.spaceMin = spaceMin;
	}
	public Integer getSpaceMax() {
		return spaceMax;
	}
	public void setSpaceMax(Integer spaceMax) {
		this.spaceMax = spaceMax;
	}
	
	
	

}
