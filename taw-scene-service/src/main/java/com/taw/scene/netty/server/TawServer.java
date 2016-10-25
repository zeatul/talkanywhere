package com.taw.scene.netty.server;

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taw.scene.netty.CtxHelper;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

public class TawServer {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	
	
	private int port;
	
	

	/**
	 * 消息构成方式，按byte计算，头四位代表一个int32=整个消息的长度，紧接着2位是char(2),代表消息类型,
	 * 后面接着的是消息的实体的json格式的字符串用utf8转换出的byte
	 * 
	 * @throws Exception
	 */
	public void bind() throws Exception {

		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();

		ServerBootstrap b = new ServerBootstrap();
		try {
			b.group(bossGroup, workGroup).channel(NioServerSocketChannel.class) //
					.option(ChannelOption.SO_BACKLOG, 100) //
					.handler(new LoggingHandler(LogLevel.INFO))//
					.childHandler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024 * 64, 0, 4, 0, 4));
							ch.pipeline().addLast(new StringDecoder(Charset.forName("utf-8")));
							ch.pipeline().addLast(new LengthFieldPrepender(4, false));
							ch.pipeline().addLast(new StringEncoder(Charset.forName("utf-8")));
							ch.pipeline().addLast("readTimeoutHandler", new ReadTimeoutHandler(CtxHelper.READ_TIMEOUT));
							ch.pipeline().addLast(new LoginAuthRespHandler());
							ch.pipeline().addLast("heartBeatHandler", new HeartBeatRespHandler());

						}
					});
			// 绑定端口，同步等待成功
			ChannelFuture f = b.bind(port).sync();
			logger.info("+++++taw server listen in port {}" , port);
			f.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}

	public TawServer(int port) throws Exception{
		this.port = port;
		Thread t = new Thread(new TawTask());
		t.start(); 
	}
	
	public class TawTask implements Runnable{

		@Override
		public void run() {
			try {
				bind();
			} catch (Exception e) {
				throw new RuntimeException("Failed to start taw server",e);
			}
			
		}
		
		
		
		
	}

	public static void main(String[] args) throws Exception {

		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
		applicationContext.setConfigLocations(new String[] { "classpath*:com/taw/user/spring/applicationContext-user-service-*.xml",
				"classpath*:com/hawk/pub/spring/applicationContext-pub-*.xml "});
		applicationContext.refresh();

		int port = 9999;
		if (args != null & args.length > 0) {
			try {
				port = Integer.parseInt(args[0]);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		new TawServer(port).bind();

		applicationContext.close();
	}

}
