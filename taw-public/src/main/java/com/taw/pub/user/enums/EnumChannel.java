package com.taw.pub.user.enums;

import com.hawk.enums.EnumTools;
import com.hawk.enums.Parsable;

/**
 * 注册来源
 * @author zhang_000
 *
 */
public enum EnumChannel implements Parsable{
	
	REGISTERED("0"), WECHAT("1") , QQ("2") ,WEIBO("3");
	
	/*微信/注册/QQ/微博*/
	
	private String channel;
	
	private EnumChannel (String channel){
		this.channel = channel;
	}
	
	@Override
	public String toString() {
		return channel;
	}

	public EnumChannel parse(Object obj){
		return EnumTools.parse(obj, EnumChannel.class);
	}
}
