package com.sunraysoft.hr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunraysoft.hr.dao.LoginInfoDao;
import com.sunraysoft.hr.dao.base.BaseDao;
import com.sunraysoft.hr.domain.entity.basic.PmBscLoginInfo;
import com.sunraysoft.hr.service.LoginInfoService;
import com.sunraysoft.hr.service.base.AbstractService;

@Service("loginInfoService")
public class LoginInfoServiceImpl extends AbstractService<PmBscLoginInfo,Long> implements
		LoginInfoService {
	
	@Resource
	LoginInfoDao loginInfoDao;
	
	public PmBscLoginInfo findLoginInfo(String loginName, String passwordMd5) {
		return loginInfoDao.findLoginInfo(loginName, passwordMd5);
	}

	@Override
	public List<PmBscLoginInfo> findLoginInfoByRole(Long roleId) {
		return loginInfoDao.findLoginInfoByRole(roleId);
	}

	@Override
	public BaseDao<PmBscLoginInfo, Long> getCurrentDao() {
		return loginInfoDao;
	}
}
