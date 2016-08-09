package com.sunraysoft.hr.web.action.base;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;
import com.sunraysoft.hr.constant.Constant;
import com.sunraysoft.hr.domain.dto.MenuDto;
import com.sunraysoft.hr.domain.dto.Node;
import com.sunraysoft.hr.domain.dto.UserDto;
import com.sunraysoft.hr.util.StringUtils;
import com.sunraysoft.hr.web.data.dto.JsonWrapper;
import com.sunraysoft.hr.web.data.dto.Page;

/**
 * 通用的Action, 是所有Action的基类
 *
 */
public class GenericAction extends ActionSupport implements
        ServletContextAware, ServletRequestAware {
private static final long serialVersionUID = -910552223482404843L;
	
	public static final String JSON_PAGE = "jsonPage";
	
	public static final String NEED_PERMISSION_PAGE = "needPermissionPage";
	
	public static final String LOGIN_PAGE = "loginPage";
	
	public static final String SEGMENT_PAGE = "segmentPage";
	
	protected static final String UNKNOWN_MSG = "服务器内部错误";
	
	private ServletContext servletContext;
    private HttpServletRequest httpServletRequest;

    private String jsonText; // jsonText用于向页面暴漏
    private boolean timeout = false; // timeout用于向页面暴漏
    private boolean needPermission = false; //needPermission用于向页面暴漏
    
    protected int pageNum = 1; // 请求的页码
    protected Page page; //分页类

    private String msg; //信息(成功或失败信息)
    private boolean success = true;
    
    private UserDto user; //当前用户
    
    private String theme = "default"; //系统风格
    
    private boolean showAdvancedQueryPanel = false;
    
    private String menuCode;
    
//////////////////////////get set method
    public String getJsonText() {
        return jsonText;
    }
    public void setJsonText(String jsonText) {
        this.jsonText = jsonText;
    }
    public void setJsonMsg(boolean flag, String msg, Object data) {
    	JsonWrapper wrapper = new JsonWrapper(flag, msg, data);
		setJsonText( JSONObject.fromObject(wrapper).toString() );
    }
    public void setJsonMsg() {
    	setJsonMsg(success, msg, null);
    }
    public void setJsonMsg(Object data) {
    	setJsonMsg(success, msg, data);
    }
    public boolean isTimeout() {
		return timeout;
	}
	public void setTimeout(boolean timeout) {
		this.timeout = timeout;
	}
	public boolean isNeedPermission() {
		return needPermission;
	}
	public void setNeedPermission(boolean needPermission) {
		this.needPermission = needPermission;
	}
	
	@Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
    public ServletContext getServletContext() {
		return servletContext;
	}

	@Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    public String getContextPath() {
        return httpServletRequest.getContextPath();
    }

	public HttpServletRequest getHttpServletRequest() {
		return httpServletRequest;
	}
	public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
		this.httpServletRequest = httpServletRequest;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = toUtf8Decode(msg);
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
	public int getPageSize() {
		return (Integer) getServletContext().getAttribute(Constant.DMS_PAGE_SIZE);
	}

	
	public void setUser(UserDto user) {
		httpServletRequest.getSession(true).setAttribute(Constant.SESSION_USER, user);
	}
	
	public UserDto getUser() {
		UserDto user = (UserDto) httpServletRequest.getSession(true).getAttribute(Constant.SESSION_USER);
		return user;
	}
	
	public String getAuthCode() {
		return (String) httpServletRequest.getSession(true).getAttribute(Constant.AUTH_CODE);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Node<MenuDto>> getMenuMap() {
		return (Map<String, Node<MenuDto>>) getHttpServletRequest().getSession().getAttribute(Constant.ALL_MENU_MAP);
	}
	
	public void setMenuMap(Map<String, Node<MenuDto>> menuMap) {
		getHttpServletRequest().getSession().setAttribute(Constant.ALL_MENU_MAP, menuMap);
	}
	
	public String getInnerMenu() {
		Node<MenuDto> e = null;
		if(menuCode == null || (e = getMenuMap().get(menuCode)).getData().getType() <= 5) {
			return null;
		}
		
		StringBuilder firstBuf = new StringBuilder();
		StringBuilder secondBuf = new StringBuilder();
		
		Node<MenuDto> parent = e;
		while(parent.getData().getType() > 5) {
			parent = parent.getParent(); 
		}
		
		boolean hasChild = false;
		for(int i = 0; i < parent.getChildren().size(); i++) {
			Node<MenuDto> node = parent.getChildren().get(i);
			if(node.hasChild()) {
				hasChild = true;
				break;
			}
		}
		
		String selected = "";
		if(hasChild) {
			firstBuf.append("<div class='iner_title_sub'><ul>");
			for(int i = 0; i < parent.getChildren().size(); i++) {
				Node<MenuDto> node = parent.getChildren().get(i);
				if(menuCode.startsWith(node.getData().getMemo())) { selected = "class='trigger_link' "; } 
				else { selected = ""; }
				
				firstBuf.append("<li code='" + i + "'><a href='" + getContextPath() + node.getData().getUrl() + "' " + selected + "><span>" + node.getData().getName() + "</span></a></li>");
				
				if(!menuCode.startsWith(node.getData().getMemo())) { secondBuf.append("<div id='menu_2_" + i + "' class='iner_title_sub_01' style='display:none;'><ul>"); } 
				else { secondBuf.append("<div id='menu_2_" + i + "' class='iner_title_sub_01 cur_menu' ><ul>"); }
				
				
				if(node.hasChild()) {
					
					for(int j = 0; j < node.getChildren().size(); j++) {
						Node<MenuDto> child = node.getChildren().get(j);
						
						if(menuCode.startsWith(child.getData().getMemo())) { selected = "class='trigger_link'"; } 
						else { selected = ""; }
						
						secondBuf.append("<li><a href='" + getContextPath() + child.getData().getUrl() + "' " + selected + ">" + child.getData().getName() + "</a></li>");
					}
				} else {
					if(menuCode.startsWith(node.getData().getMemo())) { selected = "class='trigger_link'"; } 
					else { selected = ""; }
					
					secondBuf.append("<li><a href='" + getContextPath() + node.getData().getUrl() + "' " + selected + ">" + node.getData().getName() +  "</a></li>");
				}
				
				secondBuf.append("</ul></div>");
			}
			firstBuf.append("</ul></div>");
		} else {
			selected = "class='trigger_link' ";
			firstBuf.append("<div class='iner_title_sub'><ul>");
			firstBuf.append("<li code='" + 1 + "'><a href='" + getContextPath() + parent.getData().getUrl() + "' " + selected + "><span>" + parent.getData().getName() + "</span></a></li>");
			firstBuf.append("</ul></div>");
			secondBuf.append("<div id='menu_2_" + 1 + "' class='iner_title_sub_01 cur_menu'><ul>");
			for(int i = 0; i < parent.getChildren().size(); i++) {
				Node<MenuDto> node = parent.getChildren().get(i);
				if(menuCode.startsWith(node.getData().getMemo())) { selected = "class='trigger_link'"; } 
				else { selected = ""; }
				
				secondBuf.append("<li><a href='" + getContextPath() + node.getData().getUrl() + "' " + selected + ">" + node.getData().getName() +  "</a></li>");
			}
			secondBuf.append("</ul></div>");
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(firstBuf);
		sb.append(secondBuf);
		
		return sb.toString();
	}
	
	@SuppressWarnings("unchecked")
	public Node<MenuDto> getStaffMenu() {
		return (Node<MenuDto>) httpServletRequest.getSession(true).getAttribute(Constant.STAFF_MENU);
	}
	public void setStaffMenu(Node<MenuDto> staffRoot) {
		httpServletRequest.getSession(true).setAttribute(Constant.STAFF_MENU, staffRoot);
	}
	
	@SuppressWarnings("unchecked")
	public Map<Long, Node<MenuDto>> getStaffMenuMap() {
		return (Map<Long, Node<MenuDto>>) httpServletRequest.getSession(true).getAttribute(Constant.STAFF_MENU_MAP);
	}
	public void setStaffMenuMap(Map<Long, Node<MenuDto>> map) {
		 httpServletRequest.getSession(true).setAttribute(Constant.STAFF_MENU_MAP, map);
	}
	
	public boolean isLogin() {
		return getUser() != null;
	}
	
	//public MappingConfig getMappingConfig() {
	//	return (MappingConfig) servletContext.getAttribute(Constant.MAPPING_CONFIG);
	//}
	//public void setMappingConfig(MappingConfig mappingConfig) {
	//	servletContext.setAttribute(Constant.MAPPING_CONFIG, mappingConfig);
	//}
	
	public void setFailMsg(String msg) {
		this.success = false;
		this.msg = msg;
		
	}
	public void setSuccessMsg(String msg) {
		this.success = true;
		this.msg = msg;
	}
	public String toUtf8Encode(String s) {
		if(s == null) return null;
		else return StringUtils.toUtf8Encode(s);
	}
	public String toUtf8Encode2(String s) {
		if(s == null) return null;
		else return StringUtils.toUtf8Encode2(s);
	}
	
	public String toUtf8Decode(String s) {
		if(s == null) return null;
		else return StringUtils.toUtf8Decode(s);
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getI18nJson() {
		ResourceBundle rb = getTexts("messageResource");
		Set<String> set = rb.keySet();
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for(String k : set) {
			sb.append("'" + k + "':");
			sb.append("'" + rb.getString(k) + "',");
		}
		
		sb.append("'xx':'xx'");
		sb.append("}");
		
		System.out.println(sb);
		
		return sb.toString();
	}
	public String getErrCodeMsg(int errCode) {
		ResourceBundle resource = getTexts("messageResource");
		String msg = null;
		try {
			msg = resource.getString("bolt.errcode." + errCode) + "(" + errCode + ")";
		} catch(Exception e) {
			msg = "未知代码的错误" + "(" + errCode + ")";
		}
		
		return msg;
	}
	
	
	//TODO ServerUid如何设置
	//public String getServerUid() {
	//	return BcConstant.SERVER_TYPE_ADMIN_ONLINE_SERVER + ":" + BoltSdkConfig.getInstance().getServerId();
	//}
	
	public String getVersion() {
	
		return (String) getServletContext().getAttribute(Constant.DMS_VERSION);
	}
	
	public boolean isShowAdvancedQueryPanel() {
		return showAdvancedQueryPanel;
	}
	public void setShowAdvancedQueryPanel(boolean showAdvancedQueryPanel) {
		this.showAdvancedQueryPanel = showAdvancedQueryPanel;
	}

	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public boolean showWarning() {
		
		String[] warnMenus = (String[]) getServletContext().getAttribute(Constant.DMS_WARN_MENUS);
		if(menuCode != null) {
			for(String code : warnMenus) {
				if(menuCode.startsWith(code)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * 会对输入的double 值原样输出
	 * @param d
	 * @return
	 */
	public String formatDouble(double d) {
		DecimalFormat df = new DecimalFormat("#.###################################");
		return df.format(d);
	}
}
