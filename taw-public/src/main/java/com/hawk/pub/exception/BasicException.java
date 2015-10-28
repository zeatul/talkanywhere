package com.hawk.pub.exception;

public class BasicException extends Exception{

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3557916784812655780L;
	
	
	
	private String errCode;
	
	private String errmsg;

	public BasicException(String errCode, String errmsg) {
		super();
		this.errCode = errCode;
		this.errmsg = errmsg;
	}

	public BasicException(String errCode) {
		super();
		this.errCode = errCode;
	}
	
	
}
