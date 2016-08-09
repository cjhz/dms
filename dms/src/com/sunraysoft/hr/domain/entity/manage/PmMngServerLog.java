package com.sunraysoft.hr.domain.entity.manage;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sunraysoft.hr.domain.entity.base.BizEntity;

/**
 * PmMngServerLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PM_MNG_SERVER_LOG")
@SequenceGenerator(name="sg", sequenceName="SEQ_PM_MNG_SERVER_LOG", allocationSize=1)
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class PmMngServerLog extends BizEntity {

	// Fields

	private Long pid;
	private Long logType;
	private Long operType;
	private Long operId;
	private String operIp;
	private String operServerUid;
	private Long beOperatedType;
	private Long beOperatedId;
	private String logMsg;
	private Date createdOn;
	private Long vcDataPid;

	// Constructors

	/** default constructor */
	public PmMngServerLog() {
	}

	/** minimal constructor */
	public PmMngServerLog(Long pid) {
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

	@Column(name = "LOG_TYPE", precision = 2, scale = 0)
	public Long getLogType() {
		return this.logType;
	}

	public void setLogType(Long logType) {
		this.logType = logType;
	}

	@Column(name = "OPER_TYPE", precision = 2, scale = 0)
	public Long getOperType() {
		return this.operType;
	}

	public void setOperType(Long operType) {
		this.operType = operType;
	}

	@Column(name = "OPER_ID", precision = 20, scale = 0)
	public Long getOperId() {
		return this.operId;
	}

	public void setOperId(Long operId) {
		this.operId = operId;
	}

	@Column(name = "OPER_IP")
	public String getOperIp() {
		return this.operIp;
	}

	public void setOperIp(String operIp) {
		this.operIp = operIp;
	}

	@Column(name = "OPER_SERVER_UID")
	public String getOperServerUid() {
		return this.operServerUid;
	}

	public void setOperServerUid(String operServerUid) {
		this.operServerUid = operServerUid;
	}

	@Column(name = "BE_OPERATED_TYPE", precision = 2, scale = 0)
	public Long getBeOperatedType() {
		return this.beOperatedType;
	}

	public void setBeOperatedType(Long beOperatedType) {
		this.beOperatedType = beOperatedType;
	}

	@Column(name = "BE_OPERATED_ID", precision = 20, scale = 0)
	public Long getBeOperatedId() {
		return this.beOperatedId;
	}

	public void setBeOperatedId(Long beOperatedId) {
		this.beOperatedId = beOperatedId;
	}

	@Column(name = "LOG_MSG", length = 2048)
	public String getLogMsg() {
		return this.logMsg;
	}

	public void setLogMsg(String logMsg) {
		this.logMsg = logMsg;
	}

	@Column(name = "CREATED_ON")
	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Column(name = "VC_DATA_PID")
	public Long getVcDataPid() {
		return vcDataPid;
	}

	public void setVcDataPid(Long vcDataPid) {
		this.vcDataPid = vcDataPid;
	}
	
}