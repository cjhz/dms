package com.sunraysoft.hr.dao.report.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sunraysoft.hr.dao.base.HibernateDao;
import com.sunraysoft.hr.dao.report.StaffCollectDao;
import com.sunraysoft.hr.domain.dto.report.StaffReportDto;
import com.sunraysoft.hr.util.AreaUtil;

/**
 * @msg.人员统计
 * @author chenjh
 * @version 2012-08-15
 */
@Repository("staffCollectDao")
public class StaffCollectDaoImpl  extends HibernateDao implements StaffCollectDao {
	
	/**
	 * 按人员编制统计获得结果集
	 */
	public List getListbyFormation(StaffReportDto reportdto){
		Map<String,Object> queryMapParam=new HashMap<String,Object>();
		String departmentcode = reportdto.getAreacode()==null?"":reportdto.getAreacode();
		String departmenttype = reportdto.getDepartmenttype()==null?"":reportdto.getDepartmenttype()+"";
		String code = AreaUtil.getDepartmentcode(departmentcode, departmenttype);
		int len = AreaUtil.getDepartmentAreaLen(reportdto.getDepartmenttype()+"");
		String sqlpart="";
//		if(len!=6){
			sqlpart = " substr(t.vc_department_code,1,"+len+") ";
//		}else{
//			//sqlpart = " t.vc_department_code ";
//		}
		String sql = "SELECT   tmp.code, tmp.vc_formation, COUNT (*) "
			+    " FROM (SELECT DISTINCT (s.pid), "+sqlpart+" code,s.vc_formation "
			+                     " FROM pm_bsc_staff_dept t, pm_bsc_staff_info s "
			+                    " WHERE t.vc_staff_id = s.pid "
			+                    "   AND s.vc_delete_flag = 0 ";
//		String sql = "SELECT   "+sqlpart+", s.vc_formation, COUNT (*) "
//					+    " FROM pm_bsc_staff_dept t, pm_bsc_staff_info s "
//					+   " WHERE t.vc_staff_id = s.pid ";
		queryMapParam.clear();
		if(reportdto.getObservType()!=null&&!reportdto.getObservType().equals(0L)){
			sql += " and exists(select 1 from pm_bsc_observing_staff os where s.pid =os.vc_ref_id and os.vc_observ_style = :observ_style) ";
			queryMapParam.put("observ_style", reportdto.getObservType());
		}
		if(reportdto.getRank()!=null&&!reportdto.getRank().equals(0L)){
			sql += " and s.vc_rank = :vc_rank ";
			queryMapParam.put("vc_rank", reportdto.getRank());
		}
		if(reportdto.getCultural()!=null&&!reportdto.getCultural().equals(0L)){
			sql += " and s.vc_cultural = :vc_cultural ";
			queryMapParam.put("vc_cultural", reportdto.getCultural());
		}
		if(reportdto.getSex()!=null&&!reportdto.getSex().equals(0L)){
			sql += " and s.vc_sex=:sex ";
			queryMapParam.put("sex", reportdto.getSex());
		}
		if(reportdto.getStartnum()!=null&&!reportdto.getStartnum().equals(0L)){
			sql += " and round((to_number(sysdate - (s.vc_birth+0))/365))>=:num1 ";
			queryMapParam.put("num1", reportdto.getStartnum());
		}
		if(reportdto.getEndnum()!=null&&!reportdto.getEndnum().equals(0L)){
			sql += " and round((to_number(sysdate - (s.vc_birth+0))/365))<=:num2 ";
			queryMapParam.put("num2", reportdto.getEndnum());
		}
		if(!departmentcode.equals("")){
			sql += " and instr(t.vc_department_code,:departmentcode)=1 ";
			queryMapParam.put("departmentcode", code);
		}
		sql+= "   GROUP BY s.pid, s.vc_formation,"+sqlpart+") tmp GROUP BY tmp.code, tmp.vc_formation ";
//		sql+=" GROUP BY "+sqlpart+", s.vc_formation ";
		List list =createSQLQuery(sql, queryMapParam);
		return list;
	}

	/**
	 * 按人员职级统计获得结果集
	 */
	@Override
	public List getListbyRanks(StaffReportDto reportdto) {
		Map<String,Object> queryMapParam=new HashMap<String,Object>();
		String departmentcode = reportdto.getAreacode()==null?"":reportdto.getAreacode();
		String departmenttype = reportdto.getDepartmenttype()==null?"":reportdto.getDepartmenttype()+"";
		String code = AreaUtil.getDepartmentcode(departmentcode, departmenttype);
		int len = AreaUtil.getDepartmentAreaLen(reportdto.getDepartmenttype()+"");
		String sqlpart="";
		//if(len!=6){
			sqlpart = " substr(t.vc_department_code,1,"+len+") ";
		//}else{
//			//sqlpart = " t.vc_department_code ";
		//}
		String sql = "SELECT   tmp.code, tmp.vc_rank, COUNT (*) "
					+    " FROM (SELECT DISTINCT (s.pid), "+sqlpart+" code,s.vc_rank "
					+                     " FROM pm_bsc_staff_dept t, pm_bsc_staff_info s "
					+                    " WHERE t.vc_staff_id = s.pid "
					+                    "   AND s.vc_delete_flag = 0 ";
//		String sql = "SELECT   "+sqlpart+", s.vc_rank, COUNT (*) "
//					+    " FROM pm_bsc_staff_dept t, pm_bsc_staff_info s "
//					+   " WHERE t.vc_staff_id = s.pid ";
		queryMapParam.clear();
		if(reportdto.getObservType()!=null&&!reportdto.getObservType().equals(0L)){
			sql += " and exists(select 1 from pm_bsc_observing_staff os where s.pid =os.vc_ref_id and os.vc_observ_style = :observ_style) ";
			queryMapParam.put("observ_style", reportdto.getObservType());
		}
		if(reportdto.getFormation()!=null&&!reportdto.getFormation().equals(0L)){
			sql += " and s.vc_formation = :vc_formation ";
			queryMapParam.put("vc_formation", reportdto.getFormation());
		}
		if(reportdto.getCultural()!=null&&!reportdto.getCultural().equals(0L)){
			sql += " and s.vc_cultural = :vc_cultural ";
			queryMapParam.put("vc_cultural", reportdto.getCultural());
		}
		if(reportdto.getSex()!=null&&!reportdto.getSex().equals(0L)){
			sql += " and s.vc_sex=:sex ";
			queryMapParam.put("sex", reportdto.getSex());
		}
		if(reportdto.getStartnum()!=null&&!reportdto.getStartnum().equals(0L)){
			sql += " and round((to_number(sysdate - (s.vc_birth+0))/365))>=:num1 ";
			queryMapParam.put("num1", reportdto.getStartnum());
		}
		if(reportdto.getEndnum()!=null&&!reportdto.getEndnum().equals(0L)){
			sql += " and round((to_number(sysdate - (s.vc_birth+0))/365))<=:num2 ";
			queryMapParam.put("num2", reportdto.getEndnum());
		}
		if(!departmentcode.equals("")){
			sql += " and instr(t.vc_department_code,:departmentcode)=1 ";
			queryMapParam.put("departmentcode", code);
		}
		sql+= "   GROUP BY s.pid, s.vc_rank,"+sqlpart+") tmp GROUP BY tmp.code, tmp.vc_rank ";
		//sql+=" GROUP BY "+sqlpart+", s.vc_rank ";
		List list =createSQLQuery(sql, queryMapParam);
		return list;
	}

	/**
	 * 按人员立功表彰类别获得结果集
	 */
	@Override
	public List getListbyObserTypes(StaffReportDto reportdto) {
		Map<String,Object> queryMapParam=new HashMap<String,Object>();
		String departmentcode = reportdto.getAreacode()==null?"":reportdto.getAreacode();
		String departmenttype = reportdto.getDepartmenttype()==null?"":reportdto.getDepartmenttype()+"";
		String code = AreaUtil.getDepartmentcode(departmentcode, departmenttype);
		int len = AreaUtil.getDepartmentAreaLen(reportdto.getDepartmenttype()+"");
		String sqlpart="";
		//if(len!=6){
			sqlpart = " substr(t.vc_department_code,1,"+len+") ";
		//}else{
			//sqlpart = " t.vc_department_code ";
		//}
		String sql = "SELECT   tmp.code, os.vc_observ_style, COUNT (*) "
					+    " FROM pm_bsc_observing_staff os,"
					+         " (SELECT   s.pid, "+sqlpart+" code "
					+              " FROM pm_bsc_staff_dept t, pm_bsc_staff_info s "
					+             " WHERE t.vc_staff_id = s.pid "
					+               " AND s.vc_delete_flag = 0 ";
//		String sql = "SELECT   tmp.code, tmp.vc_observ_style, COUNT (*) "
//			+    " FROM (SELECT DISTINCT (s.pid), "+sqlpart+" code,os.vc_observ_style "
//			+                     " from pm_bsc_staff_dept t, pm_bsc_staff_info s, pm_bsc_observing_staff os "
//			+                    " WHERE t.vc_staff_id = s.pid and s.pid = os.vc_ref_id "
//			+                    "   AND s.vc_delete_flag = 0 ";
//		String sql = "SELECT   "+sqlpart+", os.vc_observ_style, COUNT (*) "
//					+    " from pm_bsc_staff_dept t, pm_bsc_staff_info s, pm_bsc_observing_staff os "
//					+   " where t.vc_staff_id = s.pid and s.pid = os.vc_ref_id ";
		queryMapParam.clear();
		if(reportdto.getFormation()!=null&&!reportdto.getFormation().equals(0L)){
			sql += " and s.vc_formation = :vc_formation ";
			queryMapParam.put("vc_formation", reportdto.getFormation());
		}
		if(reportdto.getRank()!=null&&!reportdto.getRank().equals(0L)){
			sql += " and s.vc_rank = :vc_rank ";
			queryMapParam.put("vc_rank", reportdto.getRank());
		}
		if(reportdto.getCultural()!=null&&!reportdto.getCultural().equals(0L)){
			sql += " and s.vc_cultural = :vc_cultural ";
			queryMapParam.put("vc_cultural", reportdto.getCultural());
		}
		if(reportdto.getSex()!=null&&!reportdto.getSex().equals(0L)){
			sql += " and s.vc_sex=:sex ";
			queryMapParam.put("sex", reportdto.getSex());
		}
		if(reportdto.getStartnum()!=null&&!reportdto.getStartnum().equals(0L)){
			sql += " and round((to_number(sysdate - (s.vc_birth+0))/365))>=:num1 ";
			queryMapParam.put("num1", reportdto.getStartnum());
		}
		if(reportdto.getEndnum()!=null&&!reportdto.getEndnum().equals(0L)){
			sql += " and round((to_number(sysdate - (s.vc_birth+0))/365))<=:num2 ";
			queryMapParam.put("num2", reportdto.getEndnum());
		}
		if(!departmentcode.equals("")){
			sql += " and instr(t.vc_department_code,:departmentcode)=1 ";
			queryMapParam.put("departmentcode", code);
		}
		sql+= " GROUP BY s.pid, "+sqlpart+") tmp WHERE tmp.pid = os.vc_ref_id and os.vc_delete_flag = 0 GROUP BY tmp.code, os.vc_observ_style ";
//		sql+= "   GROUP BY s.pid, os.vc_observ_style,"+sqlpart+") tmp GROUP BY tmp.code, tmp.vc_observ_style ";
//		sql+=" GROUP BY "+sqlpart+", os.vc_observ_style ";
		List list =createSQLQuery(sql, queryMapParam);
		return list;
	}
	
	/**
	 * 获取按性别结果集
	 * @param reportdto
	 * @return
	 */
	public List getListbySex(StaffReportDto reportdto) {
		Map<String,Object> queryMapParam=new HashMap<String,Object>();
		String departmentcode = reportdto.getAreacode()==null?"":reportdto.getAreacode();
		String departmenttype = reportdto.getDepartmenttype()==null?"":reportdto.getDepartmenttype()+"";
		String code = AreaUtil.getDepartmentcode(departmentcode, departmenttype);
		int len = AreaUtil.getDepartmentAreaLen(reportdto.getDepartmenttype()+"");
		String sqlpart="";
		//if(len!=6){
			sqlpart = " substr(t.vc_department_code,1,"+len+") ";
		//}else{
			//sqlpart = " t.vc_department_code ";
		//}
		String sql = "SELECT   tmp.code, tmp.vc_sex, COUNT (*) "
			+    " FROM (SELECT DISTINCT (s.pid), "+sqlpart+" code,s.vc_sex "
			+                     " FROM pm_bsc_staff_dept t, pm_bsc_staff_info s "
			+                    " WHERE t.vc_staff_id = s.pid "
			+                    "   AND s.vc_delete_flag = 0 ";
//		String sql = "SELECT   "+sqlpart+", s.vc_sex, COUNT (*) "
//					+    " FROM pm_bsc_staff_dept t, pm_bsc_staff_info s "
//					+   " WHERE t.vc_staff_id = s.pid ";
		queryMapParam.clear();
		if(reportdto.getObservType()!=null&&!reportdto.getObservType().equals(0L)){
			sql += " and exists(select 1 from pm_bsc_observing_staff os where s.pid =os.vc_ref_id and os.vc_observ_style = :observ_style) ";
			queryMapParam.put("observ_style", reportdto.getObservType());
		}
		if(reportdto.getFormation()!=null&&!reportdto.getFormation().equals(0L)){
			sql += " and s.vc_formation = :vc_formation ";
			queryMapParam.put("vc_formation", reportdto.getFormation());
		}
		if(reportdto.getRank()!=null&&!reportdto.getRank().equals(0L)){
			sql += " and s.vc_rank = :vc_rank ";
			queryMapParam.put("vc_rank", reportdto.getRank());
		}
		if(!departmentcode.equals("")){
			sql += " and instr(t.vc_department_code,:departmentcode)=1 ";
			queryMapParam.put("departmentcode", code);
		}
		sql+= "   GROUP BY s.pid, s.vc_sex,"+sqlpart+") tmp GROUP BY tmp.code, tmp.vc_sex ";
//		sql+=" GROUP BY "+sqlpart+", s.vc_sex ";
		List list =createSQLQuery(sql, queryMapParam);
		return list;
	}
	/**
	 * 获取按文化程度结果集
	 * @param reportdto
	 * @return
	 */
	public List getListbyCultural(StaffReportDto reportdto) {
		Map<String,Object> queryMapParam=new HashMap<String,Object>();
		String departmentcode = reportdto.getAreacode()==null?"":reportdto.getAreacode();
		String departmenttype = reportdto.getDepartmenttype()==null?"":reportdto.getDepartmenttype()+"";
		String code = AreaUtil.getDepartmentcode(departmentcode, departmenttype);
		int len = AreaUtil.getDepartmentAreaLen(reportdto.getDepartmenttype()+"");
		String sqlpart="";
		//if(len!=6){
			sqlpart = " substr(t.vc_department_code,1,"+len+") ";
		//}else{
			//sqlpart = " t.vc_department_code ";
		//}
		String sql = "SELECT   tmp.code, tmp.vc_cultural, COUNT (*) "
			+    " FROM (SELECT DISTINCT (s.pid), "+sqlpart+" code,s.vc_cultural "
			+                     " FROM pm_bsc_staff_dept t, pm_bsc_staff_info s "
			+                    " WHERE t.vc_staff_id = s.pid "
			+                    "   AND s.vc_delete_flag = 0 ";
//		String sql = "SELECT   "+sqlpart+", s.vc_cultural, COUNT (*) "
//		+    " FROM pm_bsc_staff_dept t, pm_bsc_staff_info s "
//		+   " WHERE t.vc_staff_id = s.pid ";
		queryMapParam.clear();
		if(reportdto.getObservType()!=null&&!reportdto.getObservType().equals(0L)){
			sql += " and exists(select 1 from pm_bsc_observing_staff os where s.pid =os.vc_ref_id and os.vc_observ_style = :observ_style) ";
			queryMapParam.put("observ_style", reportdto.getObservType());
		}
		if(reportdto.getFormation()!=null&&!reportdto.getFormation().equals(0L)){
			sql += " and s.vc_formation = :vc_formation ";
			queryMapParam.put("vc_formation", reportdto.getFormation());
		}
		if(reportdto.getRank()!=null&&!reportdto.getRank().equals(0L)){
			sql += " and s.vc_rank = :vc_rank ";
			queryMapParam.put("vc_rank", reportdto.getRank());
		}
		if(!departmentcode.equals("")){
			sql += " and instr(t.vc_department_code,:departmentcode)=1 ";
			queryMapParam.put("departmentcode", code);
		}
		sql+= "   GROUP BY s.pid, s.vc_cultural,"+sqlpart+") tmp GROUP BY tmp.code, tmp.vc_cultural ";
//		sql+=" GROUP BY "+sqlpart+", s.vc_cultural ";
		List list =createSQLQuery(sql, queryMapParam);
		return list;
	}
	
	/**
	 * 获取按年龄段查询集合
	 * @param reportdto
	 * @return
	 */
	public List getListbyAgegroup(StaffReportDto reportdto) {
		Map<String,Object> queryMapParam=new HashMap<String,Object>();
		String departmentcode = reportdto.getAreacode()==null?"":reportdto.getAreacode();
		String departmenttype = reportdto.getDepartmenttype()==null?"":reportdto.getDepartmenttype()+"";
		String code = AreaUtil.getDepartmentcode(departmentcode, departmenttype);
		int len = AreaUtil.getDepartmentAreaLen(reportdto.getDepartmenttype()+"");
		String sqlpart="";
		String agegroup = " decode(sign(round((to_number(sysdate - (s.vc_birth + 0)) / 365)) - 31),-1,2,decode(sign(round((to_number(sysdate - (s.vc_birth + 0)) / 365)) - 41),-1,3,decode(sign(round((to_number(sysdate - (s.vc_birth + 0)) / 365)) - 51),-1,4,1,5,0,5,0))) ";
		//if(len!=6){
			sqlpart = " substr(t.vc_department_code,1,"+len+") ";
		//}else{
			//sqlpart = " t.vc_department_code ";
		///}
		String sql = "SELECT   tmp.code, tmp.agegroup, COUNT (*) "
			+    " FROM (SELECT DISTINCT (s.pid), "+sqlpart+" code,"+agegroup+" agegroup " 
			+                     " FROM pm_bsc_staff_dept t, pm_bsc_staff_info s "
			+                    " WHERE t.vc_staff_id = s.pid "
			+                    "   AND s.vc_delete_flag = 0 ";
//		String sql = "SELECT   "+sqlpart+", "+agegroup+", COUNT (*) "
//		+    " FROM pm_bsc_staff_dept t, pm_bsc_staff_info s "
//		+   " WHERE t.vc_staff_id = s.pid ";
		queryMapParam.clear();
		if(reportdto.getObservType()!=null&&!reportdto.getObservType().equals(0L)){
			sql += " and exists(select 1 from pm_bsc_observing_staff os where s.pid =os.vc_ref_id and os.vc_observ_style = :observ_style) ";
			queryMapParam.put("observ_style", reportdto.getObservType());
		}
		if(reportdto.getFormation()!=null&&!reportdto.getFormation().equals(0L)){
			sql += " and s.vc_formation = :vc_formation ";
			queryMapParam.put("vc_formation", reportdto.getFormation());
		}
		if(reportdto.getRank()!=null&&!reportdto.getRank().equals(0L)){
			sql += " and s.vc_rank = :vc_rank ";
			queryMapParam.put("vc_rank", reportdto.getRank());
		}
		if(!departmentcode.equals("")){
			sql += " and instr(t.vc_department_code,:departmentcode)=1 ";
			queryMapParam.put("departmentcode", code);
		}
		sql+= "   GROUP BY s.pid, "+agegroup+","+sqlpart+") tmp GROUP BY tmp.code, tmp.agegroup ";
//		sql+=" GROUP BY "+sqlpart+", "+agegroup;
		List list =createSQLQuery(sql, queryMapParam);
		return list;
	}
}
