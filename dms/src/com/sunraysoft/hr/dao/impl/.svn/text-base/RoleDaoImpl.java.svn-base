package com.sunraysoft.hr.dao.impl;

import java.util.List;
import java.util.Set;

import javax.management.relation.Role;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sunraysoft.hr.constant.BizConstant;
import com.sunraysoft.hr.dao.RoleDao;
import com.sunraysoft.hr.dao.base.HibernateDao;
import com.sunraysoft.hr.domain.entity.manage.PmMngOper;
import com.sunraysoft.hr.domain.entity.manage.PmMngRoleInfo;
import com.sunraysoft.hr.domain.entity.manage.PmMngRoleOper;
import com.sunraysoft.hr.web.data.dto.Page;

@Repository("roleDao")
public class RoleDaoImpl extends HibernateDao<PmMngRoleInfo, Long> implements RoleDao {
	
	

	@Override
	public PmMngRoleInfo findByRoleName(String roleName) {
		List<PmMngRoleInfo> list = getSession().createCriteria(PmMngRoleInfo.class)
		.add(Restrictions.eq("vcRoleName", roleName))
		.add(Restrictions.eq("vcDeleteFlag", Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL)))
		.list();
		if(list == null || list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}
	
	public void persistRoleOperRelation(PmMngRoleInfo role, List<Long> operIdList) {
		Set<PmMngRoleOper> pairList = role.getPmMngRoleOpers();
		getHibernateTemplate().deleteAll(pairList);
		role.getPmMngRoleOpers().clear();
		
		for(Long operId : operIdList) {
			PmMngRoleOper rela = new PmMngRoleOper();
			PmMngOper oper = new PmMngOper();
			oper.setPid(operId);
			rela.setPmMngOper(oper);
			rela.setPmMngRoleInfo(role);
			
			getHibernateTemplate().saveOrUpdate(rela);
		}
	}

	@Override
	public Criteria setEntityParameter(Criteria criteria, Page page, PmMngRoleInfo search) {
		if (StringUtils.isNotBlank(search.getVcRoleName())) {
			search.setVcRoleName( StringUtils.trimToEmpty(search.getVcRoleName()) );
			criteria.add(Restrictions.like("vcRoleName", search.getVcRoleName(), MatchMode.ANYWHERE));
		}
		if(StringUtils.isNotBlank(search.getVcDeleteFlag()+"")) {
			criteria.add(Restrictions.eq("vcDeleteFlag", search.getVcDeleteFlag()));
		}
		return super.setPageParameter(criteria, page);
	}
	
}
