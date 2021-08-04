package com.gez.woodware.mapper.basics;



import com.gez.woodware.entity.basics.CodeValue;
import com.gez.woodware.entity.basics.CodeValueDuolie;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author yangshangwei
 *	
 * 增加@Mapper这个注解之后，Spring 启动时会自动扫描该接口，这样就可以在需要使用时直接注入 Mapper 了
 */

@Mapper
public interface InitDataMapper {

	/**
	 * 通用编码查询
	 */
	List<CodeValue> getCommonCodingList(HashMap<String, Object> params);

	/**
	 * 待候选键
	 */
	List<CodeValue> getCommonCountList(HashMap<String, Object> params);

	List<CodeValueDuolie>  getDuolieCodingList(HashMap<String, Object> params);


	/**
	 * 查询用户账号列表
	 */
	List<CodeValue> getUserAccountList();

	/**
	 * 查询用户姓名列表
	 */
	List<CodeValue> getUserNameList();

	/**
	 * 查询部门列表
	 */
	List<CodeValue> getDepartmentNameList();

	/**
	 * 查询角色列表
	 */
	List<CodeValue> getPartNameList();
}
