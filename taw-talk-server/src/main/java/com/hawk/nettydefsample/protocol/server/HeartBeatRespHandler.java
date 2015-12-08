package com.hawk.nettydefsample.protocol.server;

import com.hawk.nettydefsample.protocol.MessageType;
import com.hawk.nettydefsample.protocol.struct.Header;
import com.hawk.nettydefsample.protocol.struct.NettyMessage;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class HeartBeatRespHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		NettyMessage message = (NettyMessage) msg;
		if (message.getHeader() != null && message.getHeader().getType() == MessageType.HEARTBEAT_REQ.value()) {
			 System.out.println("Receive client heart beat message : ---> " + message);
			 NettyMessage heartBeat = buildHeatBeat();
			 ctx.writeAndFlush(heartBeat);
			 System.out
			    .println("Send heart beat response message to client : ---> "
				    + heartBeat);
		}else{
			ctx.fireChannelRead(msg);
		}
	}

	private NettyMessage buildHeatBeat() {
		NettyMessage message = new NettyMessage();
		Header header = new Header();
		header.setType(MessageType.HEARTBEAT_RESP.value());
		message.setHeader(header);
		return message;
	    }
}
