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

import com.sunraysoft.hr.annotation.Export;
import com.sunraysoft.hr.domain.entity.base.BizEntity;

/**
 * PmBscObserving entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PM_BSC_OBSERVING_STAFF")
@SequenceGenerator(name="sg", sequenceName="SEQ_PM_BSC_OBSERVING_STAFF", allocationSize=1)
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class PmBscObservingStaff extends BizEntity {

	// Fields

	private Long pid;
	@Export(i18nKey="人员", formatType=Export.EXPORT_MULTY_FILED)
	private PmBscStaffInfo pmBscStaffInfo;
	@Export(i18nKey="表彰类型", formatType=Export.EXPORT_ATTR_CLASS)
	private PmBscObserType pmBscObserType;
	private Long vcObservType;//区别 机构/人员
	@Export(i18nKey="表彰日期",formatType = Export.DATE_FORMAT_YYYY_MM_DD, width=25)
	private Date vcObservTime;
	@Export(i18nKey="表彰说明",width=25)
	private String vcRemark;

	// Constructors

	/** default constructor */
	public PmBscObservingStaff() {
	}

	/** minimal constructor */
	public PmBscObservingStaff(Long pid) {
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
	@JoinColumn(name = "VC_OBSERV_STYLE")
	public PmBscObserType getPmBscObserType() {
		return this.pmBscObserType;
	}

	public void setPmBscObserType(PmBscObserType pmBscObserType) {
		this.pmBscObserType = pmBscObserType;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VC_REF_ID")
	public PmBscStaffInfo getPmBscStaffInfo() {
		return this.pmBscStaffInfo;
	}

	public void setPmBscStaffInfo(PmBscStaffInfo pmBscStaffInfo) {
		this.pmBscStaffInfo = pmBscStaffInfo;
	}

	@Column(name = "VC_OBSERV_TYPE", precision = 20, scale = 0)
	public Long getVcObservType() {
		return this.vcObservType;
	}

	public void setVcObservType(Long vcObservType) {
		this.vcObservType = vcObservType;
	}

	@Column(name = "VC_OBSERV_TIME")
	public Date getVcObservTime() {
		return this.vcObservTime;
	}

	public void setVcObservTime(Date vcObservTime) {
		this.vcObservTime = vcObservTime;
	}

	@Column(name = "VC_REMARK", length = 1000)
	public String getVcRemark() {
		return this.vcRemark;
	}

	public void setVcRemark(String vcRemark) {
		this.vcRemark = vcRemark;
	}

}