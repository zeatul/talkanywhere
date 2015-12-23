package com.taw.scene.netty.server;

import com.taw.scene.netty.EnumMessageType;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class HeartBeatRespHandler extends ChannelHandlerAdapter {
	
	private final static String HEART_BEAT_RESP = EnumMessageType.HEARTBEAT_RESP + "Heart beat response";

	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String message = (String)msg;
		String messageType = message.substring(0, 2);
		
		if (messageType.equals(EnumMessageType.HEARTBEAT_REQ.toString())){
			ctx.writeAndFlush(HEART_BEAT_RESP);
		}else{
			ctx.fireChannelRead(msg);
		}
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.fireExceptionCaught(cause);
	}
}
