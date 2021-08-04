package com.gez.woodware.service.jic;

import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gez.woodware.service.basics.*;

import java.util.*;
import com.gez.woodware.util.*;
import com.gez.woodware.entity.*;
import com.gez.woodware.entity.jic.*;
import com.gez.woodware.mapper.jic.*;

/**
 *  编辑司机车辆页面业务类.
 */
@Slf4j
@Service
public class BianjsjclymService {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private BianjsjclymMapper mapper;

	@Autowired
	private InitDataService initDataService;

	/**
	 * 编辑-- 设置默认值+默认编码
	 */
	public BianjsjclymData select(String id) {
		BianjsjclymData data = mapper.select(id);
		//默认编码+待候选键
		data.setStageCode(initDataService.getCommonCodingList("drivervehiclestatus"));
		return data;
	}
 
	/**
	 * 编辑
	 */
	@Transactional								
	public void update(BianjsjclymParams param) {
		mapper.update(ParamsUtil.getUserParam(request,param));

	}

 
}

