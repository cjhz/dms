package com.sunraysoft.hr.web.action.basic;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sunraysoft.hr.domain.entity.basic.PmBscAreaInfo;
import com.sunraysoft.hr.service.AreainfoService;
import com.sunraysoft.hr.web.action.base.BaseAction;

@Controller 
@Scope("prototype")
@Namespace("/basic/area")
@Results({
	@Result(name="redirectPageQuery", type="redirect", location="pageQuery.action?success=${success}&msg=${toUtf8Encode(msg)}&search.vcRealName=${toUtf8Encode(search.vcRealName)}&pageNum=${pageNum}")
})
public class AreaAction extends BaseAction   {
	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory.getLog(AreaAction.class);
	
	PmBscAreaInfo areainfo;
	private String vclevelcode;
	private String areaparam;
	
	@Resource private AreainfoService areainfoService;
	
	@Action("findByLevel")
	public String findByLevel(){
//		List<PmBscAreaInfo> arealist = areainfoService.findBy("vcLevelCode", vclevelcode);
		List<PmBscAreaInfo> arealist = areainfoService.findAll("vcDepartmentCode", true, "vcLevelCode", vclevelcode);
		
		setJsonText(JSONArray.fromObject(arealist).toString());
		return JSON_PAGE;
	}

	public String getVclevelcode() {
		return vclevelcode;
	}

	public void setVclevelcode(String vclevelcode) {
		this.vclevelcode = vclevelcode;
	}

	public String getAreaparam() {
		return areaparam;
	}

	public void setAreaparam(String areaparam) {
		this.areaparam = areaparam;
	}
}
