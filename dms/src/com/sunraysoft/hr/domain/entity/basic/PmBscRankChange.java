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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.sunraysoft.hr.domain.entity.base.BizEntity;

/**
 * PmBscRankChange entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PM_BSC_RANK_CHANGE")
@SequenceGenerator(name="sg", sequenceName="SEQ_PM_BSC_RANK_CHANGE", allocationSize=1)
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class PmBscRankChange extends BizEntity{

	// Fields

	private Long pid;
	private PmBscStaffInfo pmBscStaffInfo;
	private Date vcNowDate;
	private Date vcChgDate;
	private String vcRemark;
	private Long vcIfedit;
	private Long vcType;
	private Date vcEndDate;
	
	//private Date vcCreateDate;

	private PmBscDict dictNowRanks;//调动前职级
	private PmBscDict dictChgRanks;//调动后职级
	// Constructors

	/** default constructor */
	public PmBscRankChange() {
	}

	/** minimal constructor */
	public PmBscRankChange(Long pid) {
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

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VC_NOW_RANK", columnDefinition="NUMBER(20)", nullable=false)
	@NotFound(action=NotFoundAction.IGNORE)    
	public PmBscDict getDictNowRanks() {
		return dictNowRanks;
	}
	
	public void setDictNowRanks(PmBscDict dictNowRanks) {
		this.dictNowRanks = dictNowRanks;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VC_CHG_RANK", columnDefinition="NUMBER(20)", nullable=false)
	@NotFound(action=NotFoundAction.IGNORE)
	public PmBscDict getDictChgRanks() {
		return dictChgRanks;
	}

	public void setDictChgRanks(PmBscDict dictChgRanks) {
		this.dictChgRanks = dictChgRanks;
	}

	@Column(name = "VC_IFEDIT")
	public Long getVcIfedit() {
		return vcIfedit;
	}

	public void setVcIfedit(Long vcIfedit) {
		this.vcIfedit = vcIfedit;
	}

	@Column(name = "VC_TYPE")
	public Long getVcType() {
		return vcType;
	}

	public void setVcType(Long vcType) {
		this.vcType = vcType;
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