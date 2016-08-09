package com.sunraysoft.hr.service;

import java.util.List;

import com.sunraysoft.hr.domain.entity.basic.PmBscLoginInfo;
import com.sunraysoft.hr.service.base.BaseService;

public interface LoginInfoService extends BaseService<PmBscLoginInfo,Long> {

	public PmBscLoginInfo findLoginInfo(String loginName, String passwordMd5);
	
	/**
	 * 通过角色id查询登陆信息
	 * @param roleId
	 * @return
	 */
	public abstract List<PmBscLoginInfo> findLoginInfoByRole(Long roleId); 
}
