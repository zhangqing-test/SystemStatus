package com.system.web.form;

import java.io.Serializable;

public class UpdateHistoryForm implements Serializable {

	private static final long serialVersionUID = 5552419778992065655L;

	private Integer type;

	private String name;

	private String ip;

	public UpdateHistoryForm() {
		super();
	}

	public UpdateHistoryForm(Integer type, String name, String ip) {
		super();
		this.type = type;
		this.name = name;
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "UpdateHistoryForm [type=" + type + ", name=" + name + ", ip=" + ip + "]";
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
