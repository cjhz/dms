package com.sunraysoft.hr.service.baseinfo.bscobsertype;

import java.util.List;

import com.sunraysoft.hr.domain.entity.basic.PmBscObserType;
import com.sunraysoft.hr.service.base.BaseService;

public interface PmBscObserTypeService extends BaseService<PmBscObserType, Long>{
	public List<PmBscObserType> findAll(String orderBy, boolean isAsc, 
			final String propertyName, final Object value);
}
