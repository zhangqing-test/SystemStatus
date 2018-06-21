package com.system.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.system.common.model.BaseModel;

/**
 * <p>
 * ${table.comment}
 * </p>
 *
 * @author Yang Yang
 * @since 2018-01-12
 */
public class Manager extends BaseModel {

	private static final long serialVersionUID = 1L;

	@TableId
	private Integer id;
	@TableField("manager_name")
	private String managerName;
	@TableField("manager_phone")
	private String managerPhone;
	@TableField("manager_job")
	private String managerJob;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public String getManagerJob() {
		return managerJob;
	}

	public void setManagerJob(String managerJob) {
		this.managerJob = managerJob;
	}

}
