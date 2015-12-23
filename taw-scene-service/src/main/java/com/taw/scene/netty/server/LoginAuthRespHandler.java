package com.taw.scene.netty.server;

import com.taw.scene.netty.EnumMessageType;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class LoginAuthRespHandler extends ChannelHandlerAdapter{

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String message = (String)msg;
		String messageType = message.substring(0, 2);
		if (messageType.equals(EnumMessageType.LOGIN_REQ.toString())){
			boolean loginResult = true;
			ctx.writeAndFlush(buildLoginAuthResp(loginResult));
		}else{
			ctx.fireChannelRead(msg);
		}
	}
	
	private String buildLoginAuthResp(boolean result){
		if (result)
			return EnumMessageType.LOGIN_RESP.toString()+"1" + "loginAuthResp" + System.currentTimeMillis();
		else
			return EnumMessageType.LOGIN_RESP.toString()+"0" + "loginAuthResp" + System.currentTimeMillis();
	}
}
