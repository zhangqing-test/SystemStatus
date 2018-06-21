package com.system.quartz.bean;

import java.io.Serializable;

public class SpidEntity implements Serializable {

	private static final long serialVersionUID = 8376967324466324110L;

	private String spid;
	private String loginname;
	private String cmd;
	private String hostname;
	private String program_name;
	private String lastBatch;

	public SpidEntity() {
		super();
	}

	public SpidEntity(String spid, String loginname, String cmd, String hostname, String program_name, String lastBatch) {
		super();
		this.spid = spid;
		this.loginname = loginname;
		this.cmd = cmd;
		this.hostname = hostname;
		this.program_name = program_name;
		this.lastBatch = lastBatch;
	}

	@Override
	public String toString() {
		return "SpidEntity [spid=" + spid + ", loginname=" + loginname + ", cmd=" + cmd + ", hostname=" + hostname + ", program_name=" + program_name + ", lastBatch=" + lastBatch + "]";
	}

	public String getSpid() {
		return spid;
	}

	public void setSpid(String spid) {
		this.spid = spid;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getProgram_name() {
		return program_name;
	}

	public void setProgram_name(String program_name) {
		this.program_name = program_name;
	}

	public String getLastBatch() {
		return lastBatch;
	}

	public void setLastBatch(String lastBatch) {
		this.lastBatch = lastBatch;
	}
}
