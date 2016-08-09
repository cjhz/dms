/**
 * 
 */
package com.sunraysoft.hr.web.action.baseinfo;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sunraysoft.hr.annotation.OperCode;
import com.sunraysoft.hr.constant.BizConstant;
import com.sunraysoft.hr.constant.OpcodeConstant;
import com.sunraysoft.hr.domain.entity.basic.PmBscDeptInfo;
import com.sunraysoft.hr.domain.entity.basic.PmBscDict;
import com.sunraysoft.hr.domain.entity.basic.PmBscStaffInfo;
import com.sunraysoft.hr.service.StaffService;
import com.sunraysoft.hr.service.baseinfo.bscdict.PmBscDictService;
import com.sunraysoft.hr.service.department.deptinfo.PmBscDeptInfoService;
import com.sunraysoft.hr.web.action.base.BaseAction;
import com.sunraysoft.hr.web.data.dto.JsonWrapper;

/**
 * 基础信息-代码维护-字典表
 * 
 * @author juyf
 * @date 2012-07-31
 */
@Controller
@Scope("prototype")
@Namespace("/baseinfo/basedict")
@Results( {
		@Result(name = "redirectPageQuery", type = "redirect", location = "searchDepType.action?dictype=${dictype}&msg=${toUtf8Encode(msg)}"),
		@Result(name = "redirectAdd", type = "redirect", location = "searchDepType.action?dictype=${dictype}&msg=${toUtf8Encode(msg)}") })
public class BscDictAction extends BaseAction {

	private static final long serialVersionUID = -6978450183577348408L;
	private Long pid;
	private Long dictype;
	private String titlename;

	private PmBscDict pmBscDict;
	private List<PmBscDict> pmbscdictlist;

	@Resource
	PmBscDictService pmBscDictService;
	@Resource
	PmBscDeptInfoService pmBscDeptInfoService;
	@Resource
	StaffService staffService;

	@Action("searchDepType")
	public String searchDeptType() {
		initTitlename(dictype);
		pmbscdictlist = pmBscDictService.findAll("vcPosition", true, "vcCode", dictype);
		return LIST;
	}

	@Action("deleteDepType")
	public String deleteDeptType() {
		initTitlename(dictype);
		List<PmBscDeptInfo> deptinfos = pmBscDeptInfoService.findAll(PmBscDeptInfo.class,"pmBscDict", "pid", pid);
		List<PmBscStaffInfo> staffs = null;
		if(dictype == BizConstant.DICT_DATA_TYPE_6){
			staffs = staffService.findAll(PmBscStaffInfo.class,"dictPoliticals", "pid", pid);
		}else if(dictype == BizConstant.DICT_DATA_TYPE_5){
			staffs = staffService.findAll(PmBscStaffInfo.class,"dictCulturals", "pid", pid);
		}else if(dictype == BizConstant.DICT_DATA_TYPE_2){
			staffs = staffService.findAll(PmBscStaffInfo.class,"dictFormations", "pid", pid);
		}else if(dictype == BizConstant.DICT_DATA_TYPE_3){
			staffs = staffService.findAll(PmBscStaffInfo.class,"dictRanks", "pid", pid);
		}else if(dictype == BizConstant.DICT_DATA_TYPE_4){
			staffs = staffService.findAll("pid", false, "vcWorkDivision", pid+";");
		}else if(dictype == BizConstant.DICT_DATA_TYPE_7){
			staffs = staffService.findAll("pid", false, "vcPersonProp", pid+";");
		}else if(dictype == BizConstant.DICT_DATA_TYPE_8){
			staffs = staffService.findAll(PmBscStaffInfo.class,"dictJobs", "pid", pid);
		}
		
		if(deptinfos !=null && deptinfos.size()>0){
			setMsg("待删除的"+titlename+"已被机构引用，不能删除！");
		}else if(staffs != null && staffs.size()>0){
			for(PmBscStaffInfo staffinfo:staffs){
				if(staffinfo.getVcDeleteFlag() == BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL){
					setMsg("待删除的"+titlename+"已被人员引用，不能删除！");
					return REDIRECT_PAGE_QUERY;
				}else{
					pmBscDictService.remove(pid);
					setMsg("删除成功！");
				}
			}
		}else{
			pmBscDictService.remove(pid);
			setMsg("删除成功！");
		}
		return REDIRECT_PAGE_QUERY;
	}

	@Action("editDepType")
	public String editDeptType() {
		initTitlename(dictype);
//		List<PmBscDeptInfo> deptinfos = pmBscDeptInfoService.findAll(PmBscDeptInfo.class,"pmBscDict", "pid", pid);
//		List<PmBscStaffInfo> staffs = null;
//		if(dictype == BizConstant.DICT_DATA_TYPE_6){
//			staffs = staffService.findAll(PmBscStaffInfo.class,"dictPoliticals", "pid", pid);
//		}else if(dictype == BizConstant.DICT_DATA_TYPE_5){
//			staffs = staffService.findAll(PmBscStaffInfo.class,"dictCulturals", "pid", pid);
//		}else if(dictype == BizConstant.DICT_DATA_TYPE_2){
//			staffs = staffService.findAll(PmBscStaffInfo.class,"dictFormations", "pid", pid);
//		}else if(dictype == BizConstant.DICT_DATA_TYPE_3){
//			staffs = staffService.findAll(PmBscStaffInfo.class,"dictRanks", "pid", pid);
//		}
//		if(deptinfos !=null && deptinfos.size()>0){
//			setMsg("待修改的"+titlename+"已被机构引用，不能修改！");
//		}else if(staffs != null && staffs.size()>0){
//			setMsg("待修改的"+titlename+"已被人员引用，不能修改！");
//		}
		pmBscDictService.merge(pmBscDict);
		setMsg("修改成功！");
		return REDIRECT_PAGE_QUERY;
	}

	@Action("toEditDepType")
	public String toEditDepType() {
//		initTitlename(dictype);
//		List<PmBscDeptInfo> deptinfos = pmBscDeptInfoService.findAll(PmBscDeptInfo.class,"pmBscDict", "pid", pid);
//		List<PmBscStaffInfo> staffs = null;
//		if(dictype == BizConstant.DICT_DATA_TYPE_6){
//			staffs = staffService.findAll(PmBscStaffInfo.class,"dictPoliticals", "pid", pid);
//		}else if(dictype == BizConstant.DICT_DATA_TYPE_5){
//			staffs = staffService.findAll(PmBscStaffInfo.class,"dictCulturals", "pid", pid);
//		}else if(dictype == BizConstant.DICT_DATA_TYPE_2){
//			staffs = staffService.findAll(PmBscStaffInfo.class,"dictFormations", "pid", pid);
//		}else if(dictype == BizConstant.DICT_DATA_TYPE_3){
//			staffs = staffService.findAll(PmBscStaffInfo.class,"dictRanks", "pid", pid);
//		}
//		if(deptinfos !=null && deptinfos.size()>0){
//			setMsg("待修改的"+titlename+"已被机构引用，不能修改！");
//			return REDIRECT_PAGE_QUERY;
//		}else if(staffs != null && staffs.size()>0){
//			setMsg("待修改的"+titlename+"已被人员引用，不能修改！");
//			return REDIRECT_PAGE_QUERY;
//		}
		pmBscDict = pmBscDictService.find(pid);
		initTitlename(dictype);
		return EDIT;
	}

	@Action("toAddDepType")
	public String toAddDepType() {
		initTitlename(dictype);
		return ADD;
	}

	@Action("addDepType")
	public String addDeptType() {
		pmBscDictService.save(pmBscDict);
		setMsg("新增成功！");
		return "redirectAdd";
	}
	
	public void initTitlename(Long dictype){
		//type(1;//机构职级2;//编制3;//职级4;//工作分工5;//文化程度6;//政治面貌7;//人员属性8;//职务)
		if(dictype == BizConstant.DICT_DATA_TYPE_1){
			titlename ="机构职级";
		}else if(dictype == BizConstant.DICT_DATA_TYPE_2){
			titlename ="编制";
		}else if(dictype == BizConstant.DICT_DATA_TYPE_3){
			titlename ="职级";
		}else if(dictype == BizConstant.DICT_DATA_TYPE_4){
			titlename ="工作分工";
		}else if(dictype == BizConstant.DICT_DATA_TYPE_5){
			titlename ="文化程度";
		}else if(dictype == BizConstant.DICT_DATA_TYPE_6){
			titlename ="政治面貌";
		}else if(dictype == BizConstant.DICT_DATA_TYPE_7){
			titlename ="人员属性";
		}else if(dictype == BizConstant.DICT_DATA_TYPE_8){
			titlename ="职务";
		}
	}
	
	@SuppressWarnings("unchecked")
	@Action("ajaxCheckDepTypeNameUnique")
	public String ajaxCheckDepTypeNameUnique() {
		initTitlename(dictype);
		boolean result ;
		List<PmBscDict> deptlists = pmBscDictService.findByValues(new String[]{"vcName","vcCode"}, new Object[]{pmBscDict.getVcName(),dictype});
		if(deptlists!=null && deptlists.size()>0){
			if(pmBscDict.getPid() == null){
				result = false ;
			}else{
				boolean a = deptlists.get(0).getPid().equals(pmBscDict.getPid());
				result = a;
			}
		}else{result = true;}
		JsonWrapper wrapper = new JsonWrapper();
		wrapper.setFlag(result);
		wrapper.setMsg(result ? "" : titlename+"不能重复");
		JSONObject jsonObj = JSONObject.fromObject(wrapper);
		setJsonText( jsonObj.toString() );
		return JSON_PAGE;
	}

	public PmBscDict getPmBscDict() {
		return pmBscDict;
	}

	public void setPmBscDict(PmBscDict pmBscDict) {
		this.pmBscDict = pmBscDict;
	}

	public List<PmBscDict> getPmbscdictlist() {
		return pmbscdictlist;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Long getPid() {
		return pid;
	}

	public Long getDictype() {
		return dictype;
	}

	public void setDictype(Long dictype) {
		this.dictype = dictype;
	}

	public String getTitlename() {
		return titlename;
	}

	public void setTitlename(String titlename) {
		this.titlename = titlename;
	}

}
