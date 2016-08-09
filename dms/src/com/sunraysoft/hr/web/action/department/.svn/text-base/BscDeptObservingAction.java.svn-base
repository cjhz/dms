/**
 * 
 */
package com.sunraysoft.hr.web.action.department;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sunraysoft.hr.annotation.OperCode;
import com.sunraysoft.hr.constant.BizConstant;
import com.sunraysoft.hr.constant.OpcodeConstant;
import com.sunraysoft.hr.domain.entity.basic.PmBscAreaInfo;
import com.sunraysoft.hr.domain.entity.basic.PmBscDeptInfo;
import com.sunraysoft.hr.domain.entity.basic.PmBscDeptType;
import com.sunraysoft.hr.domain.entity.basic.PmBscDict;
import com.sunraysoft.hr.domain.entity.basic.PmBscObserType;
import com.sunraysoft.hr.domain.entity.basic.PmBscObserving;
import com.sunraysoft.hr.service.AreainfoService;
import com.sunraysoft.hr.service.baseinfo.bscdeptype.PmBscDeptTypeService;
import com.sunraysoft.hr.service.baseinfo.bscdict.PmBscDictService;
import com.sunraysoft.hr.service.baseinfo.bscobsertype.PmBscObserTypeService;
import com.sunraysoft.hr.service.department.deptinfo.PmBscDeptInfoService;
import com.sunraysoft.hr.service.department.deptobserving.PmBscObservingService;
import com.sunraysoft.hr.util.BeanUtil;
import com.sunraysoft.hr.util.ExcelUtil;
import com.sunraysoft.hr.util.ExportUtils;
import com.sunraysoft.hr.web.action.base.BaseAction;
import com.sunraysoft.hr.web.data.dto.Page;

/**
 * 基础信息-代码维护-机构表彰信息
 * @author juyf
 * @date 2012-07-31
 */
@Controller
@Scope("prototype")
@Namespace("/department/deptobserving")
@Results({
	@Result(name="redirectPageQuery",type="redirect",location="searchDept_observing.action?pid=${depid}&msg=${toUtf8Encode(msg)}&searchpmBscDeptInfo.pmBscAreaInfo.pid=${searchpmBscDeptInfo.pmBscAreaInfo.pid}&searchpmBscDeptInfo.pmBscAreaInfo.vcDepartmentCode=${searchpmBscDeptInfo.pmBscAreaInfo.vcDepartmentCode}" )
})
public class BscDeptObservingAction extends BaseAction{

	private static final long serialVersionUID = -6978450183577348408L;
	private Long pid;
	private Long depid;
	
	private PmBscObserving pmBscObserving;
	private PmBscObserving selectPmBscObserving;
	private PmBscObserType pmBscObserType;
	private PmBscDeptInfo pmBscDeptInfo;
	private PmBscDeptInfo searchpmBscDeptInfo;
	private List<PmBscObserType> pmbscobsertypelist;// 表彰类别
	private List<PmBscDeptInfo> pmbscdeptinfolist;// 机构
	private List<PmBscObserving> pmbscobservinglist;// 机构表彰列表
	
	private List<PmBscAreaInfo> pmbscareainfolist;// 地区
	private List<PmBscDict> pmbscdictlist;// 职级
	private List<PmBscDeptType> pmbscdepttypelist;// 机构性质
	
	@Resource 
	PmBscDeptInfoService pmBscDeptInfoService;
	@Resource 
	PmBscObserTypeService pmBscObserTypeService;
	@Resource
	PmBscObservingService pmBscObservingService;
	@Resource 
	AreainfoService areainfoService;
	@Resource 
	PmBscDeptTypeService pmBscDeptTypeService;
	@Resource 
	PmBscDictService pmBscDictService;
	
	String canceltype;
	
	@Action("searchDepType")
	@OperCode(code=OpcodeConstant.OPCODE_10501)
	public String searchDeptType(){
		if(searchpmBscDeptInfo == null) {
			searchpmBscDeptInfo = new PmBscDeptInfo();
			PmBscAreaInfo area = areainfoService.find(Long.parseLong(getUser().getAreaId()));
			searchpmBscDeptInfo.setPmBscAreaInfo(area);
		}
		
		searchpmBscDeptInfo.setVcDeleteFlag( Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL) );
		Page<PmBscDeptInfo> pg = new Page<PmBscDeptInfo>();
		pg.setCurrentNum(getPageNum());
		pg.setPerPageRecordCount(getPageSize());
		pg.setOrderBy("area.vcDepartmentCode");
		pg.setOrder(Page.ASC);
		page = pmBscDeptInfoService.findPage(pg, searchpmBscDeptInfo);
		setPage(page);
		return LIST;
	}
	
	@Action("searchDept_observing")
	public String searchDept_observing(){
		pmbscobservinglist = pmBscObservingService.findAll("vcObservTime", false, 
				"vcObservType", BizConstant.DICT_OBSER_TYPE_1,"pmBscDeptInfo","pid",pid);
		return "list_observing";
	}
	
	@Action("list_detail_observing")
	@OperCode(code=OpcodeConstant.OPCODE_10901)
	public String list_detail_observing(){
		loadAll();
		if(pmBscObserving == null) {
			pmBscObserving = new PmBscObserving();
			PmBscDeptInfo dept = new PmBscDeptInfo();
			PmBscAreaInfo area = areainfoService.find(Long.parseLong(getUser().getAreaId()));
			dept.setPmBscAreaInfo(area);
			pmBscObserving.setPmBscDeptInfo(dept);
		}
		
		pmBscObserving.setVcDeleteFlag( Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL) );
		Page<PmBscObserving> pg = new Page<PmBscObserving>();
		pg.setCurrentNum(getPageNum());
		pg.setPerPageRecordCount(getPageSize());
		pg.setOrderBy("area.vcDepartmentCode");
		pg.setOrder(Page.ASC);
		page = pmBscObservingService.findPage(pg, pmBscObserving);
		setPage(page);
		return "list_detail_observing";
	}
	
	@Action("deleteDepType")
	public String deleteDeptType(){
		pmBscObservingService.remove(pid);
		setMsg("删除成功！");
		return REDIRECT_PAGE_QUERY;
	}
	
	@Action("editDepType")
	public String editDeptType(){
		depid=pmBscObserving.getPmBscDeptInfo().getPid();
		//pmBscDeptInfo = pmBscDeptInfoService.find(pmBscObserving.getPmBscDeptInfo().getPid());
		pmBscObserType = pmBscObserTypeService.find(pmBscObserving.getPmBscObserType().getPid());
		//pmBscObserving.setPmBscDeptInfo(pmBscDeptInfo);
		pmBscObserving.setPmBscObserType(pmBscObserType);
		
		PmBscObserving obser = pmBscObservingService.find(pmBscObserving.getPid());
		String[] properties = new String[] {"vcObservTime", "pmBscObserType", "vcRemark"};
		BeanUtil.copyProperties(pmBscObserving, obser, properties);
		pmBscObservingService.saveOrUpdate(obser);
		setMsg("修改成功！");
		return REDIRECT_PAGE_QUERY;
	}
	
	@Action("toViewDeptObserving")
	public String toViewDeptObserving(){
		loadAllList("2");
		return SHOW;
	}
	
	@Action("toEditDepType")
	public String toEditDepType(){
		loadAllList("2");
		return EDIT;
	}
	
	@Action("toAddDepType")
	public String toAddDepType(){
		loadAllList("1");
		return ADD;
	}
	
	@Action("addDepType")
	public String addDeptType(){
		depid=pmBscObserving.getPmBscDeptInfo().getPid();
		pmBscDeptInfo = pmBscDeptInfoService.find(pmBscObserving.getPmBscDeptInfo().getPid());
		pmBscObserType = pmBscObserTypeService.find(pmBscObserving.getPmBscObserType().getPid());
		pmBscObserving.setVcObservType(BizConstant.DICT_OBSER_TYPE_1);
		pmBscObserving.setPmBscDeptInfo(pmBscDeptInfo);
		pmBscObserving.setPmBscObserType(pmBscObserType);
		pmBscObservingService.save(pmBscObserving);
		setMsg("新增成功！");
		return REDIRECT_PAGE_QUERY;
	}

	@SuppressWarnings("unchecked")
	@Action(EXPORT)
	public String export(){
		HttpServletRequest request = ServletActionContext.getRequest();  
        HttpServletResponse response = ServletActionContext.getResponse(); 
		String titleName ="机构立功表彰信息列表";
		String user = "制表人:;"+getUser().getRealName()+";2;1";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String workDate = df.format(new Date());
		String work_date = "制表日期:;"+workDate+";5;1";
		String sheetName = "数据列表";
		String[] conTitle = new String[] {titleName,sheetName, "<br/>", user,"#",work_date};

		if(pmBscObserving == null) {
			pmBscObserving = new PmBscObserving();
			PmBscDeptInfo dept = new PmBscDeptInfo();
			PmBscAreaInfo area = areainfoService.find(Long.parseLong(getUser().getAreaId()));
			dept.setPmBscAreaInfo(area);
			pmBscObserving.setPmBscDeptInfo(dept);
		}
		
		pmBscObserving.setVcDeleteFlag( Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL) );
		Page<PmBscObserving> pg = new Page<PmBscObserving>();
		pg.setCurrentNum(getPageNum());
		pg.setPerPageRecordCount(getPageSize());
		pg.setOrderBy("vcObservTime");
		pg.setOrder(Page.DESC);
		pg.setWhetherpage(false);
		page = pmBscObservingService.findPage(pg, pmBscObserving);
		List list = page.getResultSet();
		
		String[] xhead = ExportUtils.createHeader(PmBscObserving.class);
		Integer[] xheadwidth =  ExportUtils.createHeaderWidth(PmBscObserving.class);

		List newlist = ExportUtils.getexportlist(list);
		int beginRow = 3;
		String [] cells = new String[]{"0;1;2;1","3;1;4;1"};
		ExcelUtil.exportJxlExcel(request, response, newlist, xhead, xheadwidth, titleName, conTitle,beginRow,cells);
		return null;
	}
	
	//机构立功表彰用
	public void loadAllList(String str){
		pmbscobsertypelist = pmBscObserTypeService.findAll("pid",true,"vcType",BizConstant.DICT_OBSER_TYPE_1);
		if("1".equals(str)){
			pmBscDeptInfo = pmBscDeptInfoService.find(pid);
		}else if("2".equals(str)){
			pmBscObserving = pmBscObservingService.find(pid);
		}
	}
	
	// 机构立功表彰详情用
	public void loadAll(){
		pmbscareainfolist = areainfoService.findAll();
		pmbscdictlist = pmBscDictService.findAll("vcPosition", true, "vcCode", (long)BizConstant.DICT_DATA_TYPE_1);
		pmbscdepttypelist = pmBscDeptTypeService.findAll();
		pmbscobsertypelist = pmBscObserTypeService.findAll("pid", true, "vcType", BizConstant.DICT_OBSER_TYPE_1);
	}


	public PmBscDeptInfo getPmBscDeptInfo() {
		return pmBscDeptInfo;
	}

	public void setPmBscDeptInfo(PmBscDeptInfo pmBscDeptInfo) {
		this.pmBscDeptInfo = pmBscDeptInfo;
	}


	public PmBscObserving getPmBscObserving() {
		return pmBscObserving;
	}

	public void setPmBscObserving(PmBscObserving pmBscObserving) {
		this.pmBscObserving = pmBscObserving;
	}

	public PmBscObserType getPmBscObserType() {
		return pmBscObserType;
	}
	
	public List<PmBscObserving> getPmbscobservinglist() {
		return pmbscobservinglist;
	}

	public void setPmBscObserType(PmBscObserType pmBscObserType) {
		this.pmBscObserType = pmBscObserType;
	}

	public List<PmBscObserType> getPmbscobsertypelist() {
		return pmbscobsertypelist;
	}

	public List<PmBscDeptInfo> getPmbscdeptinfolist() {
		return pmbscdeptinfolist;
	}

	public List<PmBscAreaInfo> getPmbscareainfolist() {
		return pmbscareainfolist;
	}

	public List<PmBscDict> getPmbscdictlist() {
		return pmbscdictlist;
	}

	public List<PmBscDeptType> getPmbscdepttypelist() {
		return pmbscdepttypelist;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}
	
	public Long getPid() {
		return pid;
	}

	public Long getDepid() {
		return depid;
	}

	public void setDepid(Long depid) {
		this.depid = depid;
	}

	public String getCanceltype() {
		return canceltype;
	}

	public void setCanceltype(String canceltype) {
		this.canceltype = canceltype;
	}

	public void setSelectPmBscObserving(PmBscObserving selectPmBscObserving) {
		this.selectPmBscObserving = selectPmBscObserving;
	}

	public PmBscObserving getSelectPmBscObserving() {
		return selectPmBscObserving;
	}

	public PmBscDeptInfo getSearchpmBscDeptInfo() {
		return searchpmBscDeptInfo;
	}

	public void setSearchpmBscDeptInfo(PmBscDeptInfo searchpmBscDeptInfo) {
		this.searchpmBscDeptInfo = searchpmBscDeptInfo;
	}
	
}
