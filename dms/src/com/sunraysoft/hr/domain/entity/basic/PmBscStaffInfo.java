package com.sunraysoft.hr.domain.entity.basic;

import java.util.Date;
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
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sunraysoft.hr.annotation.Export;
import com.sunraysoft.hr.annotation.OperLog;
import com.sunraysoft.hr.constant.BizConstant;
import com.sunraysoft.hr.domain.entity.base.BizEntity;
import com.sunraysoft.hr.domain.entity.manage.PmMngChangeInfo;

/**
 * PmBscStaffInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PM_BSC_STAFF_INFO")
@SequenceGenerator(name="sg", sequenceName="SEQ_PM_BSC_STAFF_INFO", allocationSize=1)
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class PmBscStaffInfo extends BizEntity {

	// Fields

	private Long pid;
	@Export(i18nKey="姓名", multyI18nKey="表彰人姓名")
	@OperLog(i18nKey="姓名")
	private String vcRealName;
	
	@Export(i18nKey="性别",  
			constKeys={BizConstant.SEX_KEY_1, 
			BizConstant.SEX_KEY_2}, 
			constTexts={BizConstant.SEX_TXT_1, BizConstant.SEX_TXT_2}, width=8, multyI18nKey="表彰人性别")
	@OperLog(i18nKey="性别", 
			constKeys={BizConstant.SEX_KEY_1, 
			BizConstant.SEX_KEY_2}, 
			constTexts={BizConstant.SEX_TXT_1, BizConstant.SEX_TXT_2})
	private Long vcSex;
	@Export(i18nKey="出生日期",formatType = Export.DATE_FORMAT_YYYY_MM_DD, width=25)
	@OperLog(i18nKey="出生日期",formatType = OperLog.DATE_FORMAT_YYYY_MM_DD)
	private Date vcBirth;
	//private Long vcPolitical;//
	//private Long vcCultural;//
	@OperLog(i18nKey="手机")
	private String vcMphone;
	@OperLog(i18nKey="办公电话")
	private String vcOphone;
	@OperLog(i18nKey="传真")
	private String vcFax;
	@OperLog(i18nKey="警号")
	private String vcAlarm;
	//private Long vcFormation;
	//private Long vcRank;
	//private String vcJob;
	@OperLog(i18nKey="身份证号码")
	private String vcCardNo;
	private Date vcRankDate;
	private Date vcJobDate;
	private Date vcDrugDate;
	private String vcWorkDivision;

	private Date vcFormationDate;
	private Date vcWorkDate;
	@OperLog(i18nKey="人员属性", formatType=OperLog.EXPORT_HIDDEN_STRING)
	private String vcPersonProp;
	@OperLog(i18nKey="备注")
	private String vcRemark;
	
	private String vcOfficeOnline;
	private String vcVirtualNum;
	private String vcTransferNode;
	@Export(i18nKey="所属机构", formatType=Export.EXPORT_DEPTS, width=35)
	private Set<PmBscStaffDept> pmBscStaffDepts = new HashSet<PmBscStaffDept>();
	private Set<PmMngChangeInfo> pmMngChangeInfos = new HashSet<PmMngChangeInfo>();
	private Set<PmBscJobChange> pmBscJobChanges = new HashSet<PmBscJobChange>();
	private Set<PmBscRankChange> pmBscRankChanges = new HashSet<PmBscRankChange>();
	private Set<PmBscObservingStaff> pmBscObservings = new HashSet<PmBscObservingStaff>();
	@Export(i18nKey="政治面貌", formatType=Export.EXPORT_ATTR_CLASS)
	@OperLog(i18nKey="政治面貌", formatType=OperLog.EXPORT_ATTR_CLASS)
	private PmBscDict dictPoliticals;//政治面貌
	@Export(i18nKey="文化程度", formatType=Export.EXPORT_ATTR_CLASS)
	@OperLog(i18nKey="文化程度", formatType=OperLog.EXPORT_ATTR_CLASS)
	private PmBscDict dictCulturals;//文化程度
	@Export(i18nKey="编制", formatType=Export.EXPORT_ATTR_CLASS)
	private PmBscDict dictFormations;//编制
	@Export(i18nKey="职级", formatType=Export.EXPORT_ATTR_CLASS)
	private PmBscDict dictRanks;//职级
	@Export(i18nKey="职务", formatType=Export.EXPORT_ATTR_CLASS)
	private PmBscDict dictJobs;//职务

	private Long bbirth;
	private Long ebirth;
	private String phone_area;
	private String phone_code;
	
	private Long isShowAll;// 0 显示本机机关的人员，1 显示本级机关以及下属机关的所有人员
	// Constructors

	/** default constructor */
	public PmBscStaffInfo() {
	}

	/** minimal constructor */
	public PmBscStaffInfo(Long pid) {
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

	@Column(name = "VC_REAL_NAME")
	public String getVcRealName() {
		return this.vcRealName;
	}

	public void setVcRealName(String vcRealName) {
		this.vcRealName = vcRealName;
	}

	@Column(name = "VC_SEX", precision = 2, scale = 0)
	public Long getVcSex() {
		return this.vcSex;
	}

	public void setVcSex(Long vcSex) {
		this.vcSex = vcSex;
	}

	@Column(name = "VC_BIRTH", columnDefinition="timestamp")
	public Date getVcBirth() {
		return this.vcBirth;
	}

	public void setVcBirth(Date vcBirth) {
		this.vcBirth = vcBirth;
	}

	@Column(name = "VC_MPHONE", length = 20)
	public String getVcMphone() {
		return this.vcMphone;
	}

	public void setVcMphone(String vcMphone) {
		this.vcMphone = vcMphone;
	}

	@Column(name = "VC_OPHONE", length = 20)
	public String getVcOphone() {
		return this.vcOphone;
	}

	public void setVcOphone(String vcOphone) {
		this.vcOphone = vcOphone;
	}

	@Column(name = "VC_FAX", length = 20)
	public String getVcFax() {
		return this.vcFax;
	}

	public void setVcFax(String vcFax) {
		this.vcFax = vcFax;
	}

	@Column(name = "VC_ALARM", length = 50)
	public String getVcAlarm() {
		return this.vcAlarm;
	}

	public void setVcAlarm(String vcAlarm) {
		this.vcAlarm = vcAlarm;
	}

	@Column(name = "VC_RANK_DATE", columnDefinition="timestamp")
	public Date getVcRankDate() {
		return this.vcRankDate;
	}

	public void setVcRankDate(Date vcRankDate) {
		this.vcRankDate = vcRankDate;
	}

	@Column(name = "VC_JOB_DATE", columnDefinition="timestamp")
	public Date getVcJobDate() {
		return this.vcJobDate;
	}

	public void setVcJobDate(Date vcJobDate) {
		this.vcJobDate = vcJobDate;
	}

	@Column(name = "VC_DRUG_DATE", columnDefinition="timestamp")
	public Date getVcDrugDate() {
		return this.vcDrugDate;
	}

	public void setVcDrugDate(Date vcDrugDate) {
		this.vcDrugDate = vcDrugDate;
	}

	@Column(name = "VC_WORK_DIVISION", length = 200)
	public String getVcWorkDivision() {
		return this.vcWorkDivision;
	}

	public void setVcWorkDivision(String vcWorkDivision) {
		this.vcWorkDivision = vcWorkDivision;
	}
	
	@Column(name = "VC_FORMATION_DATE", columnDefinition="timestamp")
	public Date getVcFormationDate() {
		return vcFormationDate;
	}

	public void setVcFormationDate(Date vcFormationDate) {
		this.vcFormationDate = vcFormationDate;
	}

	@Column(name = "VC_WORK_DATE", columnDefinition="timestamp")
	public Date getVcWorkDate() {
		return vcWorkDate;
	}

	public void setVcWorkDate(Date vcWorkDate) {
		this.vcWorkDate = vcWorkDate;
	}

	@Column(name = "VC_REMARK", length = 1000)
	public String getVcRemark() {
		return this.vcRemark;
	}

	public void setVcRemark(String vcRemark) {
		this.vcRemark = vcRemark;
	}

	@OrderBy("pid")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pmBscStaffInfo")
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
		org.hibernate.annotations.CascadeType.DELETE,
        org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	public Set<PmBscStaffDept> getPmBscStaffDepts() {
		return this.pmBscStaffDepts;
	}

	public void setPmBscStaffDepts(Set<PmBscStaffDept> pmBscStaffDepts) {
		this.pmBscStaffDepts = pmBscStaffDepts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pmBscStaffInfo")
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
		org.hibernate.annotations.CascadeType.DELETE,
        org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	public Set<PmMngChangeInfo> getPmMngChangeInfos() {
		return this.pmMngChangeInfos;
	}

	public void setPmMngChangeInfos(Set<PmMngChangeInfo> pmMngChangeInfos) {
		this.pmMngChangeInfos = pmMngChangeInfos;
	}

	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pmBscStaffInfo")
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
		org.hibernate.annotations.CascadeType.DELETE,
        org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	public Set<PmBscJobChange> getPmBscJobChanges() {
		return pmBscJobChanges;
	}

	public void setPmBscJobChanges(Set<PmBscJobChange> pmBscJobChanges) {
		this.pmBscJobChanges = pmBscJobChanges;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pmBscStaffInfo")
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
		org.hibernate.annotations.CascadeType.DELETE,
        org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	public Set<PmBscRankChange> getPmBscRankChanges() {
		return pmBscRankChanges;
	}

	public void setPmBscRankChanges(Set<PmBscRankChange> pmBscRankChanges) {
		this.pmBscRankChanges = pmBscRankChanges;
	}

	@Column(name = "VC_CARD_NO")
	public String getVcCardNo() {
		return vcCardNo;
	}

	public void setVcCardNo(String vcCardNo) {
		this.vcCardNo = vcCardNo;
	}

	@Column(name = "VC_PERSON_PROP")
	public String getVcPersonProp() {
		return vcPersonProp;
	}

	public void setVcPersonProp(String vcPersonProp) {
		this.vcPersonProp = vcPersonProp;
	}


	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pmBscStaffInfo")
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
		org.hibernate.annotations.CascadeType.DELETE,
        org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    public Set<PmBscObservingStaff> getPmBscObservings() {
		return pmBscObservings;
	}
	
	public void setPmBscObservings(Set<PmBscObservingStaff> pmBscObservings) {
		this.pmBscObservings = pmBscObservings;
	}

	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="VC_POLITICAL", columnDefinition="NUMBER(20)", nullable=false)
	public PmBscDict getDictPoliticals() {
		return dictPoliticals;
	}

	public void setDictPoliticals(PmBscDict dictPoliticals) {
		this.dictPoliticals = dictPoliticals;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VC_CULTURAL", columnDefinition="NUMBER(20)", nullable=false)
	public PmBscDict getDictCulturals() {
		return dictCulturals;
	}

	public void setDictCulturals(PmBscDict dictCulturals) {
		this.dictCulturals = dictCulturals;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VC_FORMATION", columnDefinition="NUMBER(20)", nullable=false)
	public PmBscDict getDictFormations() {
		return dictFormations;
	}

	public void setDictFormations(PmBscDict dictFormations) {
		this.dictFormations = dictFormations;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VC_RANK", columnDefinition="NUMBER(20)", nullable=false)
	public PmBscDict getDictRanks() {
		return dictRanks;
	}

	public void setDictRanks(PmBscDict dictRanks) {
		this.dictRanks = dictRanks;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VC_JOB", columnDefinition="NUMBER(20)", nullable=false)
	public PmBscDict getDictJobs() {
		return dictJobs;
	}

	public void setDictJobs(PmBscDict dictJobs) {
		this.dictJobs = dictJobs;
	}

	@Column(name = "VC_OFFICE_ONLINE")
	public String getVcOfficeOnline() {
		return vcOfficeOnline;
	}

	public void setVcOfficeOnline(String vcOfficeOnline) {
		this.vcOfficeOnline = vcOfficeOnline;
	}

	@Column(name = "VC_VIRTUAL_NUM")
	public String getVcVirtualNum() {
		return vcVirtualNum;
	}

	public void setVcVirtualNum(String vcVirtualNum) {
		this.vcVirtualNum = vcVirtualNum;
	}

	@Column(name = "VC_TRANSFER_NODE")
	public String getVcTransferNode() {
		return vcTransferNode;
	}

	public void setVcTransferNode(String vcTransferNode) {
		this.vcTransferNode = vcTransferNode;
	}
	
	@Transient
	public Long getBbirth() {
		return bbirth;
	}

	public void setBbirth(Long bbirth) {
		this.bbirth = bbirth;
	}

	@Transient
	public Long getEbirth() {
		return ebirth;
	}

	public void setEbirth(Long ebirth) {
		this.ebirth = ebirth;
	}

	@Transient
	public String getPhone_area() {
		return phone_area;
	}

	public void setPhone_area(String phone_area) {
		this.phone_area = phone_area;
	}

	@Transient
	public String getPhone_code() {
		return phone_code;
	}

	public void setPhone_code(String phone_code) {
		this.phone_code = phone_code;
	}

	@Transient
	public Long getIsShowAll() {
		return isShowAll;
	}

	public void setIsShowAll(Long isShowAll) {
		this.isShowAll = isShowAll;
	}

	
}