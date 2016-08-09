/**
 * 
 */
package com.sunraysoft.hr.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 导出xls注解，如果实体变量中有该注解，则需要导出xls
 * @author Jerry Zhang
 * 2010-3-15 下午02:01:12
 * @version 1.0
 */
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Export {
	public static final String DATE_FORMAT_YYYY_MM_DD = "DATE_FORMAT_YYYY_MM_DD";
	public static final String DATE_FORMAT_YYYY_MM_DD_HH24_MI_SS = "DATE_FORMAT_YYYY_MM_DD_HH24_MI_SS";
	/**
	 * 导出的属性类, 需要实现ExportAttr
	 */
	public static final String EXPORT_ATTR_CLASS = "EXPORT_ATTR_CLASS";
	/**
	 * 导出的机构信息
	 */
	public static final String EXPORT_DEPTS = "EXPORT_DEPTS";
	/**
	 * 导出多属性
	 */
	public static final String EXPORT_MULTY_FILED = "EXPORT_MULTY_FILED";
	
	/**
	 * 导出的列头
	 * @author Jerry Zhang
	 * 2012-8-22 下午01:13:27
	 */
	public String i18nKey() default "";
	public long[] constKeys() default {};
	public String[] constTexts() default {};
	/**
	 * 格式化类型，默认都会按不同类型进行不同的格式化，碰到date 或是double的时候可能会用到
	 * 如果Date要用时间类型转换，则使用
	 * @author Jerry Zhang
	 * 2012-8-22 下午01:03:57
	 */
	public String formatType() default "";
	/**
	 * 排序字段, 默认值为100, 如果想要放在其它值前面，请使用100以下的. 如果要放到最后面，请使用100以上的
	 * @author Jerry Zhang
	 * 2012-8-22 下午01:08:43
	 */
	public int sort() default 100;
	/**
	 * 导出的列宽，默认值为25, 如果需要调整请自行加此段。
	 * @author Jerry Zhang
	 * 2012-8-22 下午01:12:35
	 */
	public int width() default 20;
	
	/////////////////////////////////以下为多属性导出使用/////////////////
	public String multyI18nKey() default "";
	/**
	 * 排序字段, 默认值为100, 如果想要放在其它值前面，请使用100以下的. 如果要放到最后面，请使用100以上的
	 * @author Jerry Zhang
	 * 2012-8-22 下午01:08:43
	 */
	public int multySort() default 100;
	/**
	 * 导出的列宽，默认值为25, 如果需要调整请自行加此段。
	 * @author Jerry Zhang
	 * 2012-8-22 下午01:12:35
	 */
	public int multyWidth() default 20;
}