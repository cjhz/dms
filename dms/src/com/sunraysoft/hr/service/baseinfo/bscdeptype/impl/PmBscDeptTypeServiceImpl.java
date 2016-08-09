package com.sunraysoft.hr.service.baseinfo.bscdeptype.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunraysoft.hr.dao.base.BaseDao;
import com.sunraysoft.hr.dao.baseinfo.bscdeptype.PmBscDeptTypeDao;
import com.sunraysoft.hr.domain.entity.basic.PmBscDeptType;
import com.sunraysoft.hr.service.base.AbstractService;
import com.sunraysoft.hr.service.baseinfo.bscdeptype.PmBscDeptTypeService;

@Service("PmBsDeptTypeService")
public class PmBscDeptTypeServiceImpl extends AbstractService<PmBscDeptType, Long> implements PmBscDeptTypeService{

	@Resource
	private PmBscDeptTypeDao pmBscDeptTypeDao;
	
	@Override
	public BaseDao<PmBscDeptType, Long> getCurrentDao() {
		return pmBscDeptTypeDao;
	}

	
}
