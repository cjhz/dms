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
import javax.persistence.Transient;

import com.sunraysoft.hr.domain.entity.base.BizEntity;
import com.sunraysoft.hr.domain.entity.manage.PmMngRoleInfo;

/**
 * PmBscLoginInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PM_BSC_LOGIN_INFO")
@SequenceGenerator(name="sg", sequenceName="SEQ_PM_BSC_LOGIN_INFO", allocationSize=1)
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class PmBscLoginInfo extends BizEntity {

	// Fields

	private Long pid;
	private PmBscAreaInfo pmBscAreaInfo;
	private PmMngRoleInfo pmMngRoleInfo;
	private String vcLoginName;
	private String vcLoginPwd;
	private Long vcUserStatus;
	private Long vcLoginStatus;
	private Date dtCreate;
	private Date dtUpdate;
	private String vcName;
	private String vcCardNo;
	private String vcRuleDept;
	private String vcRemark;
	
	private String authCode;
	// Constructors

	/** default constructor */
	public PmBscLoginInfo() {
	}

	/** minimal constructor */
	public PmBscLoginInfo(Long pid) {
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
	@JoinColumn(name = "VC_RULE_AREA")
	public PmBscAreaInfo getPmBscAreaInfo() {
		return this.pmBscAreaInfo;
	}

	public void setPmBscAreaInfo(PmBscAreaInfo pmBscAreaInfo) {
		this.pmBscAreaInfo = pmBscAreaInfo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VC_ROLE_ID")
	public PmMngRoleInfo getPmMngRoleInfo() {
		return this.pmMngRoleInfo;
	}

	public void setPmMngRoleInfo(PmMngRoleInfo pmMngRoleInfo) {
		this.pmMngRoleInfo = pmMngRoleInfo;
	}

	@Column(name = "VC_LOGIN_NAME")
	public String getVcLoginName() {
		return this.vcLoginName;
	}

	public void setVcLoginName(String vcLoginName) {
		this.vcLoginName = vcLoginName;
	}

	@Column(name = "VC_LOGIN_PWD")
	public String getVcLoginPwd() {
		return this.vcLoginPwd;
	}

	public void setVcLoginPwd(String vcLoginPwd) {
		this.vcLoginPwd = vcLoginPwd;
	}

	@Column(name = "VC_USER_STATUS", precision = 2, scale = 0)
	public Long getVcUserStatus() {
		return this.vcUserStatus;
	}

	public void setVcUserStatus(Long vcUserStatus) {
		this.vcUserStatus = vcUserStatus;
	}

	@Column(name = "VC_LOGIN_STATUS", precision = 2, scale = 0)
	public Long getVcLoginStatus() {
		return this.vcLoginStatus;
	}

	public void setVcLoginStatus(Long vcLoginStatus) {
		this.vcLoginStatus = vcLoginStatus;
	}

	@Column(name = "DT_CREATE")
	public Date getDtCreate() {
		return this.dtCreate;
	}

	public void setDtCreate(Date dtCreate) {
		this.dtCreate = dtCreate;
	}

	@Column(name = "DT_UPDATE")
	public Date getDtUpdate() {
		return this.dtUpdate;
	}

	public void setDtUpdate(Date dtUpdate) {
		this.dtUpdate = dtUpdate;
	}
	
	@Column(name = "VC_NAME")
	public String getVcName() {
		return vcName;
	}

	public void setVcName(String vcName) {
		this.vcName = vcName;
	}

	@Column(name = "VC_CARD_NO")
	public String getVcCardNo() {
		return vcCardNo;
	}

	public void setVcCardNo(String vcCardNo) {
		this.vcCardNo = vcCardNo;
	}

	@Column(name = "VC_RULE_DEPT")
	public String getVcRuleDept() {
		return vcRuleDept;
	}

	public void setVcRuleDept(String vcRuleDept) {
		this.vcRuleDept = vcRuleDept;
	}

	@Transient
	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	
	@Column(name = "VC_REMARK")
	public String getVcRemark() {
		return vcRemark;
	}

	public void setVcRemark(String vcRemark) {
		this.vcRemark = vcRemark;
	}

	
}