package com.sunraysoft.hr.service.log.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunraysoft.hr.dao.base.BaseDao;
import com.sunraysoft.hr.dao.log.PmMngServerLogDao;
import com.sunraysoft.hr.domain.entity.manage.PmMngServerLog;
import com.sunraysoft.hr.service.base.AbstractService;
import com.sunraysoft.hr.service.log.PmMngServerLogService;

@Service("PmMngServerLogService")
public class PmBscDeptInfoServiceImpl extends
		AbstractService<PmMngServerLog, Long> implements PmMngServerLogService {

	@Resource
	private PmMngServerLogDao pmMngServerLogDao;

	@Override
	public BaseDao<PmMngServerLog, Long> getCurrentDao() {
		return pmMngServerLogDao;
	}
}
