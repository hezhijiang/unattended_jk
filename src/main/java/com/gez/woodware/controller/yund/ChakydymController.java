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

@Api(tags = "4.1 查看运单页面控制类")
@RestController
@RequestMapping("/Chakydym")
public class ChakydymController {

	@Autowired
	private ChakydymService service;

	@GetMapping("/{id}")
	@ApiOperation(value ="获取初始值", notes = "根据id获取初始值")
	public RetResponse show(@PathVariable String id) {
		return new RetResponse(service.select(id)!=null,service.select(id));
	}


}
