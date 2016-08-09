package com.sunraysoft.hr.service.baseinfo.bscdict;

import java.util.List;

import com.sunraysoft.hr.domain.entity.basic.PmBscDict;
import com.sunraysoft.hr.service.base.BaseService;

public interface PmBscDictService extends BaseService<PmBscDict, Long>{
	public List<PmBscDict> findAll(String orderBy, boolean isAsc, 
			final String propertyName, final Object value);
}
