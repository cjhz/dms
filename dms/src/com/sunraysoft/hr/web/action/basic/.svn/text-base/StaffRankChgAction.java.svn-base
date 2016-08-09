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

import com.sunraysoft.hr.annotation.OperCode;
import com.sunraysoft.hr.constant.BizConstant;
import com.sunraysoft.hr.constant.OpcodeConstant;
import com.sunraysoft.hr.domain.entity.basic.PmBscDict;
import com.sunraysoft.hr.domain.entity.basic.PmBscObserType;
import com.sunraysoft.hr.domain.entity.basic.PmBscRankChange;
import com.sunraysoft.hr.domain.entity.basic.PmBscStaffInfo;
import com.sunraysoft.hr.service.StaffRankChgService;
import com.sunraysoft.hr.service.StaffService;
import com.sunraysoft.hr.service.baseinfo.bscdict.PmBscDictService;
import com.sunraysoft.hr.service.baseinfo.bscobsertype.PmBscObserTypeService;
import com.sunraysoft.hr.web.action.base.BaseAction;
import com.sunraysoft.hr.web.data.dto.Page;

@Controller 
@Scope("prototype")
@Namespace("/basic/staffRankChg")
@Results({
	@Result(name="redirectPageQuery", type="redirect", location="pageQuery.action?success=${success}&msg=${toUtf8Encode(msg)}&search.vcRealName=${toUtf8Encode2(search.vcRealName)}&pageNum=${pageNum}&dialogAreaDisplay=${dialogAreaDisplay}&staffsearch.vcOphone=${staffsearch.vcOphone}"),
	@Result(name="redirectRankChgQuery", type="redirect", location="pageQueryRankChg.action?success=${success}&msg=${toUtf8Encode(msg)}&search.vcType=${search.vcType}&staff.pid=${staff.pid}&pageNum=${pageNum}&dialogAreaDisplay=${dialogAreaDisplay}&staffsearch.vcOphone=${staffsearch.vcOphone}")
})
public class StaffRankChgAction extends BaseAction  {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(StaffRankChgAction.class);
	
	Long chgid;
	//Long staffId;
	private PmBscRankChange search;
	private PmBscRankChange staffRank;
	private PmBscStaffInfo staffsearch;
	private PmBscStaffInfo staff;
	
	@Resource private StaffRankChgService staffRankChgService;
	@Resource private StaffService staffService;
	@Resource private PmBscObserTypeService pmBscObserTypeService;
	@Resource private PmBscDictService PmBscDictService;
	
	List<PmBscDict> dictPoliticals;//政治面貌
	List<PmBscDict> dictCulturals;//文化程度
	List<PmBscDict> dictFormations;//编制
	List<PmBscDict> dictRanks;//职级
	List<PmBscDict> dictprops;//人员属性
	List<PmBscDict> dictworks;//工作分工
	
	List<PmBscDict> newdictprops;
	
	List<PmBscObserType> pmBscObserTypeList;//人员立功表彰情况
	
	boolean isedit=false;
	String typeName;
	
	String dialogAreaDisplay;
	
	/**
	 * 人员查询
	 * @return
	 */
	@Action(PAGE_QUERY)
	@OperCode(code=OpcodeConstant.OPCODE_12101)
	public String pageQuery() {
		loadAllList("search");
		if(staffsearch == null) {
			staffsearch = new PmBscStaffInfo();
			staffsearch.setVcOphone(getUser().getAreaId());
			dialogAreaDisplay = getUser().getAreaCode();
		}
		if(staffsearch.getIsShowAll()==null){staffsearch.setIsShowAll(0L);}
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
	@Action("pageQueryRankChg")
	public String pageQueryRankChg(){
		if(search == null) {
			search = new PmBscRankChange();
		}
		
		search.setPmBscStaffInfo(staff);
		search.setVcDeleteFlag( Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL) );
		Page<PmBscRankChange> pg = new Page<PmBscRankChange>();
		pg.setCurrentNum(getPageNum());
		pg.setPerPageRecordCount(getPageSize());
		pg.setOrderBy("pid");
		pg.setOrder(Page.DESC);
		page = staffRankChgService.findPage(pg, search);
		setPage(page);
		typeName = getTName();
		return "listbystaff";
	}
	
	/**
	 * 获得职级、职务、编制名字
	 * @return
	 */
	private String getTName(){
		String name="xx";
		if(search.getVcType()!=null){
			if(search.getVcType()==BizConstant.STAFF_ATTR_TYPE_KEY_1){
				name = BizConstant.STAFF_ATTR_TYPE_VAL_1;
			}else if(search.getVcType()==BizConstant.STAFF_ATTR_TYPE_KEY_2){
				name = BizConstant.STAFF_ATTR_TYPE_VAL_2;
			}else if(search.getVcType()==BizConstant.STAFF_ATTR_TYPE_KEY_3){
				name = BizConstant.STAFF_ATTR_TYPE_VAL_3;
			}
		}
		return name;
	}
	
	@Action(ADD)
	public String add(){
		return loadaddOrEdit(ADD);
	}
	
	@Action(EDIT)
	public String edit(){
		staffRank = staffRankChgService.find(chgid);
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
		dictRanks = getRanklist();
		typeName = getTName();
		return redirect;
	}
	
	@Action(CREATE)
	public String create() throws Exception {
		staffRank.setPmBscStaffInfo(staff);
		if(staffRank.getPid()==null||staffRank.getPid()==0L){
			staffRankChgService.createRankChange(staffRank);
			setSuccessMsg( "新增成功！" );
		}else{
			staffRankChgService.updateRankChange(staffRank);
			setSuccessMsg( "修改成功！" );
		}
		return "redirectRankChgQuery";
	}
	
	@Action(DESTORY)
	public String destory(){
		staffRank = staffRankChgService.find(chgid);
		staffRankChgService.remove(staffRank);
		setSuccessMsg( "删除成功！" );
		return "redirectRankChgQuery";
	}
	
	@Action(SHOW)
	public String show(){
		staffRank = staffRankChgService.find(chgid);
		return loadaddOrEdit(SHOW);
	}
	
	/**
	 * 获得字典表人员属性数据
	 * @return
	 */
	private List<PmBscDict> getRanklist() {
		long type=3;
		if(search.getVcType()!=null){
			if(search.getVcType()==BizConstant.STAFF_ATTR_TYPE_KEY_1){
				type = BizConstant.DICT_DATA_TYPE_3;
			}else if(search.getVcType()==BizConstant.STAFF_ATTR_TYPE_KEY_2){
				type = BizConstant.DICT_DATA_TYPE_8;
			}else if(search.getVcType()==BizConstant.STAFF_ATTR_TYPE_KEY_3){
				type = BizConstant.DICT_DATA_TYPE_2;
			}
		}
		List<PmBscDict> list = PmBscDictService.findByValues(new String[]{"vcDeleteFlag","vcCode"},new Object[]{BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL,type} );
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

	public PmBscRankChange getSearch() {
		return search;
	}

	public void setSearch(PmBscRankChange search) {
		this.search = search;
	}

	public PmBscRankChange getStaffRank() {
		return staffRank;
	}

	public void setStaffRank(PmBscRankChange staffRank) {
		this.staffRank = staffRank;
	}

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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDialogAreaDisplay() {
		return dialogAreaDisplay;
	}

	public void setDialogAreaDisplay(String dialogAreaDisplay) {
		this.dialogAreaDisplay = dialogAreaDisplay;
	}

	public PmBscStaffInfo getStaff() {
		return staff;
	}

	public void setStaff(PmBscStaffInfo staff) {
		this.staff = staff;
	}
	
	
}
