package com.sunraysoft.hr.web.action.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sunraysoft.hr.annotation.OperCode;
import com.sunraysoft.hr.constant.BizConstant;
import com.sunraysoft.hr.constant.OpcodeConstant;
import com.sunraysoft.hr.domain.dto.report.StaffReportDto;
import com.sunraysoft.hr.domain.entity.basic.PmBscDict;
import com.sunraysoft.hr.domain.entity.basic.PmBscObserType;
import com.sunraysoft.hr.service.baseinfo.bscdict.PmBscDictService;
import com.sunraysoft.hr.service.baseinfo.bscobsertype.PmBscObserTypeService;
import com.sunraysoft.hr.service.report.StaffCollectService;
import com.sunraysoft.hr.web.action.base.BaseAction;
import com.sunraysoft.hr.web.action.basic.StaffAction;

/**
 * 用于对人员统计相关
 * @author chenjh
 *
 */
@Controller 
@Scope("prototype")
@Namespace("/report/staffFormation")
public class StaffCollectAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(StaffAction.class);
	
	StaffReportDto staffDto;
	
	@Resource private PmBscDictService PmBscDictService;
	@Resource private PmBscObserTypeService PmBscObserTypeService;
	@Resource private StaffCollectService staffCollectService;
	
	List<PmBscDict> dictRanks;//职级
	List<PmBscDict> dictFormations;//编制
	List<PmBscDict> dictCulturals;//文化程度
	List<PmBscObserType> obserTypes;//立功表彰类别
	
	String[][] reportArray;
	Map<String,Object> staffmap;
	
	/**
	 * 进入按人员编制统计
	 * @return
	 */
	@Action("searchbyformation")
	@OperCode(code=OpcodeConstant.OPCODE_14101)
	public String searchbyformation(){
//		Map<Long, List<PmBscDict>> map = getMarketControl();
//		dictRanks = map.get(BizConstant.DICT_DATA_TYPE_3);
//		dictCulturals = map.get(BizConstant.DICT_DATA_TYPE_5);
//		obserTypes = PmBscObserTypeService.findBy("vcType", BizConstant.DICT_OBSER_TYPE_2);
//		if(staffDto==null){
//			staffDto = new StaffReportDto();
//		}
		if(staffDto == null){
			staffDto = new StaffReportDto();
			staffDto.setAreaid(Long.parseLong(getUser().getAreaId()));
		}
		Map<Long, List<PmBscDict>> map = getMarketControl();
		dictRanks = map.get(BizConstant.DICT_DATA_TYPE_3);
		dictCulturals = map.get(BizConstant.DICT_DATA_TYPE_5);
		obserTypes = PmBscObserTypeService.findBy("vcType", BizConstant.DICT_OBSER_TYPE_2);
		reportArray = staffCollectService.getReportbyFormation(staffDto);
		return "listformation";
	}

	/**
	 * 查询人员编制统计
	 * @return
	 */
	@Action("reportbyformation")
	public String reportbyformation(){
		Map<Long, List<PmBscDict>> map = getMarketControl();
		dictRanks = map.get(BizConstant.DICT_DATA_TYPE_3);
		dictCulturals = map.get(BizConstant.DICT_DATA_TYPE_5);
		obserTypes = PmBscObserTypeService.findBy("vcType", BizConstant.DICT_OBSER_TYPE_2);
		reportArray = staffCollectService.getReportbyFormation(staffDto);
		return "listformation";
	}
	
	/**
	 * 进入按人员职级统计
	 * @return
	 */
	@Action("searchbyrank")
	@OperCode(code=OpcodeConstant.OPCODE_14501)
	public String searchbyrank(){
//		Map<Long, List<PmBscDict>> map = getMarketControl();
//		dictFormations = map.get(BizConstant.DICT_DATA_TYPE_2);
//		dictCulturals = map.get(BizConstant.DICT_DATA_TYPE_5);
//		obserTypes = PmBscObserTypeService.findBy("vcType", BizConstant.DICT_OBSER_TYPE_2);
//		if(staffDto==null){
//			staffDto = new StaffReportDto();
//		}
		if(staffDto == null){
			staffDto = new StaffReportDto();
			staffDto.setAreaid(Long.parseLong(getUser().getAreaId()));
		}
		Map<Long, List<PmBscDict>> map = getMarketControl();
		dictFormations = map.get(BizConstant.DICT_DATA_TYPE_2);
		dictCulturals = map.get(BizConstant.DICT_DATA_TYPE_5);
		obserTypes = PmBscObserTypeService.findBy("vcType", BizConstant.DICT_OBSER_TYPE_2);
		reportArray = staffCollectService.getReportbyRank(staffDto);
		return "listrank";
	}
	
	/**
	 * 查询人员职级统计
	 * @return
	 */
	@Action("reportbyrank")
	public String reportbyrank(){
		Map<Long, List<PmBscDict>> map = getMarketControl();
		dictFormations = map.get(BizConstant.DICT_DATA_TYPE_2);
		dictCulturals = map.get(BizConstant.DICT_DATA_TYPE_5);
		obserTypes = PmBscObserTypeService.findBy("vcType", BizConstant.DICT_OBSER_TYPE_2);
		reportArray = staffCollectService.getReportbyRank(staffDto);
		return "listrank";
	}
	
	/**
	 * 进入按人员立功表彰统计
	 * @return
	 */
	@Action("searchbyobser")
	@OperCode(code=OpcodeConstant.OPCODE_14901)
	public String searchbyObser(){
//		Map<Long, List<PmBscDict>> map = getMarketControl();
//		dictFormations = map.get(BizConstant.DICT_DATA_TYPE_2);
//		dictRanks = map.get(BizConstant.DICT_DATA_TYPE_3);
//		dictCulturals = map.get(BizConstant.DICT_DATA_TYPE_5);
//		if(staffDto==null){
//			staffDto = new StaffReportDto();
//		}
		if(staffDto == null){
			staffDto = new StaffReportDto();
			staffDto.setAreaid(Long.parseLong(getUser().getAreaId()));
		}
		Map<Long, List<PmBscDict>> map = getMarketControl();
		dictFormations = map.get(BizConstant.DICT_DATA_TYPE_2);
		dictRanks = map.get(BizConstant.DICT_DATA_TYPE_3);
		dictCulturals = map.get(BizConstant.DICT_DATA_TYPE_5);
		reportArray = staffCollectService.getReportbyObsertype(staffDto);
		return "listobser";
	}
	
	/**
	 * 查询人员立功表彰统计
	 * @return
	 */
	@Action("reportbyobser")
	public String reportbyObser(){
		Map<Long, List<PmBscDict>> map = getMarketControl();
		dictFormations = map.get(BizConstant.DICT_DATA_TYPE_2);
		dictRanks = map.get(BizConstant.DICT_DATA_TYPE_3);
		dictCulturals = map.get(BizConstant.DICT_DATA_TYPE_5);
		reportArray = staffCollectService.getReportbyObsertype(staffDto);
		return "listobser";
	}
	/**
	 * 进入按人员基本情况统计
	 * @return
	 */
	@Action("searchbystaff")
	@OperCode(code=OpcodeConstant.OPCODE_15301)
	public String searchbyStaff(){
//		Map<Long, List<PmBscDict>> map = getMarketControl();
//		dictFormations = map.get(BizConstant.DICT_DATA_TYPE_2);
//		dictRanks = map.get(BizConstant.DICT_DATA_TYPE_3);
//		obserTypes = PmBscObserTypeService.findBy("vcType", BizConstant.DICT_OBSER_TYPE_2);
//		if(staffDto==null){
//			staffDto = new StaffReportDto();
//		}
		if(staffDto == null){
			staffDto = new StaffReportDto();
			staffDto.setAreaid(Long.parseLong(getUser().getAreaId()));
		}
		Map<Long, List<PmBscDict>> map = getMarketControl();
		dictFormations = map.get(BizConstant.DICT_DATA_TYPE_2);
		dictRanks = map.get(BizConstant.DICT_DATA_TYPE_3);
		obserTypes = PmBscObserTypeService.findBy("vcType", BizConstant.DICT_OBSER_TYPE_2);
		staffmap = staffCollectService.getReportbyStaffinfo(staffDto);
		return "liststaff";
	}
	
	/**
	 * 查询人员基本情况统计
	 * @return
	 */
	@Action("reportbystaff")
	public String reportbyStaff(){
		Map<Long, List<PmBscDict>> map = getMarketControl();
		dictFormations = map.get(BizConstant.DICT_DATA_TYPE_2);
		dictRanks = map.get(BizConstant.DICT_DATA_TYPE_3);
		obserTypes = PmBscObserTypeService.findBy("vcType", BizConstant.DICT_OBSER_TYPE_2);
		staffmap = staffCollectService.getReportbyStaffinfo(staffDto);
		return "liststaff";
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
	
	public List<PmBscDict> getDictRanks() {
		return dictRanks;
	}
	
	public List<PmBscDict> getDictFormations() {
		return dictFormations;
	}

	public List<PmBscDict> getDictCulturals() {
		return dictCulturals;
	}

	public List<PmBscObserType> getObserTypes() {
		return obserTypes;
	}

	public StaffReportDto getStaffDto() {
		return staffDto;
	}

	public void setStaffDto(StaffReportDto staffDto) {
		this.staffDto = staffDto;
	}

	public String[][] getReportArray() {
		return reportArray;
	}

	public void setReportArray(String[][] reportArray) {
		this.reportArray = reportArray;
	}

	public Map<String, Object> getStaffmap() {
		return staffmap;
	}
	
}
