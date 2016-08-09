package com.sunraysoft.hr.dao;

import java.util.List;

import com.sunraysoft.hr.dao.base.BaseDao;
import com.sunraysoft.hr.domain.entity.manage.PmMngOper;

public interface OperDao extends BaseDao<PmMngOper, Long>{

	List<PmMngOper> findAll() ;
}