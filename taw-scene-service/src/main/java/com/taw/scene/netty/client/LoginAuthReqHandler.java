package com.taw.scene.netty.client;



import com.taw.scene.netty.EnumMessageType;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class LoginAuthReqHandler extends ChannelHandlerAdapter{
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(buildLoginAuthReq());
	}
	
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String message = (String)msg;
		String messageType = message.substring(0, 2);
		
		if (messageType.equals(EnumMessageType.LOGIN_RESP.toString())){
			String loginResult = message.substring(2,3);
			if (!loginResult.equals("1")){
				// 返回1表示登录成功，否则失败并关闭链接
				System.out.println("login failed");
				ctx.close();
			}else{
				System.out.println("login succeeded");
				ctx.fireChannelRead(msg);
			}
		}else{
			ctx.fireChannelRead(msg);
		}
		
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.fireExceptionCaught(cause);
	}
	
	
	private String buildLoginAuthReq(){
		return EnumMessageType.LOGIN_REQ.toString() + "loginAuthReq" + System.currentTimeMillis();
	}

}
