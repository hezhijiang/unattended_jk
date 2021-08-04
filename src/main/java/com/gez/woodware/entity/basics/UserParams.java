package com.gez.woodware.entity.basics;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "用户登录传入参数")
public class UserParams {

    @NotNull
    @ApiModelProperty("用户账号")
    private String yonghzh;

    @NotNull
    @ApiModelProperty("用户密码")
    private String yonghmm  ;
}
