package com.gez.woodware.mapper.yund;

import java.util.*;
import org.apache.ibatis.annotations.Mapper;
import com.gez.woodware.entity.yund.*;

/**
 * 编辑异常页面.
 */
@Mapper
public interface BianjycymMapper {

	/**
	 * 查看
	 */
	BianjycymData select(String id);

	/**
	 * 编辑
	 */
	void update(HashMap<String, Object> params);

}
