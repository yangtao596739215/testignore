package com.imooc.netty;

import java.util.HashMap;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 用户id和channel的关联关系处理
 *
 */
@Slf4j
public class UserChannelRel {

	private static HashMap<String, Channel> manager = new HashMap<>();

	public static void put(String senderId, Channel channel) {
		manager.put(senderId, channel);
	}
	
	public static Channel get(String senderId) {
		return manager.get(senderId);
	}
	
	public static void output() {
		for (HashMap.Entry<String, Channel> entry : manager.entrySet()) {
			log.info("UserId: " + entry.getKey()
							+ ", ChannelId: " + entry.getValue().id().asLongText());
		}
	}
}
