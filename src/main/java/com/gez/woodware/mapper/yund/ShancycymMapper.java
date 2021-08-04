package com.gez.woodware.mapper.yund;

import java.util.*;
import org.apache.ibatis.annotations.Mapper;
import com.gez.woodware.entity.yund.*;

/**
 * 删除异常页面.
 */
@Mapper
public interface ShancycymMapper {

	/**
	 * 查看
	 */
	ShancycymData select(String id);

	/**
	 * 删除
	 */
	void delete(String id);

}
