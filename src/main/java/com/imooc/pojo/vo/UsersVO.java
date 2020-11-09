package com.imooc.pojo.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersVO {
    private String userId;
    private String username;
    private String faceImage;
    private String faceImageBig;
    private String qrcode;
    

}