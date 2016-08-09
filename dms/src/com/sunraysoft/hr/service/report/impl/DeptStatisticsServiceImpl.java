package com.sunraysoft.hr.service.report.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunraysoft.hr.constant.BizConstant;
import com.sunraysoft.hr.dao.AreainfoDao;
import com.sunraysoft.hr.dao.baseinfo.bscdeptype.PmBscDeptTypeDao;
import com.sunraysoft.hr.dao.baseinfo.bscdict.PmBscDictDao;
import com.sunraysoft.hr.dao.baseinfo.bscobsertype.PmBscObserTypeDao;
import com.sunraysoft.hr.dao.report.DeptStatisticsDao;
import com.sunraysoft.hr.domain.dto.report.DeptStatisticsDto;
import com.sunraysoft.hr.domain.entity.basic.PmBscAreaInfo;
import com.sunraysoft.hr.domain.entity.basic.PmBscDeptType;
import com.sunraysoft.hr.domain.entity.basic.PmBscDict;
import com.sunraysoft.hr.domain.entity.basic.PmBscObserType;
import com.sunraysoft.hr.service.report.DeptStatisticsService;

/**
 * 机构统计
 * @author juyf
 * @date 2012-08-16
 */
@Service("DeptStatisticsService")
public class DeptStatisticsServiceImpl implements DeptStatisticsService {

	@Resource DeptStatisticsDao deptStatisticsDao;
	@Resource PmBscDictDao dictDao;
	@Resource AreainfoDao areaDao;
	@Resource PmBscDeptTypeDao deptypedao;
	@Resource PmBscObserTypeDao obsertypedao;
	
	/**
	 * 机构统计-按机构性质统计
	 * @param deptdto
	 * @return 查询封装后的列表
	 */
	public List<Object[]> findDeptStatisticsList_deptype(DeptStatisticsDto deptdto) {
		PmBscAreaInfo areainfo = findArea(deptdto);
		deptdto.setDepartmenttype(areainfo.getVcDepartmentType());
		deptdto.setAreacode(areainfo.getVcDepartmentCode());
		// 第一列 code 第二列机构性质名称 第三列统计数量 第四列 机构性质id
		List<Object[]> countsList = deptStatisticsDao
				.findDeptStatisticsList_deptype(deptdto);
		List<PmBscAreaInfo> areaList = getAreaList(areainfo);// 地区列表(用作行标题)

		if (areaList != null && areaList.size() > 0) {
			return mergeList_deptype(areaList, ListToMap(countsList));
		}
		return null;
	}
	
	/**
	 * 机构统计-按机构职级统计
	 * @param deptdto
	 * @return 查询封装后的列表
	 */
	public List<Object[]> findDeptStatisticsList_dict(DeptStatisticsDto deptdto) {
		PmBscAreaInfo areainfo = findArea(deptdto);
		deptdto.setDepartmenttype(areainfo.getVcDepartmentType());
		deptdto.setAreacode(areainfo.getVcDepartmentCode());
		// 第一列 code 第二列机构性质名称 第三列统计数量 第四列 机构性质id
		List<Object[]> countsList = deptStatisticsDao
				.findDeptStatisticsList_dict(deptdto);
		List<PmBscAreaInfo> areaList = getAreaList(areainfo);// 地区列表(用作行标题)

		if (areaList != null && areaList.size() > 0) {
			return mergeList_deptdict(areaList, ListToMap(countsList));
		}
		return null;
	}
	
	/**
	 * 机构统计-按机构立功表彰统计
	 * @param deptdto
	 * @return 查询封装后的列表
	 */
	public List<Object[]> findDeptStatisticsList_observing(DeptStatisticsDto deptdto) {
		PmBscAreaInfo areainfo = findArea(deptdto);
		deptdto.setDepartmenttype(areainfo.getVcDepartmentType());
		deptdto.setAreacode(areainfo.getVcDepartmentCode());
		// 第一列 code 第二列机构性质名称 第三列统计数量 第四列 机构性质id
		List<Object[]> countsList = deptStatisticsDao
				.findDeptStatisticsList_observing(deptdto);
		List<PmBscAreaInfo> areaList = getAreaList(areainfo);// 地区列表(用作行标题)

		if (areaList != null && areaList.size() > 0) {
			return mergeList_deptobser(areaList, ListToMap(countsList));
		}
		return null;
	}

	/**
	 * 合并结果集，封装成想要的列表
	 * @param areaList
	 * @param countsMap
	 * @return
	 */
	public List<Object[]> mergeList_deptype(List<PmBscAreaInfo> areaList, Map<String,Object[]> countsMap) {
		List<Object[]> resultList = new ArrayList<Object[]>();
		List<PmBscDeptType> detypeList = findDepTypeList();// 机构性质列表 (用作列标题)
		int colSize = detypeList.size() + 2;
		String[] colTitleName = new String[colSize];// 第一行
		String[] colTitleID = new String[colSize];// 第一行id
		colTitleName[0] = "地区";
		for (int i = 1; i <= detypeList.size(); i++) {
			colTitleName[i] = detypeList.get(i-1).getVcTypeName();
			colTitleID[i] = detypeList.get(i-1).getPid().toString();
		}
		colTitleName[colSize-1] = "合计";
		resultList.add(colTitleName);
		Object[] objhj = new Object[colSize];
		objhj[0] = "合计"; 
		int rowhjsum = 0;
		for (PmBscAreaInfo area : areaList) {
			int rowsum = 0;
			Object[] obj = new Object[colSize];
			obj[0] = area.getVcAreaName();
			for (int j = 1; j < colTitleID.length; j++) {
				if(j == colTitleID.length-1){
					obj[colSize-1] = rowsum;
					resultList.add(obj);continue;
				}
				if (countsMap.containsKey(area.getVcExtend1()
						+ colTitleID[j])) {
					obj[j] = countsMap.get(area.getVcExtend1()
							+ colTitleID[j])[2];
				} else {
					obj[j] = 0;
				}
				objhj[j] = Integer.parseInt(objhj[j]==null?"0":objhj[j].toString())+Integer.parseInt(obj[j].toString());
				rowsum += Integer.parseInt(obj[j].toString());
				rowhjsum += Integer.parseInt(obj[j].toString());
			}
		}
		objhj[colSize-1] = rowhjsum;
		resultList.add(objhj);
		return resultList;
	}
	
	/**
	 * 合并结果集，封装成想要的列表
	 * @param areaList
	 * @param countsMap
	 * @return
	 */
	public List<Object[]> mergeList_deptdict(List<PmBscAreaInfo> areaList, Map<String,Object[]> countsMap) {
		List<Object[]> resultList = new ArrayList<Object[]>();
		List<PmBscDict> detypeList = findDictList();// 机构职级列表 (用作列标题)
		int colSize = detypeList.size() + 2;
		String[] colTitleName = new String[colSize];// 第一行
		String[] colTitleID = new String[colSize];// 第一行id
		colTitleName[0] = "地区";
		for (int i = 1; i <= detypeList.size(); i++) {
			colTitleName[i] = detypeList.get(i-1).getVcName();
			colTitleID[i] = detypeList.get(i-1).getPid().toString();
		}
		colTitleName[colSize-1] = "合计";
		resultList.add(colTitleName);
		Object[] objhj = new Object[colSize];
		objhj[0] = "合计"; 
		int rowhjsum = 0;
		for (PmBscAreaInfo area : areaList) {
			int rowsum = 0;
			Object[] obj = new Object[colSize];
			obj[0] = area.getVcAreaName();
			for (int j = 1; j < colTitleID.length; j++) {
				if(j == colTitleID.length-1){
					obj[colSize-1] = rowsum;
					resultList.add(obj);continue;
				}
				if (countsMap.containsKey(area.getVcExtend1()
						+ colTitleID[j])) {
					obj[j] = countsMap.get(area.getVcExtend1()
							+ colTitleID[j])[2];
				} else {
					obj[j] = 0;
				}
				objhj[j] = Integer.parseInt(objhj[j]==null?"0":objhj[j].toString())+Integer.parseInt(obj[j].toString());
				rowsum += Integer.parseInt(obj[j].toString());
				rowhjsum += Integer.parseInt(obj[j].toString());
			}
		}
		objhj[colSize-1] = rowhjsum;
		resultList.add(objhj);
		return resultList;
	}
	
	/**
	 * 合并结果集，封装成想要的列表
	 * @param areaList
	 * @param countsMap
	 * @return
	 */
	public List<Object[]> mergeList_deptobser(List<PmBscAreaInfo> areaList, Map<String,Object[]> countsMap) {
		List<Object[]> resultList = new ArrayList<Object[]>();
		List<PmBscObserType> detypeList = findObserTypeList();// 机构立功表彰列表 (用作列标题)
		int colSize = detypeList.size() + 2;
		String[] colTitleName = new String[colSize];// 第一行
		String[] colTitleID = new String[colSize];// 第一行id
		colTitleName[0] = "地区";
		for (int i = 1; i <= detypeList.size(); i++) {
			colTitleName[i] = detypeList.get(i-1).getVcName();
			colTitleID[i] = detypeList.get(i-1).getPid().toString();
		}
		colTitleName[colSize-1] = "合计";
		resultList.add(colTitleName);
		Object[] objhj = new Object[colSize];
		objhj[0] = "合计";
		int rowhjsum = 0;
		for (PmBscAreaInfo area : areaList) {
			int rowsum = 0;
			Object[] obj = new Object[colSize];
			obj[0] = area.getVcAreaName();
			for (int j = 1; j < colTitleID.length; j++) {
				if(j == colTitleID.length-1){
					obj[colSize-1] = rowsum;
					resultList.add(obj);continue;
				}
				if (countsMap.containsKey(area.getVcExtend1()
						+ colTitleID[j])) {
					obj[j] = countsMap.get(area.getVcExtend1()
							+ colTitleID[j])[2];
				} else {
					obj[j] = 0;
				}
				objhj[j] = Integer.parseInt(objhj[j]==null?"0":objhj[j].toString())+Integer.parseInt(obj[j].toString());
				rowsum += Integer.parseInt(obj[j].toString());
				rowhjsum += Integer.parseInt(obj[j].toString());
			}
		}
		objhj[colSize-1] = rowhjsum;
		resultList.add(objhj);
		return resultList;
	}

	/**
	 * 查询机构职级名称(用作标题)
	 */
	public List<PmBscDict> findDictList() {
		return dictDao.findAll("vcPosition", true, "vcCode", BizConstant.DICT_DATA_TYPE_1);
	}
	
	/**
	 * 查询机构性质名称(用作标题)
	 */
	public List<PmBscDeptType> findDepTypeList() {
		return deptypedao.findAll("pid", true);
	}
	
	/**
	 * 查询立功表彰名称(用作标题)
	 */
	public List<PmBscObserType> findObserTypeList() {
		return obsertypedao.findBy("vcType", BizConstant.DICT_OBSER_TYPE_1);
	}

	/**
	 * 获得地区
	 * @return
	 */
	private PmBscAreaInfo findArea(DeptStatisticsDto deptdto){
		List<PmBscAreaInfo> areaList = areaDao.findByValues(new String[] {
				"vcDeleteFlag", "pid" },
				new Object[] { BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL,
						deptdto.getAreaid() });
		return areaList.get(0);
	}
	
	/**
	 * 查询地区(用作行标题)
	 */
	private List<PmBscAreaInfo> getAreaList(PmBscAreaInfo area){
		List<Object[]> arealist =areaDao.findContainAreaCode(area);
		List<PmBscAreaInfo> list = new ArrayList<PmBscAreaInfo>();
		for(int i=0;i<arealist.size();i++){
			Object[] obj = arealist.get(i);
			PmBscAreaInfo areainfo = new PmBscAreaInfo();
			areainfo.setPid(Long.parseLong(obj[0]==null?"0":obj[0].toString()));
			areainfo.setVcDepartmentName(obj[1]==null?"0":obj[1].toString());
			areainfo.setVcDepartmentCode(obj[2]==null?"0":obj[2].toString());//地区机关编码
			areainfo.setVcDepartmentType(Long.parseLong(obj[3]==null?"0":obj[3].toString()));
			areainfo.setVcAreaName(obj[4]==null?"0":obj[4].toString());
			areainfo.setVcExtend1(obj[5]==null?"0":obj[5].toString());//地区编码（被截取过）
			list.add(areainfo);
		}
		return list;
	}
	
	/**
	 * 将List转换成map(map<"xxx县code+机构性质id",object["xxx县code","机构性质",count,"机构性质id"]>)
	 * @param list
	 * @return
	 */
	public Map<String,Object[]> ListToMap(List<Object[]> list){
		Map<String, Object[]> map = new HashMap<String, Object[]>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				if (obj[0] != null && obj[3] != null) {
					map.put(obj[0].toString() + obj[3].toString(), obj);
				}
			}
		}
		return map;
	}
	
}
