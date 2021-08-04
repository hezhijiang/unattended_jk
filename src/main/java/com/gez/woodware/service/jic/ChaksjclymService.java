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
 *  查看司机车辆页面业务类.
 */
@Slf4j
@Service
public class ChaksjclymService {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private ChaksjclymMapper mapper;

	/**
	 * 查看
	 */
	public ChaksjclymData select(String id) {
		ChaksjclymData data = mapper.select(id);
		return data;
	}
 
 
}

