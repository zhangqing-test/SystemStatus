package com.system.web.form;

import java.io.Serializable;

public class NetQueryForm implements Serializable{


	private static final long serialVersionUID = 1L;

	private String ipName;
	private Integer netStatus;
	private String targetName;
	
	
	public NetQueryForm() {
		super();
	}
	public NetQueryForm(String ipName, Integer netStatus, String targetName) {
		super();
		this.ipName = ipName;
		this.netStatus = netStatus;
		this.targetName = targetName;
	}
	public String getIpName() {
		return ipName;
	}
	public void setIpName(String ipName) {
		this.ipName = ipName;
	}
	public Integer getNetStatus() {
		return netStatus;
	}
	public void setNetStatus(Integer netStatus) {
		this.netStatus = netStatus;
	}
	public String getTargetName() {
		return targetName;
	}
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
	
	
	
	
	
	
	

}
