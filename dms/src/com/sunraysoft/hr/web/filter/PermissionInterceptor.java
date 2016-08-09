package com.sunraysoft.hr.web.filter;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;
import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sunraysoft.hr.annotation.OperCode;
import com.sunraysoft.hr.constant.Constant;
import com.sunraysoft.hr.domain.dto.UserDto;
import com.sunraysoft.hr.web.action.base.GenericAction;

public class PermissionInterceptor extends AbstractInterceptor {
	
	final static String EMPTY_OPER_CODE = "empty_oper_code";
	

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest httpServletRequest= (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		GenericAction genericAction = (GenericAction) invocation.getAction();
		genericAction.setHttpServletRequest(httpServletRequest);
		UserDto user = genericAction.getUser();
		
		if(user == null) {
			return invocation.invoke();
		} else {
			String uri = httpServletRequest.getRequestURI();
			int beginIndex = uri.lastIndexOf("/");
			int endIndex = uri.lastIndexOf( Constant.ACTION_SUFFIX );
			String actionName = uri.substring(beginIndex + 1, endIndex);
			
			Map<String, String> pair = parseAnnotation(invocation);
			String operCode = pair.get(actionName);
			
			if(!operCode.equals(EMPTY_OPER_CODE)) { //是有CODE的
				genericAction.setMenuCode( operCode.substring(0, operCode.length() - 2) ); //menuCode
			}
			
			System.out.println("actionName = " + actionName);
			System.out.println("operCode = " + operCode);
			
			
			if(operCode != null && (EMPTY_OPER_CODE.equals(operCode) || user.hasPermission(operCode))) { //pass
				return invocation.invoke();
			} else { // not pass
				if(actionName.startsWith("ajax")) { //ajax请求
					genericAction.setJsonText("{timeout: false, flag: false, msg: '权限不足', data: null}");
					return GenericAction.JSON_PAGE;
				} else if(actionName.startsWith("segment")) {
					genericAction.setNeedPermission(true);
					return GenericAction.SEGMENT_PAGE;
				} else { //html请求
					return GenericAction.NEED_PERMISSION_PAGE;
				}
			}
		}
		
		
	}
	
	public Map<String, String> parseAnnotation(ActionInvocation invocation) {
		Map<String, String> pair = new HashMap<String, String>();
		Method[] methods = invocation.getAction().getClass().getMethods();
		String url = null, operCode = null;
		
		for(Method method : methods) {
			if(method.getAnnotations() != null) {
				
				url = null; 
				operCode = null;
								
				Action action = method.getAnnotation(Action.class);
				OperCode code = method.getAnnotation(OperCode.class);
				url = action != null && action.value() != null ? action.value() : null;
				operCode = code != null && code.code() != null ? code.code() : EMPTY_OPER_CODE;
				if(url != null) {
					pair.put(url, operCode);
				}
			}
		}
		
		return pair;
	}
}
