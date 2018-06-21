package com.system.webService.form;

import java.io.Serializable;
import java.util.Date;

import com.system.entity.NetUpdate;

/**
 * 网络升级表单
 */
public class NetUpdateForm implements Serializable {

	private static final long serialVersionUID = 3833576458603349383L;
	/** 标识id */
	private Integer id;
	/** 网络监控id */
	private Integer netId;
	/** 修改简称 */
	private String name;
	/** 重要等级 */
	private Integer level;
	/** 更新缘由 */
	private String updateReason;
	/** 计划更新时间 */
	private Date planStartTime;
	/** 更新需要周期/分钟 */
	private Integer planUsedTime;
	/** 是否需要断网 */
	private Boolean netOff;
	/** 更新步骤和说明 */
	private String updateStep;
	/** 回滚方案或者操作 */
	private String rollback;
	/** 实际更新开始时间 */
	private Date startTime;
	/** 实际更新结束时间 */
	private Date endTime;
	/** 更新备注 */
	private String comments;
	/** 可能会影响到的服务器id(','英文逗号分割) */
	private String hinfluences;
	/** 可能会影响到的服务id(','英文逗号分割) */
	private String sinfluences;
	/** 可能会影响到的ip(','英文逗号分割) */
	private String ninfluences;

	@Override
	public String toString() {
		return "NetUpdateForm [id=" + id + ", netId=" + netId + ", name=" + name + ", level=" + level + ", updateReason=" + updateReason + ", planStartTime=" + planStartTime + ", planUsedTime=" + planUsedTime + ", netOff=" + netOff + ", updateStep=" + updateStep + ", rollback=" + rollback + ", startTime=" + startTime + ", endTime=" + endTime + ", comments=" + comments + ", hinfluences=" + hinfluences + ", sinfluences=" + sinfluences + ", ninfluences=" + ninfluences + "]";
	}

	public NetUpdateForm(Integer id, Integer netId, String name, Integer level, String updateReason, Date planStartTime, Integer planUsedTime, Boolean netOff, String updateStep, String rollback, Date startTime, Date endTime, String comments, String hinfluences, String sinfluences, String ninfluences) {
		super();
		this.id = id;
		this.netId = netId;
		this.name = name;
		this.level = level;
		this.updateReason = updateReason;
		this.planStartTime = planStartTime;
		this.planUsedTime = planUsedTime;
		this.netOff = netOff;
		this.updateStep = updateStep;
		this.rollback = rollback;
		this.startTime = startTime;
		this.endTime = endTime;
		this.comments = comments;
		this.hinfluences = hinfluences;
		this.sinfluences = sinfluences;
		this.ninfluences = ninfluences;
	}

	public NetUpdateForm() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNetId() {
		return netId;
	}

	public void setNetId(Integer netId) {
		this.netId = netId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getUpdateReason() {
		return updateReason;
	}

	public void setUpdateReason(String updateReason) {
		this.updateReason = updateReason;
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

	public Boolean getNetOff() {
		return netOff;
	}

	public void setNetOff(Boolean netOff) {
		this.netOff = netOff;
	}

	public String getUpdateStep() {
		return updateStep;
	}

	public void setUpdateStep(String updateStep) {
		this.updateStep = updateStep;
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

	public NetUpdate getNetUpdate() {
		NetUpdate netUpdate = new NetUpdate();
		netUpdate.setRequestId(id);
		netUpdate.setName(name);
		netUpdate.setLevel(level);
		netUpdate.setUpdateReason(updateReason);
		netUpdate.setPlanStartTime(planStartTime);
		netUpdate.setPlanUsedTime(planUsedTime);
		netUpdate.setNetOff(netOff);
		netUpdate.setUpdateStep(updateStep);
		netUpdate.setRollback(rollback);
		netUpdate.setStartTime(startTime);
		netUpdate.setEndTime(endTime);
		netUpdate.setComments(comments);
		netUpdate.setDatetime(new Date());
		netUpdate.setNetId(netId);
		return netUpdate;
	}

	public void update(NetUpdate netUpdate) {
		netUpdate.setName(name);
		netUpdate.setLevel(level);
		netUpdate.setUpdateReason(updateReason);
		netUpdate.setPlanStartTime(planStartTime);
		netUpdate.setPlanUsedTime(planUsedTime);
		netUpdate.setNetOff(netOff);
		netUpdate.setUpdateStep(updateStep);
		netUpdate.setRollback(rollback);
		netUpdate.setComments(comments);
		netUpdate.setNetId(netId);
		netUpdate.setStartTime(startTime);
		netUpdate.setEndTime(endTime);
	}

}
