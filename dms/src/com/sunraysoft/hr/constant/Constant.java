package com.sunraysoft.hr.constant;

public class Constant {

	/**session中的验证码 key*/
	public static final String AUTH_CODE = "com.sunraysoft.hr.web.data.dto.AuthCode";
	/**系统中的未删除标志*/
	public static final int SYSTEM_DATA_DELETE_FLAG_NORMAL=0;
	/**session中的当前用户 key*/
	public static final String SESSION_USER = "com.sunraysoft.hr.domain.dto.UserDto"; 
	
	public static final String ALL_MENU_MAP="com.sunraysoft.hr.domain.dto.menuMap";
	/**Session中的Staff的菜单 key*/
	public static final String STAFF_MENU = "com.sunraysoft.hr.domain.dto.StaffMenu";
	
	/**Session中的Staff的菜单Map key*/
	public static final String STAFF_MENU_MAP = "com.sunraysoft.hr.web.data.dto.StaffMenuMap";
	
	public static final String MAPPING_CONFIG = "com.sunraysoft.hr.MappingConfig";
	
	public static final String ACTION_SUFFIX = ".action";
	public static final String EXPORT_SUFFIX = ".xls";
	
	
	public static final String DMS_CONFIG_PROPERTIES = "config.properties";
	
	public static final String DMS_VERSION = "dms.version";
	public static final String DMS_PAGE_SIZE = "dms.pageSize";
	public static final String DMS_WARN_MENUS = "dms.warnMenus";
	
	public static final int SEQUENCE_START_WITH = 100;
	
	public static final String MENU_TEXT_1 = "机构信息管理";
	public static final String MENU_TEXT_2 = "人员信息管理";
	public static final String MENU_TEXT_3 = "统计分析";
	public static final String MENU_TEXT_4 = "系统设置";
	public static final String MENU_TEXT_5 = "";
	public static final String MENU_TEXT_6 = "";
}
