package com.gez.woodware.mapper.yund;

import java.util.*;
import com.gez.woodware.entity.yund.*;
import org.apache.ibatis.annotations.Mapper;

/**
 * 运单管理.
 */
@Mapper
public interface YundglMapper {

	/**								
	 * 列表								
	 */								
	List<YundglData> select(HashMap<String, Object> params);
									
	/**								
	 * 总记录								
	 */								
	int count(HashMap<String, Object> params);								
									
	/**								
	 * 选中行序号								
	 */								
	int number(HashMap<String, Object> params);								
									

}
