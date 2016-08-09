package com.sunraysoft.hr.dao.report;

import java.util.List;

import com.sunraysoft.hr.domain.dto.report.StaffReportDto;

/**
 * @msg.人员统计
 * @author chenjh
 * @version 2012-08-15
 */
public interface StaffCollectDao{
	
	/**
	 * 按人员编制统计获得结果集
	 * @return
	 */
	public abstract List getListbyFormation(StaffReportDto reportdto);
	
	/**
	 * 按人员职级统计获得结果集
	 * @return
	 */
	public abstract List getListbyRanks(StaffReportDto reportdto);
	
	/**
	 * 按人员立功表彰类别获得结果集
	 * @param reportdto
	 * @return
	 */
	public abstract List getListbyObserTypes(StaffReportDto reportdto);
	
	/**
	 * 获取按性别结果集
	 * @param reportdto
	 * @return
	 */
	public abstract List getListbySex(StaffReportDto reportdto);
	
	/**
	 * 获取按文化程度结果集
	 * @param reportdto
	 * @return
	 */
	public abstract List getListbyCultural(StaffReportDto reportdto);
	
	/**
	 * 获取按年龄段查询集合
	 * @param reportdto
	 * @return
	 */
	public List getListbyAgegroup(StaffReportDto reportdto);
}
