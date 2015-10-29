package com.hawk.pub.sms;

/**
 * 消息类型
 * @author pzhang1
 *
 */
public enum EnumMessageKind {
	
	AUTH_CODE("0");
	
	/*微信/注册/QQ/微博*/
	
	private String value;
	
	private EnumMessageKind (String value){
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}

}
