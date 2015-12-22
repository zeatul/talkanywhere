package com.taw.scene.netty.server;

import java.nio.charset.Charset;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class TawServer {
	
	public void bind(){
		
		EventLoopGroup bossGroup  = new NioEventLoopGroup();		
		EventLoopGroup workGroup = new NioEventLoopGroup();
		
		ServerBootstrap b = new ServerBootstrap();
		
		b.group(bossGroup, workGroup)
			.channel(NioServerSocketChannel.class) //
			.option(ChannelOption.SO_BACKLOG, 100) //
			.handler(new LoggingHandler(LogLevel.INFO))//
			.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024*64, 0, 4));
					ch.pipeline().addLast(new StringDecoder(Charset.forName("utf-8")));
				}
			});
	}

}
