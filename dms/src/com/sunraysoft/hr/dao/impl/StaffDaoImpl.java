package com.sunraysoft.hr.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sunraysoft.hr.dao.StaffDao;
import com.sunraysoft.hr.dao.base.HibernateDao;
import com.sunraysoft.hr.domain.entity.basic.PmBscAreaInfo;
import com.sunraysoft.hr.domain.entity.basic.PmBscStaffInfo;
import com.sunraysoft.hr.util.AreaUtil;
import com.sunraysoft.hr.util.DateUtil;
import com.sunraysoft.hr.web.data.dto.Page;

@Repository("staffDao")
public class StaffDaoImpl extends HibernateDao<PmBscStaffInfo, Long> implements StaffDao  {

	@SuppressWarnings("unchecked")
	@Override
	public Criteria setEntityParameter(Criteria criteria, Page page, PmBscStaffInfo search) {
		
		if(StringUtils.isNotBlank(search.getVcDeleteFlag()+"")) {
			criteria.add(Restrictions.eq("vcDeleteFlag", search.getVcDeleteFlag()));
		}
//		if(search.getVcRealName()!=null && !"".equals(search.getVcRealName())){
//			criteria.add(Restrictions.or(Restrictions.like("vcRealName", "%"+search
//					.getVcRealName().trim()+"%"), Restrictions.like("vcCardNo", "%"+search
//					.getVcRealName().trim()+"%")));
//		}
		// 添加地区查询条件
		if (search.getVcOphone() !=null && !"".equals(search.getVcOphone())) {
			PmBscAreaInfo area = (PmBscAreaInfo)this.getSession().createQuery("FROM PmBscAreaInfo t WHERE t.pid =? ").setString(0, search.getVcOphone()).uniqueResult();
			String deptcode = area.getVcDepartmentCode();
			if(search.getIsShowAll() != null && search.getIsShowAll() == 1L){
				deptcode = AreaUtil.getDepartmentcode(deptcode, area.getVcDepartmentType().toString());
			}
			//criteria.createAlias("pmBscStaffDepts", "sd").add(
			//		Restrictions.like("sd.vcDepartmentCode", deptcode+"%"));
			criteria.add(Restrictions.sqlRestriction(" exists(select * from pm_bsc_staff_dept d where d.vc_staff_id = {alias}.pid and instr(d.vc_department_code,'"+deptcode+"')=1 ) "));
		}
		
		// 添加人员职级查询条件
		if (search.getDictRanks() != null && search.getDictRanks().getPid() != 0) {
			criteria.createAlias("dictRanks", "dictr").add(
					Restrictions.eq("dictr.pid", search.getDictRanks().getPid()));
		}
		
		// 添加工作分工查询条件
		if (search.getVcWorkDivision() != null && !"".equals(search.getVcWorkDivision())) {
			criteria.add(createConditionWorkDivision(search.getVcWorkDivision(), null));
		}
		
		
		// 添加人员立功表彰查询条件
		if (search.getVcCardNo() != null && !"0".equals(search.getVcCardNo())) {
			//criteria.createAlias("pmBscObservings", "obsers").createAlias("obsers.pmBscObserType", "obsert").add(
			//		Restrictions.eq("obsert.pid", Long.parseLong(search.getVcCardNo())));
			criteria.add(Restrictions.sqlRestriction(" exists(select * from pm_bsc_observing_staff o  where o.vc_ref_id = {alias}.pid and o.vc_observ_style = "+search.getVcCardNo()+") "));
			
		}
		
		// 添加文化程度查询条件
		if (search.getDictCulturals() != null && search.getDictCulturals().getPid() != 0) {
			criteria.createAlias("dictCulturals", "dictc").add(
					Restrictions.eq("dictc.pid", search.getDictCulturals().getPid()));
		}
		
		// 添加性别查询条件
		if (search.getVcSex() != null && search.getVcSex() != 0) {
			criteria.add(Restrictions.eq("vcSex", search.getVcSex()));
		}
		
		// 添加年龄情况查询条件
		if(search.getBbirth() != null && !"".equals(search.getBbirth())
				&& search.getEbirth() != null && !"".equals(search.getEbirth())){
			criteria.add(Restrictions.between("vcBirth", DateUtil.queryAge(search.getEbirth().intValue()+1),
					DateUtil.queryAge(search.getBbirth().intValue())));
		}else{
			if(search.getBbirth() != null && !"".equals(search.getBbirth())){
				criteria.add(Restrictions.le("vcBirth", DateUtil.queryAge(search.getBbirth().intValue())));
			}else if(search.getEbirth() != null && !"".equals(search.getEbirth())){
				criteria.add(Restrictions.ge("vcBirth", DateUtil.queryAge(search.getEbirth().intValue()+1)));
			}
		}
		
		// 添加姓名查询条件
		if (search.getVcRealName() != null
				&& !"".equals(search.getVcRealName())) {
			criteria.add(Restrictions.like("vcRealName", "%"+search.getVcRealName().trim()+"%"));
		}
		//人员调动查看使用
		if(search.getVcExtend2()!=null&&search.getVcExtend2().equals("filter")){
			criteria.add(Restrictions.sqlRestriction(" exists(select * from pm_mng_change_info d where d.vc_staff_id = {alias}.pid and d.vc_delete_flag="+search.getVcDeleteFlag()+" ) "));
			// 人员调动查看 添加和所属机构关联的条件，去除没有所属机构的人员也显示
			criteria.add(Restrictions.sqlRestriction(" EXISTS ( select 1 FROM pm_bsc_staff_dept sd WHERE sd.VC_STAFF_ID = {alias}.pid ) "));
		}
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return super.setPageParameter(criteria, page);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<PmBscStaffInfo> pageQuery(PmBscStaffInfo search, int pageNum, int pageSize) {
		Criteria criteria = createCriteria(search);
		Integer recordCount = (Integer) criteria.setProjection(
				Projections.rowCount()).uniqueResult();
		Page page = new Page(pageNum, recordCount, pageSize);
		List list = createCriteria(search)
			.addOrder(Order.desc("pid")).
			setFirstResult(page.getBeginRecordIndex())
			.setMaxResults(page.getPerPageRecordCount())
			.list();
		page.setResultSet(list);
		return page;
	}
	
	private Criteria createCriteria(PmBscStaffInfo search) {
		Criteria criteria = getSession().createCriteria(PmBscStaffInfo.class);
		
		// 添加地区查询条件
		if (search.getVcOphone() !=null && !"".equals(search.getVcOphone())) {
			PmBscAreaInfo area = (PmBscAreaInfo)this.getSession().createQuery("FROM PmBscAreaInfo t WHERE t.pid =? ").setString(0, search.getVcOphone()).uniqueResult();
			String deptcode = AreaUtil.getDepartmentcode(area.getVcDepartmentCode(), area.getVcDepartmentType().toString());
//			criteria.createAlias("pmBscStaffDepts", "sd").add(
//					Restrictions.like("sd.vcDepartmentCode", deptcode+"%"));
			criteria.add(Restrictions.sqlRestriction(" exists(select * from pm_bsc_staff_dept d where d.vc_staff_id = {alias}.pid and instr(d.vc_department_code,'"+deptcode+"')=1 ) "));
		}
		
		// 添加人员职级查询条件
		if (search.getDictRanks() != null && search.getDictRanks().getPid() != 0) {
			criteria.createAlias("dictRanks", "dictr").add(
					Restrictions.eq("dictr.pid", search.getDictRanks().getPid()));
		}
		
		// 添加工作分工查询条件
		if (search.getVcWorkDivision() != null && !"".equals(search.getVcWorkDivision())) {
			criteria.add(createConditionWorkDivision(search.getVcWorkDivision(), null));
		}
		
		
		// 添加人员立功表彰查询条件
		if (search.getVcCardNo() != null && !"0".equals(search.getVcCardNo())) {
//			criteria.createAlias("pmBscObservings", "obsers").createAlias("obsers.pmBscObserType", "obsert").add(
//					Restrictions.eq("obsert.pid", Long.parseLong(search.getVcCardNo())));
			criteria.add(Restrictions.sqlRestriction(" exists(select * from pm_bsc_observing_staff o  where o.vc_ref_id = {alias}.pid and o.vc_observ_style = "+search.getVcCardNo()+") "));
		}
		
		// 添加文化程度查询条件
		if (search.getDictCulturals() != null && search.getDictCulturals().getPid() != 0) {
			criteria.createAlias("dictCulturals", "dictc").add(
					Restrictions.eq("dictc.pid", search.getDictCulturals().getPid()));
		}
		
		// 添加性别查询条件
		if (search.getVcSex() != null && search.getVcSex() != 0) {
			criteria.add(Restrictions.eq("vcSex", search.getVcSex()));
		}
		
		// 添加年龄情况查询条件
		if(search.getBbirth() != null && !"".equals(search.getBbirth())
				&& search.getEbirth() != null && !"".equals(search.getEbirth())){
			criteria.add(Restrictions.between("vcBirth", DateUtil.queryAge(search.getEbirth().intValue()),
					DateUtil.queryAge(search.getBbirth().intValue())));
		}else{
			if(search.getBbirth() != null && !"".equals(search.getBbirth())){
				criteria.add(Restrictions.le("vcBirth", DateUtil.queryAge(search.getBbirth().intValue())));
			}else if(search.getEbirth() != null && !"".equals(search.getEbirth())){
				criteria.add(Restrictions.ge("vcBirth", DateUtil.queryAge(search.getEbirth().intValue())));
			}
		}
		
		// 添加姓名查询条件
		if (search.getVcRealName() != null
				&& !"".equals(search.getVcRealName())) {
			criteria.add(Restrictions.like("vcRealName", "%"+search.getVcRealName().trim()+"%"));
		}
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria;
	}
	
	private static Criterion createConditionWorkDivision(String s, Criterion c){
		if(s.endsWith(";"))
			s = s.substring(0, s.length()-1);
		if(s.indexOf(';')==-1){
			if(c==null){
				return Restrictions.like("vcWorkDivision", "%"+s+"%");
			}else{
				return Restrictions.or(c, createConditionWorkDivision(s, null));
			}
		}else {
			String s1 = s.substring(0, s.lastIndexOf(';'));
			String s2 = s.substring(s.lastIndexOf(';')+1);
			Criterion newC = Restrictions.like("vcWorkDivision", "%"+s2+"%");
			if(c==null){
				return createConditionWorkDivision(s1, newC);
			}else{
				return createConditionWorkDivision(s1, Restrictions.or(c, newC));
			}
		}
	}
	
}
