package com.sunraysoft.hr.dao.department.deptobserving.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.sunraysoft.hr.dao.base.HibernateDao;
import com.sunraysoft.hr.dao.department.deptobserving.PmBscObservingDao;
import com.sunraysoft.hr.domain.entity.basic.PmBscAreaInfo;
import com.sunraysoft.hr.domain.entity.basic.PmBscObserving;
import com.sunraysoft.hr.util.AreaUtil;
import com.sunraysoft.hr.web.data.dto.Page;

@Repository("PmBscObservingDao")
public class PmBscObservingDaoImpl extends HibernateDao<PmBscObserving, Long>
		implements PmBscObservingDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public Criteria setEntityParameter(Criteria criteria, Page page, PmBscObserving search) {
		
		if(StringUtils.isNotBlank(search.getVcDeleteFlag()+"")) {
			criteria.add(Restrictions.eq("vcDeleteFlag", search.getVcDeleteFlag()));
		}
		
		if(search.getPmBscDeptInfo() != null){
			criteria.createAlias("pmBscDeptInfo", "dept");
		}
		// 添加地区查询条件
		if (search.getPmBscDeptInfo() != null && search.getPmBscDeptInfo().getPmBscAreaInfo() !=null && search.getPmBscDeptInfo().getPmBscAreaInfo().getPid() != 0) {
			PmBscAreaInfo area = (PmBscAreaInfo)this.getSession().createQuery("FROM PmBscAreaInfo t WHERE t.pid =? ").setLong(0, search.getPmBscDeptInfo().getPmBscAreaInfo().getPid()).uniqueResult();
			String deptcode = AreaUtil.getDepartmentcode(area.getVcDepartmentCode(), area.getVcDepartmentType().toString());
			criteria.createAlias("dept.pmBscAreaInfo", "area").add(
					Restrictions.like("area.vcDepartmentCode", deptcode+"%"));
		}
		
		// 添加机构性质查询条件
		if (search.getPmBscDeptInfo() != null && search.getPmBscDeptInfo().getPmBscDeptType() != null && search.getPmBscDeptInfo().getPmBscDeptType().getPid() != 0) {
			criteria.createAlias("dept.pmBscDeptType", "deptype").add(
					Restrictions.eq("deptype.pid", search.getPmBscDeptInfo().getPmBscDeptType().getPid()));
		}
		
		// 添加机构职级查询条件
		if (search.getPmBscDeptInfo() != null && search.getPmBscDeptInfo().getPmBscDict() != null && search.getPmBscDeptInfo().getPmBscDict().getPid() != 0) {
			criteria.createAlias("dept.pmBscDict", "dict").add(
					Restrictions.eq("dict.pid", search.getPmBscDeptInfo().getPmBscDict().getPid()));
		}
		
		// 添加机构立功表彰查询条件
		if (search.getPmBscObserType() != null && search.getPmBscObserType().getPid() != 0) {
			criteria.createAlias("pmBscObserType", "obsert").add(
					Restrictions.eq("obsert.pid", search.getPmBscObserType().getPid()));
		}
		
		// 添加机构编号或者机构名称
		if (search.getPmBscDeptInfo() != null && search.getPmBscDeptInfo().getVcDeptName() != null
				&& !"".equals(search.getPmBscDeptInfo().getVcDeptName())) {
			String deptnameorcode = search.getPmBscDeptInfo().getVcDeptName().trim();
			criteria.add(Restrictions.like("dept.vcDeptName", "%"+deptnameorcode+"%"));
		}
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return super.setPageParameter(criteria, page);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PmBscObserving> findAll(String orderBy, boolean isAsc,
			String propertyName, Object value, String objectname,
			String propname, Long objvalue) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = Restrictions.eq(propertyName, value);
		Criteria c = createCriteria(criterion);
		c.createAlias("pmBscDeptInfo", "dept").add(Restrictions.eq("dept."+propname, objvalue));
		if (isAsc) {
			c.addOrder(Order.asc(orderBy));
		} else {
			c.addOrder(Order.desc(orderBy));
		}
		return c.list();
	}
	
}
