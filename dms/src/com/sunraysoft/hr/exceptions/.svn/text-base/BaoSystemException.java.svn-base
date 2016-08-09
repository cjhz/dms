/**
 * 
 */
package com.sunraysoft.hr.exceptions;

/**
 * 系统异常处理
 * @author defier.lai 
 * 2010-3-10 上午10:40:34
 * @version 1.0
 */
public class BaoSystemException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BaoSystemException(String frdMessage) {
		super(createErrMsg(frdMessage));
	}

	public BaoSystemException(Throwable throwable) {
		super(throwable);
	}

	public BaoSystemException(Throwable throwable, String frdMessage) {
		super(createErrMsg(frdMessage), throwable);
	}

	private static String createErrMsg(String msgBody) {
		String prefixStr = "抱歉，";
		String suffixStr = " 请稍后再试或与管理员联系！";
		StringBuffer errMsg = new StringBuffer("");
		errMsg.append(prefixStr);
		errMsg.append(msgBody);
		errMsg.append(suffixStr);
		return errMsg.toString();
	}

}
