package com.sunraysoft.hr.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;

import com.sunraysoft.hr.annotation.Export;
import com.sunraysoft.hr.domain.entity.base.BizEntity;
import com.sunraysoft.hr.domain.entity.base.BizEntityId;
import com.sunraysoft.hr.domain.entity.base.ExportAttr;
import com.sunraysoft.hr.domain.entity.basic.PmBscStaffDept;



public class ExportUtils {
	
	public static class MultyReport{
		Export r;
		List<Export> multys;
		public MultyReport(Export r, List<Export> multys) {
			super();
			this.r = r;
			this.multys = multys;
		}
	}
	
	public static String[] createHeader(Class c){
		Field[] fields = c.getDeclaredFields();
		List<MultyReport> rtTemp = new ArrayList<MultyReport>();
		
		for(int i = 0; i < fields.length; i++) {
			Export export = fields[i].getAnnotation(Export.class);
			if(export == null) 
				continue;
			if(export.formatType().equals(Export.EXPORT_MULTY_FILED)){
				rtTemp.add(new MultyReport(export, addInnerHeader(fields[i].getType())));
			}else{
				rtTemp.add(new MultyReport(export, null));
			}
		}
		
		Collections.sort(rtTemp, new Comparator<MultyReport>() {
			@Override
			public int compare(MultyReport o1, MultyReport o2) {
				return ((Integer)o1.r.sort()).compareTo((Integer)o2.r.sort());
			}
		});
		
		if(rtTemp!=null && rtTemp.size() != 0){
			List<String> rt = new ArrayList<String>();
			for (int i = 0; i < rtTemp.size(); i++) {
				if(rtTemp.get(i).multys==null)
					rt.add(rtTemp.get(i).r.i18nKey());
				else{
					for (Export exp : rtTemp.get(i).multys) {
						rt.add(exp.multyI18nKey());
					}
				}
			}
			return rt.toArray(new String[]{});
		}else{
			return null;
		}
	}
	
	public static List<Export> addInnerHeader(Class c){
		Field[] fields = c.getDeclaredFields();
		List<Export> rtTemp = new ArrayList<Export>();
		for(int i = 0; i < fields.length; i++) {
			Export export = fields[i].getAnnotation(Export.class);
			if(export == null || export.multyI18nKey().isEmpty()) 
				continue;
			rtTemp.add(export);
		}
		
		Collections.sort(rtTemp, new Comparator<Export>() {
			@Override
			public int compare(Export o1, Export o2) {
				return ((Integer)o1.multySort()).compareTo((Integer)o2.multySort());
			}
		});
		
		return rtTemp;
	}
	
	public static Integer[] createHeaderWidth(Class c){
		Field[] fields = c.getDeclaredFields();
		List<MultyReport> rtTemp = new ArrayList<MultyReport>();
		
		for(int i = 0; i < fields.length; i++) {
			Export export = fields[i].getAnnotation(Export.class);
			if(export == null) 
				continue;
			if(export.formatType().equals(Export.EXPORT_MULTY_FILED)){
				rtTemp.add(new MultyReport(export, addInnerHeader(fields[i].getType())));
			}else{
				rtTemp.add(new MultyReport(export, null));
			}
		}
		
		Collections.sort(rtTemp, new Comparator<MultyReport>() {
			@Override
			public int compare(MultyReport o1, MultyReport o2) {
				return ((Integer)o1.r.sort()).compareTo((Integer)o2.r.sort());
			}
		});
		
		if(rtTemp!=null && rtTemp.size() != 0){
			List<Integer> rt = new ArrayList<Integer>();
			for (int i = 0; i < rtTemp.size(); i++) {
				if(rtTemp.get(i).multys==null)
					rt.add(rtTemp.get(i).r.width());
				else{
					for (Export exp : rtTemp.get(i).multys) {
						rt.add(exp.multyWidth());
					}
				}
			}
			return rt.toArray(new Integer[]{});
		}else{
			return null;
		}
	}
	
	private static List<String> longToString(long[] keys) {
		List<String> list = new ArrayList<String>();
		if(keys != null && keys.length != 0) {
			for(long e : keys) {
				list.add(String.valueOf(e));
			}
		}
		
		return list;
	}
	
	public static Object[] createSingleContext(Object obj){
		Field[] fields = obj.getClass().getDeclaredFields();
		List<SortObj> rtTemp = new ArrayList<SortObj>();
		Object afterValue = null;

		for(int i = 0; i < fields.length; i++) {
			Export export = fields[i].getAnnotation(Export.class);
			if(export == null) 
				continue;
			List<String> constKeys = longToString( export.constKeys() );
			String[] constTexts = export.constTexts();
			String formatType = export.formatType();
			
			// formatType 取类的类型
			if(StringUtils.isBlank(formatType)) {
				formatType = fields[i].getType().getCanonicalName();
			}
			String objTemp = null;
			
			try {
				afterValue = PropertyUtils.getSimpleProperty(obj, fields[i].getName());
			} catch(Exception e) {
				throw new RuntimeException(e);
			}
			
			if(formatType.equals(Export.EXPORT_MULTY_FILED)) { //表明值不是常量
				rtTemp.addAll(formatMultyObj(afterValue));
			}else if(constKeys.isEmpty()){
				objTemp = formatData(afterValue, formatType);
				rtTemp.add(new SortObj(objTemp, export.sort()));
			} else {
				objTemp = keyToText(afterValue, constKeys, constTexts);
				rtTemp.add(new SortObj(objTemp, export.sort()));
			}
			
		}
		
		Collections.sort(rtTemp, new Comparator<SortObj>() {
			@Override
			public int compare(SortObj o1, SortObj o2) {
				return ((Integer)o1.sort).compareTo((Integer)o2.sort);
			}
		});
		
		if(rtTemp!=null && rtTemp.size() != 0){
			Object[] rt = new Object[rtTemp.size()];
			for (int i = 0; i < rtTemp.size(); i++) {
				rt[i] = rtTemp.get(i).obj;
			}
			return rt;
		}else{
			return null;
		}
	}
	
	
	public static List<Object[]> getexportlist(List<Object> list){
		List<Object[]> newlist = new ArrayList<Object[]>();
		for(Object staff:list){
			Object[] obj = createSingleContext(staff);
			newlist.add(obj);
		}
		return newlist;
	}
	
	private static List<SortObj> formatMultyObj(Object obj){
		Class clazz = null;
		if((obj.getClass().getSuperclass()).equals(BizEntity.class)){
			clazz = obj.getClass();
		}else{
			clazz = obj.getClass().getSuperclass();
		}
		Field[] fields = clazz.getDeclaredFields();
		List<SortObj> rtTemp = new ArrayList<SortObj>();
		Object afterValue = null;

		for(int i = 0; i < fields.length; i++) {
			Export export = fields[i].getAnnotation(Export.class);
			if(export == null || export.multyI18nKey().isEmpty()) 
				continue;
			List<String> constKeys = longToString( export.constKeys() );
			String[] constTexts = export.constTexts();
			String formatType = export.formatType();
			
			// formatType 取类的类型
			if(StringUtils.isBlank(formatType)) {
				formatType = fields[i].getType().getCanonicalName();
			}
			String objTemp = null;
			
			try {
				afterValue = PropertyUtils.getSimpleProperty(obj, fields[i].getName());
			} catch(Exception e) {
				throw new RuntimeException(e);
			}
			
			if(constKeys.isEmpty()){
				objTemp = formatData(afterValue, formatType);
				rtTemp.add(new SortObj(objTemp, export.multySort()));
			} else {
				objTemp = keyToText(afterValue, constKeys, constTexts);
				rtTemp.add(new SortObj(objTemp, export.multySort()));
			}
		}
		return rtTemp;
	}
	
	private static String formatData(Object obj, String formatType) {
		if(obj == null) {
			return "";
		}
		if (formatType.equals("java.lang.String")) {
			return (String) obj;
			
		} else if (formatType.equals("boolean") || formatType.equals("java.lang.Boolean")) {
			return String.valueOf(obj);
			
		} else if (formatType.equals("int") || formatType.equals("java.lang.Integer")) {
			return DoubleUtil.format(Double.valueOf((Integer) obj), 0);
			
		} else if (formatType.equals("short") || formatType.equals("java.lang.Short")) {
			return DoubleUtil.format(Double.valueOf((Short) obj), 0);

		} else if (formatType.equals("long") || formatType.equals("java.lang.Long")) {
			return DoubleUtil.format(Double.valueOf((Long) obj), 0);

		} else if (formatType.equals("double") || formatType.equals("java.lang.Double")) {
			return DoubleUtil.formatDouble((Double) obj);
			
		} else if (formatType.equals("float") || formatType.equals("java.lang.Float")){
			return DoubleUtil.formatDouble((Float) obj);

		} else if (formatType.equals("java.util.Date")) {
			return DateUtil.getSecondStr((Date)obj);
			
		} else if(formatType.equals("com.lmsoft.bolt.bao.domain.base.BizEntityId")) {
			return DoubleUtil.format(Double.valueOf(((BizEntityId)obj).getPid()), 0);
		} else if(Export.DATE_FORMAT_YYYY_MM_DD.equals(formatType)) {
			return DateUtil.getDayStr((Date)obj);
		} else if(Export.DATE_FORMAT_YYYY_MM_DD_HH24_MI_SS.equals(formatType)) {
			return DateUtil.getSecondStr((Date)obj);
		} else if(Export.EXPORT_ATTR_CLASS.equals(formatType)) {
			return ((ExportAttr)obj).expString();
		} else if(Export.EXPORT_DEPTS.equals(formatType)) {
			Set<PmBscStaffDept> list = (Set<PmBscStaffDept>)obj;
			if(list!=null && list.size()!=0){
				StringBuilder rt = new StringBuilder();
				for (PmBscStaffDept pmBscStaffDept : list) {
					rt.append(pmBscStaffDept.getPmBscDeptInfo().getVcDeptName()).append(",");
				}
				String rtTemp = rt.toString();
				return rtTemp.endsWith(",")? rtTemp.substring(0, rtTemp.length()-1):rtTemp;
			}else
				return "";
		}else{
			throw new IllegalArgumentException("无法解析的formatType=" + formatType);
		}
	}
	
	private static String keyToText(Object key, List<String> keys, String[] texts) {
		key = "" + key;
		String val = "";
		int idx = keys.indexOf(key);
		if(idx != -1) {
			val = texts[idx];
		}
		
		return val;
	}
	
	public static class SortObj{
		Object obj;
		int sort;
		public SortObj(Object obj, int sort) {
			super();
			this.obj = obj;
			this.sort = sort;
		}
	}
}
