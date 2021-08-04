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
 *  添加异常页面业务类.
 */
@Slf4j
@Service
public class TianjycymService {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private TianjycymMapper mapper;

	/**
	 * 查看信息+默认编码
	 */
	public TianjycymData select() {

		TianjycymData data = new TianjycymData();

		//默认值
		data.setId(UUID.randomUUID().toString());
		data.setTyep("");
		data.setCardNumber("");
		data.setCardholder("");
		data.setCarNumber("");
		data.setDetails("");
		data.setAddTime(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));


		return data;
	}
 
	/**
	 * 添加
	 */
	@Transactional								
	public void insert(TianjycymParams param) {
	    if(param.getId()==null || param.getId().length()<30){
	        param.setId(UUID.randomUUID().toString());
	    }
		mapper.insert(ParamsUtil.getUserParam(request,param));

	}

 
}

