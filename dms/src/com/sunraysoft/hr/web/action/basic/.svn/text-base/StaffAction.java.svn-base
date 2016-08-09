package com.sunraysoft.hr.web.action.basic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.sunraysoft.hr.constant.Constant;
import com.sunraysoft.hr.constant.LogConst;
import com.sunraysoft.hr.constant.OpcodeConstant;
import com.sunraysoft.hr.domain.entity.basic.PmBscAreaInfo;
import com.sunraysoft.hr.domain.entity.basic.PmBscDeptInfo;
import com.sunraysoft.hr.domain.entity.basic.PmBscDict;
import com.sunraysoft.hr.domain.entity.basic.PmBscJobChange;
import com.sunraysoft.hr.domain.entity.basic.PmBscObserType;
import com.sunraysoft.hr.domain.entity.basic.PmBscObservingStaff;
import com.sunraysoft.hr.domain.entity.basic.PmBscRankChange;
import com.sunraysoft.hr.domain.entity.basic.PmBscStaffDept;
import com.sunraysoft.hr.domain.entity.basic.PmBscStaffInfo;
import com.sunraysoft.hr.domain.entity.manage.PmMngChangeInfo;
import com.sunraysoft.hr.service.AreainfoService;
import com.sunraysoft.hr.service.StaffChangeService;
import com.sunraysoft.hr.service.StaffDeptService;
import com.sunraysoft.hr.service.StaffJobChgService;
import com.sunraysoft.hr.service.StaffRankChgService;
import com.sunraysoft.hr.service.StaffService;
import com.sunraysoft.hr.service.baseinfo.bscdict.PmBscDictService;
import com.sunraysoft.hr.service.baseinfo.bscobsertype.PmBscObserTypeService;
import com.sunraysoft.hr.service.department.deptinfo.PmBscDeptInfoService;
import com.sunraysoft.hr.service.department.deptobserving.PmBscObservingStaffService;
import com.sunraysoft.hr.util.BeanUtil;
import com.sunraysoft.hr.util.DateUtil;
import com.sunraysoft.hr.util.ExcelUtil;
import com.sunraysoft.hr.util.ExportUtils;
import com.sunraysoft.hr.util.LogUtils;
import com.sunraysoft.hr.web.action.base.BaseAction;
import com.sunraysoft.hr.web.data.dto.Page;


@Controller 
@Scope("prototype")
@Namespace("/basic/staff")
@Results({
	@Result(name="redirectPageQuery", type="redirect", location="pageQuery.action?success=${success}&msg=${toUtf8Encode(msg)}&search.vcRealName=${toUtf8Encode2(search.vcRealName)}&pageNum=${pageNum}&dialogAreaDisplay=${dialogAreaDisplay}&search.vcOphone=${search.vcOphone}")
})
public class StaffAction extends BaseAction  {
	private static final long serialVersionUID = 1L;

//	private static final Log log = LogFactory.getLog(StaffAction.class);
	
	private Long staffId;
	private PmBscStaffInfo search;
	private PmBscStaffInfo staff;
	
	@Resource private StaffService staffService;
	@Resource private PmBscDictService PmBscDictService;
	@Resource private StaffDeptService staffDeptService;
	@Resource private StaffChangeService staffChangeService;
	@Resource private PmBscObservingStaffService pmBscObservingAtaffService;
	@Resource private PmBscDeptInfoService PmBscDeptInfoService;
	@Resource private PmBscObserTypeService pmBscObserTypeService;
	
	@Resource private StaffRankChgService rankChgService;
	@Resource private StaffJobChgService jobChgService;
	
	@Resource private AreainfoService areainfoService;

	
	
	List<PmBscDict> dictPoliticals;//政治面貌
	List<PmBscDict> dictCulturals;//文化程度
	List<PmBscDict> dictFormations;//编制
	List<PmBscDict> dictRanks;//职级
	List<PmBscDict> dictprops;//人员属性
	List<PmBscDict> dictworks;//工作分工
	List<PmBscDict> dictjobs;//职务
	
	List<PmBscDict> newdictprops;//用于查看
	List<PmBscDict> newdictworks;//
	
	private String staffdepts;
	List<PmBscStaffDept> deptList;
	
	List<PmMngChangeInfo> changeList;//人员调动
	List<PmBscObservingStaff> obserStaffList;//人员立功表彰
	List<PmBscObserType> pmBscObserTypeList;//人员立功表彰情况
	
	List<PmBscRankChange> rankChangelist;//人员职级变动记录
	List<PmBscRankChange> jobChangelist;//人员职务变动记录
	List<PmBscRankChange> formationChangelist;//人员编制变动记录
	List<PmBscJobChange> workChangelist;//人员工作分工变动记录

	String dialogArea;
	String dialogAreaDisplay;
	
	int allparam;
	String areainfoId;
	
	@Action(PAGE_QUERY)
	@OperCode(code=OpcodeConstant.OPCODE_11701+":"+OpcodeConstant.OPCODE_11702+":"+OpcodeConstant.OPCODE_11703)
	public String pageQuery() {
		loadAllList("search");
		if(search == null) {
			search = new PmBscStaffInfo();
			search.setVcOphone(getUser().getAreaId());
			dialogAreaDisplay = getUser().getAreaCode();
		}
		if(search.getIsShowAll()==null){search.setIsShowAll(0L);}
		search.setVcDeleteFlag( Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL) );
		Page<PmBscStaffInfo> pg = new Page<PmBscStaffInfo>();
		pg.setCurrentNum(getPageNum());
		pg.setPerPageRecordCount(getPageSize());
		pg.setOrderBy("pid");
		pg.setOrder(Page.DESC);
		page = staffService.findPage(pg, search);
		setPage(page);
		return LIST;
	}
	
	@Action(ADD)
	@OperCode(code=OpcodeConstant.OPCODE_11702)
	public String add() {
		loadAllList("");
		return ADD;
	}
	
	@Action(CREATE)
	@OperCode(code=OpcodeConstant.OPCODE_11702)
	public String create() throws Exception {
//		if(!checkRoleNameUnique()) {
//			setFailMsg("角色名已存在");
//			return ADD;
//		}
		PmBscDict dictCulturals =  PmBscDictService.find(staff.getDictCulturals().getPid());
		if(dictCulturals==null){
			setFailMsg("所选文化程度不存在");
			return EDIT;
		}
		staff.setDictCulturals(dictCulturals);
		
		PmBscDict dictPoliticals = PmBscDictService.find(staff.getDictPoliticals().getPid());
		if(dictPoliticals==null){
			setFailMsg("所选政治面貌不存在");
			return EDIT;
		}
		staff.setDictPoliticals(dictPoliticals);
		
		staff.setVcDeleteFlag( Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL) );
		if(staff.getPhone_area()!=null&&!staff.getPhone_area().equals("")&&staff.getPhone_code()!=null&&!staff.getPhone_code().equals("")){
			staff.setVcOphone(staff.getPhone_area()+"-"+staff.getPhone_code());
		}
		staffService.saveOrUpdate(staff);
		if(staffdepts!=null){
			String[] depts = staffdepts.split(";");
			for(int i=0;i<depts.length;i++){
				if(depts[i]!=null&&!depts[i].equals("")){
					PmBscStaffDept staffdept= new PmBscStaffDept();
					PmBscDeptInfo dept = PmBscDeptInfoService.find(Long.valueOf(depts[i]));
					staffdept.setPmBscDeptInfo(dept);
					staffdept.setPmBscStaffInfo(staff);
					staffdept.setVcDepartmentCode(dept.getPmBscAreaInfo().getVcDepartmentCode());
					staffdept.setVcDeleteFlag( Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL) );
					staffDeptService.saveOrUpdate(staffdept);
				}
			}
		}
		setSuccessMsg( "新增成功" );
		System.err.println(staff.getDictPoliticals().getVcName());
		String logMsg = makeLogUpdate(null, staff);//新增操作记录
		
		if(logMsg!=null)
			logUpdate(logMsg, staff.getPid());
		
		return REDIRECT_PAGE_QUERY;
	}
	
	@Action(SHOW)
	@OperCode(code=OpcodeConstant.OPCODE_11701+":"+OpcodeConstant.OPCODE_11702+":"+OpcodeConstant.OPCODE_11703)
	public String show() {
		if(staffId != null) {
			staff = staffService.find(staffId);
			if(staff == null) {
				setFailMsg( "待查看记录不存在" );
				return REDIRECT_PAGE_QUERY;
			}
		}
		Map<Long, List<PmBscDict>> map = getMarketControl();
		dictprops = map.get(BizConstant.DICT_DATA_TYPE_7);
		dictworks = map.get(BizConstant.DICT_DATA_TYPE_4);
		
		String PersonProp = staff.getVcPersonProp()==null?"":staff.getVcPersonProp();
		String WorkDivision = staff.getVcWorkDivision()==null?"":staff.getVcWorkDivision();
		String[] props = PersonProp.split(";");
		String[] works = WorkDivision.split(";");
		newdictprops = getList(dictprops,props);
		//newdictworks = getList(dictworks,works);
		deptList = staffDeptService.findBy("pmBscStaffInfo.pid", staffId);
		changeList = staffChangeService.findBy("pmBscStaffInfo.pid", staffId);
		obserStaffList = pmBscObservingAtaffService.findByValuesOrder("vcObservTime", false, new String[]{"vcDeleteFlag","pmBscStaffInfo.pid"}, new Object[]{BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL,staffId});
		
		//以升序排序，在页面取第一条为原始数据
		rankChangelist = rankChgService.findByValuesOrder("pid", true,new String[]{"vcDeleteFlag","pmBscStaffInfo.pid","vcType"}, new Object[]{BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL,staffId,BizConstant.STAFF_ATTR_TYPE_KEY_1});
		jobChangelist = rankChgService.findByValuesOrder("pid", true,new String[]{"vcDeleteFlag","pmBscStaffInfo.pid","vcType"}, new Object[]{BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL,staffId,BizConstant.STAFF_ATTR_TYPE_KEY_2});
		formationChangelist = rankChgService.findByValuesOrder("pid", true,new String[]{"vcDeleteFlag","pmBscStaffInfo.pid","vcType"}, new Object[]{BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL,staffId,BizConstant.STAFF_ATTR_TYPE_KEY_3});
		workChangelist = jobChgService.findByValuesOrder("pid", true,new String[]{"vcDeleteFlag","pmBscStaffInfo.pid"}, new Object[]{BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL,staffId});
		return SHOW;
	}
	
	
	
	/**
	 * 获得newList
	 * @param list
	 * @param arr
	 * @return
	 */
	public List<PmBscDict> getList(List<PmBscDict> list,String[] arr){
		List<PmBscDict> newList = new ArrayList<PmBscDict>();
		if(list!=null&&list.size()>0){
			for(PmBscDict dict:list){
				if(dict!=null&&arr.length>0){
					for(int i=0;i<arr.length;i++){
						if(arr[i].equals(dict.getPid()+"")){
							newList.add(dict);
						}
					}
				}
			}
		}
		return newList;
	}
	
	@Action(EDIT)
	@OperCode(code=OpcodeConstant.OPCODE_11703)
	public String edit() throws Exception {
		if(staffId != null) {
			staff = staffService.find(staffId);
			if(staff == null) {
				setFailMsg( "待修改的用户不存在" );
				return REDIRECT_PAGE_QUERY;
			}
		}
		loadAllList("");
		if(staff.getVcOphone()!=null&&!staff.getVcOphone().equals("")){
			String[] phones = staff.getVcOphone().split("-");
			staff.setPhone_area(phones[0]);
			staff.setPhone_code(phones[1]);
		}
		deptList = staffDeptService.findBy("pmBscStaffInfo.pid", staffId);
		return EDIT;
	}
	
	@Action(UPDATE)
	@OperCode(code=OpcodeConstant.OPCODE_11703)
	public String update() throws Exception {
//		if(!checkRoleNameUnique()) {
//			setFailMsg("角色名已存在");
//			return EDIT;
//		}
		
		PmBscDict dictCulturals =  PmBscDictService.find(staff.getDictCulturals().getPid());
		if(dictCulturals==null){
			setFailMsg("所选文化程度不存在");
			return EDIT;
		}
		staff.setDictCulturals(dictCulturals);
		
		PmBscDict dictPoliticals = PmBscDictService.find(staff.getDictPoliticals().getPid());
		if(dictPoliticals==null){
			setFailMsg("所选政治面貌不存在");
			return EDIT;
		}
		staff.setDictPoliticals(dictPoliticals);
		if(staff.getPhone_area()!=null&&!staff.getPhone_area().equals("")&&staff.getPhone_code()!=null&&!staff.getPhone_code().equals("")){
			staff.setVcOphone(staff.getPhone_area()+"-"+staff.getPhone_code());
		}
		PmBscStaffInfo dbstaff = staffService.find(staff.getPid());
		if(dbstaff == null) {
			setFailMsg( "待修改的用户不存在" );
		} else {
			String[] properties = new String[] {"vcRealName", "vcSex", "vcBirth", "vcMphone", "vcOphone", "vcFax", "vcAlarm","vcCardNo","vcRemark", "dictPoliticals", "dictCulturals","vcPersonProp","vcOfficeOnline","vcVirtualNum","vcDrugDate"};
			String logMsg = makeLogUpdate(dbstaff, staff);
			
			BeanUtil.copyProperties(staff, dbstaff, properties);
			staffService.saveOrUpdate(dbstaff);
			setSuccessMsg( "修改成功" );
			
			if(logMsg!=null)
				logUpdate(logMsg, dbstaff.getPid());
		}
		
		return REDIRECT_PAGE_QUERY;
	}
	
	private void logUpdate(String logMsg, Long id) {
		persistServerLog(LogConst.LOG_TYPE_STAFF_UPDATE, logMsg, id);
	}
	
	private String makeLogUpdate(PmBscStaffInfo before, PmBscStaffInfo after) {
		StringBuilder sb = new StringBuilder();
		String title="";
		if(before==null){
			title = DateUtil.getDayStr(System.currentTimeMillis()) + " 新增人员信息 ";
		}else{
			title = DateUtil.getDayStr(System.currentTimeMillis()) + " " + before.getVcRealName() + " ";
		}
		sb.append(title);
		sb.append(LogUtils.generateLogMsg(before, after));
		
		if(sb.toString().endsWith(title)) {
			//sb.append("没有任何更新");
			return null;
		}
		
		return sb.toString();
	}
	
	@Action(DESTORY)
	@OperCode(code=OpcodeConstant.OPCODE_11703)
	public String destory() throws Exception {
		PmBscStaffInfo dbStaff = staffService.find(staffId);
		if(dbStaff == null) {
			setFailMsg( "待删除的用户不存在" );
		} else if(staffId < Constant.SEQUENCE_START_WITH) {
			setFailMsg( "选中的记录不允许被删除" );
		} else {
			//查找 人员对应的部门；人员调动；人员立功表彰 引用；人员职级、职务调动
			List<PmBscStaffDept> staffDepts = staffDeptService.findByValues(new String[]{"vcDeleteFlag","pmBscStaffInfo.pid"}, new Object[]{BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL,staffId});
			List<PmMngChangeInfo> changeInfos = staffChangeService.findByValues(new String[]{"vcDeleteFlag","pmBscStaffInfo.pid"}, new Object[]{BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL,staffId});
			List<PmBscObservingStaff> Observings = pmBscObservingAtaffService.findByValues(new String[]{"vcDeleteFlag","pmBscStaffInfo.pid","vcObservType"}, new Object[]{BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL,staffId,BizConstant.DICT_OBSER_TYPE_2});
			
			List<PmBscRankChange> rankchanges = rankChgService.findByValues(new String[]{"vcDeleteFlag","pmBscStaffInfo.pid"}, new Object[]{BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL,staffId});
			List<PmBscJobChange> jobchanges = jobChgService.findByValues(new String[]{"vcDeleteFlag","pmBscStaffInfo.pid"}, new Object[]{BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL,staffId});
			
			if((changeInfos == null || changeInfos.isEmpty())&&(Observings == null || Observings.isEmpty())&&(rankchanges == null || rankchanges.isEmpty())&&(jobchanges == null || jobchanges.isEmpty())) {
				dbStaff.setVcDeleteFlag(Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_INVALID));
				staffService.saveOrUpdate(dbStaff);
				for(PmBscStaffDept staffdept:staffDepts){
					if(staffdept!=null){
						staffdept.setVcDeleteFlag(Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_INVALID));
						staffDeptService.saveOrUpdate(staffdept);
					}
				}
				setSuccessMsg( "删除成功" );
				//logDestory(dbRole);
				
			} else {
				setFailMsg( "待删除的用户已经被引用" );
			}
		}
		
		return REDIRECT_PAGE_QUERY;
	}
	
	@Action(DIALOG)
	public String deptDialog() throws Exception {
		PmBscDeptInfo dept = new PmBscDeptInfo();
		dept.setVcDeleteFlag( Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL) );
		// 调控制,及查询条件
		if(allparam == 0){		//allparam==0: 查询操作人所在的区域
			PmBscAreaInfo area = areainfoService.find(Long.parseLong(getUser().getAreaId()));
			if(areainfoId==null){
				dialogAreaDisplay = area.getVcDepartmentCode();
				dept.setPmBscAreaInfo(area);
			}else{
				PmBscAreaInfo display =  areainfoService.find(Long.parseLong(areainfoId));
				dept.setPmBscAreaInfo(display);
				dialogAreaDisplay = display.getVcDepartmentCode();
			}
			dialogArea = area.getVcDepartmentCode();
		}else{					//allparam==1: 查询所有的区域，供人员调动使用，控制在浙江省
			if(areainfoId==null){
				dialogAreaDisplay = "33000021";
				PmBscAreaInfo display =  areainfoService.findUniqueBy("vcDepartmentCode", "33000021");
				dept.setPmBscAreaInfo(display);
			}else{
				PmBscAreaInfo display =  areainfoService.find(Long.parseLong(areainfoId));
				dept.setPmBscAreaInfo(display);
				dialogAreaDisplay = display.getVcDepartmentCode();
			}
			// TODO 这个写死比较挫
			dialogArea = "33000021";
		}
		
		dept.setVcDeleteFlag( Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL) );
		Page<PmBscDeptInfo> pg = new Page<PmBscDeptInfo>();
		pg.setCurrentNum(getPageNum());
		pg.setPerPageRecordCount(getPageSize());
		pg.setOrderBy("area.vcDepartmentCode");
		pg.setOrder(Page.ASC);
		page = PmBscDeptInfoService.findPage(pg, dept);
		setPage(page);
		return DIALOG;
	}
	
	@SuppressWarnings("unchecked")
	@Action(EXPORT)
	public String export(){
		HttpServletRequest request = ServletActionContext.getRequest();  
        HttpServletResponse response = ServletActionContext.getResponse(); 
		String titleName ="人员信息列表";
		String user = "制表人:;"+getUser().getRealName()+";2;1";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String workDate = df.format(new Date());
		String work_date = "制表日期:;"+workDate+";5;1";
		String sheetName = "数据列表";
		String[] conTitle = new String[] {titleName,sheetName, "<br/>", user,"#",work_date};
		
		if(search == null) {
			search = new PmBscStaffInfo();
			search.setVcOphone(getUser().getAreaId());
			dialogAreaDisplay = getUser().getAreaCode();
		}
		search.setVcDeleteFlag( Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL) );
		Page<PmBscStaffInfo> pg = new Page<PmBscStaffInfo>();
		pg.setCurrentNum(getPageNum());
		pg.setPerPageRecordCount(getPageSize());
		pg.setOrderBy("pid");
		pg.setOrder(Page.DESC);
		pg.setWhetherpage(false);
		page = staffService.findPage(pg, search);
		List list = page.getResultSet();

		String[] xhead = ExportUtils.createHeader(PmBscStaffInfo.class);
		Integer[] xheadwidth =  ExportUtils.createHeaderWidth(PmBscStaffInfo.class);

		List newlist = ExportUtils.getexportlist(list);
		int beginRow = 3;
		String [] cells = new String[]{"0;1;2;1","3;1;8;1"};
		ExcelUtil.exportJxlExcel(request, response, newlist, xhead, xheadwidth, titleName, conTitle,beginRow,cells);
		return null;
	}
	
	private Map<Long, List<PmBscDict>> getMarketControl() {
		List<PmBscDict> list = PmBscDictService.findByValuesOrder("vcPosition", true, new String[]{"vcDeleteFlag"}, new Object[]{BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL});
		Map<Long, List<PmBscDict>> map = new HashMap<Long, List<PmBscDict>>();
		for(PmBscDict mc : list) {
			if(map.containsKey(mc.getVcCode())){
				map.get(mc.getVcCode()).add(mc);
			}else{
				List<PmBscDict> lst = new ArrayList<PmBscDict>();
				lst.add(mc);
				map.put(mc.getVcCode(), lst);
			}
		}
		return map;
	}
	
	public void loadAllList(String type){
		Map<Long, List<PmBscDict>> map = getMarketControl();
		dictPoliticals = map.get(BizConstant.DICT_DATA_TYPE_6);
		dictCulturals = map.get(BizConstant.DICT_DATA_TYPE_5);
		dictFormations = map.get(BizConstant.DICT_DATA_TYPE_2);
		dictRanks = map.get(BizConstant.DICT_DATA_TYPE_3);
		dictprops = map.get(BizConstant.DICT_DATA_TYPE_7);
		dictworks = map.get(BizConstant.DICT_DATA_TYPE_4);
		dictjobs = map.get(BizConstant.DICT_DATA_TYPE_8);
		if("search".equals(type)){
			pmBscObserTypeList = pmBscObserTypeService.findAll("pid", true, "vcType", BizConstant.DICT_OBSER_TYPE_2);
		}
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public List<PmBscDict> getDictCulturals() {
		return dictCulturals;
	}

	public void setDictCulturals(List<PmBscDict> dictCulturals) {
		this.dictCulturals = dictCulturals;
	}

	public List<PmBscDict> getDictPoliticals() {
		return dictPoliticals;
	}

	public List<PmBscDict> getDictFormations() {
		return dictFormations;
	}

	public List<PmBscDict> getDictRanks() {
		return dictRanks;
	}

	public List<PmBscDict> getDictprops() {
		return dictprops;
	}

	public List<PmBscDict> getDictworks() {
		return dictworks;
	}

	public PmBscStaffInfo getStaff() {
		return staff;
	}

	public void setStaff(PmBscStaffInfo staff) {
		this.staff = staff;
	}

	public PmBscStaffInfo getSearch() {
		return search;
	}

	public void setSearch(PmBscStaffInfo search) {
		this.search = search;
	}

	public List<PmBscDict> getNewdictprops() {
		return newdictprops;
	}

	public List<PmBscDict> getNewdictworks() {
		return newdictworks;
	}

	public String getStaffdepts() {
		return staffdepts;
	}

	public void setStaffdepts(String staffdepts) {
		this.staffdepts = staffdepts;
	}

	public List<PmBscStaffDept> getDeptList() {
		return deptList;
	}

	public List<PmMngChangeInfo> getChangeList() {
		return changeList;
	}

	public List<PmBscObservingStaff> getObserStaffList() {
		return obserStaffList;
	}

	public List<PmBscObserType> getPmBscObserTypeList() {
		return pmBscObserTypeList;
	}

	public List<PmBscDict> getDictjobs() {
		return dictjobs;
	}

	public List<PmBscRankChange> getRankChangelist() {
		return rankChangelist;
	}

	public List<PmBscRankChange> getJobChangelist() {
		return jobChangelist;
	}

	public List<PmBscRankChange> getFormationChangelist() {
		return formationChangelist;
	}

	public List<PmBscJobChange> getWorkChangelist() {
		return workChangelist;
	}
	

	public String getDialogArea() {
		return dialogArea;
	}

	public void setDialogArea(String dialogArea) {
		this.dialogArea = dialogArea;
	}

	public String getAreainfoId() {
		return areainfoId;
	}

	public void setAreainfoId(String areainfoId) {
		this.areainfoId = areainfoId;
	}

	public String getDialogAreaDisplay() {
		return dialogAreaDisplay;
	}

	public void setDialogAreaDisplay(String dialogAreaDisplay) {
		this.dialogAreaDisplay = dialogAreaDisplay;
	}
	public int getAllparam() {
		return allparam;
	}

	public void setAllparam(int allparam) {
		this.allparam = allparam;
	}
}
