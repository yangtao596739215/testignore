package com.imooc.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  VO         ViewObject表现层对象；主要对应界面显示的数据对象。对于一个WEB页面
 *     主要用于返回数据给前端
 *
 *  BO(Business Object) 业务对象 从前端返回到controller的对象称为 BO
 *
 *                 封装对象、复杂对象，里面可能包含多个类
 *                 主要作用是把业务逻辑封装为一个对象。这个对象可以包括一个或多个其它的对象。
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersBO {
    private String userId;
	/**
	 * 传过来的base64的数据
	 */
	private String faceData;
    private String nickname;
    

}
