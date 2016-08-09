package com.sunraysoft.hr.dao;

import java.util.List;

import com.sunraysoft.hr.dao.base.BaseDao;
import com.sunraysoft.hr.domain.entity.basic.PmBscAreaInfo;

public interface AreainfoDao extends BaseDao<PmBscAreaInfo, Long>{
	public List<Object[]> findContainAreaCode(PmBscAreaInfo area);
}
