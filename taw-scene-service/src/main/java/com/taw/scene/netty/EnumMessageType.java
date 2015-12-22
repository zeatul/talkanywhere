package com.taw.scene.netty;

public enum EnumMessageType {
	
	HEARTBEAT_REQ("00"), //心跳请求
	HEARTBEAT_RESP("01") , //心跳响应
	LOGIN_REQ("02") ,//登录请求
	LOGIN_RESP("03");//登录响应
	
	

	private String value;
	
	private EnumMessageType (String value){
		this.value = value;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return value;
	}
	
	

}
