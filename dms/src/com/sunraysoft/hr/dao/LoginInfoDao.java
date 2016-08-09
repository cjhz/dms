package com.sunraysoft.hr.dao;

import java.util.List;

import com.sunraysoft.hr.dao.base.BaseDao;
import com.sunraysoft.hr.domain.entity.basic.PmBscLoginInfo;

public interface LoginInfoDao extends BaseDao<PmBscLoginInfo, Long>{

	public abstract PmBscLoginInfo findLoginInfo(String loginName, String passwordMd5);
	
	/**
	 * 通过角色id查询登陆信息
	 * @param roleId
	 * @return
	 */
	public abstract List<PmBscLoginInfo> findLoginInfoByRole(Long roleId); 
}
