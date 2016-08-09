package com.sunraysoft.hr.service.baseinfo.bscobsertype.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunraysoft.hr.dao.base.BaseDao;
import com.sunraysoft.hr.dao.baseinfo.bscobsertype.PmBscObserTypeDao;
import com.sunraysoft.hr.domain.entity.basic.PmBscObserType;
import com.sunraysoft.hr.service.base.AbstractService;
import com.sunraysoft.hr.service.baseinfo.bscobsertype.PmBscObserTypeService;

@Service("PmBscObserTypeService")
public class PmBscObserTypeServiceimpl extends AbstractService<PmBscObserType, Long> implements PmBscObserTypeService {

	@Resource
	private PmBscObserTypeDao pmBscObserTypeDao;
	
	@Override
	public BaseDao<PmBscObserType, Long> getCurrentDao() {
		return pmBscObserTypeDao;
	}

	@Override
	public List<PmBscObserType> findAll(String orderBy, boolean isAsc,
			String propertyName, Object value) {
		return pmBscObserTypeDao.findAll(orderBy, isAsc, propertyName, value);
	}
	
	
}
