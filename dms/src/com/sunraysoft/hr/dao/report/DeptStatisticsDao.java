package com.sunraysoft.hr.dao.report;

import java.util.List;

import com.sunraysoft.hr.domain.dto.report.DeptStatisticsDto;

/**
 * 机构统计
 * @author juyf
 * @date 2012-08-16
 */
public interface DeptStatisticsDao{
	
	/**
	 * 机构统计-按机构性质统计
	 * @param deptdto
	 * @return 查询封装后的列表
	 */
	public List<Object[]> findDeptStatisticsList_deptype(DeptStatisticsDto deptdto);
	
	/**
	 * 机构统计-按机构职级统计
	 * @param deptdto
	 * @return 查询封装后的列表
	 */
	public List<Object[]> findDeptStatisticsList_dict(DeptStatisticsDto deptdto);
	
	/**
	 * 机构统计-按机构立功表彰统计
	 * @param deptdto
	 * @return 查询封装后的列表
	 */
	public List<Object[]> findDeptStatisticsList_observing(DeptStatisticsDto deptdto);
}
