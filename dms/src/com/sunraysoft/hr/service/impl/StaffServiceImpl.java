package com.sunraysoft.hr.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunraysoft.hr.constant.BizConstant;
import com.sunraysoft.hr.dao.StaffDao;
import com.sunraysoft.hr.dao.base.BaseDao;
import com.sunraysoft.hr.domain.entity.basic.PmBscStaffDept;
import com.sunraysoft.hr.domain.entity.basic.PmBscStaffInfo;
import com.sunraysoft.hr.service.StaffService;
import com.sunraysoft.hr.service.base.AbstractService;
import com.sunraysoft.hr.web.data.dto.Page;

@Service("staffService")
public class StaffServiceImpl extends AbstractService<PmBscStaffInfo, Long> implements StaffService {

	@Resource StaffDao staffDao;
	
	@Override
	public BaseDao<PmBscStaffInfo, Long> getCurrentDao() {
		return staffDao;
	}

	/**
	 * 获得导出list
	 * @param list
	 * @return
	 */
	public List<Object[]> getexportlist(List<PmBscStaffInfo> list){
		List<Object[]> newlist = new ArrayList<Object[]>();
		for(PmBscStaffInfo staff:list){
			Object[] obj = new Object[6];
			String deptnames="";
			if(staff.getPmBscStaffDepts()!=null&&staff.getPmBscStaffDepts().size()>0){
				for(PmBscStaffDept staffdept:staff.getPmBscStaffDepts()){
					deptnames=staffdept.getPmBscDeptInfo()==null?"":staffdept.getPmBscDeptInfo().getVcDeptName()==null?"":staffdept.getPmBscDeptInfo().getVcDeptName()+",";
					
				}
			}
			obj[0]=deptnames;
			obj[1]=staff.getVcRealName()==null?"":staff.getVcRealName();
			obj[2]=staff.getVcCardNo()==null?"":staff.getVcCardNo();
			Long sex = staff.getVcSex()==null?0:staff.getVcSex();
			if(sex==BizConstant.SEX_KEY_1)obj[3]=BizConstant.SEX_TXT_1;
			if(sex==BizConstant.SEX_KEY_2)obj[3]=BizConstant.SEX_TXT_2;
			obj[4]=staff.getDictRanks().getVcName()==null?"":staff.getDictRanks().getVcName();
			obj[5]=staff.getVcOphone()==null?"":staff.getVcOphone();
			newlist.add(obj);
		}
		return newlist;
	}

	@Override
	public Page<PmBscStaffInfo> pageQuery(PmBscStaffInfo search, int pageNum,
			int pageSize) {
		return staffDao.pageQuery(search, pageNum, pageSize);
	}
	
	
}
