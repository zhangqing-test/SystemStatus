package com.system.webService.form;

import java.util.Date;
import java.util.UUID;

import com.system.common.model.BaseModel;
import com.system.entity.Account;
import com.system.entity.Manager;

public class AccountForm extends BaseModel {

	/** 软件服务名称(null) */
	private String rjfwmc;
	/** 硬件账号名 */
	private String yjzhm;
	/** 软件账号名 */
	private String rjzhm;
	/** 服务器名称 */
	private String fwqmc;
	/** 重要等级 */
	private String zydj;
	/** 新增账号类型 */
	private String xzzhlx;
	/** 新增账号说明（必） */
	private String xzzhsm;
	/** 新增账号时间（必） */
	private Date xzzhrq;
	/** 周期 */
	private Integer zq;
	/** 是否加入超级管理员组 */
	private boolean sfxyjrcjglyz;
	/** 是否开通远程访问权限 */
	private boolean sfxyktycfwqx;
	/** 使用期限（必） */
	private Integer syqx;
	/** 一级责任人（必） */
	private String yjzrr;
	/** 二级责任人（必） */
	private String ejzrr;
	/** 三级责任人 */
	private String sjzrr;
	/** 一级责任人职位 */
	private String yjzrrzw;
	/** 二级责任人职位 */
	private String ejzrrzw;
	/** 三级责任人职位 */
	private String sjzrrzw;
	/** 一级责任人电话 */
	private String yjzrrdh;
	/** 二级责任人电话 */
	private String ejzrrdh;
	/** 三级责任人电话 */
	private String sjzrrdh;
	/** 备注说明 */
	private String bzsm;

	public String getRjfwmc() {
		return rjfwmc;
	}

	public void setRjfwmc(String rjfwmc) {
		this.rjfwmc = rjfwmc;
	}

	public String getYjzhm() {
		return yjzhm;
	}

	public void setYjzhm(String yjzhm) {
		this.yjzhm = yjzhm;
	}

	public String getRjzhm() {
		return rjzhm;
	}

	public void setRjzhm(String rjzhm) {
		this.rjzhm = rjzhm;
	}

	public String getFwqmc() {
		return fwqmc;
	}

	public void setFwqmc(String fwqmc) {
		this.fwqmc = fwqmc;
	}

	public String getZydj() {
		return zydj;
	}

	public void setZydj(String zydj) {
		this.zydj = zydj;
	}

	public String getXzzhlx() {
		return xzzhlx;
	}

	public void setXzzhlx(String xzzhlx) {
		this.xzzhlx = xzzhlx;
	}

	public String getXzzhsm() {
		return xzzhsm;
	}

	public void setXzzhsm(String xzzhsm) {
		this.xzzhsm = xzzhsm;
	}

	public Date getXzzhrq() {
		return xzzhrq;
	}

	public void setXzzhrq(Date xzzhrq) {
		this.xzzhrq = xzzhrq;
	}

	public Integer getZq() {
		return zq;
	}

	public void setZq(Integer zq) {
		this.zq = zq;
	}

	public boolean isSfxyjrcjglyz() {
		return sfxyjrcjglyz;
	}

	public void setSfxyjrcjglyz(boolean sfxyjrcjglyz) {
		this.sfxyjrcjglyz = sfxyjrcjglyz;
	}

	public boolean isSfxyktycfwqx() {
		return sfxyktycfwqx;
	}

	public void setSfxyktycfwqx(boolean sfxyktycfwqx) {
		this.sfxyktycfwqx = sfxyktycfwqx;
	}

	public Integer getSyqx() {
		return syqx;
	}

	public void setSyqx(Integer syqx) {
		this.syqx = syqx;
	}

	public String getYjzrr() {
		return yjzrr;
	}

	public void setYjzrr(String yjzrr) {
		this.yjzrr = yjzrr;
	}

	public String getEjzrr() {
		return ejzrr;
	}

	public void setEjzrr(String ejzrr) {
		this.ejzrr = ejzrr;
	}

	public String getSjzrr() {
		return sjzrr;
	}

	public void setSjzrr(String sjzrr) {
		this.sjzrr = sjzrr;
	}

	public String getYjzrrzw() {
		return yjzrrzw;
	}

	public void setYjzrrzw(String yjzrrzw) {
		this.yjzrrzw = yjzrrzw;
	}

	public String getEjzrrzw() {
		return ejzrrzw;
	}

	public void setEjzrrzw(String ejzrrzw) {
		this.ejzrrzw = ejzrrzw;
	}

	public String getSjzrrzw() {
		return sjzrrzw;
	}

	public void setSjzrrzw(String sjzrrzw) {
		this.sjzrrzw = sjzrrzw;
	}

	public String getYjzrrdh() {
		return yjzrrdh;
	}

	public void setYjzrrdh(String yjzrrdh) {
		this.yjzrrdh = yjzrrdh;
	}

	public String getEjzrrdh() {
		return ejzrrdh;
	}

	public void setEjzrrdh(String ejzrrdh) {
		this.ejzrrdh = ejzrrdh;
	}

	public String getSjzrrdh() {
		return sjzrrdh;
	}

	public void setSjzrrdh(String sjzrrdh) {
		this.sjzrrdh = sjzrrdh;
	}

	public String getBzsm() {
		return bzsm;
	}

	public void setBzsm(String bzsm) {
		this.bzsm = bzsm;
	}

	public AccountForm() {
		super();
	}

	public AccountForm(String rjfwmc, String yjzhm, String rjzhm, String fwqmc, String zydj, String xzzhlx, String xzzhsm, Date xzzhrq, Integer zq, boolean sfxyjrcjglyz, boolean sfxyktycfwqx, Integer syqx, String yjzrr, String ejzrr, String sjzrr, String yjzrrzw, String ejzrrzw, String sjzrrzw, String yjzrrdh, String ejzrrdh, String sjzrrdh, String bzsm) {
		super();
		this.rjfwmc = rjfwmc;
		this.yjzhm = yjzhm;
		this.rjzhm = rjzhm;
		this.fwqmc = fwqmc;
		this.zydj = zydj;
		this.xzzhlx = xzzhlx;
		this.xzzhsm = xzzhsm;
		this.xzzhrq = xzzhrq;
		this.zq = zq;
		this.sfxyjrcjglyz = sfxyjrcjglyz;
		this.sfxyktycfwqx = sfxyktycfwqx;
		this.syqx = syqx;
		this.yjzrr = yjzrr;
		this.ejzrr = ejzrr;
		this.sjzrr = sjzrr;
		this.yjzrrzw = yjzrrzw;
		this.ejzrrzw = ejzrrzw;
		this.sjzrrzw = sjzrrzw;
		this.yjzrrdh = yjzrrdh;
		this.ejzrrdh = ejzrrdh;
		this.sjzrrdh = sjzrrdh;
		this.bzsm = bzsm;
	}

	public Account createAccount(Manager one, Manager two, Manager three) {
		Account account = new Account();
		account.setUid(UUID.randomUUID().toString().replaceAll("-", ""));
		account.setServices(rjfwmc);
		account.setHardware_account(yjzhm);
		account.setService_account(rjzhm);
		account.setName(fwqmc);
		account.setType(xzzhlx);
		account.setComment(xzzhsm);
		account.setTime(xzzhrq);
		account.setWeek(zq);
		account.setGroup(sfxyjrcjglyz);
		account.setRemote(sfxyktycfwqx);
		account.setDay(syqx);
		account.setFirstManager(one.getId());
		account.setSecondManager(two.getId());
		if (three != null) {
			account.setThirdManager(three.getId());
		} else {
			account.setThirdManager(null);
		}
		account.setNote(bzsm);
		return account;
	}
}
