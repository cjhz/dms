/**
 * 
 */
package com.sunraysoft.hr.web.action.baseinfo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sunraysoft.hr.constant.BizConstant;
import com.sunraysoft.hr.domain.entity.basic.PmBscObserType;
import com.sunraysoft.hr.domain.entity.basic.PmBscObserving;
import com.sunraysoft.hr.domain.entity.basic.PmBscObservingStaff;
import com.sunraysoft.hr.service.baseinfo.bscobsertype.PmBscObserTypeService;
import com.sunraysoft.hr.service.department.deptobserving.PmBscObservingService;
import com.sunraysoft.hr.service.department.deptobserving.PmBscObservingStaffService;
import com.sunraysoft.hr.util.BeanUtil;
import com.sunraysoft.hr.web.action.base.BaseAction;
import com.sunraysoft.hr.web.data.dto.JsonWrapper;

/**
 * 基础信息-代码维护-立功表彰
 * 
 * @author juyf
 * @date 2012-07-31
 */
@Controller
@Scope("prototype")
@Namespace("/baseinfo/obsertype")
@Results( {
		@Result(name = "redirectPageQuery", type = "redirect", location = "searchDepType.action?vctype=${vctype}&msg=${toUtf8Encode(msg)}"),
		@Result(name = "redirectAdd", type = "redirect", location = "searchDepType.action?vctype=${vctype}&msg=${toUtf8Encode(msg)}") })
public class BscObserTypeAction extends BaseAction {

	private static final long serialVersionUID = -6978450183577348408L;
	private Long pid;
	private Long vctype;
	private String titlename;

	private PmBscObserType pmBscObserType;
	private List<PmBscObserType> pmbscobsertypelist;

	@Resource
	PmBscObserTypeService pmBscObserTypeService;
	@Resource
	PmBscObservingService pmBscObservingService;
	@Resource
	PmBscObservingStaffService pmBscObservingStaffService;

	@Action("searchDepType")
	public String searchDeptType() {
		if(vctype == BizConstant.DICT_OBSER_TYPE_1){
			titlename = "机构立功表彰名称";
		}else{
			titlename = "人员立功表彰名称";
		}
		pmbscobsertypelist = pmBscObserTypeService.findAll("pid", true, "vcType", vctype);
		return LIST;
	}

	@Action("deleteDepType")
	public String deleteDeptType() {
		if(vctype == BizConstant.DICT_OBSER_TYPE_1){
			titlename = "机构立功表彰名称";
			List<PmBscObserving> deptinfos = pmBscObservingService.findAll(PmBscObserving.class, "pmBscObserType","pid",pid);
			if(deptinfos != null && deptinfos.size()>0){
				setMsg("待删除的"+titlename+"已被机构立功表彰引用，不能删除！");
				return REDIRECT_PAGE_QUERY;
			}
		}else{
			titlename = "人员立功表彰名称";
			List<PmBscObservingStaff> deptinfos = pmBscObservingStaffService.findAll(PmBscObservingStaff.class, "pmBscObserType","pid",pid);
			if(deptinfos != null && deptinfos.size()>0){
				setMsg("待删除的"+titlename+"已被人员立功表彰引用，不能删除！");
				return REDIRECT_PAGE_QUERY;
			}
		}
		pmBscObserTypeService.remove(pid);
		setMsg("删除成功！");
		
		return REDIRECT_PAGE_QUERY;
	}

	@Action("editDepType")
	public String editDeptType() {
//		if(vctype == BizConstant.DICT_OBSER_TYPE_1){
//			titlename = "机构立功表彰名称";
//			List<PmBscObserving> deptinfos = pmBscObservingService.findAll(PmBscObserving.class, "pmBscObserType","pid",pid);
//			if(deptinfos != null && deptinfos.size()>0){
//				setMsg("待修改的"+titlename+"已被机构立功表彰引用，不能修改！");
//				return REDIRECT_PAGE_QUERY;
//			}
//		}else{
//			titlename = "人员立功表彰名称";
//			List<PmBscObservingStaff> deptinfos = pmBscObservingStaffService.findAll(PmBscObservingStaff.class, "pmBscObserType","pid",pid);
//			if(deptinfos != null && deptinfos.size()>0){
//				setMsg("待修改的"+titlename+"已被人员立功表彰引用，不能修改！");
//				return REDIRECT_PAGE_QUERY;
//			}
//		}
		PmBscObserType obsertype = pmBscObserTypeService.find(pmBscObserType.getPid());
		if(obsertype==null){
			setFailMsg("待修改的立功表彰不存在！");
		}
		if(vctype == BizConstant.DICT_OBSER_TYPE_1){
			titlename = "机构立功表彰名称";
		}else{
			titlename = "人员立功表彰名称";
		}
		String[] properties = new String[] {"vcName"};
		BeanUtil.copyProperties(pmBscObserType, obsertype, properties);
		pmBscObserTypeService.saveOrUpdate(obsertype);
		setMsg("修改成功！");
		return "redirectPageQuery";
	}

	@Action("toEditDepType")
	public String toEditDepType() {
		if(vctype == BizConstant.DICT_OBSER_TYPE_1){
			titlename = "机构立功表彰名称";
		}else{
			titlename = "人员立功表彰名称";
		}
//		if(vctype == BizConstant.DICT_OBSER_TYPE_1){
//			List<PmBscObserving> deptinfos = pmBscObservingService.findAll(PmBscObserving.class, "pmBscObserType","pid",pid);
//			if(deptinfos != null && deptinfos.size()>0){
//				setMsg("待修改的"+titlename+"已被机构立功表彰引用，不能修改！");
//				return REDIRECT_PAGE_QUERY;
//			}
//		}else{
//			List<PmBscObservingStaff> deptinfos = pmBscObservingStaffService.findAll(PmBscObservingStaff.class, "pmBscObserType","pid",pid);
//			if(deptinfos != null && deptinfos.size()>0){
//				setMsg("待修改的"+titlename+"已被人员立功表彰引用，不能修改！");
//				return REDIRECT_PAGE_QUERY;
//			}
//		}
		pmBscObserType = pmBscObserTypeService.find(pid);
		return EDIT;
	}

	@Action("toAddDepType")
	public String toAddDepType() {
		if(vctype == BizConstant.DICT_OBSER_TYPE_1){
			titlename = "机构立功表彰名称";
		}else{
			titlename = "人员立功表彰名称";
		}
		return ADD;
	}

	@Action("addDepType")
	public String addDeptType() {
		if(vctype == BizConstant.DICT_OBSER_TYPE_1){
			titlename = "机构立功表彰名称";
			List<PmBscObserving> deptinfos = pmBscObservingService.findAll(PmBscObserving.class, "pmBscObserType","pid",pid);
			if(deptinfos != null && deptinfos.size()>0){
				setMsg("待修改的"+titlename+"已被机构立功表彰引用，不能修改！");
				return REDIRECT_PAGE_QUERY;
			}
		}else{
			titlename = "人员立功表彰名称";
			List<PmBscObservingStaff> deptinfos = pmBscObservingStaffService.findAll(PmBscObservingStaff.class, "pmBscObserType","pid",pid);
			if(deptinfos != null && deptinfos.size()>0){
				setMsg("待修改的"+titlename+"已被人员立功表彰引用，不能修改！");
				return REDIRECT_PAGE_QUERY;
			}
		}
		Set<PmBscObserving> pmBscObservings = new HashSet<PmBscObserving>();
		pmBscObserType.setPmBscObservings(pmBscObservings);
		pmBscObserTypeService.save(pmBscObserType);
		setMsg("新增成功！");
		return "redirectAdd";
	}
	
	@SuppressWarnings("unchecked")
	@Action("ajaxCheckDepTypeNameUnique")
	public String ajaxCheckDepTypeNameUnique() {
		if(vctype == BizConstant.DICT_OBSER_TYPE_1){
			titlename = "机构立功表彰名称";
		}else{
			titlename = "人员立功表彰名称";
		}
		boolean result ;
		List<PmBscObserType> deptlists = pmBscObserTypeService.findByValues(new String[]{"vcName","vcType"}, new Object[]{pmBscObserType.getVcName(),vctype});
		if(deptlists!=null && deptlists.size()>0){
			if(pmBscObserType.getPid() == null){
				result = false ;
			}else{
				boolean a = deptlists.get(0).getPid().equals(pmBscObserType.getPid());
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


	public PmBscObserType getPmBscObserType() {
		return pmBscObserType;
	}

	public void setPmBscObserType(PmBscObserType pmBscObserType) {
		this.pmBscObserType = pmBscObserType;
	}

	public List<PmBscObserType> getPmbscobsertypelist() {
		return pmbscobsertypelist;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Long getVctype() {
		return vctype;
	}

	public void setVctype(Long vctype) {
		this.vctype = vctype;
	}

	public Long getPid() {
		return pid;
	}
	

	public String getTitlename() {
		return titlename;
	}
}
