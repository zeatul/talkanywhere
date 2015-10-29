package com.hawk.pub.sms;

/**
 * 消息状态
 * @author pzhang1
 *
 */
public enum EnumMessageStatus {

	/**
	 * 未发送
	 */
	UNSENT("0"),
	/**
	 * 发送成功
	 */
	SUCCESS("1") ,
	/**
	 * 发送失败
	 */
	FAILED("2"),
	/**
	 * 丢弃
	 */
	DISCARD("9"); 
	
	/*微信/注册/QQ/微博*/
	
	private String value;
	
	private EnumMessageStatus (String value){
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
