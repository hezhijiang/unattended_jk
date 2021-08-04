package com.gez.woodware.service.yund;

import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.gez.woodware.util.*;
import com.gez.woodware.entity.*;
import com.gez.woodware.entity.yund.*;
import com.gez.woodware.mapper.yund.*;

/**
 *  编辑异常页面业务类.
 */
@Slf4j
@Service
public class BianjycymService {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private BianjycymMapper mapper;

	/**
	 * 编辑-- 设置默认值+默认编码
	 */
	public BianjycymData select(String id) {
		BianjycymData data = mapper.select(id);
		return data;
	}
 
	/**
	 * 编辑
	 */
	@Transactional								
	public void update(BianjycymParams param) {
		mapper.update(ParamsUtil.getUserParam(request,param));

	}

 
}

