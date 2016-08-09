package com.sunraysoft.hr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunraysoft.hr.dao.OperDao;
import com.sunraysoft.hr.dao.base.BaseDao;
import com.sunraysoft.hr.domain.entity.manage.PmMngOper;
import com.sunraysoft.hr.service.OperService;
import com.sunraysoft.hr.service.base.AbstractService;
import com.sunraysoft.hr.web.data.dto.Page;
@Service("operService")
public class OperServiceImpl extends AbstractService<PmMngOper, Long> implements OperService {

	@Resource
	OperDao operDao;
	
	public List<PmMngOper> findAll() {
		return operDao.findAll();
	}

	@Override
	public BaseDao<PmMngOper, Long> getCurrentDao() {
		return operDao;
	}

	

}
