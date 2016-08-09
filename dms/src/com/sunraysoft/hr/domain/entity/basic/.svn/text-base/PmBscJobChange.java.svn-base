package com.sunraysoft.hr.domain.entity.basic;

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

/**
 * PmBscJobChange entity. @author MyEclipse Persistence Tools
 */

@Entity
@Table(name = "PM_BSC_JOB_CHANGE")
@SequenceGenerator(name="sg", sequenceName="SEQ_PM_BSC_JOB_CHANGE", allocationSize=1)
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class PmBscJobChange extends BizEntity{

	// Fields

	private Long pid;
	private PmBscStaffInfo pmBscStaffInfo;
	private String vcNowJob;
	private String vcChgJob;
	private Date vcNowDate;
	private Date vcChgDate;
	private String vcRemark;
	private Long vcIfedit;
	private Date vcEndDate;
	//private Date vcCreateDate;
	// Constructors

	/** default constructor */
	public PmBscJobChange() {
	}

	/** minimal constructor */
	public PmBscJobChange(Long pid) {
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

	@Column(name = "VC_NOW_JOB", precision = 20, scale = 0)
	public String getVcNowJob() {
		return this.vcNowJob;
	}

	public void setVcNowJob(String vcNowJob) {
		this.vcNowJob = vcNowJob;
	}

	@Column(name = "VC_CHG_JOB", precision = 20, scale = 0)
	public String getVcChgJob() {
		return this.vcChgJob;
	}

	public void setVcChgJob(String vcChgJob) {
		this.vcChgJob = vcChgJob;
	}

	@Column(name = "VC_NOW_DATE")
	public Date getVcNowDate() {
		return this.vcNowDate;
	}

	public void setVcNowDate(Date vcNowDate) {
		this.vcNowDate = vcNowDate;
	}

	@Column(name = "VC_CHG_DATE")
	public Date getVcChgDate() {
		return this.vcChgDate;
	}

	public void setVcChgDate(Date vcChgDate) {
		this.vcChgDate = vcChgDate;
	}

	@Column(name = "VC_REMARK", length = 1000)
	public String getVcRemark() {
		return this.vcRemark;
	}

	public void setVcRemark(String vcRemark) {
		this.vcRemark = vcRemark;
	}
	
	@Column(name = "VC_IFEDIT")
	public Long getVcIfedit() {
		return vcIfedit;
	}

	public void setVcIfedit(Long vcIfedit) {
		this.vcIfedit = vcIfedit;
	}

	@Column(name = "VC_END_DATE")
	public Date getVcEndDate() {
		return vcEndDate;
	}

	public void setVcEndDate(Date vcEndDate) {
		this.vcEndDate = vcEndDate;
	}
//	@Column(name = "VC_CREATE_DATE")
//	public Date getVcCreateDate() {
//		return vcCreateDate;
//	}
//
//	public void setVcCreateDate(Date vcCreateDate) {
//		this.vcCreateDate = vcCreateDate;
//	}
}