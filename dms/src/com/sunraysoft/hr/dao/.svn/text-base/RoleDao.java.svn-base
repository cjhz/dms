package com.sunraysoft.hr.dao;

import java.util.List;

import com.sunraysoft.hr.dao.base.BaseDao;
import com.sunraysoft.hr.domain.entity.manage.PmMngRoleInfo;

public interface RoleDao extends BaseDao<PmMngRoleInfo, Long> {
	public PmMngRoleInfo findByRoleName(String roleName);
	
	public void persistRoleOperRelation(PmMngRoleInfo role, List<Long> operIdList);
	
}
