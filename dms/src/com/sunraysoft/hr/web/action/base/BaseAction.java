package com.sunraysoft.hr.web.action.base;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sunraysoft.hr.constant.BizConstant;
import com.sunraysoft.hr.domain.entity.manage.PmMngServerLog;
import com.sunraysoft.hr.service.log.PmMngServerLogService;

public class BaseAction extends GenericAction {
	
	private static final Log log = LogFactory.getLog(BaseAction.class);
	
	private static final long serialVersionUID = 1L;
	protected static final String PERSIST = "persist";
	protected static final String FORM = "form";
	protected static final String ADD = "add";
	protected static final String EDIT = "edit";
	protected static final String EDITCONTROL = "editcontrol";
	protected static final String LIST = "list";
	protected static final String CREATE = "create";
	protected static final String UPDATE = "update";
	protected static final String UPDATECONTROL = "updatecontrol";
	protected static final String DESTORY = "destory";
	protected static final String PAGE_QUERY = "pageQuery";
	protected static final String REDIRECT_PAGE_QUERY = "redirectPageQuery";
	protected static final String REDIRECT_FORM = "redirectForm";
	protected static final String SHOW = "show";
	protected static final String DIALOG = "dialog";
	protected static final String EXPORT = "export";
	protected static final int LOG_MSG_MAX_LENGTH = 700;
	
	@Resource private PmMngServerLogService pmMngServerLogService;
//
//	@Override
//	public String execute() throws Exception {
//		super.execute();
//		
//		return LIST;	
//	}
//	
//	
//	public ServerLogService getServerLogService() {
//		return serverLogService;
//	}
	
	protected void persistServerLog(long logType, String logMsg, Long dataid) {
		PmMngServerLog serverLog = new PmMngServerLog();
		serverLog.setLogType( logType );
		serverLog.setLogMsg( logMsg );
		serverLog.setVcDeleteFlag( Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL) );
		//serverLog.setOperType( Long.valueOf(BizConstant.MARKET_STUCTURE_STAFF) );
		serverLog.setOperId( getUser().getId() );
		serverLog.setOperIp( getUser().getIp() );
		//serverLog.setOperServerUid( getServerUid() );
		serverLog.setCreatedOn(new Date());
		serverLog.setVcDataPid(dataid);
		serverLog.setVcExtend1( getUser().getRealName());
		substring(serverLog);
		
		pmMngServerLogService.save( serverLog );
	}
	
	private void substring(PmMngServerLog serverLog) {
		if(serverLog.getLogMsg().length() > LOG_MSG_MAX_LENGTH) {
			StringBuilder sb = new StringBuilder();
			sb.append("日志过长 ");
			sb.append("logType:" + serverLog.getLogType() + ";");
			sb.append("operType:" + serverLog.getOperType());
			sb.append("operId:" + formatDouble(serverLog.getOperId()) + ";");
			sb.append("operIp:" + serverLog.getOperIp());
			//sb.append("operServerUid:" + getServerUid());
			sb.append("createdOn:" + DateFormatUtils.format(serverLog.getCreatedOn(), "yyyy-MM-dd HH:mm:ss"));
			
			log.warn(sb.toString());
			
			serverLog.setLogMsg( serverLog.getLogMsg().substring(0, LOG_MSG_MAX_LENGTH) );
		} 
	}
}
