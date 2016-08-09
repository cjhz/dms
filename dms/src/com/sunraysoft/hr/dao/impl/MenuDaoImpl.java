package com.sunraysoft.hr.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sunraysoft.hr.constant.Constant;
import com.sunraysoft.hr.dao.MenuDao;
import com.sunraysoft.hr.dao.base.HibernateDao;
import com.sunraysoft.hr.domain.entity.manage.PmMngMenu;
@Repository("menuDao")
public class MenuDaoImpl extends HibernateDao<PmMngMenu, Long> implements MenuDao {

	public List<PmMngMenu> findAll() {
		List list = getSession().createCriteria(PmMngMenu.class)
			.add(Restrictions.eq("vcDeleteFlag", Long.valueOf(Constant.SYSTEM_DATA_DELETE_FLAG_NORMAL)))
			.list();
		return list;
	}
	
	@Override
	public List<PmMngMenu> loadRoots() {
		return getSession().createQuery("from PmMngMenu m where m.parent is null and m.vcDeleteFlag = :deleteFlag")
			.setParameter("deleteFlag", Long.valueOf(Constant.SYSTEM_DATA_DELETE_FLAG_NORMAL))
			.list();
	}
}
