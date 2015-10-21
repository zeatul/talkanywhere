package com.hawk.web.api;

import com.hawk.utility.JsonTools;



public class ExceptionResponse implements IResponse{
	
	public Exception getException() {
		return exception;
	}


	private Exception exception ;
	
	public ExceptionResponse(Exception exception){
		this.exception = exception;
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
//		Map<String,Object> rtn = new HashMap<String,Object>();
		OutPut rtn = new OutPut();
		
		int code = -1;
		
		String errmsg = "未知错误";
		
		rtn.setCode(code);
		rtn.setErrmsg(errmsg);
		String output = JsonTools.toJsonString(rtn);
		return output;
	}

}
