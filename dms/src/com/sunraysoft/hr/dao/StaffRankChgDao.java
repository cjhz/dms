package com.sunraysoft.hr.dao;

import java.util.List;

import com.sunraysoft.hr.dao.base.BaseDao;
import com.sunraysoft.hr.domain.entity.basic.PmBscRankChange;

public interface StaffRankChgDao extends BaseDao<PmBscRankChange, Long> {

	public List<PmBscRankChange> getMaxResult(final Object[] values);
	
	public Long getSecondPid(final Object[] values);
}
