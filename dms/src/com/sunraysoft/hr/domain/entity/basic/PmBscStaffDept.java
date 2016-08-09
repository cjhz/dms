package com.sunraysoft.hr.domain.entity.basic;

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
 * PmBscStaffDept entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PM_BSC_STAFF_DEPT")
@SequenceGenerator(name="sg", sequenceName="SEQ_PM_BSC_STAFF_DEPT", allocationSize=1)
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class PmBscStaffDept extends BizEntity {

	// Fields

	private Long pid;
	private String vcDepartmentCode;
	private PmBscDeptInfo pmBscDeptInfo = new PmBscDeptInfo();
	private PmBscStaffInfo pmBscStaffInfo = new PmBscStaffInfo();

	// Constructors

	/** default constructor */
	public PmBscStaffDept() {
	}

	/** minimal constructor */
	public PmBscStaffDept(Long pid) {
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
	@JoinColumn(name = "VC_DEPT_ID")
	public PmBscDeptInfo getPmBscDeptInfo() {
		return this.pmBscDeptInfo;
	}

	public void setPmBscDeptInfo(PmBscDeptInfo pmBscDeptInfo) {
		this.pmBscDeptInfo = pmBscDeptInfo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VC_STAFF_ID")
	public PmBscStaffInfo getPmBscStaffInfo() {
		return this.pmBscStaffInfo;
	}

	public void setPmBscStaffInfo(PmBscStaffInfo pmBscStaffInfo) {
		this.pmBscStaffInfo = pmBscStaffInfo;
	}
	
	@Column(name = "VC_DEPARTMENT_CODE")
	public String getVcDepartmentCode() {
		return vcDepartmentCode;
	}

	public void setVcDepartmentCode(String vcDepartmentCode) {
		this.vcDepartmentCode = vcDepartmentCode;
	}
	
}