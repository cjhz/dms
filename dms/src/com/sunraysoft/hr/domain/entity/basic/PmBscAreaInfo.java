package com.sunraysoft.hr.domain.entity.basic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sunraysoft.hr.domain.entity.base.BizEntity;
import com.sunraysoft.hr.domain.entity.base.ExportAttr;

/**
 * PmBscAreaInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PM_BSC_AREA_INFO")
@SequenceGenerator(name="sg", sequenceName="SEQ_PM_BSC_AREA_INFO", allocationSize=1)
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class PmBscAreaInfo extends BizEntity implements ExportAttr{

	// Fields

	private Long pid;//id
	private String vcAreaName;//地区名称
	private String vcLevelCode;//级别编码
	private String vcDepartmentCode;//机关编码
	private String vcDepartmentName;//机关名称
	private Long vcDepartmentType;//机关类别
	//private Set<PmBscDeptInfo> pmBscDeptInfos = new HashSet<PmBscDeptInfo>();
	//private Set<PmBscLoginInfo> pmBscLoginInfos = new HashSet<PmBscLoginInfo>();

	// Constructors

	/** default constructor */
	public PmBscAreaInfo() {
	}

	/** minimal constructor */
	public PmBscAreaInfo(Long pid) {
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

	@Column(name = "VC_AREA_NAME")
	public String getVcAreaName() {
		return this.vcAreaName;
	}

	public void setVcAreaName(String vcAreaName) {
		this.vcAreaName = vcAreaName;
	}

	@Column(name = "VC_LEVEL_CODE", length = 20)
	public String getVcLevelCode() {
		return this.vcLevelCode;
	}

	public void setVcLevelCode(String vcLevelCode) {
		this.vcLevelCode = vcLevelCode;
	}

	@Column(name = "VC_DEPARTMENT_CODE", length = 20)
	public String getVcDepartmentCode() {
		return this.vcDepartmentCode;
	}

	public void setVcDepartmentCode(String vcDepartmentCode) {
		this.vcDepartmentCode = vcDepartmentCode;
	}

	@Column(name = "VC_DEPARTMENT_NAME", length = 100)
	public String getVcDepartmentName() {
		return this.vcDepartmentName;
	}

	public void setVcDepartmentName(String vcDepartmentName) {
		this.vcDepartmentName = vcDepartmentName;
	}

	@Column(name = "VC_DEPARTMENT_TYPE", precision = 2, scale = 0)
	public Long getVcDepartmentType() {
		return this.vcDepartmentType;
	}

	public void setVcDepartmentType(Long vcDepartmentType) {
		this.vcDepartmentType = vcDepartmentType;
	}

	/* (non-Javadoc)
	 * @see com.sunraysoft.hr.domain.entity.base.ExportAttr#expString()
	 */
	@Override
	public String expString() {
		return vcAreaName;
	}
	
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pmBscAreaInfo")
//	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
//		org.hibernate.annotations.CascadeType.DELETE,
//        org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
//	public Set<PmBscDeptInfo> getPmBscDeptInfos() {
//		return this.pmBscDeptInfos;
//	}
//
//	public void setPmBscDeptInfos(Set<PmBscDeptInfo> pmBscDeptInfos) {
//		this.pmBscDeptInfos = pmBscDeptInfos;
//	}
//
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pmBscAreaInfo")
//	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
//		org.hibernate.annotations.CascadeType.DELETE,
//        org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
//	public Set<PmBscLoginInfo> getPmBscLoginInfos() {
//		return this.pmBscLoginInfos;
//	}
//
//	public void setPmBscLoginInfos(Set<PmBscLoginInfo> pmBscLoginInfos) {
//		this.pmBscLoginInfos = pmBscLoginInfos;
//	}


}