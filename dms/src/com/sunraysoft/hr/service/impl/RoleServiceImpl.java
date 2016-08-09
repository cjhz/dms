package com.sunraysoft.hr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunraysoft.hr.dao.RoleDao;
import com.sunraysoft.hr.dao.base.BaseDao;
import com.sunraysoft.hr.domain.entity.manage.PmMngRoleInfo;
import com.sunraysoft.hr.service.RoleService;
import com.sunraysoft.hr.service.base.AbstractService;

@Service("roleService")
public class RoleServiceImpl extends AbstractService<PmMngRoleInfo, Long> implements RoleService{

	@Resource RoleDao roleDao;
	@Override
	public PmMngRoleInfo findByRoleName(String roleName) {
		return roleDao.findByRoleName(roleName);
	}
	@Override
	public void persistRoleOperRelation(PmMngRoleInfo role,
			List<Long> operIdList) {
		roleDao.persistRoleOperRelation(role, operIdList);
	}
	@Override
	public BaseDao<PmMngRoleInfo, Long> getCurrentDao() {
		return roleDao;
	}

}
