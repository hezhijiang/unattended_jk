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
 *  查看运单页面业务类.
 */
@Slf4j
@Service
public class ChakydymService {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private ChakydymMapper mapper;

	/**
	 * 查看
	 */
	public ChakydymData select(String id) {
		ChakydymData data = mapper.select(id);
		return data;
	}
 
 
}

