package com.taw.scene.netty.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hawk.pub.spring.FrameworkContext;
import com.taw.scene.netty.CtxHelper;
import com.taw.scene.netty.EnumMessageType;
import com.taw.user.auth.TokenSecurityHelper;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class LoginAuthRespHandler extends ChannelHandlerAdapter{
	
	private ExecutorService executor = Executors.newCachedThreadPool();
	private TokenSecurityHelper tokenSecurityHelper = FrameworkContext.getApplicationContext().getBean(TokenSecurityHelper.class) ;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String message = (String)msg;
		String messageType = message.substring(0, 2);
		if (messageType.equals(EnumMessageType.LOGIN_REQ.toString())){
			String ticket = message.substring(2);
			logger.info("ticket={}",ticket);
			executor.execute(new LoginAuthTask(ctx, ticket));
			
		}else{
			ctx.fireChannelRead(msg);
		}
	}
	
	
	
	private class LoginAuthTask implements Runnable{
		
		private final ChannelHandlerContext ctx;
		
		private final String ticket;

		public LoginAuthTask(ChannelHandlerContext ctx,String ticket){
			this.ctx = ctx;
			this.ticket = ticket;
		}
		
		@Override
		public void run() {
			
			boolean loginResult = false;
			String errMsg = "";
			
			try {
				String token = tokenSecurityHelper.computeToken(ticket);				
				System.out.println("Succeeded to pass auth check,token="+token);
				/**
				 * 检查重复登录
				 */
				if (CtxHelper.existClientLogin(token)){
					logger.error("======Duplicated login,token={}",token);
					errMsg = "Duplicated Login";
					loginResult = false;
				}else{
					CtxHelper.registClientLogin(token,ctx);
					loginResult = true;
					logger.info("======Success login,token={}",token);
				}
			} catch (Exception ex) {
				errMsg = "Invalid Token";
				logger.error("=====Failed to pass auth check",ex);
			}
			
			ctx.writeAndFlush(buildLoginAuthResp(loginResult,errMsg));
			
		}
		
		private String buildLoginAuthResp(boolean result,String msg){
			if (result)
				return EnumMessageType.LOGIN_RESP.toString()+"1";
			else
				return EnumMessageType.LOGIN_RESP.toString()+"0" + msg;
		}
		
	}
}
