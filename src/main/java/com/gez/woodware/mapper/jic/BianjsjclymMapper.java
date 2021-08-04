package com.gez.woodware.mapper.jic;

import java.util.*;
import org.apache.ibatis.annotations.Mapper;
import com.gez.woodware.entity.jic.*;

/**
 * 编辑司机车辆页面.
 */
@Mapper
public interface BianjsjclymMapper {

	/**
	 * 查看
	 */
	BianjsjclymData select(String id);

	/**
	 * 编辑
	 */
	void update(HashMap<String, Object> params);

}
