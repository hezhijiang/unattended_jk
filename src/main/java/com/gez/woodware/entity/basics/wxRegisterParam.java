package com.gez.woodware.entity.basics;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class wxRegisterParam {

    @NotNull
    @ApiModelProperty("系统名称")
    private String systemName;

    @NotNull
    @ApiModelProperty("微信openId")
    private String openId;

    @NotNull
    @ApiModelProperty("注册手机号")
    private String shoujh;

    @NotNull
    @ApiModelProperty("验证码")
    private String VerificationCode;
}
