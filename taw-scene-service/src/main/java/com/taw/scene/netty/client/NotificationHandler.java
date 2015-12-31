package com.taw.scene.netty.client;

import com.taw.scene.netty.EnumMessageType;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class NotificationHandler extends ChannelHandlerAdapter{

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String message = (String)msg;
		String messageType = message.substring(0, 2);
		
		System.out.println("message="+message);
		
		if (messageType.equals(EnumMessageType.CONVERSATION_NOTIFICATION.toString())){
			System.out.println("Receive conversation create notification = " + message.substring(2));
		}else if(messageType.equals(EnumMessageType.MESSAGE_NOTIFICATION.toString())){
			System.out.println("Receive message create notification = " + message.substring(2));
		}else{
			ctx.fireChannelRead(msg);
		}
	}
}
