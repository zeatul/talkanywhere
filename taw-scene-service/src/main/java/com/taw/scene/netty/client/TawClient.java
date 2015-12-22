package com.taw.scene.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.Charset;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class TawClient {
	
	private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
	
	private EventLoopGroup group = new NioEventLoopGroup();
	
	public void connect(final String hots ,final int port){
		try{
			Bootstrap b = new Bootstrap();
			b.group(group)//
				.channel(NioSocketChannel.class)//
				.option(ChannelOption.TCP_NODELAY, true)//
				.handler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024*64, 0, 4));
						ch.pipeline().addLast(new StringDecoder(Charset.forName("utf-8")));
					}
				});
		}finally{
			
		}
	}

}
