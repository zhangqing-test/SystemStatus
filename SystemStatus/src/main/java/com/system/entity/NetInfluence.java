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
@TableName("net_influence")
public class NetInfluence extends BaseModel {

    private static final long serialVersionUID = 1L;

	@TableField("relation_id")
	private Integer relationId;
	private String ip;
	private Integer type;


	public Integer getRelationId() {
		return relationId;
	}

	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
