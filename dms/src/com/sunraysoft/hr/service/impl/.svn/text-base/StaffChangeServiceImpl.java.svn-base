package com.sunraysoft.hr.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunraysoft.hr.constant.BizConstant;
import com.sunraysoft.hr.dao.StaffChangeDao;
import com.sunraysoft.hr.dao.base.BaseDao;
import com.sunraysoft.hr.dao.baseinfo.bscdict.PmBscDictDao;
import com.sunraysoft.hr.dao.department.deptinfo.PmBscDeptInfoDao;
import com.sunraysoft.hr.domain.entity.basic.PmBscDeptInfo;
import com.sunraysoft.hr.domain.entity.basic.PmBscDict;
import com.sunraysoft.hr.domain.entity.manage.PmMngChangeInfo;
import com.sunraysoft.hr.service.StaffChangeService;
import com.sunraysoft.hr.service.base.AbstractService;

@Service("staffChangeService")
public class StaffChangeServiceImpl extends AbstractService<PmMngChangeInfo, Long> implements StaffChangeService  {

	@Resource StaffChangeDao staffChangeDao;
	@Resource PmBscDeptInfoDao deptInfoDao;
	@Resource PmBscDictDao dictDao;
	@Override
	public BaseDao<PmMngChangeInfo, Long> getCurrentDao() {
		return staffChangeDao;
	}

	public List<PmMngChangeInfo> findChangeInfoBy(Long staffId){
		List<PmMngChangeInfo> changelist = staffChangeDao.findByValuesOrder("vcChangeDate", false, new String[]{"vcDeleteFlag","pmBscStaffInfo.pid"}, new Object[]{BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL,staffId});
		return setChangeInfo(changelist);
	}
	
	/**
	 * 
	 * @param changelist
	 * @return
	 */
	public List<PmMngChangeInfo> setChangeInfo(List<PmMngChangeInfo> changelist){
		List<PmMngChangeInfo> chglist = new ArrayList<PmMngChangeInfo>();
		List<PmBscDeptInfo> deptlist =  deptInfoDao.findAll();
		List<PmBscDict> dictlist =  dictDao.findByValues(new String[]{"vcDeleteFlag","vcCode"}, new Object[]{BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL,BizConstant.DICT_DATA_TYPE_7});//人员属性
		String bdept="",edept="",bprop="",eprop="";
		String[] bdepts={},edepts={},bprops={},eprops={};
		for(PmMngChangeInfo chgInfo : changelist){
			PmMngChangeInfo changeInfo = new PmMngChangeInfo();
			bdept=chgInfo.getVcNowDept()==null?"":chgInfo.getVcNowDept();
			edept=chgInfo.getVcChgDept()==null?"":chgInfo.getVcChgDept();
			bprop=chgInfo.getVcBpersonProp()==null?"":chgInfo.getVcBpersonProp();
			eprop=chgInfo.getVcEpersonProp()==null?"":chgInfo.getVcEpersonProp();
			bdepts = bdept.split(";");
			edepts = edept.split(";");
			bprops = bprop.split(";");
			eprops = eprop.split(";");
			changeInfo.setVcExtend1(getdeptString(deptlist,bdepts));
			changeInfo.setVcExtend2(getdeptString(deptlist,edepts));
			changeInfo.setVcExtend3(getdictString(dictlist,bprops));
			changeInfo.setVcExtend4(getdictString(dictlist,eprops));
			chglist.add(changeInfo);
		}
		return chglist;
		
	}
	
	/**
	 * 
	 * @param list
	 * @param arr
	 * @return
	 */
	public String getdeptString(List<PmBscDeptInfo> list,String[] arr){
		String deptnames = "";
		if(arr.length>0&&list!=null){
			for(int i=0;i<arr.length;i++){
				for(PmBscDeptInfo deptInfo : list){
					if(arr[i].equals(deptInfo.getPid()+"")){
						if(i==arr.length-1){
							deptnames+=deptInfo.getVcDeptName();
						}else{
							deptnames+=deptInfo.getVcDeptName()+";";
						}
					}
				}
			}
		}
		return deptnames;
	}
	
	/**
	 * 
	 * @param list
	 * @param arr
	 * @return
	 */
	public String getdictString(List<PmBscDict> list,String[] arr){
		String dictnames = "";
		if(arr.length>0&&list!=null){
			for(int i=0;i<arr.length;i++){
				for(PmBscDict dict : list){
					if(arr[i].equals(dict.getPid()+"")){
						if(i==arr.length-1){
							dictnames+=dict.getVcName();
						}else{
							dictnames+=dict.getVcName()+";";
						}
					}
				}
			}
		}
		return dictnames;
	}
	
	
}
