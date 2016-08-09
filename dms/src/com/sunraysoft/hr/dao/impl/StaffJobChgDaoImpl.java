package com.sunraysoft.hr.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sunraysoft.hr.dao.StaffJobChgDao;
import com.sunraysoft.hr.dao.base.HibernateDao;
import com.sunraysoft.hr.domain.entity.basic.PmBscJobChange;
import com.sunraysoft.hr.domain.entity.basic.PmBscRankChange;
import com.sunraysoft.hr.web.data.dto.Page;

@Repository("staffJobChgDao")
public class StaffJobChgDaoImpl extends HibernateDao<PmBscJobChange, Long> implements StaffJobChgDao  {
	@Override
	public Criteria setEntityParameter(Criteria criteria, Page page, PmBscJobChange search) {
		
		if(StringUtils.isNotBlank(search.getVcDeleteFlag()+"")) {
			criteria.add(Restrictions.eq("vcDeleteFlag", search.getVcDeleteFlag()));
		}
		if(search.getPmBscStaffInfo()!=null&&search.getPmBscStaffInfo().getPid()!=null) {
			criteria.add(Restrictions.eq("pmBscStaffInfo.pid", search.getPmBscStaffInfo().getPid()));
		}
		return super.setPageParameter(criteria, page);
	}
	
	/**
	 * 获取最新的一条记录
	 * @param propertyNames
	 * @param values
	 * @return
	 */
	public List<PmBscJobChange> getMaxResult(final Object[] values){
		List<PmBscJobChange> ranklist = getHibernateTemplate().find("from PmBscJobChange t where t.pid = (select max(pid) from PmBscJobChange r where r.vcDeleteFlag=? and r.pmBscStaffInfo.pid=?)",values);
		return ranklist;
	}
	
	/**
	 * 查询第二新数据
	 * @param values
	 * @return
	 */
	public Long getSecondPid(final Object[] values){
		Map<String,Object> queryMapParam=new HashMap<String,Object>();
		String sql="select pid from (select row_number() over(order by t.pid desc) as p,t.pid from pm_bsc_job_change t where t.vc_delete_flag=:vc_delete_flag and t.vc_staff_id=:vc_staff_id) where p=2";
		queryMapParam.put("vc_delete_flag", values[0]);
		queryMapParam.put("vc_staff_id", values[1]);
		List list =createSQLQuery(sql, queryMapParam);
		Object o = (list==null||list.size()==0)?0L:(list.get(0)==null?0L:list.get(0));
		Long pid =Long.parseLong(o.toString());
		return pid;
	}
}
