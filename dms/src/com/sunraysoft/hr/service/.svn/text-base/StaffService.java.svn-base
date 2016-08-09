package com.sunraysoft.hr.service;

import java.util.List;

import com.sunraysoft.hr.domain.entity.basic.PmBscStaffInfo;
import com.sunraysoft.hr.service.base.BaseService;
import com.sunraysoft.hr.web.data.dto.Page;

public interface StaffService extends BaseService<PmBscStaffInfo, Long>  {
	public List<Object[]> getexportlist(List<PmBscStaffInfo> list);
	
	public Page<PmBscStaffInfo> pageQuery(PmBscStaffInfo search, int pageNum, int pageSize);
}
