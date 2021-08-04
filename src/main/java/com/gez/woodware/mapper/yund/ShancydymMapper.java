package com.gez.woodware.mapper.yund;

import java.util.*;
import org.apache.ibatis.annotations.Mapper;
import com.gez.woodware.entity.yund.*;

/**
 * 删除运单页面.
 */
@Mapper
public interface ShancydymMapper {

	/**
	 * 查看
	 */
	ShancydymData select(String id);

	/**
	 * 删除
	 */
	void delete(String id);

}
