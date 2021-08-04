package com.gez.woodware.mapper.basics;

import com.gez.woodware.entity.basics.User;
import com.gez.woodware.entity.basics.UserParams;
import com.gez.woodware.entity.basics.WxxcxzhData;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;


@Mapper
public interface BaseMapper {
	
	/**
	 *  查询全部数据
	 */
	void log(HashMap<String, Object>  params);

}
