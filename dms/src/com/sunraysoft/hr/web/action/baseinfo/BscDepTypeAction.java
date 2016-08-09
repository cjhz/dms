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

import com.sunraysoft.hr.annotation.OperCode;
import com.sunraysoft.hr.constant.OpcodeConstant;
import com.sunraysoft.hr.domain.entity.basic.PmBscDeptInfo;
import com.sunraysoft.hr.domain.entity.basic.PmBscDeptType;
import com.sunraysoft.hr.service.baseinfo.bscdeptype.PmBscDeptTypeService;
import com.sunraysoft.hr.service.department.deptinfo.PmBscDeptInfoService;
import com.sunraysoft.hr.util.BeanUtil;
import com.sunraysoft.hr.web.action.base.BaseAction;
import com.sunraysoft.hr.web.data.dto.JsonWrapper;

/**
 * 基础信息-代码维护-机构性质
 * @author juyf
 * @date 2012-07-31
 */
@Controller
@Scope("prototype")
@Namespace("/baseinfo/deptype")
@Results({
	@Result(name="redirectPageQuery",type="redirect",location="searchDepType.action?msg=${toUtf8Encode(msg)}" ),
	@Result(name="redirectAdd",type="redirect",location="searchDepType.action?msg=${toUtf8Encode(msg)}")
})
public class BscDepTypeAction extends BaseAction{

	private static final long serialVersionUID = -6978450183577348408L;
	private Long pid;
	
	private PmBscDeptType pmBscDeptType;
	private List<PmBscDeptType> pmbscdeptypelist;
	
	@Resource 
	PmBscDeptTypeService pmBsDeptTypeService;
	@Resource 
	PmBscDeptInfoService pmBscDeptInfoService;
	
	@Action("searchDepType")
	public String searchDeptType(){
		pmbscdeptypelist = pmBsDeptTypeService.findAll("pid", true);
		return LIST;
	}
	
	@Action("deleteDepType")
	public String deleteDeptType(){
		List<PmBscDeptInfo> deptinfos = pmBscDeptInfoService.findAll(PmBscDeptInfo.class,"pmBscDeptType", "pid", pid);
		if(deptinfos !=null && deptinfos.size()>0){
			setMsg("待删除的机构性质已被机构引用，不能删除！");
		}else{
			pmBsDeptTypeService.remove(pid);
			setMsg("删除成功！");
		}
		return REDIRECT_PAGE_QUERY;
	}
	
	@Action("editDepType")
	public String editDeptType(){
//		
//		//删除做引用判断(是否有机关引用)
//		List<PmBscDeptInfo> deptinfos = pmBscDeptInfoService.findAll(PmBscDeptInfo.class,"pmBscDeptType", "pid", pid);
//		if(deptinfos !=null && deptinfos.size()>0){
//			setMsg("待修改的机构性质已被机构引用，不能修改！");
//		}
		PmBscDeptType depttype = pmBsDeptTypeService.find(pmBscDeptType.getPid());
		String[] properties = new String[] {"vcTypeName", "vcDeptProp"};
		
		BeanUtil.copyProperties(pmBscDeptType, depttype, properties);
		pmBsDeptTypeService.saveOrUpdate(depttype);
		setMsg("修改成功！");
		return REDIRECT_PAGE_QUERY;
	}
	
	@Action("toEditDepType")
	public String toEditDepType(){
//		List<PmBscDeptInfo> deptinfos = pmBscDeptInfoService.findAll(PmBscDeptInfo.class,"pmBscDeptType", "pid", pid);
//		if(deptinfos !=null && deptinfos.size()>0){
//			setMsg("待修改的机构性质已被机构引用，不能修改！");
//			return REDIRECT_PAGE_QUERY;
//		}
		pmBscDeptType = pmBsDeptTypeService.find(pid);
		return EDIT;
	}
	
	@Action("toAddDepType")
	public String toAddDepType(){
		return ADD;
	}
	
	@Action("addDepType")
	public String addDeptType(){
		Set<PmBscDeptInfo> pmBscDeptInfos = new HashSet<PmBscDeptInfo>();
		pmBscDeptType.setPmBscDeptInfos(pmBscDeptInfos);
		pmBsDeptTypeService.save(pmBscDeptType);
		setMsg("新增成功！");
		return REDIRECT_PAGE_QUERY;
	}

	@SuppressWarnings("unchecked")
	@Action("ajaxCheckDepTypeNameUnique")
	public String ajaxCheckDepTypeNameUnique() {
		boolean result ;
		List<PmBscDeptType> deptlists = pmBsDeptTypeService.findBy("vcTypeName", pmBscDeptType.getVcTypeName());
		if(deptlists!=null && deptlists.size()>0){
			if(pmBscDeptType.getPid() == null){
				result = false ;
			}else{
				boolean a = deptlists.get(0).getPid().equals(pmBscDeptType.getPid());
				result = a;
			}
		}else{result = true;}
		JsonWrapper wrapper = new JsonWrapper();
		wrapper.setFlag(result);
		wrapper.setMsg(result ? "" : "机构性质不能重复");
		JSONObject jsonObj = JSONObject.fromObject(wrapper);
		setJsonText( jsonObj.toString() );
		return JSON_PAGE;
	}

	public List<PmBscDeptType> getPmbscdeptypelist() {
		return pmbscdeptypelist;
	}
	
	public void setPmBscDeptType(PmBscDeptType pmBscDeptType) {
		this.pmBscDeptType = pmBscDeptType;
	}
	
	public PmBscDeptType getPmBscDeptType() {
		return pmBscDeptType;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}
	
	public Long getPid() {
		return pid;
	}
	
}
