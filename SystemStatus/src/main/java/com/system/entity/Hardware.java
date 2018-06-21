package com.system.entity;

import java.util.Date;

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
public class Hardware extends BaseModel {

	private static final long serialVersionUID = 1L;

	@TableId
	private Integer id;
	private String name;
	private String zjm;
	private Integer fwqsx;
	private String yt;
	private String yu;
	private String fwqxh;
	private String ssjf;
	private String jg;
	private String uw;
	@TableField("cpu_config")
	private String cpuConfig;
	@TableField("memory_config")
	private String memoryConfig;
	private String yp;
	private String wk;
	private String gxk;
	private String dy;
	private String cc;
	private String xlh;
	private Date gmsj;
	private Date gbsj;
	private Integer synx;
	private String sdrj;
	private String ssjg;
	private String szjip;
	private String ip;
	@TableField("first_manager")
	private Integer firstManager;
	@TableField("second_manager")
	private Integer secondManager;
	@TableField("third_manager")
	private Integer thirdManager;
	private String comments;
	@TableField("request_id")
	private Integer requestId;
	private String yxxt;
	private String mac;
	
	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
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

	public String getZjm() {
		return zjm;
	}

	public void setZjm(String zjm) {
		this.zjm = zjm;
	}

	public Integer getFwqsx() {
		return fwqsx;
	}

	public void setFwqsx(Integer fwqsx) {
		this.fwqsx = fwqsx;
	}

	public String getYt() {
		return yt;
	}

	public void setYt(String yt) {
		this.yt = yt;
	}

	public String getYu() {
		return yu;
	}

	public void setYu(String yu) {
		this.yu = yu;
	}

	public String getFwqxh() {
		return fwqxh;
	}

	public void setFwqxh(String fwqxh) {
		this.fwqxh = fwqxh;
	}

	public String getSsjf() {
		return ssjf;
	}

	public void setSsjf(String ssjf) {
		this.ssjf = ssjf;
	}

	public String getJg() {
		return jg;
	}

	public void setJg(String jg) {
		this.jg = jg;
	}

	public String getUw() {
		return uw;
	}

	public void setUw(String uw) {
		this.uw = uw;
	}

	public String getCpuConfig() {
		return cpuConfig;
	}

	public void setCpuConfig(String cpuConfig) {
		this.cpuConfig = cpuConfig;
	}

	public String getMemoryConfig() {
		return memoryConfig;
	}

	public void setMemoryConfig(String memoryConfig) {
		this.memoryConfig = memoryConfig;
	}

	public String getYp() {
		return yp;
	}

	public void setYp(String yp) {
		this.yp = yp;
	}

	public String getWk() {
		return wk;
	}

	public void setWk(String wk) {
		this.wk = wk;
	}

	public String getGxk() {
		return gxk;
	}

	public void setGxk(String gxk) {
		this.gxk = gxk;
	}

	public String getDy() {
		return dy;
	}

	public void setDy(String dy) {
		this.dy = dy;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getXlh() {
		return xlh;
	}

	public void setXlh(String xlh) {
		this.xlh = xlh;
	}

	public Date getGmsj() {
		return gmsj;
	}

	public void setGmsj(Date gmsj) {
		this.gmsj = gmsj;
	}

	public Date getGbsj() {
		return gbsj;
	}

	public void setGbsj(Date gbsj) {
		this.gbsj = gbsj;
	}

	public Integer getSynx() {
		return synx;
	}

	public void setSynx(Integer synx) {
		this.synx = synx;
	}

	public String getSdrj() {
		return sdrj;
	}

	public void setSdrj(String sdrj) {
		this.sdrj = sdrj;
	}

	public String getSsjg() {
		return ssjg;
	}

	public void setSsjg(String ssjg) {
		this.ssjg = ssjg;
	}

	public String getSzjip() {
		return szjip;
	}

	public void setSzjip(String szjip) {
		this.szjip = szjip;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
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

	public String getYxxt() {
		return yxxt;
	}

	public void setYxxt(String yxxt) {
		this.yxxt = yxxt;
	}

}
