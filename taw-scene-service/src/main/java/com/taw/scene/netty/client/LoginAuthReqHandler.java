package com.taw.scene.netty.client;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hawk.pub.spring.FrameworkContext;
import com.taw.scene.netty.EnumMessageType;
import com.taw.user.auth.TokenSecurityHelper;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class LoginAuthReqHandler extends ChannelHandlerAdapter{
	
	private TokenSecurityHelper tokenSecurityHelper = FrameworkContext.getApplicationContext().getBean(TokenSecurityHelper.class) ;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
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
				System.out.println("login failed : " + message.substring(3));
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
		String token = TawClient.TOKEN;
		String ticket = tokenSecurityHelper.generate(token, System.currentTimeMillis(), "hettpclient4.5");
		return EnumMessageType.LOGIN_REQ.toString() + ticket;//"loginAuthReq" + System.currentTimeMillis();
	}

}
