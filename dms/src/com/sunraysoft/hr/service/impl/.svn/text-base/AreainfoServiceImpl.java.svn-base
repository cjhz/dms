package com.sunraysoft.hr.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunraysoft.hr.dao.AreainfoDao;
import com.sunraysoft.hr.dao.base.BaseDao;
import com.sunraysoft.hr.domain.entity.basic.PmBscAreaInfo;
import com.sunraysoft.hr.service.AreainfoService;
import com.sunraysoft.hr.service.base.AbstractService;

@Service("areainfoService")
public class AreainfoServiceImpl extends AbstractService<PmBscAreaInfo,Long> implements AreainfoService {

	@Resource
	AreainfoDao areaInfodao;
	@Override
	public BaseDao<PmBscAreaInfo, Long> getCurrentDao() {
		return areaInfodao;
	}
	
}
