package com.sunraysoft.hr.domain.entity.basic;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sunraysoft.hr.domain.entity.base.BizEntity;
import com.sunraysoft.hr.domain.entity.base.ExportAttr;

/**
 * PmBscDeptType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PM_BSC_DEPT_TYPE")
@SequenceGenerator(name="sg", sequenceName="SEQ_PM_BSC_DEPT_TYPE", allocationSize=1)
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class PmBscDeptType extends BizEntity implements ExportAttr{

	// Fields

	private Long pid;
	private String vcTypeName;
	private String vcDeptProp;
	private Set<PmBscDeptInfo> pmBscDeptInfos = new HashSet<PmBscDeptInfo>();

	// Constructors

	/** default constructor */
	public PmBscDeptType() {
	}

	/** minimal constructor */
	public PmBscDeptType(Long pid) {
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

	@Column(name = "VC_TYPE_NAME")
	public String getVcTypeName() {
		return this.vcTypeName;
	}

	public void setVcTypeName(String vcTypeName) {
		this.vcTypeName = vcTypeName;
	}


	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pmBscDeptType")
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
		org.hibernate.annotations.CascadeType.DELETE,
        org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	public Set<PmBscDeptInfo> getPmBscDeptInfos() {
		return this.pmBscDeptInfos;
	}

	public void setPmBscDeptInfos(Set<PmBscDeptInfo> pmBscDeptInfos) {
		this.pmBscDeptInfos = pmBscDeptInfos;
	}

	@Column(name = "VC_DEPT_PROP")
	public String getVcDeptProp() {
		return vcDeptProp;
	}

	public void setVcDeptProp(String vcDeptProp) {
		this.vcDeptProp = vcDeptProp;
	}

	/* (non-Javadoc)
	 * @see com.sunraysoft.hr.domain.entity.base.ExportAttr#expString()
	 */
	@Override
	public String expString() {
		return vcTypeName;
	}
	
}