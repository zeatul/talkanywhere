package com.hawk.pub.web;

import com.hawk.exception.BasicException;
import com.hawk.utility.JsonTools;

public class ExceptionResponse implements IResponse{
	
	public Exception getException() {
		return exception;
	}


	private Exception exception ;
	
	public ExceptionResponse(Exception exception){
		this.exception = exception;
	}
	
	
	

	public String toJson(){

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

		rtn.setCode(code);
		rtn.setData(message);
		String output = JsonTools.toJsonString(rtn);
		return output;
	}

}
