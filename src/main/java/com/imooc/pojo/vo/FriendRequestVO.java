package com.imooc.pojo.vo;

import lombok.Data;

/**
 * @Description: 好友请求发送方的信息
 *
 * 从数据库查询出来的,返回给前端的信息,封装成一个vo
 *
 *
 */

@Data
public class FriendRequestVO {
	
    private String sendUserId;
    private String sendUsername;
    private String sendFaceImage;
    private String sendNickname;
    

}