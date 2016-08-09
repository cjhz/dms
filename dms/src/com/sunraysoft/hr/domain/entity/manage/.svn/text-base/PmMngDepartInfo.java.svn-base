package com.sunraysoft.hr.domain.entity.manage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.sunraysoft.hr.domain.entity.base.BizEntity;

/**
 * PmMngDepartInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PM_MNG_DEPART_INFO", uniqueConstraints = {
		@UniqueConstraint(columnNames = "VCDEPARTMENTCODE"),
		@UniqueConstraint(columnNames = "VCDEPARTMENTNAME") })
public class PmMngDepartInfo extends BizEntity {

	// Fields

	private Long idepartmentid;//机关编号
	private String vcdepartmentcode;//机构编码
	private String vclevelcode;//级别编码
	private String vcdepartmentname;//机关名称
	//private String vcessdepartmentname;//公安局
	private Long numdepartmenttype;//机关类型
	//private String vcdepartmentabbreviation;//机关简称
	//private String vcauditinglevel;//审核等级
	//private String bdeleteflag;//删除标记
	private String vcareainfo;//所属地区
	private Long numsort;//显示顺序
	//private String vcproxydepartmentname;//发证机关
	//private String vcreconsiderationname;//行政复议机关

	// Constructors

	/** default constructor */
	public PmMngDepartInfo() {
	}

	/** minimal constructor */
	public PmMngDepartInfo(Long idepartmentid) {
		this.idepartmentid = idepartmentid;
	}

	// Property accessors
//	@Id
//	@Column(name = "IDEPARTMENTID", unique = true, nullable = false, precision = 10, scale = 0)
//	public Long getIdepartmentid() {
//		return this.idepartmentid;
//	}
//
//	public void setIdepartmentid(Long idepartmentid) {
//		this.idepartmentid = idepartmentid;
//	}

	@Column(name = "VCDEPARTMENTCODE", unique = true, length = 20)
	public String getVcdepartmentcode() {
		return this.vcdepartmentcode;
	}

	public void setVcdepartmentcode(String vcdepartmentcode) {
		this.vcdepartmentcode = vcdepartmentcode;
	}

	@Column(name = "VCLEVELCODE", length = 20)
	public String getVclevelcode() {
		return this.vclevelcode;
	}

	public void setVclevelcode(String vclevelcode) {
		this.vclevelcode = vclevelcode;
	}

	@Column(name = "VCDEPARTMENTNAME", unique = true, length = 50)
	public String getVcdepartmentname() {
		return this.vcdepartmentname;
	}

	public void setVcdepartmentname(String vcdepartmentname) {
		this.vcdepartmentname = vcdepartmentname;
	}

//	@Column(name = "VCESSDEPARTMENTNAME", length = 50)
//	public String getVcessdepartmentname() {
//		return this.vcessdepartmentname;
//	}
//
//	public void setVcessdepartmentname(String vcessdepartmentname) {
//		this.vcessdepartmentname = vcessdepartmentname;
//	}
//
//	@Column(name = "VCDEPARTMENTABBREVIATION", length = 20)
//	public String getVcdepartmentabbreviation() {
//		return this.vcdepartmentabbreviation;
//	}
//
//	public void setVcdepartmentabbreviation(String vcdepartmentabbreviation) {
//		this.vcdepartmentabbreviation = vcdepartmentabbreviation;
//	}
//
//	@Column(name = "VCAUDITINGLEVEL", length = 20)
//	public String getVcauditinglevel() {
//		return this.vcauditinglevel;
//	}
//
//	public void setVcauditinglevel(String vcauditinglevel) {
//		this.vcauditinglevel = vcauditinglevel;
//	}
//
//	@Column(name = "BDELETEFLAG", length = 1)
//	public String getBdeleteflag() {
//		return this.bdeleteflag;
//	}
//
//	public void setBdeleteflag(String bdeleteflag) {
//		this.bdeleteflag = bdeleteflag;
//	}

	@Column(name = "VCAREAINFO", length = 200)
	public String getVcareainfo() {
		return this.vcareainfo;
	}

	public void setVcareainfo(String vcareainfo) {
		this.vcareainfo = vcareainfo;
	}

	@Column(name = "NUMSORT", precision = 22, scale = 0)
	public Long getNumsort() {
		return this.numsort;
	}

	public void setNumsort(Long numsort) {
		this.numsort = numsort;
	}


//	@Column(name = "VCPROXYDEPARTMENTNAME", length = 50)
//	public String getVcproxydepartmentname() {
//		return this.vcproxydepartmentname;
//	}
//
//	public void setVcproxydepartmentname(String vcproxydepartmentname) {
//		this.vcproxydepartmentname = vcproxydepartmentname;
//	}
//
//
//
//	@Column(name = "VCRECONSIDERATIONNAME", length = 50)
//	public String getVcreconsiderationname() {
//		return this.vcreconsiderationname;
//	}
//
//	public void setVcreconsiderationname(String vcreconsiderationname) {
//		this.vcreconsiderationname = vcreconsiderationname;
//	}

	@Column(name = "NUMDEPARTMENTTYPE")
	public Long getNumdepartmenttype() {
		return numdepartmenttype;
	}

	public void setNumdepartmenttype(Long numdepartmenttype) {
		this.numdepartmenttype = numdepartmenttype;
	}

	@Id
	@Column(name = "IDEPARTMENTID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getPid() {
		return idepartmentid;
	}
	public void setPid(Long pid) {
		this.idepartmentid = pid;
	}
}