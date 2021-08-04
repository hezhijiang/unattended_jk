package com.gez.woodware.service.jic;

import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.gez.woodware.util.*;
import com.gez.woodware.entity.*;
import com.gez.woodware.entity.jic.*;
import com.gez.woodware.mapper.jic.*;

/**
 *  添加司机车辆页面业务类.
 */
@Slf4j
@Service
public class TianjsjclymService {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private TianjsjclymMapper mapper;

	/**
	 * 查看信息+默认编码
	 */
	public TianjsjclymData select() {

		TianjsjclymData data = new TianjsjclymData();

		//默认值
		data.setId(UUID.randomUUID().toString());
		data.setDriverName("");
		data.setDriverPhoneNumber("");
		data.setCarNumber("");
		data.setModel("");
		data.setVolume("");
		data.setCardNumber("");
		data.setFleet("");
		data.setCarrier("");
		data.setIDCard("");
		data.setDrivingLicense("");
		data.setVehicleLicense("");
		data.setTaxiLicense("");
		data.setCardFace("");
		data.setAddTime("");
		data.setUserId(ResourceUtil.getUser(request).getId());


		return data;
	}
 
	/**
	 * 添加
	 */
	@Transactional								
	public void insert(TianjsjclymParams param) {
	    if(param.getId()==null || param.getId().length()<30){
	        param.setId(UUID.randomUUID().toString());
	    }
		mapper.insert(ParamsUtil.getUserParam(request,param));

	}

 
}

