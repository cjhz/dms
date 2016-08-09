/**
 * 
 */
package com.sunraysoft.hr.web.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sunraysoft.hr.exceptions.BaoSystemException;

/**
 * 异常统一处理
 * @author defier.lai
 * 2010-3-10 上午10:35:56
 * @version 1.0
 */
public class ExceptionInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;
	private static final Log LOG = LogFactory.getLog(ExceptionInterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String result = ""; 
		try {
			result = invocation.invoke();
		} catch (Exception ex) {
			LOG.error("被捕获的系统异常", ex);
			throw new BaoSystemException(ex, "程序内部错误，操作失败，");
		}
		return result;
	}

}
