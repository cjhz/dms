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
import com.sunraysoft.hr.dao.department.deptobserving.PmBscObservingStaffDao;
import com.sunraysoft.hr.domain.entity.basic.PmBscAreaInfo;
import com.sunraysoft.hr.domain.entity.basic.PmBscObservingStaff;
import com.sunraysoft.hr.domain.entity.basic.PmBscStaffInfo;
import com.sunraysoft.hr.util.AreaUtil;
import com.sunraysoft.hr.util.DateUtil;
import com.sunraysoft.hr.web.data.dto.Page;

@Repository("PmBscObservingStaffDao")
public class PmBscObservingStaffDaoImpl extends HibernateDao<PmBscObservingStaff, Long>
		implements PmBscObservingStaffDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public Criteria setEntityParameter(Criteria criteria, Page page, PmBscObservingStaff pmBscObservingStaff) {
		
		if(StringUtils.isNotBlank(pmBscObservingStaff.getVcDeleteFlag()+"")) {
			criteria.add(Restrictions.eq("vcDeleteFlag", pmBscObservingStaff.getVcDeleteFlag()));
		}
		
		PmBscStaffInfo search = pmBscObservingStaff.getPmBscStaffInfo();
		if(search == null){
			return criteria;
		}
		criteria.createAlias("pmBscStaffInfo", "staff").add(Restrictions.eq("staff.vcDeleteFlag", pmBscObservingStaff.getVcDeleteFlag()));
		// 添加地区查询条件
		if (search.getVcOphone() !=null && !"".equals(search.getVcOphone())) {
			PmBscAreaInfo area = (PmBscAreaInfo)this.getSession().createQuery("FROM PmBscAreaInfo t WHERE t.pid =? ").setString(0, search.getVcOphone()).uniqueResult();
			String deptcode = area.getVcDepartmentCode();
			if(search.getIsShowAll() != null && search.getIsShowAll() == 1L){
				deptcode = AreaUtil.getDepartmentcode(deptcode, area.getVcDepartmentType().toString());
			}
			//criteria.createAlias("staff.pmBscStaffDepts", "sd").add(
			//		Restrictions.like("sd.vcDepartmentCode", deptcode+"%"));
			criteria.add(Restrictions.sqlRestriction(" exists(select * from pm_bsc_staff_dept d where d.vc_staff_id = {alias}.vc_ref_id and instr(d.vc_department_code,'"+deptcode+"')=1 ) "));
		}
		
		// 添加人员职级查询条件
		if (search.getDictRanks() != null && search.getDictRanks().getPid() != 0) {
			criteria.createAlias("staff.dictRanks", "dictr").add(
					Restrictions.eq("dictr.pid", search.getDictRanks().getPid()));
		}
		
		// 添加工作分工查询条件
		if (search.getVcWorkDivision() != null && !"".equals(search.getVcWorkDivision())) {
			criteria.add(createConditionWorkDivision(search.getVcWorkDivision(), null));
			
		}
		
		
		// 添加人员立功表彰查询条件
		if (search.getVcCardNo() != null && !"0".equals(search.getVcCardNo())) {
			criteria.createAlias("pmBscObserType", "obsers").add(
					Restrictions.eq("obsers.pid", Long.parseLong(search.getVcCardNo())));
		}
		
		// 添加文化程度查询条件
		if (search.getDictCulturals() != null && search.getDictCulturals().getPid() != 0) {
			criteria.createAlias("staff.dictCulturals", "dictc").add(
					Restrictions.eq("dictc.pid", search.getDictCulturals().getPid()));
		}
		
		// 添加性别查询条件
		if (search.getVcSex() != null && search.getVcSex() != 0) {
			criteria.add(Restrictions.eq("staff.vcSex", search.getVcSex()));
		}
		
		// 添加年龄情况查询条件
		if(search.getBbirth() != null && !"".equals(search.getBbirth())
				&& search.getEbirth() != null && !"".equals(search.getEbirth())){
			criteria.add(Restrictions.between("staff.vcBirth", DateUtil.queryAge(search.getEbirth().intValue()+1),
					DateUtil.queryAge(search.getBbirth().intValue())));
		}else{
			if(search.getBbirth() != null && !"".equals(search.getBbirth())){
				criteria.add(Restrictions.le("staff.vcBirth", DateUtil.queryAge(search.getBbirth().intValue())));
			}else if(search.getEbirth() != null && !"".equals(search.getEbirth())){
				criteria.add(Restrictions.ge("staff.vcBirth", DateUtil.queryAge(search.getEbirth().intValue()+1)));
			}
		}
		
		// 添加姓名查询条件
		if (search.getVcRealName() != null
				&& !"".equals(search.getVcRealName())) {
			criteria.add(Restrictions.like("staff.vcRealName", "%"+search.getVcRealName().trim()+"%"));
		}
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return super.setPageParameter(criteria, page);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PmBscObservingStaff> findAll(String orderBy, boolean isAsc,
			String propertyName, Object value, String objectname,
			String propname, Long objvalue) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = Restrictions.eq(propertyName, value);
		Criteria c = createCriteria(criterion);
		c.createAlias("pmBscStaffInfo", "dept").add(Restrictions.eq("dept."+propname, objvalue));
		if (isAsc) {
			c.addOrder(Order.asc(orderBy));
		} else {
			c.addOrder(Order.desc(orderBy));
		}
		return c.list();
	}
	
	
//	public static void main(String[] args) {
//		Criterion c = createConditionWorkDivision("111;112;113;", null);
//		System.err.println(c);
//	}
	
	private static Criterion createConditionWorkDivision(String s, Criterion c){
		if(s.endsWith(";"))
			s = s.substring(0, s.length()-1);
		if(s.indexOf(';')==-1){
			if(c==null){
				return Restrictions.like("staff.vcWorkDivision", "%"+s+"%");
			}else{
				return Restrictions.or(c, createConditionWorkDivision(s, null));
			}
		}else {
			String s1 = s.substring(0, s.lastIndexOf(';'));
			String s2 = s.substring(s.lastIndexOf(';')+1);
			Criterion newC = Restrictions.like("staff.vcWorkDivision", "%"+s2+"%");
			if(c==null){
				return createConditionWorkDivision(s1, newC);
			}else{
				return createConditionWorkDivision(s1, Restrictions.or(c, newC));
			}
		}
	}
	
}
