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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sunraysoft.hr.domain.entity.base.BizEntity;

/**
 * PmMngOper entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PM_MNG_OPER")
@SequenceGenerator(name="sg", sequenceName="SEQ_PM_MNG_OPER", allocationSize=1)
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class PmMngOper extends BizEntity {

	// Fields

	private Long pid;
	private PmMngMenu pmMngMenu;
	private String vcOperName;
	private String vcOperAction;
	private String vcMemo;
	private Set<PmMngRoleOper> pmMngRoleOpers = new HashSet<PmMngRoleOper>();

	// Constructors

	/** default constructor */
	public PmMngOper() {
	}

	/** minimal constructor */
	public PmMngOper(Long pid) {
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
	@JoinColumn(name = "VC_MENU_ID")
	public PmMngMenu getPmMngMenu() {
		return this.pmMngMenu;
	}

	public void setPmMngMenu(PmMngMenu pmMngMenu) {
		this.pmMngMenu = pmMngMenu;
	}

	@Column(name = "VC_OPER_NAME")
	public String getVcOperName() {
		return this.vcOperName;
	}

	public void setVcOperName(String vcOperName) {
		this.vcOperName = vcOperName;
	}

	@Column(name = "VC_OPER_ACTION")
	public String getVcOperAction() {
		return this.vcOperAction;
	}

	public void setVcOperAction(String vcOperAction) {
		this.vcOperAction = vcOperAction;
	}


	@Column(name = "VC_MEMO", length = 1024)
	public String getVcMemo() {
		return this.vcMemo;
	}

	public void setVcMemo(String vcMemo) {
		this.vcMemo = vcMemo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pmMngOper")
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