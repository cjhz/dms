package com.sunraysoft.hr.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

import com.sunraysoft.hr.annotation.OperLog;
import com.sunraysoft.hr.domain.base.BizEntityId;
import com.sunraysoft.hr.domain.entity.base.ExportAttr;



public class LogUtils {
	
	public final static String FORMAT_TYPE_DATE = "DATE";
	public final static String FORMAT_TYPE_MONEY = "MONEY";
	public final static String FORMAT_TYPE_PERCENT = "PERCENT";
	
	/**
	 * 记录日志
	 * @param logType
	 * @param before, 如果是新增，则设置before为null
	 * @param after
	 * @throws Exception
	 */
	public static String generateLogMsg(Object before, Object after) {
		if(after == null) { //不管是新增还是修改 after都必须有值
			throw new IllegalArgumentException("after is null");
		}
		
		if(before == null) { //新建
			return logCreate(after, new String[]{});
		} else { //修改
			return logUpdate(before, after, new String[]{});
		}	
	}
	
	public static String generateLogMsg(Object before, Object after, String[] excludeProps) {
		if(after == null) { //不管是新增还是修改 after都必须有值
			throw new IllegalArgumentException("after is null");
		}
		
		if(before == null) { //新建
			return logCreate(after, excludeProps);
		} else { //修改
			return logUpdate(before, after, excludeProps);
		}	
	}
	
	private static String logCreate(Object obj, String[] excludeProps) {
		Field[] fields = obj.getClass().getDeclaredFields();
		List<String> props = Arrays.asList(excludeProps);
		StringBuffer logs = new StringBuffer();
		Object afterValue = null;
		
		for(int i = 0; i < fields.length; i++) {
			if(props.indexOf( fields[i].getName() ) >= 0) { //包含在排除的字段列表之内 就跳过
				continue;
			}
			OperLog operLog = fields[i].getAnnotation(OperLog.class);
			if(operLog == null) 
				continue;
			
			try {
				afterValue = PropertyUtils.getSimpleProperty(obj, fields[i].getName());
			} catch(Exception e) {
				throw new RuntimeException(e);
			}
			
			String i18nKey = operLog.i18nKey();
			List<String> constKeys = longToString( operLog.constKeys() );
			String[] constTexts = operLog.constTexts();
			String formatType = operLog.formatType();
			String i18nName = i18nKey;
			
			if(StringUtils.isBlank(formatType)) {
				formatType = fields[i].getType().getCanonicalName();
			}
			
			if(afterValue instanceof BizEntityId) {
				afterValue = ((BizEntityId) afterValue).getId();
				formatType = "long";
			}
			
			if(constKeys.isEmpty()) { //表明值不是常量
				if((OperLog.EXPORT_HIDDEN_STRING).equals(formatType)){
					logs.append("<!--" + i18nName + ":" + formatData(afterValue, formatType) + ", -->");
				}else{
					logs.append(i18nName + ":" + formatData(afterValue, formatType) + ", ");
				}
			} else {
				logs.append(i18nName + ":" + keyToText(afterValue, constKeys, constTexts) + ", ");
			}
		}
		
		if(logs.toString().endsWith(", ")) {
			return logs.toString().substring(0, logs.length() - 2);
		} else {
			return logs.toString();
		}
	}
	
	private static String logUpdate(Object before, Object after, String[] excludeProps) {
		List<String> props = Arrays.asList(excludeProps);
		StringBuffer logs = new StringBuffer();
		Field[] fields = after.getClass().getDeclaredFields();
		
		Object beforeValue = null; 
		Object afterValue = null;
		
		for(int i = 0; i < fields.length; i++) {
			if(props.indexOf( fields[i].getName() ) >= 0) { //包含在排除的字段列表之内 就跳过
				continue;
			}
			OperLog operLog = fields[i].getAnnotation(OperLog.class);
			if(operLog == null) 
				continue;
			
			try {
				beforeValue = PropertyUtils.getSimpleProperty(before, fields[i].getName());
				afterValue = PropertyUtils.getSimpleProperty(after, fields[i].getName());
			} catch(Exception e) {
				throw new RuntimeException(e);
			}
			
			Class<?> fieldType = fields[i].getType();
			String i18nKey = operLog.i18nKey();
			List<String> constKeys = longToString( operLog.constKeys() );
			String[] constTexts = operLog.constTexts();
			String formatType = operLog.formatType();
			String i18nName = i18nKey; 
			String canonicalName = fields[i].getType().getCanonicalName();
			
			if(beforeValue instanceof String || afterValue instanceof String) {
				beforeValue = StringUtils.trimToNull((String) beforeValue);
				afterValue = StringUtils.trimToNull((String) afterValue);
			}
			
			if(beforeValue instanceof BizEntityId) {
				beforeValue = ((BizEntityId) beforeValue).getId();
				formatType = "long";
			}
			if(afterValue instanceof BizEntityId) {
				afterValue = ((BizEntityId) afterValue).getId();
				formatType = "long";
			}
			
			if((beforeValue == null && afterValue == null) || !compareValue(beforeValue, afterValue, canonicalName, formatType))  {
				continue;
			}
			
			if(StringUtils.isBlank(formatType)) {
				formatType = fields[i].getType().getCanonicalName();
			}
			
			if(constKeys.isEmpty()) {//表明值不是常量
				if((OperLog.EXPORT_HIDDEN_STRING).equals(formatType)){
					logs.append("<!--" + i18nName + ":" + formatData(beforeValue, formatType) + "->" + formatData(afterValue, formatType) + ", -->");
				}else{
					logs.append(i18nName + ":" + formatData(beforeValue, formatType) + "-->" + formatData(afterValue, formatType) + ", ");
				}			} else {
				logs.append("["+i18nName + "]:" + keyToText(beforeValue, constKeys, constTexts) + "-->" + keyToText(afterValue, constKeys, constTexts) + ", ");
			}
		}
		
		if(logs.toString().endsWith(", ")) {
			return logs.toString().substring(0, logs.length() - 2);
		} else {
			return logs.toString();
		}
		
	}
	
	public static String changeToString(Object beforeValue, Object afterValue, String formatType, 
			String i18nName, long[] keys, String[] constTexts) {
		StringBuilder sb = new StringBuilder();
		List<String> constKeys = longToString(keys);
		
		if(beforeValue instanceof String || afterValue instanceof String) {
			beforeValue = StringUtils.trimToNull((String) beforeValue);
			afterValue = StringUtils.trimToNull((String) afterValue);
		}
		
		if((beforeValue == null && afterValue == null) || !compareValue(beforeValue, afterValue, formatType, formatType))  {
			return sb.toString();
		}
		
		if(formatType.equals("java.util.Date")) {
			if(beforeValue != null) {
				beforeValue = DateUtil.getSecondStr((Date) beforeValue);
			}
			if(afterValue != null) {
				afterValue = DateUtil.getSecondStr((Date) afterValue);
			}
		}
		if(beforeValue instanceof BizEntityId) {
			beforeValue = ((BizEntityId) beforeValue).getId();
		}
		if(afterValue instanceof BizEntityId) {
			afterValue = ((BizEntityId) afterValue).getId();
		}
		if(beforeValue == null) { beforeValue = ""; }
		if(afterValue == null) { afterValue = ""; }
		
		if(constKeys.isEmpty()) {//表明值不是常量
			if((OperLog.EXPORT_HIDDEN_STRING).equals(formatType)){
				sb.append("<!--" + i18nName + ":" + formatData(beforeValue, formatType) + "->" + formatData(afterValue, formatType) + ", -->");
			}else{
				sb.append(i18nName + ":" + formatData(beforeValue, formatType) + "-->" + formatData(afterValue, formatType) + ", ");
			}
		} else {
			sb.append(i18nName + ":" + keyToText(beforeValue, constKeys, constTexts) + "-->" + keyToText(afterValue, constKeys, constTexts) + ", ");
		}
		
		if(sb.toString().endsWith(", ")) {
			return sb.toString().substring(0, sb.length() - 2);
		} else {
			return sb.toString();
		} 
	}


	public static boolean compareValue(Object beforeValue, Object afterValue, String typeName, String formatType) {
		boolean isChange = false;
		
		if(beforeValue == null && afterValue != null) {
			isChange = true; 
		} else if(beforeValue != null && afterValue == null) {
			isChange = true;
		} if(beforeValue == null && afterValue != null) {
			isChange = true;
		}else if(beforeValue != null && afterValue == null) {
			isChange = true;
		} else {
			isChange = compareValue(beforeValue, afterValue, typeName, isChange, formatType);
		}
		return isChange;
	}

	private static boolean compareValue(Object beforeValue, Object afterValue, String typeName, boolean isChange, String formatType) {
		if(OperLog.EXPORT_ATTR_CLASS.equals(formatType)) {
			isChange = !(((ExportAttr)beforeValue).expString() .equals(((ExportAttr)afterValue).expString()));
			return isChange;
		}
		
		if (typeName.equals("java.lang.String")) {
			if(!beforeValue.equals(afterValue)) {
				isChange = true;
			}
			
		} else if (typeName.equals("boolean") || typeName.equals("java.lang.Boolean")) {
			if((!(Boolean)beforeValue && (Boolean)afterValue) || ((Boolean)beforeValue && !(Boolean)afterValue)) {
				isChange=true;
			}
			
		} else if (typeName.equals("int") || typeName.equals("java.lang.Integer")) {
			if((Integer)beforeValue - (Integer)afterValue != 0) {
				isChange = true;
			}
			
		} else if (typeName.equals("short") || typeName.equals("java.lang.Short")) {
			if((Short)beforeValue - (Short)afterValue != 0) {
				isChange = true;
			}

		} else if (typeName.equals("long") || typeName.equals("java.lang.Long")) {
			if((Long)beforeValue - (Long)afterValue != 0L) {
				isChange = true;
			}

		} else if (typeName.equals("double") || typeName.equals("java.lang.Double")) {
			if((Double)beforeValue - (Double)afterValue != 0D) {
				isChange = true;
			}
			
		} else if (typeName.equals("float") || typeName.equals("java.lang.Float")){
			if((Float)beforeValue - (Float)afterValue != 0F) {
				isChange = true;
			}

		} else if (typeName.equals("java.util.Date")) {
			String beforeTime = DateUtil.getSecondStr((Date)beforeValue);
			String afterTime = DateUtil.getSecondStr((Date)afterValue);
			if(!beforeTime.equals(afterTime)) {
				isChange = true;
			}
		} else if(typeName.equals("com.lmsoft.bolt.bao.domain.base.BizEntityId")) { // 其他实体，比较ID是否一样
			if(((BizEntityId)beforeValue).getId() - ((BizEntityId)afterValue).getId() != 0L) {
				isChange = true;
			}
		} 
		return isChange;
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
	
	private static String keyToText(Object key, List<String> keys, String[] texts) {
		key = "" + key;
		String val = "";
		int idx = keys.indexOf(key);
		if(idx != -1) {
			val = texts[idx];
		}
		
		return val;
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
			return DoubleUtil.format(Double.valueOf(((BizEntityId)obj).getId()), 0);
		} else if(formatType.equals(FORMAT_TYPE_MONEY)) {
			double d = 0;
			if (obj instanceof Integer) {
				d = Double.valueOf((Integer) obj);
				
			} else if (obj instanceof Short) {
				d = Double.valueOf((Short) obj);

			} else if (obj instanceof Long) {
				d = Double.valueOf((Long) obj);

			} else if (obj instanceof Double) {
				d = (Double) obj;
				
			} else if (obj instanceof Float) {
				d = Double.valueOf((Float) obj);
			} else {
				throw new IllegalArgumentException("错误的数据类型=" + obj.getClass());
			}
			
			return DoubleUtil.formatMoney(DoubleUtil.divide(d, 100.0), 2);
		}  else if(OperLog.EXPORT_ATTR_CLASS.equals(formatType)) {
			return ((ExportAttr)obj).expString();
		} else if(OperLog.EXPORT_HIDDEN_STRING.equals(formatType)) {
			return (String)obj;
		} else if(OperLog.DATE_FORMAT_YYYY_MM_DD.equals(formatType)) {
			return DateUtil.getDayStr((Date)obj);
		} else if(OperLog.DATE_FORMAT_YYYY_MM_DD_HH24_MI_SS.equals(formatType)) {
			return DateUtil.getSecondStr((Date)obj);
		} else if(formatType.equals(FORMAT_TYPE_PERCENT)) {
			double d = 0;
			if (obj instanceof Integer) {
				d = Double.valueOf((Integer) obj);
				
			} else if (obj instanceof Short) {
				d = Double.valueOf((Short) obj);

			} else if (obj instanceof Long) {
				d = Double.valueOf((Long) obj);

			} else if (obj instanceof Double) {
				d = (Double) obj;
				
			} else if (obj instanceof Float) {
				d = Double.valueOf((Float) obj);
			} else {
				throw new IllegalArgumentException("错误的数据类型=" + obj.getClass());
			}
			
			return DoubleUtil.formatDouble(DoubleUtil.multiply(d, 100)) + '%';
		} else {
			throw new IllegalArgumentException("无法解析的formatType=" + formatType);
		}
	}
}
