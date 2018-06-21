package com.system.web.vo;

import java.io.Serializable;
import java.util.Date;

import com.system.entity.HardwareUpdate;
import com.system.entity.NetUpdate;
import com.system.entity.ServicesUpdate;

public class UpdateHistoryVo implements Serializable {

	private static final long serialVersionUID = -1209904688741024025L;

	private Integer id;
	private String name;
	private String updateContent;
	private String ip;
	private Integer level;
	private Date planStartTime;
	private Integer planUsedTime;
	private String updateStepContent;
	private String rollback;
	private Date startTime;
	private Date endTime;
	private String comments;
	private Date datetime;
	private Boolean restartService;
	private Boolean netOff;
	private String hinfluence;
	private String sinfluence;
	private String ninfluence;
	private String updateName;

	public UpdateHistoryVo() {
		super();
	}

	@Override
	public String toString() {
		return "UpdateHistoryVo [id=" + id + ", name=" + name + ", updateContent=" + updateContent + ", ip=" + ip + ", level=" + level + ", planStartTime=" + planStartTime + ", planUsedTime=" + planUsedTime + ", updateStepContent=" + updateStepContent + ", rollback=" + rollback + ", startTime=" + startTime + ", endTime=" + endTime + ", comments=" + comments + ", datetime=" + datetime + ", restartService=" + restartService + ", netOff=" + netOff + "]";
	}

	public UpdateHistoryVo(Integer id, String name, String updateContent, String ip, Integer level, Date planStartTime, Integer planUsedTime, String updateStepContent, String rollback, Date startTime, Date endTime, String comments, Date datetime, Boolean restartService, Boolean netOff) {
		super();
		this.id = id;
		this.name = name;
		this.updateContent = updateContent;
		this.ip = ip;
		this.level = level;
		this.planStartTime = planStartTime;
		this.planUsedTime = planUsedTime;
		this.updateStepContent = updateStepContent;
		this.rollback = rollback;
		this.startTime = startTime;
		this.endTime = endTime;
		this.comments = comments;
		this.datetime = datetime;
		this.restartService = restartService;
		this.netOff = netOff;
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

	public String getUpdateContent() {
		return updateContent;
	}

	public void setUpdateContent(String updateContent) {
		this.updateContent = updateContent;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
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

	public String getUpdateStepContent() {
		return updateStepContent;
	}

	public void setUpdateStepContent(String updateStepContent) {
		this.updateStepContent = updateStepContent;
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

	public Boolean getRestartService() {
		return restartService;
	}

	public void setRestartService(Boolean restartService) {
		this.restartService = restartService;
	}

	public Boolean getNetOff() {
		return netOff;
	}

	public void setNetOff(Boolean netOff) {
		this.netOff = netOff;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getHinfluence() {
		return hinfluence;
	}

	public void setHinfluence(String hinfluence) {
		this.hinfluence = hinfluence;
	}

	public String getSinfluence() {
		return sinfluence;
	}

	public void setSinfluence(String sinfluence) {
		this.sinfluence = sinfluence;
	}

	public String getNinfluence() {
		return ninfluence;
	}

	public void setNinfluence(String ninfluence) {
		this.ninfluence = ninfluence;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public void init(Object obj, String _name, String _ip, String his, String sis, String nis) {
		if (obj instanceof HardwareUpdate) {
			HardwareUpdate hardwareUpdate = (HardwareUpdate) obj;
			this.id = hardwareUpdate.getId();
			this.name = hardwareUpdate.getName();
			this.updateName = _name;
			this.ip = _ip;
			this.updateContent = hardwareUpdate.getUpdateReason();
			this.level = hardwareUpdate.getLevel();
			this.planStartTime = hardwareUpdate.getPlanStartTime();
			this.planUsedTime = hardwareUpdate.getPlanUsedTime();
			this.updateStepContent = hardwareUpdate.getUpdateStep();
			this.rollback = hardwareUpdate.getRollback();
			this.startTime = hardwareUpdate.getStartTime();
			this.endTime = hardwareUpdate.getEndTime();
			this.comments = hardwareUpdate.getComments();
			this.datetime = hardwareUpdate.getDatetime();
			this.netOff = hardwareUpdate.isNetOff();
			this.hinfluence = his;
			this.sinfluence = sis;
			this.ninfluence = nis;
		}

		if (obj instanceof ServicesUpdate) {
			ServicesUpdate servicesUpdate = (ServicesUpdate) obj;
			this.id = servicesUpdate.getId();
			this.name = servicesUpdate.getName();
			this.updateName = _name;
			this.ip = _ip;
			this.updateContent = servicesUpdate.getUpdateContent();
			this.level = servicesUpdate.getLevel();
			this.planStartTime = servicesUpdate.getPlanStartTime();
			this.planUsedTime = servicesUpdate.getPlanUsedTime();
			this.updateStepContent = servicesUpdate.getUpdateStep();
			this.rollback = servicesUpdate.getRollback();
			this.startTime = servicesUpdate.getStartTime();
			this.endTime = servicesUpdate.getEndTime();
			this.comments = servicesUpdate.getComments();
			this.datetime = servicesUpdate.getDatetime();
			this.restartService = servicesUpdate.isRestart();
			this.hinfluence = his;
			this.sinfluence = sis;
			this.ninfluence = nis;
		}

		if (obj instanceof NetUpdate) {
			NetUpdate netUpdate = (NetUpdate) obj;
			this.id = netUpdate.getId();
			this.name = netUpdate.getName();
			this.updateName = _name;
			this.ip = _ip;
			this.updateContent = netUpdate.getUpdateReason();
			this.level = netUpdate.getLevel();
			this.planStartTime = netUpdate.getPlanStartTime();
			this.planUsedTime = netUpdate.getPlanUsedTime();
			this.updateStepContent = netUpdate.getUpdateStep();
			this.rollback = netUpdate.getRollback();
			this.startTime = netUpdate.getStartTime();
			this.endTime = netUpdate.getEndTime();
			this.comments = netUpdate.getComments();
			this.datetime = netUpdate.getDatetime();
			this.netOff = netUpdate.isNetOff();
			this.hinfluence = his;
			this.sinfluence = sis;
			this.ninfluence = nis;
		}

	}

}
