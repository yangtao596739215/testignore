package com.imooc.netty;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 单例实现,加入到容器中
 */
@Component
@Slf4j
public class WSServer {

	private static class SingletionWSServer {
		static final WSServer instance = new WSServer();
	}
	
	public static WSServer getInstance() {
		return SingletionWSServer.instance;
	}
	
	private EventLoopGroup mainGroup;
	private EventLoopGroup subGroup;
	private ServerBootstrap server;
	private ChannelFuture future;
	
	private WSServer() {
		mainGroup = new NioEventLoopGroup();
		subGroup = new NioEventLoopGroup();
		server = new ServerBootstrap();
		server.group(mainGroup, subGroup)
			.channel(NioServerSocketChannel.class)
			.childHandler(new WSServerInitialzer());
	}
	
	public void start() {
		this.future = server.bind(8088); //这里不需要进行同步,因为这个不是在main方法里面
		log.info("netty websocket server 启动完毕...");
	}
}
