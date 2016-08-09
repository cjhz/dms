package com.sunraysoft.hr.dao.report.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sunraysoft.hr.dao.base.HibernateDao;
import com.sunraysoft.hr.dao.report.DeptStatisticsDao;
import com.sunraysoft.hr.domain.dto.report.DeptStatisticsDto;
import com.sunraysoft.hr.util.AreaUtil;

/**
 * 机构统计
 * @author juyf
 * @date 2012-08-16
 */
@SuppressWarnings("unchecked")
@Repository("DeptStatisticsDao")
public class DeptStatisticsDaoImpl extends HibernateDao
		implements DeptStatisticsDao {

	/**
	 * 机构统计-按机构性质统计
	 * @param deptdto
	 * @return 查询封装后的列表
	 */
	public List<Object[]> findDeptStatisticsList_deptype(DeptStatisticsDto deptdto){
		String departmentcode = deptdto.getAreacode()==null?"":deptdto.getAreacode();
		String departmenttype = deptdto.getDepartmenttype()==null?"":deptdto.getDepartmenttype().toString();
		String deptobservingid = deptdto.getDeptobserving()==null||deptdto.getDeptobserving()==0L?"":deptdto.getDeptobserving().toString();
		//String deptypeid = deptdto.getDeptype()==null?"":deptdto.getDeptype().toString();
		String deptdictid = deptdto.getDeptdict()==null||deptdto.getDeptdict()==0L?"":deptdto.getDeptdict().toString();
		String code = AreaUtil.getDepartmentcode(departmentcode, departmenttype);
		int len = AreaUtil.getDepartmentAreaLen(deptdto.getDepartmenttype()+"");
		String substrSql="";
		//if(len!=6){
			substrSql = " SUBSTR(area.vc_department_code,1,"+len+") ";
		//}else{
//			substrSql = " area.vc_department_code ";
		//}
		Map<String, Object> queryMap = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT "+substrSql+", DETE.VC_TYPE_NAME, COUNT(DISTINCT DEP.PID), DETE.PID ");
		sb.append("FROM PM_BSC_AREA_INFO AREA ");
		sb.append("LEFT JOIN PM_BSC_DEPT_INFO DEP ON DEP.VC_AREA_ID = AREA.PID ");
		sb.append("LEFT JOIN PM_BSC_DEPT_TYPE DETE ON DEP.VC_TYPE_ID = DETE.PID ");
		sb.append("LEFT JOIN PM_BSC_DICT DICT ON DICT.PID = DEP.VC_RANK ");
		sb.append("LEFT JOIN PM_BSC_OBSERVING OBS ON OBS.VC_REF_ID = DEP.PID ");
		sb.append("LEFT JOIN PM_BSC_OBSER_TYPE OBSTY ON OBS.VC_OBSERV_STYLE = OBSTY.PID ");
		sb.append("WHERE 1=1 AND DETE.PID IS NOT NULL  ");
		if(!departmentcode.equals("")){//  地区条件
			sb.append("AND INSTR(AREA.VC_DEPARTMENT_CODE,:departmentcode)=1 ");
			queryMap.put("departmentcode", code);
		}
		if(!"".equals(deptobservingid)){// 机构立功表彰条件
			sb.append("AND OBSTY.Pid =:deptobservingid ");
			queryMap.put("deptobservingid", deptobservingid);
		}
		if(!"".equals(deptdictid)){// 机构职级条件
			sb.append("AND dict.pid =:deptdictid ");
			queryMap.put("deptdictid", deptdictid);
		}
		sb.append("GROUP BY "+substrSql+", DETE.VC_TYPE_NAME, DETE.PID");
		List list =createSQLQuery(sb.toString(), queryMap);
		return list;
	}
	
	/**
	 * 机构统计-按机构职级统计
	 * @param deptdto
	 * @return 查询封装后的列表
	 */
	public List<Object[]> findDeptStatisticsList_dict(DeptStatisticsDto deptdto){
		String departmentcode = deptdto.getAreacode()==null?"":deptdto.getAreacode();
		String departmenttype = deptdto.getDepartmenttype()==null?"":deptdto.getDepartmenttype().toString();
		String deptobservingid = deptdto.getDeptobserving()==null||deptdto.getDeptobserving()==0L?"":deptdto.getDeptobserving().toString();
		String deptypeid = deptdto.getDeptype()==null||deptdto.getDeptype()==0L?"":deptdto.getDeptype().toString();
		//String deptdictid = deptdto.getDeptdict()==0L?"":deptdto.getDeptdict().toString();
		String code = AreaUtil.getDepartmentcode(departmentcode, departmenttype);
		int len = AreaUtil.getDepartmentAreaLen(deptdto.getDepartmenttype()+"");
		String substrSql="";
		//if(len!=6){
			substrSql = " SUBSTR(area.vc_department_code,1,"+len+") ";
		//}else{
//			substrSql = " area.vc_department_code ";
		//}
		Map<String, Object> queryMap = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT "+substrSql+", DICT.VC_NAME, COUNT(DISTINCT DEP.PID), DICT.PID ");
		sb.append("FROM PM_BSC_AREA_INFO AREA ");
		sb.append("LEFT JOIN PM_BSC_DEPT_INFO DEP ON DEP.VC_AREA_ID = AREA.PID ");
		sb.append("LEFT JOIN PM_BSC_DEPT_TYPE DETE ON DEP.VC_TYPE_ID = DETE.PID ");
		sb.append("LEFT JOIN PM_BSC_DICT DICT ON DICT.PID = DEP.VC_RANK ");
		sb.append("LEFT JOIN PM_BSC_OBSERVING OBS ON OBS.VC_REF_ID = DEP.PID ");
		sb.append("LEFT JOIN PM_BSC_OBSER_TYPE OBSTY ON OBS.VC_OBSERV_STYLE = OBSTY.PID ");
		sb.append("WHERE 1=1 AND DICT.PID IS NOT NULL  ");
		if(!departmentcode.equals("")){//  地区条件
			sb.append("AND INSTR(AREA.VC_DEPARTMENT_CODE,:departmentcode)=1 ");
			queryMap.put("departmentcode", code);
		}
		if(!"".equals(deptobservingid)){// 机构立功表彰条件
			sb.append("AND OBSTY.Pid =:deptobservingid ");
			queryMap.put("deptobservingid", deptobservingid);
		}
		if(!"".equals(deptypeid)){// 机构性质条件
			sb.append("AND dete.pid =:deptypeid ");
			queryMap.put("deptypeid", deptypeid);
		}
		sb.append("GROUP BY "+substrSql+", DICT.VC_NAME, DICT.PID");
		List list =createSQLQuery(sb.toString(), queryMap);
		return list;
	}
	
	/**
	 * 机构统计-按机构立功表彰统计
	 * @param deptdto
	 * @return 查询封装后的列表
	 */
	public List<Object[]> findDeptStatisticsList_observing(DeptStatisticsDto deptdto){
		String departmentcode = deptdto.getAreacode()==null?"":deptdto.getAreacode();
		String departmenttype = deptdto.getDepartmenttype()==null?"":deptdto.getDepartmenttype().toString();
		//String deptobservingid = deptdto.getDeptobserving()==0L?"":deptdto.getDeptobserving().toString();
		String deptypeid = deptdto.getDeptype()==null||deptdto.getDeptype()==0L?"":deptdto.getDeptype().toString();
		String deptdictid = deptdto.getDeptdict()==null||deptdto.getDeptdict()==0L?"":deptdto.getDeptdict().toString();
		String code = AreaUtil.getDepartmentcode(departmentcode, departmenttype);
		int len = AreaUtil.getDepartmentAreaLen(deptdto.getDepartmenttype()+"");
		String substrSql="";
		//if(len!=6){
			substrSql = " SUBSTR(area.vc_department_code,1,"+len+") ";
		//}else{
//			substrSql = " area.vc_department_code ";
		//}
		Map<String, Object> queryMap = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT "+substrSql+", OBSTY.VC_NAME, COUNT(OBSTY.PID), OBSTY.PID ");
		sb.append("FROM PM_BSC_AREA_INFO AREA ");
		sb.append("LEFT JOIN PM_BSC_DEPT_INFO DEP ON DEP.VC_AREA_ID = AREA.PID ");
		sb.append("LEFT JOIN PM_BSC_DEPT_TYPE DETE ON DEP.VC_TYPE_ID = DETE.PID ");
		sb.append("LEFT JOIN PM_BSC_DICT DICT ON DICT.PID = DEP.VC_RANK ");
		sb.append("LEFT JOIN PM_BSC_OBSERVING OBS ON OBS.VC_REF_ID = DEP.PID ");
		sb.append("LEFT JOIN PM_BSC_OBSER_TYPE OBSTY ON OBS.VC_OBSERV_STYLE = OBSTY.PID ");
		sb.append("WHERE 1=1 AND OBS.PID IS NOT NULL ");
		if(!departmentcode.equals("")){//  地区条件
			sb.append("AND INSTR(AREA.VC_DEPARTMENT_CODE,:departmentcode)=1 ");
			queryMap.put("departmentcode", code);
		}
		if(!"".equals(deptdictid)){// 机构职级条件
			sb.append("AND dict.pid =:deptdictid ");
			queryMap.put("deptdictid", deptdictid);
		}
		if(!"".equals(deptypeid)){// 机构性质条件
			sb.append("AND dete.pid =:deptypeid ");
			queryMap.put("deptypeid", deptypeid);
		}
		sb.append("GROUP BY "+substrSql+", OBSTY.VC_NAME, OBSTY.PID");
		List list =createSQLQuery(sb.toString(), queryMap);
		return list;
	}
	
}
