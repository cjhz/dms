package com.sunraysoft.hr.web.listener;

import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.StringUtils;

import com.sunraysoft.hr.constant.Constant;


public class ApplicationListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent evt) {
		//BoltSdkBiz boltSdkBiz = new BoltGryphonSdk().createSdk();
		//evt.getServletContext().setAttribute(Constant.DMS_SDK_BIZ, boltSdkBiz);
		
		loadConifg(evt);
	}
	
	private void loadConifg(ServletContextEvent evt) {
		try {
			InputStream inputStream = getClass().getResourceAsStream("/" + Constant.DMS_CONFIG_PROPERTIES);
			Properties props = new Properties();
			props.load(inputStream);
			
			evt.getServletContext().setAttribute(Constant.DMS_CONFIG_PROPERTIES, props);
			parseParam(evt.getServletContext(), props);
			
		} catch(Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	private void parseParam(ServletContext servletContext, Properties props) {
		String version = props.getProperty(Constant.DMS_VERSION);
		if(StringUtils.isBlank(version)) {
			throw new IllegalArgumentException("config is null or empty");
		}
		servletContext.setAttribute(Constant.DMS_VERSION, version);
		
		//String bizIdStr = props.getProperty(Constant.DMS_BIZ_ID);
		//Long bizId = Long.parseLong(bizIdStr);
		//servletContext.setAttribute(Constant.DMS_BIZ_ID, bizId);
		
		String pageSizeStr = props.getProperty(Constant.DMS_PAGE_SIZE);
		Integer pageSize = Integer.parseInt(pageSizeStr);
		servletContext.setAttribute(Constant.DMS_PAGE_SIZE, pageSize);
		
		if(props.containsKey(Constant.DMS_WARN_MENUS)) {
			String warnMenus = props.getProperty(Constant.DMS_WARN_MENUS);
			String[] menus = warnMenus.split("\\,");
			servletContext.setAttribute(Constant.DMS_WARN_MENUS, menus);
		}
	}
}
