package com.system.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.system.common.model.BaseModel;

@TableName("process_info")
public class ProcessMsg extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 12L;

	@TableField("sid")
	Integer sid;

	@TableField("pid")
	Integer pid;

	@TableField("name")
	String name;

	@TableField("user")
	String user;

	@TableField("memSize")
	String memSize;

	@TableField("memUse")
	String memUse;

	public ProcessMsg(Integer sid, Integer pid, String name, String user, String memSize, String memUse) {
		super();
		this.sid = sid;
		this.pid = pid;
		this.name = name;
		this.user = user;
		this.memSize = memSize;
		this.memUse = memUse;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getMemSize() {
		return memSize;
	}

	public void setMemSize(String memSize) {
		this.memSize = memSize;
	}

	public String getMemUse() {
		return memUse;
	}

	public void setMemUse(String memUse) {
		this.memUse = memUse;
	}

}
