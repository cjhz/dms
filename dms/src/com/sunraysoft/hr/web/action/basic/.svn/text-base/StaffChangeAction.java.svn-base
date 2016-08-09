package com.sunraysoft.hr.web.action.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.sunraysoft.hr.domain.entity.basic.PmBscStaffDept;
import com.sunraysoft.hr.domain.entity.basic.PmBscStaffInfo;
import com.sunraysoft.hr.domain.entity.manage.PmMngChangeInfo;
import com.sunraysoft.hr.service.StaffChangeService;
import com.sunraysoft.hr.service.StaffDeptService;
import com.sunraysoft.hr.service.StaffService;
import com.sunraysoft.hr.service.baseinfo.bscdict.PmBscDictService;
import com.sunraysoft.hr.service.department.deptinfo.PmBscDeptInfoService;
import com.sunraysoft.hr.util.StringUtils;
import com.sunraysoft.hr.web.action.base.BaseAction;
import com.sunraysoft.hr.web.data.dto.JsonWrapper;
import com.sunraysoft.hr.web.data.dto.Page;

/**
 * @msg 人员调动管理
 * @author chenjh
 * @version 2012-08-10
 */

@Controller 
@Scope("prototype")
@Namespace("/basic/staffChange")
@Results({
	@Result(name="redirectPageQuery", type="redirect", location="pageQuery.action?success=${success}&msg=${toUtf8Encode(msg)}&search.vcRealName=${toUtf8Encode2(search.vcRealName)}&pageNum=${pageNum}&dialogAreaDisplay=${dialogAreaDisplay}&search.vcOphone=${search.vcOphone}")
})
public class StaffChangeAction extends BaseAction   {

	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory.getLog(StaffAction.class);
	
	private Long staffId;
	private PmBscStaffInfo search;
	private PmBscStaffInfo staff;
	private PmMngChangeInfo staffchange;
	
	@Resource private StaffService staffService;
	@Resource private PmBscDictService PmBscDictService;
	@Resource private StaffChangeService staffChangeService;
	@Resource private StaffDeptService staffDeptService;
	@Resource private PmBscDeptInfoService PmBscDeptInfoService;
	
	List<PmBscDict> dictprops;//人员属性
	List<PmBscDict> dictworks;//工作分工
	
	List<PmBscDict> newdictprops;
	List<PmBscDict> newdictworks;//
	List<PmBscStaffDept> deptList;
	List<PmMngChangeInfo> changeList;//人员调动
	String dialogAreaDisplay;
	/**
	 * 人员调动进入列表
	 * @return
	 */
	@Action(PAGE_QUERY)
	@OperCode(code=OpcodeConstant.OPCODE_12701)
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
		List<PmBscDict> propslist = getDictProps();
		page.setResultSet(getNewStaffLIst(page.getResultSet(),propslist));
		setPage(page);
		return LIST;
	}
	/**
	 * 人员调动查看
	 * @return
	 */
	@Action("chgstaff_page_query")
	@OperCode(code=OpcodeConstant.OPCODE_12801)
	public String chgstaff_page_query() {
		if(search == null) {
			search = new PmBscStaffInfo();
			//search.setVcOphone(getUser().getAreaId());
		}
		
		search.setVcDeleteFlag( Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL) );
		search.setVcExtend2("filter");//用于过滤
		Page<PmBscStaffInfo> pg = new Page<PmBscStaffInfo>();
		pg.setCurrentNum(getPageNum());
		pg.setPerPageRecordCount(getPageSize());
		pg.setOrderBy("pid");
		pg.setOrder(Page.DESC);
		page = staffService.findPage(pg, search);
		List<PmBscDict> propslist = getDictProps();
		page.setResultSet(getNewStaffLIst(page.getResultSet(),propslist));
		setPage(page);
		return "viewlist";
	}
	
	@Action(ADD)
	//@OperCode(code=OpcodeConstant.OPCODE_01010301)
	public String add() {
		if(staffId != null) {
			staff = staffService.find(staffId);
			if(staff == null) {
				setFailMsg( "待查看记录不存在" );
				return REDIRECT_PAGE_QUERY;
			}
		}
		dictprops = getDictProps();
		String PersonProp = staff.getVcPersonProp()==null?"":staff.getVcPersonProp();
		String[] props = PersonProp.split(";");
		newdictprops = getList(dictprops,props);
		return ADD;
	}
	
	@Action(CREATE)
	//@OperCode(code=OpcodeConstant.OPCODE_060302)
	public String create() throws Exception {
		//修改人员属性
		staffId=staffchange.getPmBscStaffInfo().getPid();
		PmBscStaffInfo stf = staffService.find(Long.valueOf(staffId));
		boolean boolprop = StringUtils.comparechange(stf.getVcPersonProp(), staffchange.getVcEpersonProp());
		//修改人员机构
		List<PmBscStaffDept> staffdepts = staffDeptService.findBy("pmBscStaffInfo.pid", staffId);
		String stfdepts = "";
		for(PmBscStaffDept stfdept:staffdepts){
			stfdepts+=stfdept.getPmBscStaffInfo()==null?"":stfdept.getPmBscDeptInfo().getPid()+";";
		}
		String sdepts = staffchange.getVcChgDept();
		boolean booldepts = StringUtils.comparechange(stfdepts,sdepts);
		
		//添加人员调动
		if(boolprop&&booldepts){
			setSuccessMsg( "调动前后相同，没有任何调动！" );
			
		}else{
			if(!boolprop){
				stf.setVcPersonProp(staffchange.getVcEpersonProp());
				staffService.saveOrUpdate(stf);
			}else{//人员属性没有调动
				staffchange.setVcBpersonProp("");
				staffchange.setVcBpersonPropName("");
				staffchange.setVcEpersonProp("");
				staffchange.setVcEpersonPropName("");
			}
			
			if(!booldepts){
				staffDeptService.removeAll(staffdepts);
				if(sdepts!=null){
					String[] depts = sdepts.split(";");
					for(int i=0;i<depts.length;i++){
						if(depts[i]!=null&&!depts[i].equals("")){
							PmBscStaffDept staffdept= new PmBscStaffDept();
							PmBscDeptInfo dept = PmBscDeptInfoService.find(Long.valueOf(depts[i]));
							staffdept.setPmBscDeptInfo(dept);
							staffdept.setPmBscStaffInfo(stf);
							staffdept.setVcDepartmentCode(dept.getPmBscAreaInfo().getVcDepartmentCode());
							staffdept.setVcDeleteFlag( Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL) );
							staffDeptService.saveOrUpdate(staffdept);
						}
					}
				}
			}else{//人员机构没有调动
				staffchange.setVcNowDept("");
				staffchange.setVcNowDeptName("");
				staffchange.setVcChgDept("");
				staffchange.setVcChgDeptName("");
			}
		
			staffchange.setVcDeleteFlag( Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL) );
			staffChangeService.saveOrUpdate(staffchange);
			setSuccessMsg( "新建成功" );
		}
		return REDIRECT_PAGE_QUERY;
		//logCreate(role);
		
	}
	
	@SuppressWarnings("unchecked")
	@Action("ajaxCompareChangeUnique")
	public String ajaxCompareChangeUnique() {
		boolean result ;
		//修改人员属性
		staffId=staffchange.getPmBscStaffInfo().getPid();
		PmBscStaffInfo stf = staffService.find(Long.valueOf(staffId));
		boolean boolprop = StringUtils.comparechange(stf.getVcPersonProp(), staffchange.getVcEpersonProp());
		//修改人员机构
		List<PmBscStaffDept> staffdepts = staffDeptService.findBy("pmBscStaffInfo.pid", staffId);
		String stfdepts = "";
		for(PmBscStaffDept stfdept:staffdepts){
			stfdepts+=stfdept.getPmBscStaffInfo()==null?"":stfdept.getPmBscDeptInfo().getPid()+";";
		}
		String sdepts = staffchange.getVcChgDept();
		boolean booldepts = StringUtils.comparechange(stfdepts,sdepts);
		
		JsonWrapper wrapper = new JsonWrapper();
		//添加人员调动
		if(boolprop && booldepts){
			result = false;
			wrapper.setMsg("人员属性和所属机构调动前后相同，请重新选择！");
		}else{result=true;wrapper.setMsg("");}
		wrapper.setFlag(result);
		JSONObject jsonObj = JSONObject.fromObject(wrapper);
		setJsonText( jsonObj.toString() );
		return JSON_PAGE;
	}
	
	@Action(SHOW)
	//@OperCode(code=OpcodeConstant.OPCODE_01010301)
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
		changeList = staffChangeService.findBy("pmBscStaffInfo.pid", staffId);
		return SHOW;
	}
	
	@Action("updateStaffDelFlag")
	//@OperCode(code=OpcodeConstant.OPCODE_060303)
	public String updateStaffDelFlag() {
		String msg = staff.getVcDeleteFlag()==2?"调离操作成功":"离退休操作成功";
		PmBscStaffInfo dbstaff = staffService.find(staff.getPid());
		dbstaff.setVcDeleteFlag(staff.getVcDeleteFlag());
		dbstaff.setVcTransferNode(staff.getVcTransferNode());
		staffService.saveOrUpdate(dbstaff);
		setSuccessMsg(msg);
		return REDIRECT_PAGE_QUERY;
	}
	
	private Map<Long, List<PmBscDict>> getMarketControl() {
		List<PmBscDict> list = PmBscDictService.findBy("vcDeleteFlag", BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL);
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
	/**
	 * 设置人员属性的名字到VcExtend1
	 * @param list
	 * @param dictlist
	 * @return
	 */
	public List<PmBscStaffInfo> getNewStaffLIst(List<PmBscStaffInfo> list,List<PmBscDict> dictlist){
		List<PmBscStaffInfo> newList = new ArrayList<PmBscStaffInfo>();
		String dictname="";
		for(PmBscStaffInfo staff:list){
			if(staff!=null){
				dictname = getPropNames(dictlist,(staff.getVcPersonProp()==null?"":staff.getVcPersonProp()).split(";"));
				staff.setVcExtend1(dictname);
				newList.add(staff);
			}
		}
		return newList;
	}
	
	
	/**
	 * 获得字典表人员属性数据
	 * @return
	 */
	private List<PmBscDict> getDictProps() {
		List<PmBscDict> list = PmBscDictService.findByValues(new String[]{"vcDeleteFlag","vcCode"},new Object[]{BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL,BizConstant.DICT_DATA_TYPE_7} );
		return list;
	}
	
	/**
	 * 获得newList
	 * @param list
	 * @param arr
	 * @return
	 */
	public String getPropNames(List<PmBscDict> list,String[] arr){
		String dictname = "";
		if(list!=null&&list.size()>0&&arr.length>0){
			for(int i=0;i<arr.length;i++){
				for(PmBscDict dict:list){
					if(dict!=null){
						if(arr[i].equals(dict.getPid()+"")){
							dictname+=dict.getVcName()+",";
						}
					}
				}
			}
		}
		return dictname.equals("")?"":dictname.substring(0, dictname.length()-1);
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
	
	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public PmBscStaffInfo getSearch() {
		return search;
	}

	public void setSearch(PmBscStaffInfo search) {
		this.search = search;
	}

	public PmBscStaffInfo getStaff() {
		return staff;
	}

	public void setStaff(PmBscStaffInfo staff) {
		this.staff = staff;
	}

	public List<PmBscDict> getDictprops() {
		return dictprops;
	}

	public List<PmBscDict> getNewdictprops() {
		return newdictprops;
	}

	public PmMngChangeInfo getStaffchange() {
		return staffchange;
	}

	public void setStaffchange(PmMngChangeInfo staffchange) {
		this.staffchange = staffchange;
	}
	public List<PmBscDict> getDictworks() {
		return dictworks;
	}
	public List<PmBscDict> getNewdictworks() {
		return newdictworks;
	}
	public List<PmBscStaffDept> getDeptList() {
		return deptList;
	}
	public List<PmMngChangeInfo> getChangeList() {
		return changeList;
	}
	public String getDialogAreaDisplay() {
		return dialogAreaDisplay;
	}

	public void setDialogAreaDisplay(String dialogAreaDisplay) {
		this.dialogAreaDisplay = dialogAreaDisplay;
	}
}
