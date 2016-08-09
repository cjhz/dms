package com.sunraysoft.hr.web.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sunraysoft.hr.domain.dto.UserDto;
import com.sunraysoft.hr.web.action.base.GenericAction;

public class LogonInterceptor extends AbstractInterceptor {
	
	private static final long serialVersionUID = 1L;

	static final String LOGIN_PAGE_URI = "/common/loginPage.action";
	
	/**不需要登录就可以访问的URI*/
	static Set<String> noNeedLoginUriSet = new HashSet<String>();

	@Override
	public void init() {
		super.init();
		
		noNeedLoginUriSet.add( "/common/ajaxLogin.action" );
		noNeedLoginUriSet.add( "/common/logout.action" );
		noNeedLoginUriSet.add( LOGIN_PAGE_URI );
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = invocation.getInvocationContext();
		
		HttpServletRequest httpRequest = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		GenericAction genericAction = (GenericAction) invocation.getAction();
		genericAction.setHttpServletRequest(httpRequest);
		UserDto user = genericAction.getUser();
		
		String uri = parseUri( httpRequest );
		
		System.out.println(uri);
		
		if(isNeedLogin(uri) && user == null) {
			return forwarePage(httpRequest, genericAction, uri);
		}  else {
			return invocation.invoke();
		}
	}
	
	private String parseUri(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		String uri = request.getRequestURI();
		if(!contextPath.equals("/")) {
			uri = uri.substring(contextPath.length());
		}
		
		return uri;
	}
	
	private boolean isNeedLogin(String uri) {
		if(uri == null) {
			return true;
		} 
		return !noNeedLoginUriSet.contains(uri);
	}
	
	private String forwarePage(HttpServletRequest httpRequest, GenericAction genericAction, String uri) throws IOException, ServletException {
		if(uri == null || (uri.indexOf("ajax") == -1 && uri.indexOf("segment") == -1)) { 
			return genericAction.LOGIN_PAGE;
		} else if(uri != null && uri.indexOf("segment") != -1) { //请求部分的freemarker页面
			genericAction.setTimeout(true);
			return genericAction.SEGMENT_PAGE;
		} else if(uri != null && uri.indexOf("ajax") != -1) { //ajax
			genericAction.setJsonText("{timeout: true, flag: false, msg: null, data: null}");
			return genericAction.JSON_PAGE;
		} else {
			return genericAction.LOGIN_PAGE;
		}
	}
}
