package com.huiju.pms.myword.common;

public enum SysStatus {

	SUCCESS(200, "成功"), 
	ALERT(201, "提示返回结果"), 
	INVALID_AUTH(402, "非法授权"), 
	INVALID_SIGN(403, "签名失败"), 
	INVALID_SESSION(407, "会话失效"), 
	ERROR(500, "错误"),  
	ERROR_CUSTOM(501, ""),
	
	
	SMS_ERROR(600, "发送短信失败"),  
	PARAM_ERROR(700, "请求参数错误"), 
	PARAM_NOUNIQUE(7001, " 参数不唯一"), 
	PARAM_NOTNULL(7002,"缺少请求参数或参数为空"), 
	PARAM_FORMAT(7006, "参数值格式不正确"),
	PARAM_NOFOUND(7007, "找不到该参数对应值"),	
 
 	
 
	IMAGE_EXTENSION_ERROR(8000, "图片后缀名不符合规范"),
	IMAGE_CAPACITY_ERROR(8001, "图片容量超出限制"),
	
	
	PWD(709, "密码错误"), 
	PWD_ISNULL(7091, "密码为空"),
	PWD_INVALID(7092, "账号或密码不正确"),
	PWD_EQUAL(7093, "和原密码相同"),	
	CODE_INVALID(707, "验证码不正确");


	
 

	 

	private SysStatus(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	private int status;

	private String msg;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public SysStatus setMsg(String msg) {
		 
		this.msg = msg;
		return this;
	}

	public SysStatus appendMsg(String msg) {
		this.msg = this.msg+","+msg;
		return this;
	}

}