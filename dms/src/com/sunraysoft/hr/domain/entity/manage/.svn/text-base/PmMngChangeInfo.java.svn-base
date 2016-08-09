package com.sunraysoft.hr.domain.entity.manage;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sunraysoft.hr.domain.entity.base.BizEntity;
import com.sunraysoft.hr.domain.entity.basic.PmBscStaffInfo;

/**
 * PmMngChangeInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PM_MNG_CHANGE_INFO")
@SequenceGenerator(name="sg", sequenceName="SEQ_PM_MNG_CHANGE_INFO", allocationSize=1)
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class PmMngChangeInfo extends BizEntity {

	// Fields

	private Long pid;
	private PmBscStaffInfo pmBscStaffInfo;
	private String vcNowDept;
	private String vcChgDept;
	private String vcBpersonProp;
	private String vcEpersonProp;
	private String vcNowDeptName;
	private String vcChgDeptName;
	private String vcBpersonPropName;
	private String vcEpersonPropName;
	private Date vcChangeDate;
	private String vcRemark;

	// Constructors

	/** default constructor */
	public PmMngChangeInfo() {
	}

	/** minimal constructor */
	public PmMngChangeInfo(Long pid) {
		this.pid = pid;
	}

	// Property accessors
	@Id
	@Column(name = "PID", unique = true, nullable = false, precision = 20, scale = 0)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sg")
	public Long getPid() {
		return this.pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VC_STAFF_ID")
	public PmBscStaffInfo getPmBscStaffInfo() {
		return this.pmBscStaffInfo;
	}

	public void setPmBscStaffInfo(PmBscStaffInfo pmBscStaffInfo) {
		this.pmBscStaffInfo = pmBscStaffInfo;
	}

	@Column(name = "VC_NOW_DEPT", length = 100)
	public String getVcNowDept() {
		return this.vcNowDept;
	}

	public void setVcNowDept(String vcNowDept) {
		this.vcNowDept = vcNowDept;
	}

	@Column(name = "VC_CHG_DEPT", length = 100)
	public String getVcChgDept() {
		return this.vcChgDept;
	}

	public void setVcChgDept(String vcChgDept) {
		this.vcChgDept = vcChgDept;
	}

	@Column(name = "VC_BPERSON_PROP", length = 100)
	public String getVcBpersonProp() {
		return this.vcBpersonProp;
	}

	public void setVcBpersonProp(String vcBpersonProp) {
		this.vcBpersonProp = vcBpersonProp;
	}

	@Column(name = "VC_EPERSON_PROP", length = 100)
	public String getVcEpersonProp() {
		return this.vcEpersonProp;
	}

	public void setVcEpersonProp(String vcEpersonProp) {
		this.vcEpersonProp = vcEpersonProp;
	}

	@Column(name = "VC_CHANGE_DATE")
	public Date getVcChangeDate() {
		return this.vcChangeDate;
	}

	public void setVcChangeDate(Date vcChangeDate) {
		this.vcChangeDate = vcChangeDate;
	}

	@Column(name = "VC_REMARK")
	public String getVcRemark() {
		return vcRemark;
	}

	public void setVcRemark(String vcRemark) {
		this.vcRemark = vcRemark;
	}

	@Column(name = "VC_NOW_DEPT_NAME", length = 100)
	public String getVcNowDeptName() {
		return vcNowDeptName;
	}

	public void setVcNowDeptName(String vcNowDeptName) {
		this.vcNowDeptName = vcNowDeptName;
	}

	@Column(name = "VC_CHG_DEPT_NAME", length = 100)
	public String getVcChgDeptName() {
		return vcChgDeptName;
	}

	public void setVcChgDeptName(String vcChgDeptName) {
		this.vcChgDeptName = vcChgDeptName;
	}

	@Column(name = "VC_BPERSON_PROP_NAME", length = 100)
	public String getVcBpersonPropName() {
		return vcBpersonPropName;
	}

	public void setVcBpersonPropName(String vcBpersonPropName) {
		this.vcBpersonPropName = vcBpersonPropName;
	}

	@Column(name = "VC_EPERSON_PROP_NAME", length = 100)
	public String getVcEpersonPropName() {
		return vcEpersonPropName;
	}

	public void setVcEpersonPropName(String vcEpersonPropName) {
		this.vcEpersonPropName = vcEpersonPropName;
	}
	
}