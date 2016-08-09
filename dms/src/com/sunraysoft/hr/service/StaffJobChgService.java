package com.sunraysoft.hr.service;

import com.sunraysoft.hr.domain.entity.basic.PmBscJobChange;
import com.sunraysoft.hr.service.base.BaseService;

public interface StaffJobChgService extends BaseService<PmBscJobChange, Long> {
	public void createJobChange(PmBscJobChange staffRank) throws Exception;
	public void updateJobChange(PmBscJobChange staffRank) throws Exception;
}
