package com.gez.woodware.controller.basics;


import com.gez.woodware.entity.basics.*;
import com.gez.woodware.entity.basics.UserParams;

import com.gez.woodware.service.basics.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;


@Api(tags = "1 用户管理")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService service;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/login")
    @ApiOperation(value = "1.1 用户登录", notes = "根据用户账号和密码进行登录")
    public RetResponse login(@Valid UserParams data) {
        User u = service.getUserByYonghzhmm(data);


        if (u != null) {
            return new RetResponse(u);
        } else {
            return new RetResponse(false, "登录失败");
        }
    }


    @PostMapping("/register")
    @ApiOperation(value = "1.2 用户注册", notes = "根据用户账号和密码进行注册")
    public RetResponse register(@RequestBody UserParams data) {
        String id = service.register(data);


        if (id.length() > 30) {
            User u = service.getUserById(id);
            if (u != null) {
                return new RetResponse(u);
            } else {
                return new RetResponse(false, "注册失败");
            }
        } else {
            return new RetResponse(false, "该用户已存在，注册失败");
        }


    }


    @GetMapping("/logout")
    @ApiOperation(value = "1.3 用户退出登录", notes = "当前用户退出登录")
    public RetResponse logout() {
        service.logout();
        return new RetResponse("退出成功");

    }



    @GetMapping("/notLoggedIn")
    @ApiOperation(value = "1.4 用户未登录", notes = "当前用户未登录")
    public RetResponse notLoggedIn() {
        return new RetResponse(false, "用户未登录");
    }


    @GetMapping("/test")
    @ApiOperation(value = "1.5 用户登录", notes = "根据用户账号和密码进行登录")
    public RetResponse test() {

        System.out.println("this is zj");
        redisTemplate.opsForValue().set("hezj", "12313241234");
        return new RetResponse(redisTemplate.opsForValue().get("hezj"));

    }

}
