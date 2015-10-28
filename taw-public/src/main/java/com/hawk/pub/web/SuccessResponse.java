package com.hawk.pub.web;

import com.hawk.utility.JsonTools;

public class SuccessResponse implements IResponse{
	
	public Object getResult() {
		return result;
	}

	public final static SuccessResponse SUCCESS_RESPONSE = new SuccessResponse(null);

	private Object result;
	
	protected SuccessResponse(Object value){
		this.result = value;
	}
	
	public static class OutPut{
		
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public Object getData() {
			return data;
		}
		public void setData(Object data) {
			this.data = data;
		}
		private String code;
		private Object data;
	}
	
	public String toJson(){

		OutPut rtn = new OutPut();
		rtn.setCode("1");
		rtn.setData(result);
		String output = JsonTools.toJsonString(rtn);
		return output;
	}
	

	
	public static SuccessResponse build(Object value){
		SuccessResponse response = new SuccessResponse(value);
		return response;
	}
	
}
