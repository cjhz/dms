package com.sunraysoft.hr.service.department.deptobserving.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunraysoft.hr.dao.base.BaseDao;
import com.sunraysoft.hr.dao.department.deptobserving.PmBscObservingStaffDao;
import com.sunraysoft.hr.domain.entity.basic.PmBscObservingStaff;
import com.sunraysoft.hr.service.base.AbstractService;
import com.sunraysoft.hr.service.department.deptobserving.PmBscObservingStaffService;

@Service("PmBscObservingStaffService")
public class PmBscObservingStaffServiceImpl extends
		AbstractService<PmBscObservingStaff, Long> implements PmBscObservingStaffService {

	@Resource
	private PmBscObservingStaffDao pmBscObservingStaffDao;
	
	@Override
	public BaseDao<PmBscObservingStaff, Long> getCurrentDao() {
		return pmBscObservingStaffDao;
	}
	
	@Override
	public List<PmBscObservingStaff> findAll(String orderBy, boolean isAsc,
			String propertyName, Object value, String objectname,
			String propname, Long objvalue) {
		return pmBscObservingStaffDao.findAll(orderBy, isAsc, propertyName, 
				value, objectname, propname, objvalue);
		
	}
	
}
