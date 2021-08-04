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
 *  删除运单页面业务类.
 */
@Slf4j
@Service
public class ShancydymService {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private ShancydymMapper mapper;

	/**
	 * 查看
	 */
	public ShancydymData select(String id) {
		ShancydymData data = mapper.select(id);
		return data;
	}
 
	/**
	 * 删除
	 */
	@Transactional								
	public void delete(String id) {
		mapper.delete(id);

	}

 
}

