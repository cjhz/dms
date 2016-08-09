package com.sunraysoft.hr.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sunraysoft.hr.dao.StaffRankChgDao;
import com.sunraysoft.hr.dao.base.HibernateDao;
import com.sunraysoft.hr.domain.entity.basic.PmBscRankChange;
import com.sunraysoft.hr.web.data.dto.Page;

@Repository("staffRankChgDao")
public class StaffRankChgDaoImpl extends HibernateDao<PmBscRankChange, Long> implements StaffRankChgDao  {
	@Override
	public Criteria setEntityParameter(Criteria criteria, Page page, PmBscRankChange search) {
		
		if(StringUtils.isNotBlank(search.getVcDeleteFlag()+"")) {
			criteria.add(Restrictions.eq("vcDeleteFlag", search.getVcDeleteFlag()));
		}
		if(search.getPmBscStaffInfo()!=null&&search.getPmBscStaffInfo().getPid()!=null) {
			criteria.add(Restrictions.eq("pmBscStaffInfo.pid", search.getPmBscStaffInfo().getPid()));
		}
		if(StringUtils.isNotBlank(search.getVcType()+"")) {//1：职级:2：职务:3：编制
			criteria.add(Restrictions.eq("vcType", search.getVcType()));
		}
		return super.setPageParameter(criteria, page);
	}
	
	/**
	 * 获取最新的一条记录
	 * @param propertyNames
	 * @param values
	 * @return
	 */
	public List<PmBscRankChange> getMaxResult(final Object[] values){
		List<PmBscRankChange> ranklist = getHibernateTemplate().find("from PmBscRankChange t where t.pid = (select max(pid) from PmBscRankChange r where r.vcDeleteFlag=? and vcType=? and r.pmBscStaffInfo.pid=?)",values);
		return ranklist;
	}
	
	/**
	 * 查询第二新数据
	 * @param values
	 * @return
	 */
	public Long getSecondPid(final Object[] values){
		Map<String,Object> queryMapParam=new HashMap<String,Object>();
		String sql="select pid from (select row_number() over(order by t.pid desc) as p,t.pid from pm_bsc_rank_change t where t.vc_delete_flag=:vc_delete_flag and t.vc_type=:vc_type and t.vc_staff_id=:vc_staff_id) where p=2";
		queryMapParam.put("vc_delete_flag", values[0]);
		queryMapParam.put("vc_type", values[1]);
		queryMapParam.put("vc_staff_id", values[2]);
		List list =createSQLQuery(sql, queryMapParam);
		Object o = (list==null||list.size()==0)?0L:(list.get(0)==null?0L:list.get(0));
		Long pid =Long.parseLong(o.toString());
		return pid;
	}
}
