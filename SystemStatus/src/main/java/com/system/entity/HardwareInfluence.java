package com.system.entity;

import com.baomidou.mybatisplus.annotations.TableField;
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
@TableName("hardware_influence")
public class HardwareInfluence extends BaseModel {

    private static final long serialVersionUID = 1L;

	@TableField("relation_id")
	private Integer relationId;
	@TableField("hardware_id")
	private Integer hardwareId;
	private Integer type;


	public Integer getRelationId() {
		return relationId;
	}

	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}

	public Integer getHardwareId() {
		return hardwareId;
	}

	public void setHardwareId(Integer hardwareId) {
		this.hardwareId = hardwareId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
