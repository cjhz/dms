package com.sunraysoft.hr.domain.dto.report;

import java.util.ArrayList;
import java.util.List;

public class StaffReportDto {
	private Long areaid;//地区id
	private String areacode;//地区编码
	private Long departmenttype;//机关类型
	private Long rank;//职级
	private Long formation;//编制
	private Long cultural;//文化程度
	private Long observType;//立功表彰情况
	private Long sex;
	private Long startnum;
	private Long endnum;
	private List<Long> startnumlist;
	private List<Long> endnumlist;
	public Long getAreaid() {
		return areaid;
	}
	public void setAreaid(Long areaid) {
		this.areaid = areaid;
	}
	public String getAreacode() {
		return areacode;
	}
	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}
	public Long getRank() {
		return rank;
	}
	public void setRank(Long rank) {
		this.rank = rank;
	}
	
	public Long getFormation() {
		return formation;
	}
	public void setFormation(Long formation) {
		this.formation = formation;
	}
	public Long getObservType() {
		return observType;
	}
	public void setObservType(Long observType) {
		this.observType = observType;
	}
	public Long getCultural() {
		return cultural;
	}
	public void setCultural(Long cultural) {
		this.cultural = cultural;
	}
	public Long getDepartmenttype() {
		return departmenttype;
	}
	public void setDepartmenttype(Long departmenttype) {
		this.departmenttype = departmenttype;
	}
	public Long getSex() {
		return sex;
	}
	public void setSex(Long sex) {
		this.sex = sex;
	}
	public Long getStartnum() {
		return startnum;
	}
	public void setStartnum(Long startnum) {
		this.startnum = startnum;
	}
	public Long getEndnum() {
		return endnum;
	}
	public void setEndnum(Long endnum) {
		this.endnum = endnum;
	}
	
	public List<Long> getStartnumlist() {
		startnumlist = getnumlist();
		return startnumlist;
	}
	public void setStartnumlist(List<Long> startnumlist) {
		this.startnumlist = startnumlist;
	}
	public List<Long> getEndnumlist() {
		endnumlist = getnumlist();
		return endnumlist;
	}
	public void setEndnumlist(List<Long> endnumlist) {
		this.endnumlist = endnumlist;
	}
	public List<Long> getnumlist(){
		List<Long> list = new ArrayList<Long>();
		for(long i=1;i<=100;i++){
			list.add(i);
		}
		return list;
	}
}
