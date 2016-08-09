package com.sunraysoft.hr.dao.department.deptobserving;

import java.util.List;

import com.sunraysoft.hr.dao.base.BaseDao;
import com.sunraysoft.hr.domain.entity.basic.PmBscObservingStaff;

public interface PmBscObservingStaffDao extends BaseDao<PmBscObservingStaff, Long> {
	public List<PmBscObservingStaff> findAll(String orderBy, boolean isAsc,
			String propertyName, Object value, String objectname,
			String propname, Long objvalue);

}
