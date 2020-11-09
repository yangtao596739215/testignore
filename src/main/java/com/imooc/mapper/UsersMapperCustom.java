package com.imooc.mapper;

import java.util.List;

import com.imooc.pojo.Users;
import com.imooc.pojo.vo.FriendRequestVO;
import com.imooc.pojo.vo.MyFriendsVO;
import com.imooc.utils.MyMapper;

/**
 * 和xml文件一一对应
 *
 * 此Mapper对应自定义的多表关联查询 UsersMapperCustom.xml
 *
 * 一个方法对应xml文件中的一个sql
 *
 */
public interface UsersMapperCustom extends MyMapper<Users> {
	
	public List<FriendRequestVO> queryFriendRequestList(String acceptUserId);
	
	public List<MyFriendsVO> queryMyFriends(String userId);
	
	public void batchUpdateMsgSigned(List<String> msgIdList);
	
}