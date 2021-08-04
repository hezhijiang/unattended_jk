package com.gez.woodware.controller.basics;


import com.gez.woodware.entity.basics.*;
import com.gez.woodware.service.basics.*;
import com.gez.woodware.util.wxxcxPay.PayMent;
import io.swagger.annotations.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Api(tags = "2 微信用户")
@Slf4j
@RestController
@RequestMapping("/WeChatuser")
public class WeChatUserController {


    @Autowired

    private WeChatUserService service;


    @GetMapping("/login")
    @ApiOperation(value = "2.1 微信小程序用户登录", notes = "根据微信Code登录")
    public RetResponse login(@Valid WeChatUserLoginParam params) {
        return service.Login(params);
    }


    @PostMapping("/authorizedLogin")
    @ApiOperation(value = "2.2 微信小程序用户授权注册登录", notes = "授权注册登录")
    public RetResponse authorizedLogin(@Valid @RequestBody wxRegisterPhoneParams params) throws Exception {
        return new RetResponse(service.authorizedLogin(params));

    }


    @GetMapping("/VerificationCode/{shoujh}")
    @ApiOperation(value = "2.3 获取短信验证码", notes = "调用第三方平台获取验证码")
    public RetResponse VerificationCode(@PathVariable String shoujh) throws Exception {
        return new RetResponse(service.authorizedLogin(shoujh));

    }


    @PostMapping("/register")
    @ApiOperation(value = "2.4 微信小程序用户手机号注册登录", notes = "手机号注册登录")
    public RetResponse register(@Valid @RequestBody wxRegisterParam params) throws Exception {

        User u = service.register(params);
        if (u != null) {
            return new RetResponse(u);
        } else {
            return new RetResponse(false, "用户验证码不正确");
        }
    }


    @DeleteMapping("/logout/{openId}")
    @ApiOperation(value = "2.5 微信小程序用户解除与用户的绑定关系", notes = "退出登录")
    public RetResponse logout(@PathVariable String openId) throws Exception {
        service.logout(openId);
        return new RetResponse("解除绑定成功");

    }


    @PostMapping("/userInfo")
    @ApiOperation(value = "2.6 更新微信用户的昵称和头像", notes = "更新昵称头像")
    public RetResponse userInfo(@Valid @RequestBody wxUserParams params) throws Exception {
        service.userInfo(params);
        return new RetResponse("设置成功");
    }

    @GetMapping("/userInfo/{userId}")
    @ApiOperation(value = "2.7 获取用户的昵称和头像", notes = "获取昵称头像")
    public RetResponse userInfo(@PathVariable String userId) throws Exception {

        return new RetResponse(service.getuserInfo(userId));
    }



    @PostMapping("/wxNotify")
    @ApiOperation(value = "微信支付回调", notes = "微信支付回调函数")
    public RetResponse wxNotify(HttpServletRequest request, String dingdbh) throws Exception {

        if (dingdbh == null) {
            dingdbh = PayMent.WxNotfily(request);;
        }


        return new RetResponse(service.zhif(dingdbh));
    }

    @PostMapping("/messge")
    @ApiOperation(value = "微信支付回调", notes = "验证用户身份")
    public RetResponse messge(HttpServletRequest request, String dingdbh) throws Exception {


        return new RetResponse(service.message("lng"));
    }



    @PostMapping("/wxXcxPayBusinesToChange")
    @ApiOperation(value = "微信支付回调", notes = "验证用户身份")
    public RetResponse wxXcxPayBusinesToChange(HttpServletRequest request, String dingdbh) throws Exception {




        return new RetResponse(service.wxXcxPayBusinesToChange("lng"));
    }

}
