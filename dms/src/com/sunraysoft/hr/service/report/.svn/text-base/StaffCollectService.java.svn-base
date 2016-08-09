package com.sunraysoft.hr.service.report;

import java.util.Map;

import com.sunraysoft.hr.domain.dto.report.StaffReportDto;


/**
 * @msg.人员统计service
 * @author chenjh
 * @version 2012-08-15
 */
public interface StaffCollectService{
	
	/**
	 * 获得按人员编制统计结果集
	 * @param reportdto
	 * @return
	 */
	public abstract String[][] getReportbyFormation(StaffReportDto reportdto);
	
	/**
	 * 获得按人员职级统计结果集
	 * @param reportdto
	 * @return
	 */
	public String[][] getReportbyRank(StaffReportDto reportdto);
	
	/**
	 * 获得按人员立功表彰统计结果集
	 * @param reportdto
	 * @return
	 */
	public String[][] getReportbyObsertype(StaffReportDto reportdto);
	
	/**
	 * 获取按人员基本情况统计结果集
	 * @param reportdto
	 * @return
	 */
	public Map getReportbyStaffinfo(StaffReportDto reportdto);
}
