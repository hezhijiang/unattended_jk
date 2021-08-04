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
 *  添加运单页面业务类.
 */
@Slf4j
@Service
public class TianjydymService {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private TianjydymMapper mapper;

	@Autowired
	private InitDataService initDataService;

	/**
	 * 查看信息+默认编码
	 */
	public TianjydymData select() {

		TianjydymData data = new TianjydymData();

		//默认值
		data.setId(UUID.randomUUID().toString());
		data.setNumber("");
		data.setProject("");
		data.setPlaceOfLoading("");
		data.setPlaceOfDischarge("");
		data.setVolume("0");
		data.setFreight("0");
		data.setHeadstock("");
		data.setCarriage("");
		data.setSource("");
		data.setCardNumber("");
		data.setCarNumber("");
		data.setState("");
		data.setRemarks("");

		//默认编码+待候选键
		data.setStateCode(initDataService.getCommonCodingList("waybillstatus"));

		return data;
	}
 
	/**
	 * 添加
	 */
	@Transactional								
	public void insert(TianjydymParams param) {
	    if(param.getId()==null || param.getId().length()<30){
	        param.setId(UUID.randomUUID().toString());
	    }
		mapper.insert(ParamsUtil.getUserParam(request,param));

	}

 
}

