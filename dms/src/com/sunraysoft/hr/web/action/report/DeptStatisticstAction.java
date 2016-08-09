package com.sunraysoft.hr.web.action.report;

import java.util.List;

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
import com.sunraysoft.hr.domain.dto.report.DeptStatisticsDto;
import com.sunraysoft.hr.domain.entity.basic.PmBscDeptType;
import com.sunraysoft.hr.domain.entity.basic.PmBscDict;
import com.sunraysoft.hr.domain.entity.basic.PmBscObserType;
import com.sunraysoft.hr.service.baseinfo.bscdeptype.PmBscDeptTypeService;
import com.sunraysoft.hr.service.baseinfo.bscdict.PmBscDictService;
import com.sunraysoft.hr.service.baseinfo.bscobsertype.PmBscObserTypeService;
import com.sunraysoft.hr.service.report.DeptStatisticsService;
import com.sunraysoft.hr.web.action.base.BaseAction;
import com.sunraysoft.hr.web.action.basic.StaffAction;

/**
 * 机构统计
 * @author juyf
 * @date 2012-08-16
 */
@Controller 
@Scope("prototype")
@Namespace("/report/deptstatistics")
public class DeptStatisticstAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(StaffAction.class);
	
	private DeptStatisticsDto deptdto;
	
	@Resource private PmBscDictService PmBscDictService;
	@Resource private PmBscObserTypeService PmBscObserTypeService;
	@Resource private PmBscDeptTypeService pmBscDeptTypeService;
	@Resource private DeptStatisticsService deptStatisticsService;
	
	private List<PmBscDict> dictRanks;//职级
	private List<PmBscObserType> obserTypes;//立功表彰类别
	private List<PmBscDeptType> deptypes;// 机构性质
	private List<Object[]> deptstatistics;// 封装的list
	
	//进入按机构性质统计
	@Action("searchdeptype")
	@OperCode(code=OpcodeConstant.OPCODE_12901)
	public String searchdeptype(){
		loadAllList(1L);
		if(deptdto == null){
			deptdto = new DeptStatisticsDto();
			deptdto.setAreaid(Long.parseLong(getUser().getAreaId()));
			deptdto.setAreacode(getUser().getAreaCode());
			deptdto.setDepartmenttype(getUser().getDepartmentype());
		}
		deptstatistics = deptStatisticsService.findDeptStatisticsList_deptype(deptdto);
		return "deptypelist";
	}
	
	//机构性质统计 点击查询调用
	@Action("listdeptype")
	public String listdeptype(){
		loadAllList(1L);
		deptstatistics = deptStatisticsService.findDeptStatisticsList_deptype(deptdto);
		return "deptypelist";
	}
	
	//进入按机构职级统计
	@Action("searchdeptdict")
	@OperCode(code=OpcodeConstant.OPCODE_13301)
	public String searchdeptdict(){
		loadAllList(2L);
		if(deptdto == null){
			deptdto = new DeptStatisticsDto();
			deptdto.setAreaid(Long.parseLong(getUser().getAreaId()));
			deptdto.setAreacode(getUser().getAreaCode());
			deptdto.setDepartmenttype(getUser().getDepartmentype());
		}
		deptstatistics = deptStatisticsService.findDeptStatisticsList_dict(deptdto);
		return "deptdictlist";
	}
	
	//机构职级统计 点击查询调用
	@Action("listdeptdict")
	public String listeptdict(){
		loadAllList(2L);
		deptstatistics = deptStatisticsService.findDeptStatisticsList_dict(deptdto);
		return "deptdictlist";
	}
	
	//进入按机构立功表彰统计
	@Action("searchobsertype")
	@OperCode(code=OpcodeConstant.OPCODE_13701)
	public String searchobsertype(){
		loadAllList(3L);
		if(deptdto == null){
			deptdto = new DeptStatisticsDto();
			deptdto.setAreaid(Long.parseLong(getUser().getAreaId()));
			deptdto.setAreacode(getUser().getAreaCode());
			deptdto.setDepartmenttype(getUser().getDepartmentype());
		}
		deptstatistics = deptStatisticsService.findDeptStatisticsList_observing(deptdto);
		return "deptobserlist";
	}
	
	//机构立功表彰统计 点击查询调用
	@Action("listobsertype")
	public String listobsertype(){
		loadAllList(3L);
		deptstatistics = deptStatisticsService.findDeptStatisticsList_observing(deptdto);
		return "deptobserlist";
	}
	
	public void loadAllList(Long type){
		if(BizConstant.DICT_STATISTICS_1 == type){// 按照机构性质统计, 不加载机构性质列表
			dictRanks = PmBscDictService.findAll("vcPosition", true, "vcCode",
					BizConstant.DICT_DATA_TYPE_1);// 机构职级
			obserTypes = PmBscObserTypeService.findAll("pid", true, "vcType",
					BizConstant.DICT_OBSER_TYPE_1);// 立功表彰
		}else if(BizConstant.DICT_STATISTICS_2 == type){// 按照机构职级统计, 不加载机构职级列表
			obserTypes = PmBscObserTypeService.findAll("pid", true, "vcType",
					BizConstant.DICT_OBSER_TYPE_1);// 立功表彰
			deptypes = pmBscDeptTypeService.findAll("pid", true);// 机构性质
		}else if(BizConstant.DICT_STATISTICS_3 == type){// 按照立功表彰统计, 不加载立功表彰列表
			dictRanks = PmBscDictService.findAll("vcPosition", true, "vcCode",
					BizConstant.DICT_DATA_TYPE_1);// 机构职级
			deptypes = pmBscDeptTypeService.findAll("pid", true);// 机构性质
		}
	}
	
	public List<PmBscDict> getDictRanks() {
		return dictRanks;
	}

	public List<PmBscObserType> getObserTypes() {
		return obserTypes;
	}

	public List<PmBscDeptType> getDeptypes() {
		return deptypes;
	}

	public List<Object[]> getDeptstatistics() {
		return deptstatistics;
	}

	public DeptStatisticsDto getDeptdto() {
		return deptdto;
	}

	public void setDeptdto(DeptStatisticsDto deptdto) {
		this.deptdto = deptdto;
	}

}
