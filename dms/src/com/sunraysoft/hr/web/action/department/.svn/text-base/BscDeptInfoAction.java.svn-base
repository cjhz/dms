/**
 * 
 */
package com.sunraysoft.hr.web.action.department;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sunraysoft.hr.annotation.OperCode;
import com.sunraysoft.hr.constant.BizConstant;
import com.sunraysoft.hr.constant.LogConst;
import com.sunraysoft.hr.constant.OpcodeConstant;
import com.sunraysoft.hr.domain.entity.basic.PmBscAreaInfo;
import com.sunraysoft.hr.domain.entity.basic.PmBscDeptInfo;
import com.sunraysoft.hr.domain.entity.basic.PmBscDeptType;
import com.sunraysoft.hr.domain.entity.basic.PmBscDict;
import com.sunraysoft.hr.domain.entity.basic.PmBscObserType;
import com.sunraysoft.hr.domain.entity.basic.PmBscObserving;
import com.sunraysoft.hr.domain.entity.basic.PmBscStaffDept;
import com.sunraysoft.hr.domain.entity.manage.PmMngServerLog;
import com.sunraysoft.hr.service.AreainfoService;
import com.sunraysoft.hr.service.StaffDeptService;
import com.sunraysoft.hr.service.baseinfo.bscdeptype.PmBscDeptTypeService;
import com.sunraysoft.hr.service.baseinfo.bscdict.PmBscDictService;
import com.sunraysoft.hr.service.baseinfo.bscobsertype.PmBscObserTypeService;
import com.sunraysoft.hr.service.department.deptinfo.PmBscDeptInfoService;
import com.sunraysoft.hr.service.department.deptobserving.PmBscObservingService;
import com.sunraysoft.hr.service.log.PmMngServerLogService;
import com.sunraysoft.hr.util.BeanUtil;
import com.sunraysoft.hr.util.DateUtil;
import com.sunraysoft.hr.util.ExcelUtil;
import com.sunraysoft.hr.util.ExportUtils;
import com.sunraysoft.hr.util.LogUtils;
import com.sunraysoft.hr.web.action.base.BaseAction;
import com.sunraysoft.hr.web.data.dto.JsonWrapper;
import com.sunraysoft.hr.web.data.dto.Page;

/**
 * 基础信息-代码维护-机构信息管理
 * @author juyf
 * @date 2012-07-31
 */
@Controller
@Scope("prototype")
@Namespace("/department/deptinfo")
@Results({
	@Result(name="redirectPageQuery",type="redirect",location="searchDepType.action?msg=${toUtf8Encode(msg)}&searchPmBscDeptInfo.pmBscAreaInfo.pid=${searchPmBscDeptInfo.pmBscAreaInfo.pid}&searchPmBscDeptInfo.pmBscAreaInfo.vcDepartmentCode=${searchPmBscDeptInfo.pmBscAreaInfo.vcDepartmentCode}" )
})
public class BscDeptInfoAction extends BaseAction{

	private static final long serialVersionUID = -6978450183577348408L;
	private Long pid;
	
	private PmBscDeptInfo pmBscDeptInfo;
	private PmBscDeptInfo searchPmBscDeptInfo;
	private PmBscDeptType pmBscDeptType;
	private PmBscAreaInfo pmBscAreaInfo;
	private PmBscDict pmBscDict;
	private List<PmBscDeptInfo> pmbscdeptinfolist;// 机构
	private List<PmBscAreaInfo> pmbscareainfolist;// 地区
	private List<PmBscDict> pmbscdictlist;// 职级
	private List<PmBscDeptType> pmbscdepttypelist;// 机构性质
	private List<PmBscObserType> pmBscObserTypelist;// 机构立功表彰
	private List<PmMngServerLog> serverlogs;
	private List<PmBscObserving> obserList;
	
	@Resource 
	PmBscDeptInfoService pmBscDeptInfoService;
	@Resource 
	AreainfoService areainfoService;
	@Resource 
	PmBscDeptTypeService pmBscDeptTypeService;
	@Resource 
	PmBscDictService pmBscDictService;
	@Resource 
    StaffDeptService staffDeptService;
	@Resource 
    PmBscObservingService pmBscObservingService;
	@Resource
	PmBscObserTypeService pmBscObserTypeService;
	@Resource PmMngServerLogService serverLogService;
	
	@Action("searchDepType")
	@OperCode(code=OpcodeConstant.OPCODE_10101+":"+OpcodeConstant.OPCODE_10102+":"+OpcodeConstant.OPCODE_10103)
	public String searchDeptType(){
		loadAllList("search");
		
		if(searchPmBscDeptInfo == null) {
			searchPmBscDeptInfo = new PmBscDeptInfo();
			PmBscAreaInfo area = areainfoService.find(Long.parseLong(getUser().getAreaId()));
			searchPmBscDeptInfo.setPmBscAreaInfo(area);
		}
		searchPmBscDeptInfo.setVcDeleteFlag( Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL) );
		Page<PmBscDeptInfo> pg = new Page<PmBscDeptInfo>();
		pg.setCurrentNum(getPageNum());
		pg.setPerPageRecordCount(getPageSize());
		pg.setOrderBy("area.vcDepartmentCode");
		pg.setOrder(Page.ASC);
		page = pmBscDeptInfoService.findPage(pg, searchPmBscDeptInfo);
		setPage(page);
		return LIST;
	}
	
	@Action("deleteDepType")
	@OperCode(code=OpcodeConstant.OPCODE_10103)
	public String deleteDeptType(){
		List<PmBscStaffDept> staffDepts = staffDeptService.findAll(PmBscStaffDept.class, "pmBscDeptInfo", "pid", pid);
		List<PmBscObserving> observings = pmBscObservingService.findAll(PmBscObserving.class, "pmBscDeptInfo", "pid", pid);
		if(staffDepts != null && staffDepts.size()>0){
			for(PmBscStaffDept staffdept:staffDepts){
				if(staffdept.getVcDeleteFlag() == BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL){
					setMsg("待删除的机构已被人员引用，不能删除！");
					return REDIRECT_PAGE_QUERY;
				}
			}
		}else if(observings != null && observings.size()>0){
			setMsg("待删除的机构已被机构立功表彰引用，不能删除！");
			return REDIRECT_PAGE_QUERY;
		}
		pmBscDeptInfoService.remove(pid);
		setMsg("删除成功！");
		return REDIRECT_PAGE_QUERY;
	}
	
	@SuppressWarnings("unchecked")
	@Action(EXPORT)
	public String export(){
		HttpServletRequest request = ServletActionContext.getRequest();  
        HttpServletResponse response = ServletActionContext.getResponse(); 
		String titleName ="机构信息列表";
		String user = "制表人:;"+getUser().getRealName()+";2;1";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String workDate = df.format(new Date());
		String work_date = "制表日期:;"+workDate+";5;1";
		String sheetName = "数据列表";
		String[] conTitle = new String[] {titleName,sheetName, "<br/>", user,"#",work_date};

		if(searchPmBscDeptInfo == null) {
			searchPmBscDeptInfo = new PmBscDeptInfo();
			PmBscAreaInfo area = areainfoService.find(Long.parseLong(getUser().getAreaId()));
			searchPmBscDeptInfo.setPmBscAreaInfo(area);
		}
		searchPmBscDeptInfo.setVcDeleteFlag( Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL) );
		Page<PmBscDeptInfo> pg = new Page<PmBscDeptInfo>();
		pg.setCurrentNum(getPageNum());
		pg.setPerPageRecordCount(getPageSize());
		pg.setOrderBy("area.vcDepartmentCode");
		pg.setOrder(Page.ASC);
		pg.setWhetherpage(false);
		page = pmBscDeptInfoService.findPage(pg, searchPmBscDeptInfo);
		List list = page.getResultSet();
		
		String[] xhead = ExportUtils.createHeader(PmBscDeptInfo.class);
		Integer[] xheadwidth =  ExportUtils.createHeaderWidth(PmBscDeptInfo.class);

		List newlist = ExportUtils.getexportlist(list);
		int beginRow = 3;
		String [] cells = new String[]{"0;1;2;1","3;1;8;1"};
		ExcelUtil.exportJxlExcel(request, response, newlist, xhead, xheadwidth, titleName, conTitle,beginRow,cells);
		return null;
	}
	
	@Action("editDepType")
	@OperCode(code=OpcodeConstant.OPCODE_10103)
	public String editDeptType(){
//		List<PmBscStaffDept> staffDepts = staffDeptService.findAll(PmBscStaffDept.class, "pmBscDeptInfo", "pid", pid);
//		List<PmBscObserving> observings = pmBscObservingService.findAll(PmBscObserving.class, "pmBscDeptInfo", "pid", pid);
//		if(staffDepts != null && staffDepts.size()>0){
//			setMsg("待修改的机构已被人员引用，不能修改！");
//			return REDIRECT_PAGE_QUERY;
//		}else if(observings != null && observings.size()>0){
//			setMsg("待修改的机构已被机构立功表彰引用，不能修改！");
//			return REDIRECT_PAGE_QUERY;
//		}
		
		pmBscDeptType = pmBscDeptTypeService.find(pmBscDeptInfo.getPmBscDeptType().getPid());
		if(pmBscDeptType == null){
			setMsg("待修改的机构类型已不存在，不能修改！");
			return EDIT;
		}
		pmBscDeptInfo.setPmBscDeptType(pmBscDeptType);

		pmBscAreaInfo = areainfoService.find(pmBscDeptInfo.getPmBscAreaInfo().getPid());
		if(pmBscAreaInfo == null){
			setMsg("待修改的机构地区已不存在，不能修改！");
			return EDIT;
		}
		pmBscDeptInfo.setPmBscAreaInfo(pmBscAreaInfo);

		pmBscDict = pmBscDictService.find(pmBscDeptInfo.getPmBscDict().getPid());
		if(pmBscDict == null){
			setMsg("待修改的机构职级已不存在，不能修改！");
			return EDIT;
		}		
		pmBscDeptInfo.setPmBscDict(pmBscDict);
		
		PmBscDeptInfo pmBscDeptInfodb = pmBscDeptInfoService.find(pmBscDeptInfo.getPid());
		if(pmBscDeptInfodb == null){
			setMsg("待修改的机构已不存在，不能修改！");
			return EDIT;
		}
		
		String[] properties = new String[] {"pmBscDeptType", "pmBscAreaInfo", "pmBscDict", "vcDeptName", "vcChargeName", "vcPhone","vcContactName","vcContactPhone", "vcAddress", "vcRemark"};
		String logMsg = makeLogUpdate(pmBscDeptInfodb, pmBscDeptInfo);
		
		BeanUtil.copyProperties(pmBscDeptInfo, pmBscDeptInfodb, properties);
		
		pmBscDeptInfoService.saveOrUpdate(pmBscDeptInfodb);
		setMsg("修改成功！");
		if(logMsg!=null)
			logUpdate(logMsg, pmBscDeptInfodb.getPid());
		
		return REDIRECT_PAGE_QUERY;
	}
	
	private void logUpdate(String logMsg, Long beOperaterId) {
		persistServerLog(LogConst.LOG_TYPE_DEPT_UPDATE, logMsg, beOperaterId);
	}

	
	private String makeLogUpdate(PmBscDeptInfo before, PmBscDeptInfo after) {
		StringBuilder sb = new StringBuilder();
		String title ="";
		if(before==null){
			title = DateUtil.getDayStr(System.currentTimeMillis()) + " 新增机构 " ;
		}else{
			title = DateUtil.getDayStr(System.currentTimeMillis()) + " " + before.getVcDeptName() + " ";
		}
		sb.append(title);
		sb.append(LogUtils.generateLogMsg(before, after));
		
		if(sb.toString().endsWith(title)) {
			//sb.append("没有任何更新");
			return null;
		}
		
		return sb.toString();
	}
	
	@Action("toEditDepType")
	@OperCode(code=OpcodeConstant.OPCODE_10103)
	public String toEditDepType(){
//		List<PmBscStaffDept> staffDepts = staffDeptService.findAll(PmBscStaffDept.class, "pmBscDeptInfo", "pid", pid);
//		List<PmBscObserving> observings = pmBscObservingService.findAll(PmBscObserving.class, "pmBscDeptInfo", "pid", pid);
//		if(staffDepts != null && staffDepts.size()>0){
//			setMsg("待修改的机构已被人员引用，不能修改！");
//			return REDIRECT_PAGE_QUERY;
//		}else if(observings != null && observings.size()>0){
//			setMsg("待修改的机构已被机构立功表彰引用，不能修改！");
//			return REDIRECT_PAGE_QUERY;
//		}
		loadAllList("");
		pmBscDeptInfo = pmBscDeptInfoService.find(pid);
		return EDIT;
	}
	
	@Action("toViewDepType")
	@OperCode(code=OpcodeConstant.OPCODE_10101+":"+OpcodeConstant.OPCODE_10102+":"+OpcodeConstant.OPCODE_10103)
	public String toViewDepType(){
		loadAllList("");
		serverlogs = serverLogService.findByValuesOrder("pid", true, new String[]{"vcDeleteFlag","logType","vcDataPid"}, new Object[]{BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL,LogConst.LOG_TYPE_DEPT_UPDATE,pid});
		pmBscDeptInfo = pmBscDeptInfoService.find(pid);
		obserList = pmBscObservingService.findByValuesOrder("vcObservTime", false, new String[]{"vcDeleteFlag","pmBscDeptInfo.pid"}, new Object[]{BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL,pid});
		return SHOW;
	}
	
	@Action("toAddDepType")
	@OperCode(code=OpcodeConstant.OPCODE_10102)
	public String toAddDepType(){
		loadAllList("");
		return ADD;
	}
	
	@Action("addDepType")
	@OperCode(code=OpcodeConstant.OPCODE_10102)
	public String addDeptType(){
		Set<PmBscObserving> pmBscObservings = new HashSet<PmBscObserving>();
		Set<PmBscStaffDept> pmBscStaffDepts = new HashSet<PmBscStaffDept>();
		pmBscDeptType = pmBscDeptTypeService.find(pmBscDeptInfo.getPmBscDeptType().getPid());
		pmBscAreaInfo = areainfoService.find(pmBscDeptInfo.getPmBscAreaInfo().getPid());
		pmBscDict = pmBscDictService.find(pmBscDeptInfo.getPmBscDict().getPid());
		pmBscDeptInfo.setPmBscObservings(pmBscObservings);
		pmBscDeptInfo.setPmBscStaffDepts(pmBscStaffDepts);
		pmBscDeptInfo.setPmBscDeptType(pmBscDeptType);
		pmBscDeptInfo.setPmBscAreaInfo(pmBscAreaInfo);
		pmBscDeptInfo.setPmBscDict(pmBscDict);
		String logMsg = makeLogUpdate(null, pmBscDeptInfo);//记录操作日志
		pmBscDeptInfoService.save(pmBscDeptInfo);
		setMsg("新增成功！");
		if(logMsg!=null)
			logUpdate(logMsg, pmBscDeptInfo.getPid());
		return REDIRECT_PAGE_QUERY;
	}
	
	@SuppressWarnings("unchecked")
	@Action("ajaxCheckDeptNameUnique")
	public String ajaxCheckRoleNameUnique() {
		boolean result ;
		List<PmBscDeptInfo> deptlists = pmBscDeptInfoService.findBy("vcDeptName", pmBscDeptInfo.getVcDeptName());
		if(deptlists!=null && deptlists.size()>0){
			if(pmBscDeptInfo.getPid() == null){
				result = false ;
			}else{
				boolean a = deptlists.get(0).getPid().equals(pmBscDeptInfo.getPid());
				result = a;
			}
		}else{result = true;}
		JsonWrapper wrapper = new JsonWrapper();
		wrapper.setFlag(result);
		wrapper.setMsg(result ? "" : "机构名称不能重复");
		JSONObject jsonObj = JSONObject.fromObject(wrapper);
		setJsonText( jsonObj.toString() );
		return JSON_PAGE;
	}

	public void loadAllList(String type){
		pmbscareainfolist = areainfoService.findAll();
		pmbscdictlist = pmBscDictService.findAll("vcPosition", true, "vcCode", (long)BizConstant.DICT_DATA_TYPE_1);
		pmbscdepttypelist = pmBscDeptTypeService.findAll();
		if("search".equals(type)){
			pmBscObserTypelist = pmBscObserTypeService.findAll("pid", true, "vcType", BizConstant.DICT_OBSER_TYPE_1);
		}
	}

	public List<PmBscDict> getPmbscdictlist() {
		return pmbscdictlist;
	}

	public List<PmBscAreaInfo> getPmbscareainfolist() {
		return pmbscareainfolist;
	}

	public List<PmBscDeptType> getPmbscdepttypelist() {
		return pmbscdepttypelist;
	}

	public PmBscDeptInfo getPmBscDeptInfo() {
		return pmBscDeptInfo;
	}

	public void setPmBscDeptInfo(PmBscDeptInfo pmBscDeptInfo) {
		this.pmBscDeptInfo = pmBscDeptInfo;
	}

	public PmBscDeptType getPmBscDeptType() {
		return pmBscDeptType;
	}

	public List<PmBscObserType> getPmBscObserTypelist() {
		return pmBscObserTypelist;
	}

	public void setPmBscDeptType(PmBscDeptType pmBscDeptType) {
		this.pmBscDeptType = pmBscDeptType;
	}

	public PmBscAreaInfo getPmBscAreaInfo() {
		return pmBscAreaInfo;
	}

	public void setPmBscAreaInfo(PmBscAreaInfo pmBscAreaInfo) {
		this.pmBscAreaInfo = pmBscAreaInfo;
	}

	public PmBscDict getPmBscDict() {
		return pmBscDict;
	}

	public void setPmBscDict(PmBscDict pmBscDict) {
		this.pmBscDict = pmBscDict;
	}

	public List<PmBscDeptInfo> getPmbscdeptinfolist() {
		return pmbscdeptinfolist;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}
	
	public Long getPid() {
		return pid;
	}

	public PmBscDeptInfo getSearchPmBscDeptInfo() {
		return searchPmBscDeptInfo;
	}

	public void setSearchPmBscDeptInfo(PmBscDeptInfo searchPmBscDeptInfo) {
		this.searchPmBscDeptInfo = searchPmBscDeptInfo;
	}

	public List<PmMngServerLog> getServerlogs() {
		return serverlogs;
	}

	public List<PmBscObserving> getObserList() {
		return obserList;
	}
	
}
