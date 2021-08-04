package com.gez.woodware.mapper.yund;

import java.util.*;
import org.apache.ibatis.annotations.Mapper;
import com.gez.woodware.entity.yund.*;

/**
 * 添加运单页面.
 */
@Mapper
public interface TianjydymMapper {


	/**
	 * 添加
	 */
	void insert(HashMap<String, Object> params);

}
