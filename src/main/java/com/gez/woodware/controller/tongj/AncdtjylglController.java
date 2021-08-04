package com.gez.woodware.controller.tongj;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gez.woodware.entity.*;
import com.gez.woodware.entity.basics.*;
import com.gez.woodware.service.tongj.*;
import com.gez.woodware.entity.tongj.*;
import com.gez.woodware.util.*;
import javax.validation.Valid;

@Api(tags = "6 按车队统计运量管理控制类")
@RestController
@RequestMapping("/Ancdtjylgl")
public class AncdtjylglController {

	@Autowired
	private AncdtjylglService service;

	@GetMapping("/")
	@ApiOperation(value = "获取列表数据", notes ="获取列表数据")
	public RetResponse select(PageArgs param) {	
		return new RetResponse(service.select(param));
	}									 

}
