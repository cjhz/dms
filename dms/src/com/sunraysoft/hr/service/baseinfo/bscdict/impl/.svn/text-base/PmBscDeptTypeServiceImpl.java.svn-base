package com.sunraysoft.hr.service.baseinfo.bscdict.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunraysoft.hr.dao.base.BaseDao;
import com.sunraysoft.hr.dao.baseinfo.bscdict.PmBscDictDao;
import com.sunraysoft.hr.domain.entity.basic.PmBscDict;
import com.sunraysoft.hr.service.base.AbstractService;
import com.sunraysoft.hr.service.baseinfo.bscdict.PmBscDictService;

@Service("PmBscDictService")
public class PmBscDeptTypeServiceImpl extends AbstractService<PmBscDict, Long> implements PmBscDictService{

	@Resource
	private PmBscDictDao pmBscDictDao;
	
	@Override
	public BaseDao<PmBscDict, Long> getCurrentDao() {
		return pmBscDictDao;
	}

	@Override
	public List<PmBscDict> findAll(String orderBy, boolean isAsc,
			String propertyName, Object value) {
		return pmBscDictDao.findAll(orderBy, isAsc, propertyName, value);
	}
}
