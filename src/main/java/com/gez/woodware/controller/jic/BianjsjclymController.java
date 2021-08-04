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

@Api(tags = "3.3 编辑司机车辆页面控制类")
@RestController
@RequestMapping("/Bianjsjclym")
public class BianjsjclymController {

	@Autowired
	private BianjsjclymService service;

	@GetMapping("/{id}")
	@ApiOperation(value ="获取初始值", notes = "根据id获取初始值")
	public RetResponse show(@PathVariable String id) {
		return new RetResponse(service.select(id)!=null,service.select(id));
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "编辑",notes = "根据id编辑对象内容")
	public RetResponse update(@PathVariable String id,@Valid @RequestBody BianjsjclymParams param) {
		param.setId(id);
		service.update(param);
		return new RetResponse("编辑成功");
	}
	

}
