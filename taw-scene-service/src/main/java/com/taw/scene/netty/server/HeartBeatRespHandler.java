package com.taw.scene.netty.server;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taw.scene.netty.CtxHelper;
import com.taw.scene.netty.EnumMessageType;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.ReadTimeoutException;

public class HeartBeatRespHandler extends ChannelHandlerAdapter {
	
	private final static String HEART_BEAT_RESP = EnumMessageType.HEARTBEAT_RESP + "Heart beat response";

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String message = (String)msg;
		String messageType = message.substring(0, 2);
		
		if (messageType.equals(EnumMessageType.HEARTBEAT_REQ.toString())){
			logger.info("======Receive Heart Beat Request : {}",message);
			ctx.writeAndFlush(HEART_BEAT_RESP);
			logger.info("======Send Heart Beat Response : {}",HEART_BEAT_RESP);
			CtxHelper.refreshClientLogin(ctx.channel().id().toString());			
		}else{
			ctx.fireChannelRead(msg);
		}
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		/**
		 * 该方法没有意义，强制关闭链接，没法控制
		 */
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		
		System.out.println("exception=" + cause.getClass().getSimpleName()+ ","+ cause.getMessage());
		
		
		
		if (cause instanceof ReadTimeoutException || cause instanceof IOException){
			CtxHelper.removeClientLogin(ctx);
			try {
				ctx.close();
			} catch (Exception e) {
				
			}
			
		}
		else{
			ctx.fireExceptionCaught(cause);
		}
	}
}
