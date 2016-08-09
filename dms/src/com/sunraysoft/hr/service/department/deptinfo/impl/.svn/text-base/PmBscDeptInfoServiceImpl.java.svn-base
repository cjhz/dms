package com.sunraysoft.hr.service.department.deptinfo.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunraysoft.hr.dao.base.BaseDao;
import com.sunraysoft.hr.dao.department.deptinfo.PmBscDeptInfoDao;
import com.sunraysoft.hr.domain.entity.basic.PmBscDeptInfo;
import com.sunraysoft.hr.service.base.AbstractService;
import com.sunraysoft.hr.service.department.deptinfo.PmBscDeptInfoService;

@Service("PmBscDeptInfoService")
public class PmBscDeptInfoServiceImpl extends
		AbstractService<PmBscDeptInfo, Long> implements PmBscDeptInfoService {

	@Resource
	private PmBscDeptInfoDao pmBscDeptInfoDao;

	@Override
	public BaseDao<PmBscDeptInfo, Long> getCurrentDao() {
		return pmBscDeptInfoDao;
	}

}
