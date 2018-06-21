package com.system.web.vo;

import java.io.Serializable;

public class DeadlockVo implements Serializable {

	private static final long serialVersionUID = 494957817538451882L;

	private String spid;
	private String entityString;
	private String blockedSpid;
	private String code;

	public DeadlockVo(String spid, String entityString, String blockedSpid, String code) {
		super();
		this.spid = spid;
		this.entityString = entityString;
		this.blockedSpid = blockedSpid;
		this.code = code;
	}

	public DeadlockVo() {
		super();
	}

	@Override
	public String toString() {
		return "DeadlockVo [spid=" + spid + ", entityString=" + entityString + ", blockedSpid=" + blockedSpid + ", code=" + code + "]";
	}

	public String getSpid() {
		return spid;
	}

	public void setSpid(String spid) {
		this.spid = spid;
	}

	public String getEntityString() {
		return entityString;
	}

	public void setEntityString(String entityString) {
		this.entityString = entityString;
	}

	public String getBlockedSpid() {
		return blockedSpid;
	}

	public void setBlockedSpid(String blockedSpid) {
		this.blockedSpid = blockedSpid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
