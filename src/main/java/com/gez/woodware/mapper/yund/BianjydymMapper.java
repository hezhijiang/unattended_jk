package com.gez.woodware.mapper.yund;

import java.util.*;
import org.apache.ibatis.annotations.Mapper;
import com.gez.woodware.entity.yund.*;

/**
 * 编辑运单页面.
 */
@Mapper
public interface BianjydymMapper {

	/**
	 * 查看
	 */
	BianjydymData select(String id);

	/**
	 * 编辑
	 */
	void update(HashMap<String, Object> params);

}
