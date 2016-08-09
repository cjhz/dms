package com.sunraysoft.hr.dao.log.impl;

import org.springframework.stereotype.Repository;

import com.sunraysoft.hr.dao.base.HibernateDao;
import com.sunraysoft.hr.dao.log.PmMngServerLogDao;
import com.sunraysoft.hr.domain.entity.manage.PmMngServerLog;

@Repository("PmMngServerLogDao")
public class PmMngServerLogDaoImpl extends HibernateDao<PmMngServerLog, Long> implements
	PmMngServerLogDao {
	
}
