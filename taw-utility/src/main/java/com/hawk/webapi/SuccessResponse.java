package com.hawk.webapi;

import com.hawk.utility.JsonTools;



public class SuccessResponse implements IResponse{
	
	public Object getResult() {
		return result;
	}

	public final static SuccessResponse SUCCESS_RESPONSE = new SuccessResponse(null);

	private Object result;
	@SuppressWarnings("unused")
	private Integer size;
	
	protected SuccessResponse(Object value){
		this.result = value;
	}
	
	protected SuccessResponse(Object value,Integer size){
		this.result = value;
		this.size = size;
	}
	
	public static class OutPut{
		public String getOpenid() {
			return openid;
		}
		public void setOpenid(String openid) {
			this.openid = openid;
		}
		public String getPassport() {
			return passport;
		}
		public void setPassport(String passport) {
			this.passport = passport;
		}
		public int getCode() {
			return code;
		}
		public void setCode(int code) {
			this.code = code;
		}
		public String getErrmsg() {
			return errmsg;
		}
		public void setErrmsg(String errmsg) {
			this.errmsg = errmsg;
		}
		public Integer getFlag() {
			return flag;
		}
		public void setFlag(Integer flag) {
			this.flag = flag;
		}
		public Object getObj() {
			return obj;
		}
		public void setObj(Object obj) {
			this.obj = obj;
		}
		private String openid;
		private String passport;
		private int code = 0;
		private String errmsg = "";
		private Integer flag = null;
		private Object obj = Boolean.TRUE;
	}
	
	public String toJson(){

		OutPut rtn = new OutPut();
		if (result!=null)
		rtn.setObj(result);
		String output = JsonTools.toJsonString(rtn);
		return output;
	}
	

	
	public static SuccessResponse buildResponse(Object value){
		SuccessResponse response = new SuccessResponse(value);
		return response;
	}
	
	public static SuccessResponse buildResponse(Object value,Integer size){
		SuccessResponse response = new SuccessResponse(value,size);
		return response;
	}
	
}
