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
 * PmBscObserType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PM_BSC_OBSER_TYPE")
@SequenceGenerator(name="sg", sequenceName="SEQ_PM_BSC_OBSER_TYPE", allocationSize=1)
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class PmBscObserType extends BizEntity implements ExportAttr{

	// Fields

	private Long pid;
	private String vcName;
	private Long vcType;
	private Set<PmBscObserving> pmBscObservings = new HashSet<PmBscObserving>();

	// Constructors

	/** default constructor */
	public PmBscObserType() {
	}

	/** minimal constructor */
	public PmBscObserType(Long pid) {
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

	@Column(name = "VC_NAME", length = 50)
	public String getVcName() {
		return this.vcName;
	}

	public void setVcName(String vcName) {
		this.vcName = vcName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pmBscObserType")
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
		org.hibernate.annotations.CascadeType.DELETE,
        org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	public Set<PmBscObserving> getPmBscObservings() {
		return this.pmBscObservings;
	}

	public void setPmBscObservings(Set<PmBscObserving> pmBscObservings) {
		this.pmBscObservings = pmBscObservings;
	}

	
	@Column(name = "VC_TYPE")
	public Long getVcType() {
		return vcType;
	}
	
	public void setVcType(Long vcType) {
		this.vcType = vcType;
	}

	/* (non-Javadoc)
	 * @see com.sunraysoft.hr.domain.entity.base.ExportAttr#expString()
	 */
	@Override
	public String expString() {
		return vcName;
	}
	
}