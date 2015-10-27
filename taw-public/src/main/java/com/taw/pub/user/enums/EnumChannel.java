package com.taw.pub.user.enums;

/**
 * 注册来源
 * @author zhang_000
 *
 */
public enum EnumChannel {
	
	REGISTERED("0"), WECHAT("1") , QQ("2") ,WEIBO("3");
	
	/*微信/注册/QQ/微博*/
	
	private String value;
	
	private EnumChannel (String value){
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}

	
}
