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
 * 操作日志注解，如果实体变量中有该注解，则需要记录日志
 * @author defier.lai
 * 2010-3-15 下午02:01:12
 * @version 1.0
 */
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperLog {
	public static final String EXPORT_ATTR_CLASS = "EXPORT_ATTR_CLASS";
	public static final String EXPORT_HIDDEN_STRING = "EXPORT_HIDDEN_STRING";
	public static final String DATE_FORMAT_YYYY_MM_DD = "DATE_FORMAT_YYYY_MM_DD";
	public static final String DATE_FORMAT_YYYY_MM_DD_HH24_MI_SS = "DATE_FORMAT_YYYY_MM_DD_HH24_MI_SS";

	public String i18nKey() default "";
	public long[] constKeys() default {};
	public String[] constTexts() default {};
	public String formatType() default "";
}
