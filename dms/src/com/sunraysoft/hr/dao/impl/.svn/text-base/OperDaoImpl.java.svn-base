package com.sunraysoft.hr.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sunraysoft.hr.constant.Constant;
import com.sunraysoft.hr.dao.OperDao;
import com.sunraysoft.hr.dao.base.HibernateDao;
import com.sunraysoft.hr.domain.entity.manage.PmMngOper;
@Repository("operDao")
public class OperDaoImpl extends HibernateDao<PmMngOper, Long> implements OperDao {

	public List<PmMngOper> findAll() {
		return getSession().createCriteria(PmMngOper.class)
		.add(Restrictions.eq("vcDeleteFlag", Long.valueOf(Constant.SYSTEM_DATA_DELETE_FLAG_NORMAL)))
		.list();
	}
}
