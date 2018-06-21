package com.system.web.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UpdateInfoVo implements Serializable {

	private static final long serialVersionUID = 494957817538451882L;

	private Integer id;
	private String name;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date time;
	private String updateInfo;
	private String fluence;

	public UpdateInfoVo() {
		super();
	}

	public UpdateInfoVo(Integer id, String name, Date time,String updateInfo, String fluence) {
		super();
		this.id = id;
		this.name = name;
		this.time = time;
		this.updateInfo=updateInfo;
		this.fluence = fluence;
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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	
	
	public String getUpdateInfo() {
		return updateInfo;
	}

	public void setUpdateInfo(String updateInfo) {
		this.updateInfo = updateInfo;
	}

	public String getFluence() {
		return fluence;
	}

	public void setFluence(String fluence) {
		this.fluence = fluence;
	}

	

	@Override
	public String toString() {
		return "UpdateInfoVo [id=" +id + ", name=" + name + ", time=" + time +",updateInfo= "+updateInfo+", fluence=" + fluence + "]";
	}
	
}
