package com.gez.woodware.mapper.yund;

import java.util.*;
import org.apache.ibatis.annotations.Mapper;
import com.gez.woodware.entity.yund.*;

/**
 * 查看运单页面.
 */
@Mapper
public interface ChakydymMapper {

	/**
	 * 查看
	 */
	ChakydymData select(String id);

}
