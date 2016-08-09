package com.sunraysoft.hr.domain.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * HttpSession中的USER
 *
 */
public class UserDto {
	
	private Long id;
	private String loginName;
	private String realName;
	private String department;
	private Date beginTime;
	private Date endTime;
	private String roleName;
	private String ip;
	private String areaCode;
	private String areaId;
	private String areaName;
	private Long departmentype;
	
	private Set<String> operActions = new HashSet<String>();
	private Date loginTime = new Date();
	private Long userstatus;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Set<String> getOperActions() {
		return operActions;
	}
	public void addOperAction(String[] actions) {
		if(actions != null && actions.length > 0) {
			for(String action : actions) {
				addOperAction(action);
			}
		}
	}
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
	
	public void addOperAction(String action) {
		if(action != null && !action.isEmpty()) {
			operActions.add(action);
		}
	}
	
	public boolean hasPermission(String operCode) {
		String[] code=operCode.split(":");
		for(String s:code){
			if(operActions.contains(s)){
				return true;
			}
		}
		return false;
	}
	public Long getUserstatus() {
		return userstatus;
	}
	public void setUserstatus(Long userstatus) {
		this.userstatus = userstatus;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Long getDepartmentype() {
		return departmentype;
	}
	public void setDepartmentype(Long departmentype) {
		this.departmentype = departmentype;
	}
	
}
