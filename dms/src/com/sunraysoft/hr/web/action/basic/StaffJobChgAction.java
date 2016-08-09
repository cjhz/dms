package com.sunraysoft.hr.web.action.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sunraysoft.hr.constant.BizConstant;
import com.sunraysoft.hr.domain.entity.basic.PmBscDict;
import com.sunraysoft.hr.domain.entity.basic.PmBscJobChange;
import com.sunraysoft.hr.domain.entity.basic.PmBscObserType;
import com.sunraysoft.hr.domain.entity.basic.PmBscStaffInfo;
import com.sunraysoft.hr.service.StaffJobChgService;
import com.sunraysoft.hr.service.StaffService;
import com.sunraysoft.hr.service.baseinfo.bscdict.PmBscDictService;
import com.sunraysoft.hr.service.baseinfo.bscobsertype.PmBscObserTypeService;
import com.sunraysoft.hr.web.action.base.BaseAction;
import com.sunraysoft.hr.web.data.dto.Page;

@Controller 
@Scope("prototype")
@Namespace("/basic/staffJobChg")
@Results({
	@Result(name="redirectPageQuery", type="redirect", location="pageQuery.action?success=${success}&msg=${toUtf8Encode(msg)}&search.vcRealName=${toUtf8Encode2(search.vcRealName)}&pageNum=${pageNum}"),
	@Result(name="redirectJobChgQuery", type="redirect", location="pageQueryJobChg.action?success=${success}&msg=${toUtf8Encode(msg)}&staff.pid=${staff.pid}&pageNum=${pageNum}&dialogAreaDisplay=${dialogAreaDisplay}&staffsearch.vcOphone=${staffsearch.vcOphone}")
})
public class StaffJobChgAction extends BaseAction  {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(StaffRankChgAction.class);
	
	Long chgid;
	private PmBscJobChange search;
	private PmBscJobChange staffJob;
	private PmBscStaffInfo staffsearch;
	private PmBscStaffInfo staff;
	
	@Resource private StaffJobChgService staffJobChgService;
	@Resource private StaffService staffService;
	@Resource private PmBscObserTypeService pmBscObserTypeService;
	@Resource private PmBscDictService PmBscDictService;
	
	List<PmBscDict> dictPoliticals;//政治面貌
	List<PmBscDict> dictCulturals;//文化程度
	List<PmBscDict> dictFormations;//编制
	List<PmBscDict> dictRanks;//职级
	List<PmBscDict> dictprops;//人员属性
	List<PmBscDict> dictworks;//工作分工
	List<PmBscDict> dictjobs;//职务
	
	List<PmBscDict> newdictprops;
	
	List<PmBscObserType> pmBscObserTypeList;//人员立功表彰情况
	String dialogAreaDisplay;
	boolean isedit=false;
	
	/**
	 * 人员查询
	 * @return
	 */
	@Action(PAGE_QUERY)
	//@OperCode(code=OpcodeConstant.OPCODE_060301)
	public String pageQuery() {
		loadAllList("search");
		if(staffsearch == null) {
			staffsearch = new PmBscStaffInfo();
		}
		staffsearch.setVcDeleteFlag( Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL) );
		Page<PmBscStaffInfo> pg = new Page<PmBscStaffInfo>();
		pg.setCurrentNum(getPageNum());
		pg.setPerPageRecordCount(getPageSize());
		pg.setOrderBy("pid");
		pg.setOrder(Page.DESC);
		page = staffService.findPage(pg, staffsearch);
		setPage(page);
		return LIST;
	}

	/**
	 * 查看某个人员职级变动列表
	 * @return
	 */
	@Action("pageQueryJobChg")
	public String pageQueryJobChg(){
		if(search == null) {
			search = new PmBscJobChange();
		}
		
		search.setPmBscStaffInfo(staff);
		search.setVcDeleteFlag( Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL) );
		Page<PmBscJobChange> pg = new Page<PmBscJobChange>();
		pg.setCurrentNum(getPageNum());
		pg.setPerPageRecordCount(getPageSize());
		pg.setOrderBy("pid");
		pg.setOrder(Page.DESC);
		page = staffJobChgService.findPage(pg, search);
		setPage(page);
		return "listbystaff";
	}
	
	@Action(ADD)
	public String add(){
		return loadaddOrEdit(ADD);
	}
	
	@Action(EDIT)
	public String edit(){
		staffJob = staffJobChgService.find(chgid);
		isedit=true;
		return loadaddOrEdit(ADD);
	}
	
	public String loadaddOrEdit(String redirect){
		if(staff != null&&staff.getPid()!=null) {
			staff = staffService.find(staff.getPid());
			if(staff == null) {
				setFailMsg( "人员信息不存在" );
				return REDIRECT_PAGE_QUERY;
			}
		}
		dictprops = getDictProps();
		String PersonProp = staff.getVcPersonProp()==null?"":staff.getVcPersonProp();
		String[] props = PersonProp.split(";");
		newdictprops = getList(dictprops,props);
		dictworks = getWorklist();
		return redirect;
	}
	
	@Action(CREATE)
	public String create() throws Exception {
		staffJob.setPmBscStaffInfo(staff);
		if(staffJob.getPid()==null||staffJob.getPid()==0L){
			staffJobChgService.createJobChange(staffJob);
			setSuccessMsg( "新增成功！" );
		}else{
			staffJobChgService.updateJobChange(staffJob);
			setSuccessMsg( "修改成功！" );
		}
		return "redirectJobChgQuery";
	}
	
	@Action(DESTORY)
	public String destory(){
		staffJob = staffJobChgService.find(chgid);
		staffJobChgService.remove(staffJob);
		setSuccessMsg( "删除成功！" );
		return "redirectJobChgQuery";
	}
	
	@Action(SHOW)
	public String show(){
		staffJob = staffJobChgService.find(chgid);
		return loadaddOrEdit(SHOW);
	}
	
	/**
	 * 工作分工
	 * @return
	 */
	private List<PmBscDict> getWorklist() {
		List<PmBscDict> list = PmBscDictService.findByValues(new String[]{"vcDeleteFlag","vcCode"},new Object[]{BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL,BizConstant.DICT_DATA_TYPE_4} );
		return list;
	}
	
	private List<PmBscDict> getDictProps() {
		List<PmBscDict> list = PmBscDictService.findByValues(new String[]{"vcDeleteFlag","vcCode"},new Object[]{BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL,BizConstant.DICT_DATA_TYPE_7} );
		return list;
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
			pmBscObserTypeList = pmBscObserTypeService.findAll("pid", true, "vcType", BizConstant.DICT_OBSER_TYPE_2);
		}
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
	public Long getChgid() {
		return chgid;
	}

	public void setChgid(Long chgid) {
		this.chgid = chgid;
	}

//	public Long getStaffId() {
//		return staffId;
//	}
//
//	public void setStaffId(Long staffId) {
//		this.staffId = staffId;
//	}

	

	public PmBscStaffInfo getStaffsearch() {
		return staffsearch;
	}

	public void setStaffsearch(PmBscStaffInfo staffsearch) {
		this.staffsearch = staffsearch;
	}

	public List<PmBscDict> getDictPoliticals() {
		return dictPoliticals;
	}

	public List<PmBscDict> getDictCulturals() {
		return dictCulturals;
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

	public List<PmBscObserType> getPmBscObserTypeList() {
		return pmBscObserTypeList;
	}

	public List<PmBscDict> getNewdictprops() {
		return newdictprops;
	}

	public boolean isIsedit() {
		return isedit;
	}

	public void setIsedit(boolean isedit) {
		this.isedit = isedit;
	}

	public PmBscJobChange getSearch() {
		return search;
	}

	public void setSearch(PmBscJobChange search) {
		this.search = search;
	}

	public PmBscJobChange getStaffJob() {
		return staffJob;
	}

	public void setStaffJob(PmBscJobChange staffJob) {
		this.staffJob = staffJob;
	}

	public List<PmBscDict> getDictjobs() {
		return dictjobs;
	}

	public PmBscStaffInfo getStaff() {
		return staff;
	}

	public void setStaff(PmBscStaffInfo staff) {
		this.staff = staff;
	}

	public String getDialogAreaDisplay() {
		return dialogAreaDisplay;
	}

	public void setDialogAreaDisplay(String dialogAreaDisplay) {
		this.dialogAreaDisplay = dialogAreaDisplay;
	}
	
}
