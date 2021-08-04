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

@Api(tags = "3.2 添加司机车辆页面控制类")
@RestController
@RequestMapping("/Tianjsjclym")
public class TianjsjclymController {

	@Autowired
	private TianjsjclymService service;

	@GetMapping("/")
	@ApiOperation(value = "获取页面默认值", notes = "获取页面默认值")
	public RetResponse show() {
		return new RetResponse(service.select());
	}

	@PostMapping("/")
	@ApiOperation(value = "添加",notes = "根据传入的参数进行添加操作")
	public RetResponse insert(@Valid @RequestBody TianjsjclymParams param) {
		service.insert(param);
		return new RetResponse("添加成功");
	}
	

}
