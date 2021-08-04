package com.gez.woodware.entity.basics;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DecodeWxAppPhoneData {

    @ApiModelProperty("用户手机号")
    private String shoujh;

    @ApiModelProperty("用户的openId")
    private String openId;



}
