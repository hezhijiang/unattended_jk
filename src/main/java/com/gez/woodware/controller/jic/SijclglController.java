package com.gez.woodware.controller.jic;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gez.woodware.entity.*;
import com.gez.woodware.entity.basics.*;
import com.gez.woodware.service.jic.*;
import com.gez.woodware.entity.jic.*;
import com.gez.woodware.util.*;
import javax.validation.Valid;

@Api(tags = "4 司机车辆管理控制类")
@RestController
@RequestMapping("/Sijclgl")
public class SijclglController {

	@Autowired
	private SijclglService service;

	@GetMapping("/")
	@ApiOperation(value = "获取列表数据", notes ="获取列表数据")
	public RetResponse select(PageArgs param) {	
		return new RetResponse(service.select(param));
	}									 

}
