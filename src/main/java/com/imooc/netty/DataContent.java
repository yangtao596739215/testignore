package com.imooc.netty;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * 前端传过来的json转化成的pojo对象
 *
 */
@Data
public class DataContent implements Serializable {

	private static final long serialVersionUID = 8021381444738260454L;

	private Integer action;		// 动作类型
	private ChatMsg chatMsg;	// 用户的聊天内容entity
	/**
	 * 以防万一,
	 */
	private String extand;		// 扩展字段
	

}
