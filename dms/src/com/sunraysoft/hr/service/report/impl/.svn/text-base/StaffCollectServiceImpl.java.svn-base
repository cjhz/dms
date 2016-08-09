package com.sunraysoft.hr.service.report.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunraysoft.hr.constant.BizConstant;
import com.sunraysoft.hr.dao.AreainfoDao;
import com.sunraysoft.hr.dao.baseinfo.bscdict.PmBscDictDao;
import com.sunraysoft.hr.dao.baseinfo.bscobsertype.PmBscObserTypeDao;
import com.sunraysoft.hr.dao.report.StaffCollectDao;
import com.sunraysoft.hr.domain.dto.report.EntityDto;
import com.sunraysoft.hr.domain.dto.report.StaffReportDto;
import com.sunraysoft.hr.domain.entity.basic.PmBscAreaInfo;
import com.sunraysoft.hr.domain.entity.basic.PmBscDict;
import com.sunraysoft.hr.domain.entity.basic.PmBscObserType;
import com.sunraysoft.hr.service.report.StaffCollectService;

/**
 * @msg.人员统计service
 * @author chenjh
 * @version 2012-08-15
 */
@Service("staffCollectService")
public class StaffCollectServiceImpl implements StaffCollectService {

	@Resource StaffCollectDao staffCollectDao;
	@Resource PmBscDictDao dictDao;
	@Resource PmBscObserTypeDao obserTypeDao;
	@Resource AreainfoDao areaDao;

	/**
	 * 获得按人员统计结果集 横向从PmBscDict查出
	 * @param reportdto
	 * @return
	 */
    public String[][] getReportForDict(PmBscAreaInfo areainfo,List<PmBscDict> headers,Map<String,Map<String,Object[]>> map){
		List<PmBscAreaInfo> arealist = getAreaList(areainfo);//地区
		String[][] strings = new String[arealist.size()+2][headers.size()];
		String[] headernames = new String[headers.size()];//第一行
		for(int i=0;i<headers.size();i++){
			PmBscDict dict = headers.get(i);
			headernames[i]=dict.getVcName();
		}
		strings[0]=headernames;
		int sum=0;  
		int row=1;//行
		String[] footnames = new String[headers.size()];//最后一行
		footnames[0]="合计";
		for(int i=0;i<arealist.size();i++){
			int col=1;//列
			int areasum=0;//最后一列：合计
			PmBscAreaInfo area = arealist.get(i);
			String code = area.getVcExtend1();//地区编码（截取过后）
			strings[row][0]=area.getVcAreaName();
			for(int j=0;j<headers.size();j++){
				PmBscDict dict =headers.get(j);
				if(!dict.getPid().equals("")&&!dict.getPid().equals(0L)){
					if(map.get(code)!=null&&map.get(code).get(dict.getPid()+"")!=null){
						Object[] obj =map.get(code).get(dict.getPid()+"");
						sum = obj[2]==null?0:Integer.parseInt(obj[2].toString());
					}else{
						sum=0;
					}
					strings[row][col]=sum+"";
					areasum+=sum;
					if(footnames[col]==null||"".equals(footnames[col])){
						footnames[col]=0+"";
						footnames[col]=(Integer.parseInt(footnames[col])+sum)+"";
					}else{
						footnames[col]=(Integer.parseInt(footnames[col])+sum)+"";
					}	
					col++;
				}
			}
			strings[row][headers.size()-1]=areasum+"";
			if(footnames[headers.size()-1]==null||"".equals(footnames[headers.size()-1])){
				footnames[headers.size()-1]=0+"";
				footnames[headers.size()-1]=(Integer.parseInt(footnames[headers.size()-1])+areasum)+"";
			}else
				footnames[headers.size()-1]=(Integer.parseInt(footnames[headers.size()-1])+areasum)+"";
			row++;
		}
		strings[arealist.size()+1]=footnames;
		return strings;
	}
    
    /**
     * 获得按人员统计结果集 横向从PmBscObserType查出
     * @param areainfo
     * @param headers
     * @param map
     * @return
     */
    public String[][] getReportForOtype(PmBscAreaInfo areainfo,List<PmBscObserType> headers,Map<String,Map<String,Object[]>> map){
    	List<PmBscAreaInfo> arealist = getAreaList(areainfo);//地区
    	String[][] strings = new String[arealist.size()+2][headers.size()];
    	String[] headernames = new String[headers.size()];//第一行
    	for(int i=0;i<headers.size();i++){
    		PmBscObserType dict = headers.get(i);
    		headernames[i]=dict.getVcName();
    	}
    	strings[0]=headernames;
    	int sum=0;  
    	int row=1;//行
    	String[] footnames = new String[headers.size()];//最后一行
    	footnames[0]="合计";
    	for(int i=0;i<arealist.size();i++){
    		int col=1;//列
    		int areasum=0;//最后一列：合计
    		PmBscAreaInfo area = arealist.get(i);
    		String code = area.getVcExtend1();//地区编码（截取过后）
    		strings[row][0]=area.getVcAreaName();
    		for(int j=0;j<headers.size();j++){
    			PmBscObserType dict =headers.get(j);
    			if(!dict.getPid().equals("")&&!dict.getPid().equals(0L)){
    				if(map.get(code)!=null&&map.get(code).get(dict.getPid()+"")!=null){
    					Object[] obj =map.get(code).get(dict.getPid()+"");
    					sum = obj[2]==null?0:Integer.parseInt(obj[2].toString());
    				}else{
    					sum=0;
    				}
    				strings[row][col]=sum+"";
    				areasum+=sum;
    				if(footnames[col]==null||"".equals(footnames[col])){
    					footnames[col]=0+"";
    					footnames[col]=(Integer.parseInt(footnames[col])+sum)+"";
    				}else{
    					footnames[col]=(Integer.parseInt(footnames[col])+sum)+"";
    				}	
    				col++;
    			}
    		}
    		strings[row][headers.size()-1]=areasum+"";
    		if(footnames[headers.size()-1]==null||"".equals(footnames[headers.size()-1])){
    			footnames[headers.size()-1]=0+"";
    			footnames[headers.size()-1]=(Integer.parseInt(footnames[headers.size()-1])+areasum)+"";
    		}else
    			footnames[headers.size()-1]=(Integer.parseInt(footnames[headers.size()-1])+areasum)+"";
    		row++;
    	}
    	strings[arealist.size()+1]=footnames;
    	return strings;
    }
    
    /**
     * 按人员基本情况统计
     * @param areainfo
     * @param headers
     * @param map
     * @return
     */
    public Map getReportForStaff(PmBscAreaInfo areainfo,LinkedHashMap<String,List<EntityDto>> headers,Map<String,Map<String,Map<String,Object[]>>> map){
    	List<PmBscAreaInfo> arealist = getAreaList(areainfo);//地区
    	int length = getColLength(headers);
    	String[][] strings = new String[arealist.size()+2][length+1];//没有合计，多第一列空位
    	String[] headernames = new String[length+1];//第一行
    	int[] len = new int[4];
    	headernames[0]="";
    	len[0]=2;//地区合并行数
    	int sum=0;  
    	int row=1;//行
    	String[] footnames = new String[length+1];//最后一行
    	footnames[0]="合计";
    	Map<String,Map<String,Object[]>> newMap = new HashMap<String,Map<String,Object[]>>();
    	for(int i=0;i<arealist.size();i++){
    		int col=1;//列
    		int areasum=0;//最后一列：合计
    		PmBscAreaInfo area = arealist.get(i);
    		String code = area.getVcExtend1();//地区编码（截取过后）
    		strings[row][0]=area.getVcAreaName();
    		int k=0;
    		int l=0;
    		for(Map.Entry<String,List<EntityDto>> entry:headers.entrySet()){
    			l++;
    			List<EntityDto> header = entry.getValue();
    			newMap = map.get(entry.getKey());
    			if(i==0)len[l]=header.size();
    			for(int j=0;j<header.size();j++){
    				k++;
    				EntityDto dict =header.get(j);
    				if(i==0)headernames[k]=dict.getValue();//设置一次；k从1开始
    				if(!dict.getKey().equals("")&&!dict.getKey().equals(0L)){
    					if(newMap.get(code)!=null&&newMap.get(code).get(dict.getKey()+"")!=null){
    						Object[] obj =newMap.get(code).get(dict.getKey()+"");
    						sum = obj[2]==null?0:Integer.parseInt(obj[2].toString());
    					}else{
    						sum=0;
    					}
    					strings[row][col]=sum+"";
    					areasum+=sum;
    					if(footnames[col]==null||"".equals(footnames[col])){
    						footnames[col]=0+"";
    						footnames[col]=(Integer.parseInt(footnames[col])+sum)+"";
    					}else{
    						footnames[col]=(Integer.parseInt(footnames[col])+sum)+"";
    					}	
    					col++;
    				}
    			}
    		}
    		
//    		strings[row][length]=areasum+"";
//    		if(footnames[length]==null||"".equals(footnames[length])){
//    			footnames[length]=0+"";
//    			footnames[length]=(Integer.parseInt(footnames[length])+areasum)+"";
//    		}else
//    			footnames[length]=(Integer.parseInt(footnames[length])+areasum)+"";
    		row++;
    	}
    	strings[0]=headernames;
    	strings[arealist.size()+1]=footnames;
    	LinkedHashMap lmap = new LinkedHashMap();
    	lmap.put("merge", len);//合并
    	lmap.put("content", strings);//内容
    	return lmap;
    }
    
    /**
     * 获得按人员编制统计结果集
     */
    public String[][] getReportbyFormation(StaffReportDto reportdto){
    	PmBscAreaInfo areainfo = findArea(reportdto);
    	List<PmBscDict> formations = getDictFormations();//编制 处理前
    	List<PmBscDict>	headers = getRowHeader(formations);//编制 处理后
    	
    	reportdto.setDepartmenttype(areainfo.getVcDepartmentType());
    	reportdto.setAreacode(areainfo.getVcDepartmentCode());
    	List list =staffCollectDao.getListbyFormation(reportdto);
    	Map<String,Map<String,Object[]>> map=ListToMap(list);
    	return getReportForDict(areainfo,headers,map);
    }
    
    /**
     * 获得按人员职级统计结果集
     * @param reportdto
     * @return
     */
    public String[][] getReportbyRank(StaffReportDto reportdto){
    	PmBscAreaInfo areainfo = findArea(reportdto);
    	List<PmBscDict> ranks = getDictRanks();//职级 处理前
    	List<PmBscDict>	headers = getRowHeader(ranks);//职级 处理后
    	
    	reportdto.setDepartmenttype(areainfo.getVcDepartmentType());
    	reportdto.setAreacode(areainfo.getVcDepartmentCode());
    	List list =staffCollectDao.getListbyRanks(reportdto);
    	Map<String,Map<String,Object[]>> map=ListToMap(list);
    	return getReportForDict(areainfo,headers,map);
    }
    
    /**
     * 获得按人员立功表彰统计结果集
     * @param reportdto
     * @return
     */
    public String[][] getReportbyObsertype(StaffReportDto reportdto){
    	PmBscAreaInfo areainfo = findArea(reportdto);
    	List<PmBscObserType> otypes = getObsertypes();//立功表彰类别 处理前
    	List<PmBscObserType> headers = getRowHeaderByOtype(otypes);//立功表彰类别 处理后
    	
    	reportdto.setDepartmenttype(areainfo.getVcDepartmentType());
    	reportdto.setAreacode(areainfo.getVcDepartmentCode());
    	List list =staffCollectDao.getListbyObserTypes(reportdto);
    	Map<String,Map<String,Object[]>> map=ListToMap(list);
    	return getReportForOtype(areainfo,headers,map);
    }
    
    /**
     * 获取按人员基本情况统计结果集
     * @param reportdto
     * @return
     */
    public Map getReportbyStaffinfo(StaffReportDto reportdto){
    	PmBscAreaInfo areainfo = findArea(reportdto);
    	List<EntityDto> sexlist = getSexlist();
    	List<EntityDto> culturalList = getCulturalList();
    	List<EntityDto> ageGroupList = getAgeGrouplist();
    	LinkedHashMap<String,List<EntityDto>> mapheader = new LinkedHashMap<String, List<EntityDto>>();
    	mapheader.put("1", sexlist);//性别
    	mapheader.put("2", culturalList);//文化程度
    	mapheader.put("3", ageGroupList);//年龄情况
    	
    	reportdto.setDepartmenttype(areainfo.getVcDepartmentType());
    	reportdto.setAreacode(areainfo.getVcDepartmentCode());
    	List sexs =staffCollectDao.getListbySex(reportdto);
    	List culturals =staffCollectDao.getListbyCultural(reportdto);
    	List ageGroups =staffCollectDao.getListbyAgegroup(reportdto);
    	Map<String,Map<String,Object[]>> mapsexs=ListToMap(sexs);
    	Map<String,Map<String,Object[]>> mapculturals=ListToMap(culturals);
    	Map<String,Map<String,Object[]>> mapageGroups=ListToMap(ageGroups);
    	Map<String,Map<String,Map<String,Object[]>>> map = new HashMap<String, Map<String,Map<String,Object[]>>>();
    	map.put("1", mapsexs);
    	map.put("2", mapculturals);
    	map.put("3", mapageGroups);
    	return getReportForStaff(areainfo,mapheader,map);
    }
    
    /**
     * 获得横向抬头PmBscDict
     * @return
     */
    private List<PmBscDict> getRowHeader(List<PmBscDict> dictlist){
    	PmBscDict dict = new PmBscDict();
    	List<PmBscDict> newdicts = new ArrayList<PmBscDict>();
    	dict.setPid(0L);
    	dict.setVcName("地区");
    	newdicts.add(dict);
    	for(int i=0;i<dictlist.size();i++){
    		dict = new PmBscDict();
    		PmBscDict d = dictlist.get(i);
    		dict.setPid(d.getPid());
    		dict.setVcName(d.getVcName());
    		newdicts.add(dict);
    	}
    	dict = new PmBscDict();
    	dict.setPid(0L);
    	dict.setVcName("合计");
    	newdicts.add(dict);
    	return newdicts;
    }
    
    /**
     * 获得横向抬头PmBscObserType
     * @param dictlist
     * @return
     */
    private List<PmBscObserType> getRowHeaderByOtype(List<PmBscObserType> dictlist){
    	PmBscObserType dict = new PmBscObserType();
    	List<PmBscObserType> newdicts = new ArrayList<PmBscObserType>();
    	dict.setPid(0L);
    	dict.setVcName("地区");
    	newdicts.add(dict);
    	for(int i=0;i<dictlist.size();i++){
    		dict = new PmBscObserType();
    		PmBscObserType d = dictlist.get(i);
    		dict.setPid(d.getPid());
    		dict.setVcName(d.getVcName());
    		newdicts.add(dict);
    	}
    	dict = new PmBscObserType();
    	dict.setPid(0L);
    	dict.setVcName("合计");
    	newdicts.add(dict);
    	return newdicts;
    }
    
    /**
     * 获得性别集合
     * @return
     */
    private List<EntityDto> getSexlist(){
    	List<EntityDto> list = new ArrayList<EntityDto>();
    	EntityDto entity1 = new EntityDto();
    	entity1.setKey(BizConstant.SEX_KEY_1);
    	entity1.setValue(BizConstant.SEX_TXT_1);
    	EntityDto entity2 = new EntityDto();
    	entity2.setKey(BizConstant.SEX_KEY_2);
    	entity2.setValue(BizConstant.SEX_TXT_2);
    	list.add(entity1);
    	list.add(entity2);
    	return list;
    }
    
    /**
     * 获得文化程度集合
     * @return
     */
    private List<EntityDto> getCulturalList(){
    	List<PmBscDict> list = getDictCulturals();
    	List<EntityDto> newlist = new ArrayList<EntityDto>();
    	for(int i=0;i<list.size();i++){
    		PmBscDict dict = list.get(i);
    		EntityDto entity = new EntityDto();
    		entity.setKey(dict.getPid());
    		entity.setValue(dict.getVcName());
    		newlist.add(entity);
    	}
    	return newlist;
    }
    
    /**
     * 获得年龄段集合
     * @return
     */
    private List<EntityDto> getAgeGrouplist(){
    	List<EntityDto> list = new ArrayList<EntityDto>();
    	EntityDto entity1 = new EntityDto();
    	entity1.setKey(BizConstant.AGE_GROUP_KEY_2);
    	entity1.setValue(BizConstant.AGE_GROUP_VAL_2);
    	EntityDto entity2 = new EntityDto();
    	entity2.setKey(BizConstant.AGE_GROUP_KEY_3);
    	entity2.setValue(BizConstant.AGE_GROUP_VAL_3);
    	EntityDto entity3 = new EntityDto();
    	entity3.setKey(BizConstant.AGE_GROUP_KEY_4);
    	entity3.setValue(BizConstant.AGE_GROUP_VAL_4);
    	EntityDto entity4 = new EntityDto();
    	entity4.setKey(BizConstant.AGE_GROUP_KEY_5);
    	entity4.setValue(BizConstant.AGE_GROUP_VAL_5);
    	list.add(entity1);
    	list.add(entity2);
    	list.add(entity3);
    	list.add(entity4);
    	return list;
    }
    
    /**
     * 获得列长
     * @param map
     * @return
     */
    public int getColLength(Map<String,List<EntityDto>> map){
    	Iterator<List<EntityDto>> vit=map.values().iterator();  
    	int length=0;
    	while(vit.hasNext()){  
    		List<EntityDto> list = vit.next();
    		length+=list.size();
    	}
    	return length;
    }
    
    
    /**
	 * 将List转换成map（如：{key1:{1key1:1obj1,1key2:1obj2;...},key2:{2key1:2obj1;2key2:2obj2;...}} 的map模式）
	 * @param list
	 * @return
	 */
	public Map<String,Map<String,Object[]>> ListToMap(List list){
		Map<String, Map<String,Object[]>> hashmap=new HashMap<String, Map<String,Object[]>>();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] obj=(Object[])list.get(i);
				if(hashmap.containsKey(obj[0])){
					hashmap.get(obj[0]).put(obj[1].toString(), obj);//obj[0]：机关编码；obj[1]：编制
				}else{
					Map<String, Object[]> map=new HashMap<String, Object[]>();
					map.put(obj[1].toString(), obj);
					hashmap.put(obj[0].toString(), map);
				}
			}
		}
		return hashmap;
	}
    
    /**
	 * 获得字典表人员编制属性数据
	 * @return
	 */
	private List<PmBscDict> getDictFormations() {
		List<PmBscDict> list = dictDao.findByValuesOrder("vcPosition", true, new String[]{"vcDeleteFlag","vcCode"},new Object[]{BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL,BizConstant.DICT_DATA_TYPE_2} );
		return list;
	}
	
	/**
	 * 获得字典表人员职级属性数据
	 * @return
	 */
	private List<PmBscDict> getDictRanks() {
		List<PmBscDict> list = dictDao.findByValuesOrder("vcPosition", true, new String[]{"vcDeleteFlag","vcCode"},new Object[]{BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL,BizConstant.DICT_DATA_TYPE_3} );
		return list;
	}
	
	/**
	 * 获得立功表彰人员类别属性数据
	 * @return
	 */
	private List<PmBscObserType> getObsertypes() {
		List<PmBscObserType> list = obserTypeDao.findByValues(new String[]{"vcDeleteFlag","vcType"},new Object[]{BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL,BizConstant.DICT_OBSER_TYPE_2} );
		return list;
	}
	
	/**
	 * 通过编号获得地区信息
	 * @param reportdto
	 * @return
	 */
	private PmBscAreaInfo findArea(StaffReportDto reportdto){
		List<PmBscAreaInfo> areaList = areaDao.findByValues(new String[]{"vcDeleteFlag","pid"}, new Object[]{BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL,reportdto.getAreaid()});
		PmBscAreaInfo area = areaList.get(0);
		return area;
	}
	
	/**
	 * 获得文化程度集合
	 * @return
	 */
	private List<PmBscDict> getDictCulturals() {
		List<PmBscDict> list = dictDao.findByValuesOrder("vcPosition", true, new String[]{"vcDeleteFlag","vcCode"},new Object[]{BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL,BizConstant.DICT_DATA_TYPE_5});
		return list;
	}
	
	/**
	 * 获得地区集合
	 * @return
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
}
