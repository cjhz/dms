package com.sunraysoft.hr.web.data.dto;

/**
 * Json封装类
 *
 * @param <T>
 */
public class JsonWrapper<T> {
	
	private boolean flag;
	private boolean timeout;
	private String msg;
	private T data;
	
	public JsonWrapper() {
		super();
	}
	public JsonWrapper(boolean timeout, boolean flag, String msg, T data) {
		super();
		this.timeout = timeout;
		this.flag = flag;
		this.msg = msg;
		this.data = data;
	}
	public JsonWrapper(boolean flag, String msg, T data) {
		super();
		this.timeout = false;
		this.flag = flag;
		this.msg = msg;
		this.data = data;
	}
	
	public boolean isTimeout() {
		return timeout;
	}
	public void setTimeout(boolean timeout) {
		this.timeout = timeout;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
