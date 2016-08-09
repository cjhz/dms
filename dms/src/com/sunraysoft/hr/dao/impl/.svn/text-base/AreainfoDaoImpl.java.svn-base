package com.sunraysoft.hr.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.sunraysoft.hr.dao.AreainfoDao;
import com.sunraysoft.hr.dao.base.HibernateDao;
import com.sunraysoft.hr.domain.entity.basic.PmBscAreaInfo;
import com.sunraysoft.hr.util.AreaUtil;

@Repository("areaInfodao")
public class AreainfoDaoImpl extends HibernateDao<PmBscAreaInfo, Long> implements AreainfoDao {
	
	public List<Object[]> findContainAreaCode(PmBscAreaInfo area){
		List<PmBscAreaInfo> arealist = new ArrayList<PmBscAreaInfo>();
		String sql = "";
		int len = AreaUtil.getDepartmentAreaLen(area.getVcDepartmentType()+"");
//		if (area.getVcDepartmentType().equals(3L)|| area.getVcDepartmentType().equals(4L)) {//大队或者派出所
//			sql = "SELECT D.PID,D.VC_DEPARTMENT_NAME, D.VC_DEPARTMENT_CODE,D.VC_DEPARTMENT_TYPE,D.VC_AREA_NAME,D.VC_DEPARTMENT_CODE AREACODE FROM PM_BSC_AREA_INFO D WHERE D.PID ="+area.getPid()+" or  D.VC_LEVEL_CODE ='"+area.getPid()+"' order by d.VC_DEPARTMENT_TYPE,d.VC_DEPARTMENT_CODE";	
//		}else{
//			if(len!=6){//为全部或省时
				sql = "SELECT D.PID,D.VC_DEPARTMENT_NAME, D.VC_DEPARTMENT_CODE,D.VC_DEPARTMENT_TYPE,D.VC_AREA_NAME,SUBSTR(D.VC_DEPARTMENT_CODE,1,"+len+") AREACODE FROM PM_BSC_AREA_INFO D WHERE D.PID ="+area.getPid()+" or  D.VC_LEVEL_CODE ='"+area.getPid()+"' order by d.VC_DEPARTMENT_TYPE,d.VC_DEPARTMENT_CODE";
//			}else{//为市时
//				sql = "SELECT D.PID,D.VC_DEPARTMENT_NAME, D.VC_DEPARTMENT_CODE,D.VC_DEPARTMENT_TYPE,D.VC_AREA_NAME,D.VC_DEPARTMENT_CODE AREACODE FROM PM_BSC_AREA_INFO D WHERE D.VC_LEVEL_CODE ='"+area.getPid()+"' order by d.VC_DEPARTMENT_TYPE,d.VC_DEPARTMENT_CODE";
//			}
//		}
		SQLQuery query = this.getSession().createSQLQuery(sql);
		return query.list();
	}
}
