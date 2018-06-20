package com.huiju.pms.myword.common;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;


public class ResponseModal extends Modal implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public ResponseModal(){
		super();
		result=new JSONObject();
	}
	
	public ResponseModal(int code,String message){
		super(code,message);
	}
	
	public ResponseModal(SysStatus sysStatus){
		super(sysStatus.getStatus(),sysStatus.getMsg());
	}
	
	public ResponseModal(int code,String message,Object obj){
		super(code,message);
		this.result = obj;
	}
	
	private Object result;

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

    public static ResponseModal occurException(Integer status, String msg,Object result) {
        return new ResponseModal(status, msg,result);
    }
    
    public static ResponseModal occurException(ResponseModal rm) {
        return rm;
    }
    
    @JSONField(serialize=false) 
    public boolean isSuccess(){
    	return (this.getStatus()==SysStatus.SUCCESS.getStatus());
    }
    
    
    public JSONObject toJSONObject(){
    	JSONObject json=new JSONObject();
    	json.put("status",this.getStatus());
    	json.put("msg",this.getMsg());
    	json.put("result", result);
    	return json;
    }
	
}
