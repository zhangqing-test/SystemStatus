package com.system.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("account_info")
public class Account {

	private String uid;
	@TableField("services")
	private String services;
	@TableField("hardware_account")
	private String hardware_account;
	@TableField("service_account")
	private String service_account;
	@TableField("name")
	private String name;
	@TableField("level")
	private String level;
	@TableField("type")
	private String type;
	@TableField("comment")
	private String comment;
	@TableField("create_time")
	private Date time;
	@TableField("week")
	private Integer week;
	@TableField("isgroup")
	private boolean group;
	@TableField("isremote")
	private boolean remote;
	@TableField("expire")
	private Integer day;
	@TableField("first_manager")
	private Integer firstManager;
	@TableField("second_manager")
	private Integer secondManager;
	@TableField("third_manager")
	private Integer thirdManager;
	@TableField("note")
	private String note;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public String getHardware_account() {
		return hardware_account;
	}

	public void setHardware_account(String hardware_account) {
		this.hardware_account = hardware_account;
	}

	public String getService_account() {
		return service_account;
	}

	public void setService_account(String service_account) {
		this.service_account = service_account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getWeek() {
		return week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public boolean isGroup() {
		return group;
	}

	public void setGroup(boolean group) {
		this.group = group;
	}

	public boolean isRemote() {
		return remote;
	}

	public void setRemote(boolean remote) {
		this.remote = remote;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getFirstManager() {
		return firstManager;
	}

	public void setFirstManager(Integer firstManager) {
		this.firstManager = firstManager;
	}

	public Integer getSecondManager() {
		return secondManager;
	}

	public void setSecondManager(Integer secondManager) {
		this.secondManager = secondManager;
	}

	public Integer getThirdManager() {
		return thirdManager;
	}

	public void setThirdManager(Integer thirdManager) {
		this.thirdManager = thirdManager;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
