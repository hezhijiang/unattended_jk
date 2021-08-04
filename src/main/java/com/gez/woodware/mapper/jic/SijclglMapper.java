package com.gez.woodware.mapper.jic;

import java.util.*;
import com.gez.woodware.entity.jic.*;
import org.apache.ibatis.annotations.Mapper;

/**
 * 司机车辆管理.
 */
@Mapper
public interface SijclglMapper {

	/**								
	 * 列表								
	 */								
	List<SijclglData> select(HashMap<String, Object> params);
									
	/**								
	 * 总记录								
	 */								
	int count(HashMap<String, Object> params);								
									
	/**								
	 * 选中行序号								
	 */								
	int number(HashMap<String, Object> params);								
									

}
