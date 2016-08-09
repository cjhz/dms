package com.sunraysoft.hr.domain.entity.manage;

import java.math.BigDecimal;
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
 * PmMngRoleOper entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PM_MNG_ROLE_OPER")
@SequenceGenerator(name="sg", sequenceName="SEQ_PM_MNG_ROLE_OPER", allocationSize=1)
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class PmMngRoleOper extends BizEntity {

	// Fields

	private Long pid;
	private PmMngRoleInfo pmMngRoleInfo;
	private PmMngOper pmMngOper;
	// Constructors

	/** default constructor */
	public PmMngRoleOper() {
	}

	/** minimal constructor */
	public PmMngRoleOper(Long pid) {
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
	@JoinColumn(name = "VC_ROLE_ID")
	public PmMngRoleInfo getPmMngRoleInfo() {
		return this.pmMngRoleInfo;
	}

	public void setPmMngRoleInfo(PmMngRoleInfo pmMngRoleInfo) {
		this.pmMngRoleInfo = pmMngRoleInfo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VC_OPER_ID")
	public PmMngOper getPmMngOper() {
		return this.pmMngOper;
	}

	public void setPmMngOper(PmMngOper pmMngOper) {
		this.pmMngOper = pmMngOper;
	}

}