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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sunraysoft.hr.annotation.Export;
import com.sunraysoft.hr.annotation.OperLog;
import com.sunraysoft.hr.domain.entity.base.BizEntity;

/**
 * PmBscDeptInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PM_BSC_DEPT_INFO")
@SequenceGenerator(name="sg", sequenceName="SEQ_PM_BSC_DEPT_INFO", allocationSize=1)
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class PmBscDeptInfo extends BizEntity {

	// Fields

	private Long pid;
	@Export(i18nKey="机关类型", formatType=Export.EXPORT_ATTR_CLASS, width=35)
	@OperLog(i18nKey="机关类型", formatType=OperLog.EXPORT_ATTR_CLASS)
	private PmBscDeptType pmBscDeptType;
	@Export(i18nKey="所辖地区", multyI18nKey="表彰机构地区", formatType=Export.EXPORT_ATTR_CLASS, width=10)
	@OperLog(i18nKey="所辖地区", formatType=OperLog.EXPORT_ATTR_CLASS)
	private PmBscAreaInfo pmBscAreaInfo;
	@Export(i18nKey="机构职级", formatType=Export.EXPORT_ATTR_CLASS)
	@OperLog(i18nKey="机构职级", formatType=OperLog.EXPORT_ATTR_CLASS)
	private PmBscDict pmBscDict;
	private String vcDeptNo;// 编号
	@Export(i18nKey="机关名称", multyI18nKey="表彰机构名称", width=35, sort=99)
	@OperLog(i18nKey="机关名称")
	private String vcDeptName;
	private String vcRender;// 立功表彰情况
	@Export(i18nKey="机构负责人", width=10)
	@OperLog(i18nKey="机构负责人")
	private String vcChargeName;// 机构负责人
	@Export(i18nKey="负责人电话", width=15)
	@OperLog(i18nKey="负责人电话")
	private String vcPhone;// 负责人电话
	@Export(i18nKey="机构联系人", width=10)
	@OperLog(i18nKey="机构联系人")
	private String vcContactName;//机构联系人
	@Export(i18nKey="联系人电话", width=15)
	@OperLog(i18nKey="联系人电话")
	private String vcContactPhone;// 联系人电话
	@Export(i18nKey="地址")
	@OperLog(i18nKey="地址")
	private String vcAddress;
	@OperLog(i18nKey="备注")
	private String vcRemark;//memo
	private Set<PmBscObserving> pmBscObservings = new HashSet<PmBscObserving>();
	private Set<PmBscStaffDept> pmBscStaffDepts = new HashSet<PmBscStaffDept>();

	// Constructors

	/** default constructor */
	public PmBscDeptInfo() {
	}

	/** minimal constructor */
	public PmBscDeptInfo(Long pid) {
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
	@JoinColumn(name = "VC_TYPE_ID")
	public PmBscDeptType getPmBscDeptType() {
		return this.pmBscDeptType;
	}

	public void setPmBscDeptType(PmBscDeptType pmBscDeptType) {
		this.pmBscDeptType = pmBscDeptType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VC_RANK")
	public PmBscDict getPmBscDict() {
		return pmBscDict;
	}

	public void setPmBscDict(PmBscDict pmBscDict) {
		this.pmBscDict = pmBscDict;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "VC_AREA_ID")
	public PmBscAreaInfo getPmBscAreaInfo() {
		return this.pmBscAreaInfo;
	}

	public void setPmBscAreaInfo(PmBscAreaInfo pmBscAreaInfo) {
		this.pmBscAreaInfo = pmBscAreaInfo;
	}


	@Column(name = "VC_DEPT_NO", length = 50)
	public String getVcDeptNo() {
		return this.vcDeptNo;
	}

	public void setVcDeptNo(String vcDeptNo) {
		this.vcDeptNo = vcDeptNo;
	}

	@Column(name = "VC_DEPT_NAME")
	public String getVcDeptName() {
		return this.vcDeptName;
	}

	public void setVcDeptName(String vcDeptName) {
		this.vcDeptName = vcDeptName;
	}

	@Column(name = "VC_RENDER", length = 200)
	public String getVcRender() {
		return this.vcRender;
	}

	public void setVcRender(String vcRender) {
		this.vcRender = vcRender;
	}

	@Column(name = "VC_CHARGE_NAME", length = 30)
	public String getVcChargeName() {
		return this.vcChargeName;
	}

	public void setVcChargeName(String vcChargeName) {
		this.vcChargeName = vcChargeName;
	}

	@Column(name = "VC_PHONE", length = 20)
	public String getVcPhone() {
		return this.vcPhone;
	}

	public void setVcPhone(String vcPhone) {
		this.vcPhone = vcPhone;
	}

	@Column(name = "VC_CONTACT_NAME", length = 30)
	public String getVcContactName() {
		return vcContactName;
	}

	public void setVcContactName(String vcContactName) {
		this.vcContactName = vcContactName;
	}

	@Column(name = "VC_CONTACT_PHONE", length = 20)
	public String getVcContactPhone() {
		return vcContactPhone;
	}

	public void setVcContactPhone(String vcContactPhone) {
		this.vcContactPhone = vcContactPhone;
	}

	@Column(name = "VC_ADDRESS", length = 500)
	public String getVcAddress() {
		return this.vcAddress;
	}

	public void setVcAddress(String vcAddress) {
		this.vcAddress = vcAddress;
	}

	@Column(name = "VC_REMARK", length = 1000)
	public String getVcRemark() {
		return this.vcRemark;
	}

	public void setVcRemark(String vcRemark) {
		this.vcRemark = vcRemark;
	}


	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pmBscDeptInfo")
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
		org.hibernate.annotations.CascadeType.DELETE,
        org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	public Set<PmBscStaffDept> getPmBscStaffDepts() {
		return this.pmBscStaffDepts;
	}

	public void setPmBscStaffDepts(Set<PmBscStaffDept> pmBscStaffDepts) {
		this.pmBscStaffDepts = pmBscStaffDepts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pmBscDeptInfo")
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
		org.hibernate.annotations.CascadeType.DELETE,
        org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	public Set<PmBscObserving> getPmBscObservings() {
		return this.pmBscObservings;
	}

	public void setPmBscObservings(Set<PmBscObserving> pmBscObservings) {
		this.pmBscObservings = pmBscObservings;
	}
	
	
}