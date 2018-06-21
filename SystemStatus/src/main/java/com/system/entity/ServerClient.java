package com.system.entity;

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
@TableName("server_client")
public class ServerClient extends BaseModel {

	private static final long serialVersionUID = 1L;

	@TableId
	private Integer id;
	private String ip;
	private String name;
	@TableField("server_status")
	private Integer serverStatus;
	@TableField("build_status")
	private Boolean buildStatus;
	@TableField("restart_status")
	private Boolean restartStatus;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getServerStatus() {
		return serverStatus;
	}

	public void setServerStatus(Integer serverStatus) {
		this.serverStatus = serverStatus;
	}

	public Boolean isBuildStatus() {
		return buildStatus;
	}

	public void setBuildStatus(Boolean buildStatus) {
		this.buildStatus = buildStatus;
	}

	public Boolean isRestartStatus() {
		return restartStatus;
	}

	public void setRestartStatus(Boolean restartStatus) {
		this.restartStatus = restartStatus;
	}

}
