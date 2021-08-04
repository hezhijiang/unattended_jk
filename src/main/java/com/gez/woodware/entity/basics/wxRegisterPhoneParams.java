package com.gez.woodware.entity.basics;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class wxRegisterPhoneParams {


    @NotNull
    @ApiModelProperty("encrypted")
    private String encrypted;

    @NotNull
    @ApiModelProperty("iv")
    private  String iv;

    @NotNull
    @ApiModelProperty("系统名称")
    private  String system;


    @NotNull
    @ApiModelProperty("微信的openiD")
    private  String openId;
}
