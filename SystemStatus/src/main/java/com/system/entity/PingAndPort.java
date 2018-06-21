package com.system.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
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
@TableName("ping_and_port")
public class PingAndPort extends BaseModel {

	private static final long serialVersionUID = 1L;

	@TableId
	private Integer id;
	private String ip;
	@TableField("target_ip")
	private String targetIp;
	private String port;
	private Date datetime;
	private Integer lifecycle;
	@TableField("ip_name")
	private String ipName;
	@TableField("target_ip_name")
	private String targetIpName;
	private Boolean message;
	@TableField("net_name")
	private String netName;
	private Integer type;
	@TableField("target_port")
	private String targetPort;
	@TableField("first_manager")
	private Integer firstManager;
	@TableField("second_manager")
	private Integer secondManager;
	@TableField("third_manager")
	private Integer thirdManager;
	private String comments;
	@TableField("request_id")
	private Integer requestId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getTargetIp() {
		return targetIp;
	}

	public void setTargetIp(String targetIp) {
		this.targetIp = targetIp;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public Integer getLifecycle() {
		return lifecycle;
	}

	public void setLifecycle(Integer lifecycle) {
		this.lifecycle = lifecycle;
	}

	public String getIpName() {
		return ipName;
	}

	public void setIpName(String ipName) {
		this.ipName = ipName;
	}

	public String getTargetIpName() {
		return targetIpName;
	}

	public void setTargetIpName(String targetIpName) {
		this.targetIpName = targetIpName;
	}

	public Boolean isMessage() {
		return message;
	}

	public void setMessage(Boolean message) {
		this.message = message;
	}

	public String getNetName() {
		return netName;
	}

	public void setNetName(String netName) {
		this.netName = netName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTargetPort() {
		return targetPort;
	}

	public void setTargetPort(String targetPort) {
		this.targetPort = targetPort;
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

}
