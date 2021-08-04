package com.gez.woodware.service.yund;

import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gez.woodware.service.basics.*;

import java.util.*;
import com.gez.woodware.util.*;
import com.gez.woodware.entity.*;
import com.gez.woodware.entity.yund.*;
import com.gez.woodware.mapper.yund.*;

/**
 *  编辑运单页面业务类.
 */
@Slf4j
@Service
public class BianjydymService {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private BianjydymMapper mapper;

	@Autowired
	private InitDataService initDataService;

	/**
	 * 编辑-- 设置默认值+默认编码
	 */
	public BianjydymData select(String id) {
		BianjydymData data = mapper.select(id);
		//默认编码+待候选键
		data.setStateCode(initDataService.getCommonCodingList("waybillstatus"));
		return data;
	}
 
	/**
	 * 编辑
	 */
	@Transactional								
	public void update(BianjydymParams param) {
		mapper.update(ParamsUtil.getUserParam(request,param));

	}

 
}

