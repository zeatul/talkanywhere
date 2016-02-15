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

import com.taw.scene.netty.CtxHelper;

public class TawClient {

	private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

	private EventLoopGroup group = new NioEventLoopGroup();
	
	

	public void connect(final String host, final int port) throws Exception {
		try {
			Bootstrap b = new Bootstrap();
			b.group(group)//
					.channel(NioSocketChannel.class)//
					.option(ChannelOption.TCP_NODELAY, true)//
					.handler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024 * 64, 0, 4, 0, 4));
							ch.pipeline().addLast(new StringDecoder(Charset.forName("utf-8")));
							ch.pipeline().addLast(new LengthFieldPrepender(4, false));
							ch.pipeline().addLast(new StringEncoder(Charset.forName("utf-8")));
							ch.pipeline().addLast("readTimeoutHandler", new ReadTimeoutHandler(CtxHelper.READ_TIMEOUT));
							ch.pipeline().addLast("LoginAuthReqHandler", new LoginAuthReqHandler());
							ch.pipeline().addLast("HeartBeatReqHandler", new HeartBeatReqHandler());
							ch.pipeline().addLast("NotificationHandler", new NotificationHandler());
						}
					});

			ChannelFuture future = b.connect(host, port).sync();
			future.channel().closeFuture().sync();
		} finally {
			executor.execute(new Runnable() {

				@Override
				public void run() {
					try {
						TimeUnit.SECONDS.sleep(60*2);
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

	public static String TOKEN = null;	
	public static String host = null;	
	public static boolean DEV = false;	
	public static void main(String[] args) throws Exception {
		if (DEV){
			TOKEN = "def54e69058d4fd19e47a85e2c175579";
			host = "localhost";
		}else{
			TOKEN = "ff3bd2bb58b0443ba5d51a293e0c6bcd";
			host = "211.157.19.83";
		}		
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
		applicationContext.setConfigLocations(new String[] { "classpath*:com/taw/user/spring/applicationContext-user-service-*.xml",
				"classpath*:com/hawk/pub/spring/applicationContext-pub-*.xml " });
		applicationContext.refresh();		
		int port = 50090;
		if (args != null && args.length > 1) {
			host = args[0];
			port = Integer.parseInt(args[1]);
		}
		new TawClient().connect(host, port);
	}

}
