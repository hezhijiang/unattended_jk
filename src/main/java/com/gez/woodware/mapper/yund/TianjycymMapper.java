package com.gez.woodware.mapper.yund;

import java.util.*;
import org.apache.ibatis.annotations.Mapper;
import com.gez.woodware.entity.yund.*;

/**
 * 添加异常页面.
 */
@Mapper
public interface TianjycymMapper {


	/**
	 * 添加
	 */
	void insert(HashMap<String, Object> params);

}
