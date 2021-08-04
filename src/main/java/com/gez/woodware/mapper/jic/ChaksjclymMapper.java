package com.gez.woodware.mapper.jic;

import java.util.*;
import org.apache.ibatis.annotations.Mapper;
import com.gez.woodware.entity.jic.*;

/**
 * 查看司机车辆页面.
 */
@Mapper
public interface ChaksjclymMapper {

	/**
	 * 查看
	 */
	ChaksjclymData select(String id);

}
