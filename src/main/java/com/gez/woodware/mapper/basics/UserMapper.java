package com.gez.woodware.mapper.basics;

import com.gez.woodware.entity.basics.*;


import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;



@Mapper
public interface UserMapper {
	
	/**
	 *  查询全部数据
	 */
	User getUserByYonghzhmm(UserParams params);

	void delete_Weixyh_openid(String openid);

	User getUserByOpenId(String openid);

	User getUserByYonghzh(String yonghzh);

	User getUserById(String id);


	WxxcxzhData getAppid(String systemName);


	String getOpenId(String userId );
	String getyanzm(String phone);

	void insert_yongh(HashMap<String, Object> params);
	void insert_Weixyh(HashMap<String, Object> params);
}
