package com.taw.scene.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.nio.charset.Charset;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TawClient {
	
	private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
	
	private EventLoopGroup group = new NioEventLoopGroup();
	
	public void connect(final String host ,final int port) throws Exception{
		try{
			Bootstrap b = new Bootstrap();
			b.group(group)//
				.channel(NioSocketChannel.class)//
				.option(ChannelOption.TCP_NODELAY, true)//
				.handler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024*64, 0, 4,0,4));
						ch.pipeline().addLast(new StringDecoder(Charset.forName("utf-8")));
						ch.pipeline().addLast(new LengthFieldPrepender(4,false));
						ch.pipeline().addLast(new StringEncoder(Charset.forName("utf-8")));
						ch.pipeline().addLast("readTimeoutHandler", new ReadTimeoutHandler(180*3));
						ch.pipeline().addLast("LoginAuthReqHandler", new LoginAuthReqHandler());
						ch.pipeline().addLast("HeartBeatReqHandler", new HeartBeatReqHandler());
					}
				});
			
			ChannelFuture future = b.connect(host, port).sync();
			future.channel().closeFuture().sync();
		}finally{
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						TimeUnit.SECONDS.sleep(30);
						try {
							connect(host, port);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			});
		}
	}
	
	public static void main(String[] args) throws Exception{
		
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
		applicationContext.setConfigLocations(new String[]{"classpath*:com/taw/user/spring/applicationContext-user-service-*.xml"});
		applicationContext.refresh();	
		
		String host = "localhost";
		int port = 9999;
		
		if (args!=null && args.length > 1){
			host = args[0];
			port = Integer.parseInt(args[1]);
		}
		
		new TawClient().connect(host, port);
		
		applicationContext.close();
	}

}
