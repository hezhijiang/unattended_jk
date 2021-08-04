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

@Api(tags = "4.3 删除异常页面控制类")
@RestController
@RequestMapping("/Shancycym")
public class ShancycymController {

	@Autowired
	private ShancycymService service;

	@GetMapping("/{id}")
	@ApiOperation(value ="获取初始值", notes = "根据id获取初始值")
	public RetResponse show(@PathVariable String id) {
		return new RetResponse(service.select(id)!=null,service.select(id));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除",notes = "根据id删除对象内容")
	public RetResponse delete(@PathVariable String id){
		service.delete(id);
		return new RetResponse("删除成功");
	}


}
