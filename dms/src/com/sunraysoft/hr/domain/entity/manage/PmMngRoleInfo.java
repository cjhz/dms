package com.sunraysoft.hr.domain.entity.manage;

import java.math.BigDecimal;
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
import com.sunraysoft.hr.domain.entity.basic.PmBscLoginInfo;

/**
 * PmMngRoleInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PM_MNG_ROLE_INFO")
@SequenceGenerator(name="sg", sequenceName="SEQ_PM_MNG_ROLE_INFO", allocationSize=1)
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)

public class PmMngRoleInfo extends BizEntity {
	// Fields

	private Long pid;
	private String vcRoleName;
	private String vcMemo;
	private Set<PmBscLoginInfo> pmBscLoginInfos = new HashSet<PmBscLoginInfo>();
	private Set<PmMngRoleOper> pmMngRoleOpers = new HashSet<PmMngRoleOper>();

	// Constructors

	/** default constructor */
	public PmMngRoleInfo() {
	}

	/** minimal constructor */
	public PmMngRoleInfo(Long pid) {
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

	@Column(name = "VC_ROLE_NAME")
	public String getVcRoleName() {
		return this.vcRoleName;
	}

	public void setVcRoleName(String vcRoleName) {
		this.vcRoleName = vcRoleName;
	}

	@Column(name = "VC_MEMO", length = 1024)
	public String getVcMemo() {
		return this.vcMemo;
	}

	public void setVcMemo(String vcMemo) {
		this.vcMemo = vcMemo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pmMngRoleInfo")
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
		org.hibernate.annotations.CascadeType.DELETE,
        org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	public Set<PmBscLoginInfo> getPmBscLoginInfos() {
		return this.pmBscLoginInfos;
	}

	public void setPmBscLoginInfos(Set<PmBscLoginInfo> pmBscLoginInfos) {
		this.pmBscLoginInfos = pmBscLoginInfos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pmMngRoleInfo")
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
		org.hibernate.annotations.CascadeType.DELETE,
        org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	public Set<PmMngRoleOper> getPmMngRoleOpers() {
		return this.pmMngRoleOpers;
	}

	public void setPmMngRoleOpers(Set<PmMngRoleOper> pmMngRoleOpers) {
		this.pmMngRoleOpers = pmMngRoleOpers;
	}

}