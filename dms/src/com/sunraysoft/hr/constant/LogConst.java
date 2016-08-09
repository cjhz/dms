
package com.sunraysoft.hr.constant;

/**
 * 日志相关的常量
 * 在记日志的时候如果使用了 !BOLT_LOG_XXX样的格式, 日志服务器会自动做多语言的处理，进行按多语言进行
 * @author chenjh
 * 2012-08-01
 * 
 */
public class LogConst {
	public static final long LOG_TYPE_LOGIN = 1;	// 日志类型: 登陆
	public static final long LOG_TYPE_STAFF_UPDATE = 2;	// 日志类型: 人员信息修改
	public static final long LOG_TYPE_DEPT_UPDATE = 3;	// 日志类型: 机构修改
	public static final long LOG_TYPE_UPDATE = 4;    // 日志类型: 设置
}
