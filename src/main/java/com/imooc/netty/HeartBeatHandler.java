package com.imooc.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 用于检测channel的心跳handler
 *
 *                心跳检测的意义,比如手机开启飞行模式,属于客户端异常关闭,这时候,它不会告诉服务器去关闭连接,
 *                如果服务器的连接一直开着,会造成服务器资源的浪费,所以,要开启心跳检测
 *
 *                如果一个手机连接以后,开启飞行模式,再重新连接,就会获得一个新的channel
 *
 *
 * 				 继承ChannelInboundHandlerAdapter，从而不需要实现channelRead0方法
 */
@Slf4j
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		
		// 判断evt是否是IdleStateEvent（用于触发用户事件，包含 读空闲/写空闲/读写空闲 ）
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent)evt;		// 强制类型转换
			
			if (event.state() == IdleState.READER_IDLE) {
				log.info("进入读空闲...");
			} else if (event.state() == IdleState.WRITER_IDLE) {
				log.warn("进入写空闲...");
			} else if (event.state() == IdleState.ALL_IDLE) {
				
				log.info("channel关闭前，users的数量为：" + ChatHandler.users.size());
				Channel channel = ctx.channel();
				// 如果进入的无读写的状态,则关闭无用的channel，以防资源浪费
				channel.close();
				log.info("channel关闭后，users的数量为：" + ChatHandler.users.size());
			}
		}
		
	}
	
}
