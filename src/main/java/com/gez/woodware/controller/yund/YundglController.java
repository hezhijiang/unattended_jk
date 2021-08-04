package com.gez.woodware.controller.yund;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gez.woodware.entity.*;
import com.gez.woodware.entity.basics.*;
import com.gez.woodware.service.yund.*;
import com.gez.woodware.entity.yund.*;
import com.gez.woodware.util.*;
import javax.validation.Valid;

@Api(tags = "5 运单管理控制类")
@RestController
@RequestMapping("/Yundgl")
public class YundglController {

	@Autowired
	private YundglService service;

	@GetMapping("/")
	@ApiOperation(value = "获取列表数据", notes ="获取列表数据")
	public RetResponse select(PageArgs param) {	
		return new RetResponse(service.select(param));
	}									 

}
