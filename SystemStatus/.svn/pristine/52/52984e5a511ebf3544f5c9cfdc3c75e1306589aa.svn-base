package com.system.webService.form;

import java.io.Serializable;
import java.util.Date;

import com.system.entity.HardwareUpdate;

/**
 * 硬件升级表单
 */
public class HardwareUpdateForm implements Serializable {

	private static final long serialVersionUID = -8985581078954580674L;

	/** 标识id */
	private Integer id;
	/** 修改简称 */
	private String name;
	/** 服务器id */
	private Integer hardwareId;
	/** 重要等级 */
	private Integer level;
	/** 修改理由 */
	private String updateReason;
	/** 计划更新时间 */
	private Date planStartTime;
	/** 更新需要周期/分钟 */
	private Integer planUsedTime;
	/** 是否需要断网 */
	private Boolean netOff;
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

	public HardwareUpdateForm() {
		super();
	}

	public HardwareUpdateForm(Integer id, String name, Integer hardwareId, Integer level, String updateReason, Date planStartTime, Integer planUsedTime, Boolean netOff, String updateStep, String hinfluences, String sinfluences, String ninfluences, String rollback, Date startTime, Date endTime, String comments) {
		super();
		this.id = id;
		this.name = name;
		this.hardwareId = hardwareId;
		this.level = level;
		this.updateReason = updateReason;
		this.planStartTime = planStartTime;
		this.planUsedTime = planUsedTime;
		this.netOff = netOff;
		this.updateStep = updateStep;
		this.hinfluences = hinfluences;
		this.sinfluences = sinfluences;
		this.ninfluences = ninfluences;
		this.rollback = rollback;
		this.startTime = startTime;
		this.endTime = endTime;
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "HardwareUpdateForm [id=" + id + ", name=" + name + ", hardwareId=" + hardwareId + ", level=" + level + ", updateReason=" + updateReason + ", planStartTime=" + planStartTime + ", planUsedTime=" + planUsedTime + ", netOff=" + netOff + ", updateStep=" + updateStep + ", hinfluences=" + hinfluences + ", sinfluences=" + sinfluences + ", nInfluences=" + ninfluences + ", rollback=" + rollback + ", startTime=" + startTime + ", endTime=" + endTime + ", comments=" + comments + "]";
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

	public Integer getHardwareId() {
		return hardwareId;
	}

	public void setHardwareId(Integer hardwareId) {
		this.hardwareId = hardwareId;
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

	public HardwareUpdate getHardwareUpdate() {
		HardwareUpdate temp = new HardwareUpdate();
		temp.setRequestId(id);
		temp.setName(name);
		temp.setLevel(level);
		temp.setUpdateReason(updateReason);
		temp.setPlanStartTime(planStartTime);
		temp.setPlanUsedTime(planUsedTime);
		temp.setNetOff(netOff);
		temp.setUpdateStep(updateStep);
		temp.setRollback(rollback);
		temp.setStartTime(startTime);
		temp.setEndTime(endTime);
		temp.setComments(comments);
		temp.setDatetime(new Date());
		temp.setHardwareId(hardwareId);
		return temp;
	}

	public void update(HardwareUpdate hardwareUpdate) {
		hardwareUpdate.setName(name);
		hardwareUpdate.setLevel(level);
		hardwareUpdate.setUpdateReason(updateReason);
		hardwareUpdate.setPlanStartTime(planStartTime);
		hardwareUpdate.setPlanUsedTime(planUsedTime);
		hardwareUpdate.setNetOff(netOff);
		hardwareUpdate.setUpdateStep(updateStep);
		hardwareUpdate.setRollback(rollback);
		hardwareUpdate.setStartTime(startTime);
		hardwareUpdate.setEndTime(endTime);
		hardwareUpdate.setComments(comments);
		hardwareUpdate.setHardwareId(hardwareId);
		hardwareUpdate.setStartTime(startTime);
		hardwareUpdate.setEndTime(endTime);
	}

}
