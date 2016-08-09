package com.sunraysoft.hr.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunraysoft.hr.dao.StaffDeptDao;
import com.sunraysoft.hr.dao.base.BaseDao;
import com.sunraysoft.hr.domain.entity.basic.PmBscStaffDept;
import com.sunraysoft.hr.service.StaffDeptService;
import com.sunraysoft.hr.service.base.AbstractService;

@Service("staffDeptService")
public class StaffDeptServiceImpl extends AbstractService<PmBscStaffDept, Long> implements StaffDeptService {

	@Resource StaffDeptDao staffDeptDao;
	
	@Override
	public BaseDao<PmBscStaffDept, Long> getCurrentDao() {
		return staffDeptDao;
	}

}
