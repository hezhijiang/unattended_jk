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

@Api(tags = "4.2 编辑异常页面控制类")
@RestController
@RequestMapping("/Bianjycym")
public class BianjycymController {

	@Autowired
	private BianjycymService service;

	@GetMapping("/{id}")
	@ApiOperation(value ="获取初始值", notes = "根据id获取初始值")
	public RetResponse show(@PathVariable String id) {
		return new RetResponse(service.select(id)!=null,service.select(id));
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "编辑",notes = "根据id编辑对象内容")
	public RetResponse update(@PathVariable String id,@Valid @RequestBody BianjycymParams param) {
		param.setId(id);
		service.update(param);
		return new RetResponse("编辑成功");
	}
	

}
