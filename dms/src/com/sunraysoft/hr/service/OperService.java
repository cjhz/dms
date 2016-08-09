package com.sunraysoft.hr.service;

import java.util.List;

import com.sunraysoft.hr.domain.entity.manage.PmMngOper;
import com.sunraysoft.hr.service.base.BaseService;

public interface OperService extends BaseService<PmMngOper, Long> {

	public List<PmMngOper> findAll();
}
