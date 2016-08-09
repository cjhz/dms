package com.sunraysoft.hr.service.department.deptobserving;

import java.util.List;

import com.sunraysoft.hr.domain.entity.basic.PmBscObservingStaff;
import com.sunraysoft.hr.service.base.BaseService;

public interface PmBscObservingStaffService extends BaseService<PmBscObservingStaff, Long>{
	public List<PmBscObservingStaff> findAll(String orderBy, boolean isAsc,
			String propertyName, Object value, String objectname,
			String propname, Long objvalue);
	
}
