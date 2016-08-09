package com.sunraysoft.hr.web.action.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sunraysoft.hr.constant.Constant;
import com.sunraysoft.hr.web.action.base.BaseAction;

/**
 * @msg.读取页面导出方式 统计时非常适用
 * @author chenjh
 * @version 2012-08-21
 */
@Controller
@Scope("prototype")
@Namespace(value = "/common/export")
public class ExportAction  extends BaseAction {
	private static final Log LOG = LogFactory.getLog(ExportAction.class);
	
	String filename;
	String body;
	@Action(EXPORT)
	public String export() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();  
        HttpServletResponse response = ServletActionContext.getResponse(); 
		response.setContentType("application/vnd.ms-excel");
		request.setCharacterEncoding("GBK");
		response.setContentType("text/html;charset=GBK");
		response.setHeader("Content-Disposition", "attachment;filename=" + new String((filename+Constant.EXPORT_SUFFIX).getBytes("GBK"), "ISO-8859-1" ));
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires",0);
		setJsonText(body);
		return EXPORT;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
}
