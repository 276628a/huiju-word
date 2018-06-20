package com.huiju.pms.myword.common;

import com.alibaba.fastjson.JSONObject;

public class Modal {
	

	public Modal() {
		super();
	}

	public Modal(int status, String msg) {
		super();
		this.status = status;	
		this.msg = msg;
	}

	protected int status;
	protected JSONObject params;

	
	protected String msg;

	public void setSysStatus(SysStatus sysStatus) {
		status=sysStatus.getStatus();
		msg=sysStatus.getMsg();		 
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public JSONObject getParams() {
		return params;
	}

	public void setParams(JSONObject params) {
		this.params = params;
	}

 
	
	
	
}
