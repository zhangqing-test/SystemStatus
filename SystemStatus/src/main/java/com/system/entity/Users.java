package com.system.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.system.common.model.BaseModel;

/**
 * 
 * @author zhangyou
 *
 */
@TableName("users")
public class Users extends BaseModel {

	private static final long serialVersionUID = 1L;

	@TableId
	private Integer id;
	@TableField("user_name")
	private String userName;
	@TableField("user_pwd")
	private String userPwd;
	@TableField("user_dept")
	private String userDept;

	private String phone;

	@TableField("user_code")
	private String userCode;
	@TableField("user_email")
	private String userEmail;
	@TableField("auth")
	private String auth;

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	private Date datetime;

	public Users() {
		super();
	}

	public Users(Integer id, String userName, String userPwd, String userDept, String phone, String userCode, String userEmail, Date datetime, String auth) {
		super();
		this.id = id;
		this.userName = userName;
		this.userPwd = userPwd;
		this.userDept = userDept;
		this.phone = phone;
		this.userCode = userCode;
		this.userEmail = userEmail;
		this.datetime = datetime;
		this.auth = auth;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserDept() {
		return userDept;
	}

	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

}
