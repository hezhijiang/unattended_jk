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

@Api(tags = "4.2 添加运单页面控制类")
@RestController
@RequestMapping("/Tianjydym")
public class TianjydymController {

	@Autowired
	private TianjydymService service;

	@GetMapping("/")
	@ApiOperation(value = "获取页面默认值", notes = "获取页面默认值")
	public RetResponse show() {
		return new RetResponse(service.select());
	}

	@PostMapping("/")
	@ApiOperation(value = "添加",notes = "根据传入的参数进行添加操作")
	public RetResponse insert(@Valid @RequestBody TianjydymParams param) {
		service.insert(param);
		return new RetResponse("添加成功");
	}
	

}
