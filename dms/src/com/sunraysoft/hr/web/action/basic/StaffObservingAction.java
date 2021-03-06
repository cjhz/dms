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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.sunraysoft.hr.domain.entity.basic.PmBscDeptInfo;
import com.sunraysoft.hr.domain.entity.basic.PmBscDict;
import com.sunraysoft.hr.domain.entity.basic.PmBscObserType;
import com.sunraysoft.hr.domain.entity.basic.PmBscObservingStaff;
import com.sunraysoft.hr.domain.entity.basic.PmBscStaffDept;
import com.sunraysoft.hr.domain.entity.basic.PmBscStaffInfo;
import com.sunraysoft.hr.service.StaffDeptService;
import com.sunraysoft.hr.service.StaffService;
import com.sunraysoft.hr.service.baseinfo.bscdict.PmBscDictService;
import com.sunraysoft.hr.service.baseinfo.bscobsertype.PmBscObserTypeService;
import com.sunraysoft.hr.service.department.deptobserving.PmBscObservingStaffService;
import com.sunraysoft.hr.util.BeanUtil;
import com.sunraysoft.hr.util.ExcelUtil;
import com.sunraysoft.hr.util.ExportUtils;
import com.sunraysoft.hr.web.action.base.BaseAction;
import com.sunraysoft.hr.web.data.dto.Page;


@Controller 
@Scope("prototype")
@Namespace("/basic/staffobserving")
@Results({
	@Result(name="redirectPageQuery", type="redirect", location="pageQuery.action?success=${success}&msg=${toUtf8Encode(msg)}&search.vcRealName=${toUtf8Encode2(search.vcRealName)}&pageNum=${pageNum}&dialogAreaDisplay=${dialogAreaDisplay}&search.vcOphone=${search.vcOphone}"),
	@Result(name="redirectPageEdit", type="redirect", location="pageQueryObserving.action?staffId=${staffId}&success=${success}&msg=${toUtf8Encode(msg)}&search.vcRealName=${toUtf8Encode2(search.vcRealName)}&pageNum=${pageNum}&dialogAreaDisplay=${dialogAreaDisplay}&search.vcOphone=${search.vcOphone}")
})
public class StaffObservingAction extends BaseAction  {
	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory.getLog(StaffObservingAction.class);
	
	private Long staffId;
	private Long pid;
	private PmBscStaffInfo search;
	private PmBscStaffInfo staff;
	private PmBscDeptInfo pmBscDeptInfo;
	private PmBscObservingStaff pmBscObservingStaff;
	private PmBscObserType pmBscObserType;
	private Long viewType;
	
	
	@Resource private StaffService staffService;
	//@Resource private StaffChangeService staffChangeService;
	@Resource private PmBscObservingStaffService pmBscObservingStaffService;
	@Resource private PmBscObserTypeService pmBscObserTypeService;
	@Resource private PmBscDictService pmBscDictService;
	@Resource private StaffDeptService staffDeptService;
	//@Resource private PmBscDeptInfoService pmBscDeptInfoService;
	
	
	List<PmBscDict> dictPoliticals;//政治面貌
	List<PmBscDict> dictCulturals;//文化程度
	List<PmBscDict> dictFormations;//编制
	List<PmBscDict> dictRanks;//职级
	List<PmBscDict> dictprops;//人员属性
	List<PmBscDict> dictworks;//工作分工
	List<PmBscObservingStaff> pmBscObservingStafflist;
	List<PmBscObserType> pmbscobsertypelist;
	
	List<PmBscDict> newdictprops;//用于查看
	List<PmBscDict> newdictworks;//
	
	private String staffdepts;
	List<PmBscStaffDept> deptList;
	
	String dialogAreaDisplay;

	
	@Action("pageQuery")
	@OperCode(code=OpcodeConstant.OPCODE_12501)
	public String pageQuery() {
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
	
	@Action("pageQueryObserving")
	public String pageQueryObserving(){
		pmBscObservingStafflist = pmBscObservingStaffService.findAll("vcObservTime", false, 
				"vcObservType", BizConstant.DICT_OBSER_TYPE_2,"pmBscStaffInfo","pid",staffId);
		return "list_observing";
	}
	
	@Action("pageQueryObservingDetail")
	@OperCode(code=OpcodeConstant.OPCODE_12601)
	public String pageQueryObservingDetail(){
		loadAllList("search");
		if(pmBscObservingStaff == null) {
			pmBscObservingStaff = new PmBscObservingStaff();
			if(search==null){
				search = new PmBscStaffInfo();
				search.setVcOphone(getUser().getAreaId());
				dialogAreaDisplay = getUser().getAreaCode();
			}
		}
		if(search.getIsShowAll()==null){search.setIsShowAll(0L);}
		pmBscObservingStaff.setVcDeleteFlag( Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL) );
		pmBscObservingStaff.setPmBscStaffInfo(search);
		
		pmBscObservingStaff.setVcDeleteFlag( Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL) );
		Page<PmBscObservingStaff> pg = new Page<PmBscObservingStaff>();
		pg.setCurrentNum(getPageNum());
		pg.setPerPageRecordCount(getPageSize());
		pg.setOrderBy("pid");
		pg.setOrder(Page.DESC);
		page = pmBscObservingStaffService.findPage(pg, pmBscObservingStaff);
		setPage(page);
		return "list_detail_observing";
	}
	
	@Action(ADD)
	//@OperCode(code=OpcodeConstant.OPCODE_060302)
	public String Add() {
		staffId = pmBscObservingStaff.getPmBscStaffInfo().getPid();
		staff = staffService.find(pmBscObservingStaff.getPmBscStaffInfo().getPid());
		pmBscObserType = pmBscObserTypeService.find(pmBscObservingStaff.getPmBscObserType().getPid());
		pmBscObservingStaff.setVcObservType(BizConstant.DICT_OBSER_TYPE_2);
		pmBscObservingStaff.setPmBscStaffInfo(staff);
		pmBscObservingStaff.setPmBscObserType(pmBscObserType);
		pmBscObservingStaffService.save(pmBscObservingStaff);
		setMsg("新增成功！");
		return "redirectPageEdit";
	}
	
	@Action(EDIT)
	//@OperCode(code=OpcodeConstant.OPCODE_060303)
	public String edit() {
		staffId = pmBscObservingStaff.getPmBscStaffInfo().getPid();
		staff = staffService.find(pmBscObservingStaff.getPmBscStaffInfo().getPid());
		pmBscObserType = pmBscObserTypeService.find(pmBscObservingStaff.getPmBscObserType().getPid());
		pmBscObservingStaff.setVcObservType(BizConstant.DICT_OBSER_TYPE_2);
		pmBscObservingStaff.setPmBscStaffInfo(staff);
		pmBscObservingStaff.setPmBscObserType(pmBscObserType);
		
		PmBscObservingStaff obserstaff = pmBscObservingStaffService.find(pmBscObservingStaff.getPid());
		if(obserstaff==null){
			setFailMsg("待修改的人员立功表彰不存在");
		}
		String[] properties = new String[] {"vcObservTime","pmBscObserType","vcRemark"};
		BeanUtil.copyProperties(pmBscObservingStaff, obserstaff, properties);
		pmBscObservingStaffService.saveOrUpdate(obserstaff);
		setMsg("修改成功！");
		return "redirectPageEdit";
	}
	
	@Action("toAdd")
	//@OperCode(code=OpcodeConstant.OPCODE_060302)
	public String toAdd() {
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
		newdictworks = getList(dictworks,works);
		deptList = staffDeptService.findBy("pmBscStaffInfo.pid", staffId);
		//loadAllList("1");
		pmbscobsertypelist = pmBscObserTypeService.findAll("pid",true,"vcType",BizConstant.DICT_OBSER_TYPE_2);
		return "add";
	}
	
	@Action("toEdit")
	public String toEdit() {
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
		newdictworks = getList(dictworks,works);
		deptList = staffDeptService.findBy("pmBscStaffInfo.pid", staffId);
		//loadAllList("1");
		pmBscObservingStaff = pmBscObservingStaffService.find(pid);
		pmbscobsertypelist = pmBscObserTypeService.findAll("pid",true,"vcType",BizConstant.DICT_OBSER_TYPE_2);
		return EDIT;
	}
	
	@Action(SHOW)
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
		newdictworks = getList(dictworks,works);
		deptList = staffDeptService.findBy("pmBscStaffInfo.pid", staffId);
		pmBscObservingStaff = pmBscObservingStaffService.find(pid);
		return SHOW;
	}
	
	@SuppressWarnings("unchecked")
	@Action(EXPORT)
	public String export(){
		HttpServletRequest request = ServletActionContext.getRequest();  
        HttpServletResponse response = ServletActionContext.getResponse(); 
		String titleName ="个人立功表彰信息列表";
		String user = "制表人:;"+getUser().getRealName()+";2;1";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String workDate = df.format(new Date());
		String work_date = "制表日期:;"+workDate+";5;1";
		String sheetName = "数据列表";
		String[] conTitle = new String[] {titleName,sheetName, "<br/>", user,"#",work_date};
		if(pmBscObservingStaff == null) {
			pmBscObservingStaff = new PmBscObservingStaff();
			if(search==null){
				search = new PmBscStaffInfo();
				search.setVcOphone(getUser().getAreaId());
				dialogAreaDisplay = getUser().getAreaCode();
			}
		}
		pmBscObservingStaff.setVcDeleteFlag( Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL) );
		pmBscObservingStaff.setPmBscStaffInfo(search);
		
		pmBscObservingStaff.setVcDeleteFlag( Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL) );
		Page<PmBscObservingStaff> pg = new Page<PmBscObservingStaff>();
		pg.setCurrentNum(getPageNum());
		pg.setPerPageRecordCount(getPageSize());
		pg.setOrderBy("vcObservTime");
		pg.setOrder(Page.DESC);
		pg.setWhetherpage(false);
		page = pmBscObservingStaffService.findPage(pg, pmBscObservingStaff);
		List list = page.getResultSet();
		
		String[] xhead = ExportUtils.createHeader(PmBscObservingStaff.class);
		Integer[] xheadwidth =  ExportUtils.createHeaderWidth(PmBscObservingStaff.class);

		List newlist = ExportUtils.getexportlist(list);
		int beginRow = 3;
		String [] cells = new String[]{"0;1;2;1","3;1;4;1"};
		ExcelUtil.exportJxlExcel(request, response, newlist, xhead, xheadwidth, titleName, conTitle,beginRow,cells);
		return null;
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
	
	@Action(DESTORY)
	public String destory(){
		pmBscObservingStaffService.remove(pid);
		setMsg("删除成功！");
		return "redirectPageEdit";
	}
	
	private Map<Long, List<PmBscDict>> getMarketControl() {
		List<PmBscDict> list = pmBscDictService.findBy("vcDeleteFlag", BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL);
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
		if("search".equals(type)){
			pmbscobsertypelist = pmBscObserTypeService.findAll("pid", true, "vcType", BizConstant.DICT_OBSER_TYPE_2);
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

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public void setPmBscDeptInfo(PmBscDeptInfo pmBscDeptInfo) {
		this.pmBscDeptInfo = pmBscDeptInfo;
	}

	public PmBscObservingStaff getPmBscObservingStaff() {
		return pmBscObservingStaff;
	}

	public void setPmBscObservingStaff(PmBscObservingStaff pmBscObservingStaff) {
		this.pmBscObservingStaff = pmBscObservingStaff;
	}

	public PmBscDeptInfo getPmBscDeptInfo() {
		return pmBscDeptInfo;
	}

	public List<PmBscObserType> getPmbscobsertypelist() {
		return pmbscobsertypelist;
	}

	public List<PmBscObservingStaff> getPmBscObservingStafflist() {
		return pmBscObservingStafflist;
	}

	public Long getViewType() {
		return viewType;
	}

	public void setViewType(Long viewType) {
		this.viewType = viewType;
	}

	public String getDialogAreaDisplay() {
		return dialogAreaDisplay;
	}

	public void setDialogAreaDisplay(String dialogAreaDisplay) {
		this.dialogAreaDisplay = dialogAreaDisplay;
	}

	
}
