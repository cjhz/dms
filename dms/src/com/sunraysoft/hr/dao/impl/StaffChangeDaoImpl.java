package com.sunraysoft.hr.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sunraysoft.hr.dao.StaffChangeDao;
import com.sunraysoft.hr.dao.base.HibernateDao;
import com.sunraysoft.hr.domain.entity.manage.PmMngChangeInfo;
import com.sunraysoft.hr.web.data.dto.Page;

@Repository("staffChangeDao")
public class StaffChangeDaoImpl extends HibernateDao<PmMngChangeInfo, Long> implements StaffChangeDao {
	@Override
	public Criteria setEntityParameter(Criteria criteria, Page page, PmMngChangeInfo search) {
		
		if(StringUtils.isNotBlank(search.getVcDeleteFlag()+"")) {
			criteria.add(Restrictions.eq("vcDeleteFlag", search.getVcDeleteFlag()));
		}
		return super.setPageParameter(criteria, page);
	}
}
