package com.gez.woodware.mapper.tongj;

import java.util.*;
import com.gez.woodware.entity.tongj.*;
import org.apache.ibatis.annotations.Mapper;

/**
 * 按车队统计运量管理.
 */
@Mapper
public interface AncdtjylglMapper {

	/**								
	 * 列表								
	 */								
	List<AncdtjylglData> select(HashMap<String, Object> params);
									
	/**								
	 * 总记录								
	 */								
	int count(HashMap<String, Object> params);								
									

}
