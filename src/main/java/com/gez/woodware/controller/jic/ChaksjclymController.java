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

@Api(tags = "3.1 查看司机车辆页面控制类")
@RestController
@RequestMapping("/Chaksjclym")
public class ChaksjclymController {

	@Autowired
	private ChaksjclymService service;

	@GetMapping("/{id}")
	@ApiOperation(value ="获取初始值", notes = "根据id获取初始值")
	public RetResponse show(@PathVariable String id) {
		return new RetResponse(service.select(id)!=null,service.select(id));
	}


}
