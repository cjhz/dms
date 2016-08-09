package com.sunraysoft.hr.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sunraysoft.hr.constant.Constant;
import com.sunraysoft.hr.dao.LoginInfoDao;
import com.sunraysoft.hr.dao.base.HibernateDao;
import com.sunraysoft.hr.domain.entity.basic.PmBscAreaInfo;
import com.sunraysoft.hr.domain.entity.basic.PmBscLoginInfo;
import com.sunraysoft.hr.util.AreaUtil;
import com.sunraysoft.hr.web.data.dto.Page;

@Repository("loginInfoDao")
public class LoginInfoDaoImpl extends HibernateDao<PmBscLoginInfo, Long> implements LoginInfoDao {

	public PmBscLoginInfo findLoginInfo(String loginName, String passwordMd5) {
		List<PmBscLoginInfo> loginInfo = (List<PmBscLoginInfo>) getHibernateTemplate().findByNamedParam("from PmBscLoginInfo s where s.vcLoginName = :loginName and s.vcLoginPwd = :passwordMd5 and s.vcDeleteFlag = :deleteFlag", 
				new String[]{"loginName", "passwordMd5", "deleteFlag"}, 
				new Object[]{loginName, passwordMd5, Long.valueOf(Constant.SYSTEM_DATA_DELETE_FLAG_NORMAL)});
		
		if(loginInfo == null || loginInfo.isEmpty()) {
			return null;
		} else {
			return loginInfo.get(0);
		}
	}
	
	/**
	 * 通过角色id查询登陆信息
	 * @param roleId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PmBscLoginInfo> findLoginInfoByRole(Long roleId) {
		Criteria criteria = getSession().createCriteria(PmBscLoginInfo.class);
		criteria.add( Restrictions.eq("vcDeleteFlag", (long)Constant.SYSTEM_DATA_DELETE_FLAG_NORMAL));
		criteria.add(Restrictions.eq("pmMngRoleInfo.pid", roleId));
		return criteria.list();
		
	}
	
	@Override
	public Criteria setEntityParameter(Criteria criteria, Page page, PmBscLoginInfo search) {
		
		if(StringUtils.isNotBlank(search.getVcDeleteFlag()+"")) {
			criteria.add(Restrictions.eq("vcDeleteFlag", search.getVcDeleteFlag()));
		}
		
		if(search.getPmBscAreaInfo()!=null){
			
			String deptcode = AreaUtil.getDepartmentcode(search.getPmBscAreaInfo().getVcDepartmentCode(), search.getPmBscAreaInfo().getVcDepartmentType().toString());
			criteria.createAlias("pmBscAreaInfo", "aread").add(
					Restrictions.like("aread.vcDepartmentCode", deptcode+"%"));
		}
			
		if(search.getVcName() != null && !"".equals(search.getVcName())){
			criteria.add(Restrictions.like("vcName", "%" + search.getVcName().trim() + "%"));
		}
		
		return super.setPageParameter(criteria, page);
	}
}
