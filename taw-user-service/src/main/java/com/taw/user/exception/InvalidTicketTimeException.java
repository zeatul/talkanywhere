package com.taw.user.exception;

import com.hawk.exception.BasicException;

public class InvalidTicketTimeException extends BasicException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1731339004429673956L;

	public InvalidTicketTimeException() {
		super("-106","invalid ticket time");
	}

}
