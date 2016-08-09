package com.sunraysoft.hr.service.department.deptobserving.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunraysoft.hr.dao.base.BaseDao;
import com.sunraysoft.hr.dao.department.deptobserving.PmBscObservingDao;
import com.sunraysoft.hr.domain.entity.basic.PmBscObserving;
import com.sunraysoft.hr.service.base.AbstractService;
import com.sunraysoft.hr.service.department.deptobserving.PmBscObservingService;

@Service("PmBscObservingService")
public class PmBscObservingServiceImpl extends
		AbstractService<PmBscObserving, Long> implements PmBscObservingService {

	@Resource
	private PmBscObservingDao pmBscObservingDao;
	
	@Override
	public BaseDao<PmBscObserving, Long> getCurrentDao() {
		return pmBscObservingDao;
	}
	
	@Override
	public List<PmBscObserving> findAll(String orderBy, boolean isAsc,
			String propertyName, Object value, String objectname,
			String propname, Long objvalue) {
		return pmBscObservingDao.findAll(orderBy, isAsc, propertyName, 
				value, objectname, propname, objvalue);
		
	}

}
