package com.system.webService.form;

import java.io.Serializable;
import java.util.Date;

import com.system.entity.ServicesUpdate;

/**
 * 服务升级表单
 */
public class ServicesUpdateForm implements Serializable {

	private static final long serialVersionUID = -4928458864563110369L;
	/** 标识id */
	private Integer id;
	/** 修改简称 */
	private String name;
	/** 服务id */
	private Integer servicesId;
	/** 修改理由 */
	private String updateContent;
	/** 重要等级 */
	private Integer level;
	/** 计划更新时间 */
	private Date planStartTime;
	/** 更新需要周期/分钟 */
	private Integer planUsedTime;
	/** 是否需要重启服务 */
	private Boolean restart;
	/** 更新步骤和说明 */
	private String updateStep;
	/** 可能会影响到的服务器id(','英文逗号分割) */
	private String hinfluences;
	/** 可能会影响到的服务id(','英文逗号分割) */
	private String sinfluences;
	/** 可能会影响到的ip(','英文逗号分割) */
	private String ninfluences;
	/** 回滚方案或者操作 */
	private String rollback;
	/** 实际更新开始时间 */
	private Date startTime;
	/** 实际更新结束时间 */
	private Date endTime;
	/** 更新备注 */
	private String comments;

	public ServicesUpdateForm() {
		super();
	}

	public ServicesUpdateForm(Integer id, String name, Integer servicesId, String updateContent, Integer level, Date planStartTime, Integer planUsedTime, Boolean restart, String updateStep, String hinfluences, String sinfluences, String ninfluences, String rollback, Date startTime, Date endTime, String comments) {
		super();
		this.id = id;
		this.name = name;
		this.servicesId = servicesId;
		this.updateContent = updateContent;
		this.level = level;
		this.planStartTime = planStartTime;
		this.planUsedTime = planUsedTime;
		this.restart = restart;
		this.updateStep = updateStep;
		this.hinfluences = hinfluences;
		this.sinfluences = sinfluences;
		this.ninfluences = ninfluences;
		this.rollback = rollback;
		this.startTime = startTime;
		this.endTime = endTime;
		this.comments = comments;
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

	public Integer getServicesId() {
		return servicesId;
	}

	public void setServicesId(Integer servicesId) {
		this.servicesId = servicesId;
	}

	public String getUpdateContent() {
		return updateContent;
	}

	public void setUpdateContent(String updateContent) {
		this.updateContent = updateContent;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Date getPlanStartTime() {
		return planStartTime;
	}

	public void setPlanStartTime(Date planStartTime) {
		this.planStartTime = planStartTime;
	}

	public Integer getPlanUsedTime() {
		return planUsedTime;
	}

	public void setPlanUsedTime(Integer planUsedTime) {
		this.planUsedTime = planUsedTime;
	}

	public Boolean getRestart() {
		return restart;
	}

	public void setRestart(Boolean restart) {
		this.restart = restart;
	}

	public String getUpdateStep() {
		return updateStep;
	}

	public void setUpdateStep(String updateStep) {
		this.updateStep = updateStep;
	}

	public String getHinfluences() {
		return hinfluences;
	}

	public void setHinfluences(String hinfluences) {
		this.hinfluences = hinfluences;
	}

	public String getSinfluences() {
		return sinfluences;
	}

	public void setSinfluences(String sinfluences) {
		this.sinfluences = sinfluences;
	}

	public String getNinfluences() {
		return ninfluences;
	}

	public void setNinfluences(String ninfluences) {
		this.ninfluences = ninfluences;
	}

	public String getRollback() {
		return rollback;
	}

	public void setRollback(String rollback) {
		this.rollback = rollback;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "ServicesUpdateForm [id=" + id + ", name=" + name + ", servicesId=" + servicesId + ", updateContent=" + updateContent + ", level=" + level + ", planStartTime=" + planStartTime + ", planUsedTime=" + planUsedTime + ", restart=" + restart + ", updateStep=" + updateStep + ", hinfluences=" + hinfluences + ", sinfluences=" + sinfluences + ", ninfluences=" + ninfluences + ", rollback=" + rollback + ", startTime=" + startTime + ", endTime=" + endTime + ", comments=" + comments + "]";
	}

	public ServicesUpdate getServicesUpdate() {
		ServicesUpdate servicesUpdate = new ServicesUpdate();
		servicesUpdate.setRequestId(id);
		servicesUpdate.setName(name);
		servicesUpdate.setUpdateContent(updateContent);
		servicesUpdate.setLevel(level);
		servicesUpdate.setPlanStartTime(planStartTime);
		servicesUpdate.setPlanUsedTime(planUsedTime);
		servicesUpdate.setRestart(restart);
		servicesUpdate.setUpdateStep(updateStep);
		servicesUpdate.setRollback(rollback);
		servicesUpdate.setStartTime(startTime);
		servicesUpdate.setEndTime(endTime);
		servicesUpdate.setComments(comments);
		servicesUpdate.setDatetime(new Date());
		servicesUpdate.setServicesId(servicesId);
		return servicesUpdate;
	}

	public void update(ServicesUpdate servicesUpdate) {
		servicesUpdate.setName(name);
		servicesUpdate.setUpdateContent(updateContent);
		servicesUpdate.setLevel(level);
		servicesUpdate.setPlanStartTime(planStartTime);
		servicesUpdate.setPlanUsedTime(planUsedTime);
		servicesUpdate.setRestart(restart);
		servicesUpdate.setUpdateStep(updateStep);
		servicesUpdate.setRollback(rollback);
		servicesUpdate.setComments(comments);
		servicesUpdate.setServicesId(servicesId);
		servicesUpdate.setStartTime(startTime);
		servicesUpdate.setEndTime(endTime);
	}

}
