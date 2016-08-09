package com.sunraysoft.hr.service;

import java.util.List;

import com.sunraysoft.hr.domain.entity.manage.PmMngRoleInfo;
import com.sunraysoft.hr.service.base.BaseService;

public interface RoleService extends BaseService<PmMngRoleInfo, Long> {
	public PmMngRoleInfo findByRoleName(String roleName);
	
	public void persistRoleOperRelation(PmMngRoleInfo role, List<Long> operIdList);
	
}
