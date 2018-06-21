package com.system.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableName;
import com.system.common.model.BaseModel;

/**
 * <p>
 * ${table.comment}
 * </p>
 *
 * @author Yang Yang
 * @since 2018-01-12
 */
@TableName("monitor_alarm_log")
public class MonitorAlarmLog extends BaseModel {

    private static final long serialVersionUID = 1L;

	@TableField("relation_id")
	private Integer relationId;
	@TableField("alarm_time")
	private Date alarmTime;
	private Integer status;
	@TableField("alarm_content")
	private String alarmContent;
	private Integer type;


	public Integer getRelationId() {
		return relationId;
	}

	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}

	public Date getAlarmTime() {
		return alarmTime;
	}

	public void setAlarmTime(Date alarmTime) {
		this.alarmTime = alarmTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAlarmContent() {
		return alarmContent;
	}

	public void setAlarmContent(String alarmContent) {
		this.alarmContent = alarmContent;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
