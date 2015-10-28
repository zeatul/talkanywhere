package com.hawk.pub.web;

import com.hawk.pub.exception.BasicException;
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
		
		
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getErrMsg() {
			return errMsg;
		}
		public void setErrMsg(String errMsg) {
			this.errMsg = errMsg;
		}
		private String code;
		private String errMsg;
	}
	

	public String toJson(){
//		Map<String,Object> rtn = new HashMap<String,Object>();
		OutPut rtn = new OutPut();
		String code = "";
		String message = exception.getMessage();
		if (exception instanceof BasicException){
			code = ((BasicException)exception).getErrCode();
			message = ((BasicException)exception).getErrmsg();
		}
		else{
			code = "-1";
		}

		rtn.setRetCode(code);
		rtn.setMessage(message);
		String output = JsonTools.toJsonString(rtn);
		return output;
	}

}
