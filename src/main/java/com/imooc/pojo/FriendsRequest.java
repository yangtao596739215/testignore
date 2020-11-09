package com.imooc.pojo;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

/**
 * 请求表,如果请求被同意或者被拒绝以后,从请求表中把纪录删除
 *
 */

@Data
@Table(name = "friends_request")
public class FriendsRequest {
    @Id
    private String id;

    @Column(name = "send_user_id")
    private String sendUserId;

    @Column(name = "accept_user_id")
    private String acceptUserId;

    /**
     * 发送请求的事件
     */
    @Column(name = "request_date_time")
    private Date requestDateTime;



}