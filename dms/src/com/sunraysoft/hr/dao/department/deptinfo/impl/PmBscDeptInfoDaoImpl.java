package com.sunraysoft.hr.dao.department.deptinfo.impl;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sunraysoft.hr.dao.base.HibernateDao;
import com.sunraysoft.hr.dao.department.deptinfo.PmBscDeptInfoDao;
import com.sunraysoft.hr.domain.entity.basic.PmBscAreaInfo;
import com.sunraysoft.hr.domain.entity.basic.PmBscDeptInfo;
import com.sunraysoft.hr.util.AreaUtil;
import com.sunraysoft.hr.web.data.dto.Page;

@Repository("PmBscDeptInfoDao")
public class PmBscDeptInfoDaoImpl extends HibernateDao<PmBscDeptInfo, Long>
		implements PmBscDeptInfoDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public Criteria setEntityParameter(Criteria criteria, Page page, PmBscDeptInfo search) {
		
		if(StringUtils.isNotBlank(search.getVcDeleteFlag()+"")) {
			criteria.add(Restrictions.eq("vcDeleteFlag", search.getVcDeleteFlag()));
		}
		
		// 添加地区查询条件
		if (search.getPmBscAreaInfo() !=null && search.getPmBscAreaInfo().getPid() != 0) {
			if(search.getPmBscAreaInfo().getVcExtend1()!=null && "loadDeptInfos".equals(search.getPmBscAreaInfo().getVcExtend1())){
				criteria.createAlias("pmBscAreaInfo", "area").add(
						Restrictions.eq("area.pid", search.getPmBscAreaInfo().getPid()));
			}else{
			PmBscAreaInfo area = (PmBscAreaInfo)this.getSession().createQuery("FROM PmBscAreaInfo t WHERE t.pid =? ").setLong(0, search.getPmBscAreaInfo().getPid()).uniqueResult();
			String deptcode = AreaUtil.getDepartmentcode(area.getVcDepartmentCode(), area.getVcDepartmentType().toString());
			criteria.createAlias("pmBscAreaInfo", "area").add(
					Restrictions.like("area.vcDepartmentCode", deptcode+"%"));
			}
		}
		
		// 添加机构性质查询条件
		if (search.getPmBscDeptType() != null && search.getPmBscDeptType().getPid() != 0) {
			criteria.createAlias("pmBscDeptType", "dept").add(
					Restrictions.eq("dept.pid", search.getPmBscDeptType().getPid()));
		}
		
		// 添加机构职级查询条件
		if (search.getPmBscDict() != null && search.getPmBscDict().getPid() != 0) {
			criteria.createAlias("pmBscDict", "dict").add(
					Restrictions.eq("dict.pid", search.getPmBscDict().getPid()));
		}
		
		// 添加机构立功表彰查询条件
		if (search.getVcRender() != null && !"0".equals(search.getVcRender())) {
			//criteria.createAlias("pmBscObservings", "obsers").createAlias("obsers.pmBscObserType", "obsert").add(
			//		Restrictions.eq("obsert.pid", Long.parseLong(search.getVcRender())));
			criteria.add(Restrictions.sqlRestriction(" exists(select * from pm_bsc_observing o where o.vc_ref_id = {alias}.pid and o.vc_observ_style = "+search.getVcRender()+") "));
		}
		
		// 添加机构编号或者机构名称
		if (search.getVcDeptName() != null
				&& !"".equals(search.getVcDeptName())) {
			criteria.add( Restrictions.like("vcDeptName", "%"+search
					.getVcDeptName().trim()+"%"));
		}
//		
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return super.setPageParameter(criteria, page);
	}
	
}
