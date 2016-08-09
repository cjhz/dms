package com.sunraysoft.hr.service;

import java.util.List;

import com.sunraysoft.hr.domain.entity.manage.PmMngChangeInfo;
import com.sunraysoft.hr.service.base.BaseService;

public interface StaffChangeService extends BaseService<PmMngChangeInfo, Long>  {
	public List<PmMngChangeInfo> findChangeInfoBy(Long staffId);
}
