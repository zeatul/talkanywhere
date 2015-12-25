package com.taw.scene.netty.client;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.taw.scene.netty.CtxHelper;
import com.taw.scene.netty.EnumMessageType;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class HeartBeatReqHandler extends ChannelHandlerAdapter{
	
	private volatile ScheduledFuture<?> heartBeat;
	private final static String HEART_BEAT_REQ = EnumMessageType.HEARTBEAT_REQ + "Heart beat request";
	
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String message = (String)msg;
		String messageType = message.substring(0, 2);
		
		if (messageType.equals(EnumMessageType.LOGIN_RESP.toString())){
			heartBeat = ctx.executor().scheduleAtFixedRate(new HeartBeatTask(ctx), 0, CtxHelper.HEART_BEAT_INTERVAL, TimeUnit.MILLISECONDS);
		}else if (messageType.equals(EnumMessageType.HEARTBEAT_RESP.toString())){
			System.out.println("Receive heart beat response messsage = " +message);
		}else{
			ctx.fireChannelRead(msg);
		}
	}
	
	private class HeartBeatTask implements Runnable{
		private final ChannelHandlerContext ctx;

		public HeartBeatTask(final ChannelHandlerContext ctx) {
			this.ctx = ctx;
		}
		@Override
		public void run() {
			System.out.println("Send heart beat request messsage = " + HEART_BEAT_REQ);			
			ctx.writeAndFlush(HEART_BEAT_REQ);
		}
		
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		if (heartBeat != null){
			heartBeat.cancel(true);
			heartBeat = null;
		}
		
		ctx.fireExceptionCaught(cause);
	}

}
