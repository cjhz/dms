package com.sunraysoft.hr.domain.entity.manage;

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

import org.hibernate.annotations.Where;

import com.sunraysoft.hr.domain.entity.base.BizEntity;

/**
 * PmMngMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PM_MNG_MENU")
@SequenceGenerator(name="sg", sequenceName="SEQ_PM_MNG_MENU", allocationSize=1)
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class PmMngMenu extends BizEntity {

	// Fields

	private Long pid;
	private String vcMenuName;
	private Long vcClientType;
	private PmMngMenu parent;
	private String vcMenuUrl;
	private Long vcPosition;
	private String vcMeno;

	private Set<PmMngMenu> children = new HashSet<PmMngMenu>();
	
	private Set<PmMngOper> pmMngOpers = new HashSet<PmMngOper>();

	// Constructors

	/** default constructor */
	public PmMngMenu() {
	}

	/** minimal constructor */
	public PmMngMenu(Long pid) {
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

	@Column(name = "VC_MENU_NAME")
	public String getVcMenuName() {
		return this.vcMenuName;
	}

	public void setVcMenuName(String vcMenuName) {
		this.vcMenuName = vcMenuName;
	}

	@Column(name = "VC_CLIENT_TYPE", precision = 2, scale = 0)
	public Long getVcClientType() {
		return this.vcClientType;
	}

	public void setVcClientType(Long vcClientType) {
		this.vcClientType = vcClientType;
	}

	@Where(clause="DELETE_FLAG = 0")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="VC_PARENT_ID", columnDefinition="NUMBER(20)")
	public PmMngMenu getParent() {
		return parent;
	}
	public void setParent(PmMngMenu parent) {
		this.parent = parent;
	}

	@OrderBy("vcPosition")
	@OneToMany(mappedBy="parent", fetch=FetchType.LAZY)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
		org.hibernate.annotations.CascadeType.DELETE,
        org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	public Set<PmMngMenu> getChildren() {
		return children;
	}
	public void setChildren(Set<PmMngMenu> children) {
		this.children = children;
	}
	
	@Column(name = "VC_MENU_URL")
	public String getVcMenuUrl() {
		return this.vcMenuUrl;
	}

	public void setVcMenuUrl(String vcMenuUrl) {
		this.vcMenuUrl = vcMenuUrl;
	}

	@Column(name = "VC_POSITION", precision = 20, scale = 0)
	public Long getVcPosition() {
		return this.vcPosition;
	}

	public void setVcPosition(Long vcPosition) {
		this.vcPosition = vcPosition;
	}

	@Column(name = "VC_MENO", length = 1024)
	public String getVcMeno() {
		return this.vcMeno;
	}

	public void setVcMeno(String vcMeno) {
		this.vcMeno = vcMeno;
	}

	@OrderBy("pid")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pmMngMenu")
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
		org.hibernate.annotations.CascadeType.DELETE,
        org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	public Set<PmMngOper> getPmMngOpers() {
		return this.pmMngOpers;
	}

	public void setPmMngOpers(Set<PmMngOper> pmMngOpers) {
		this.pmMngOpers = pmMngOpers;
	}

}