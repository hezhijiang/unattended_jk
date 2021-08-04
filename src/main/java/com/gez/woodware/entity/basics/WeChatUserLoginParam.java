package com.gez.woodware.entity.basics;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Data
@ApiModel(description = "微信用户登录传入参数类")
public class WeChatUserLoginParam extends Object{

    @ApiModelProperty("系统名称")
    @NotEmpty(message = "系统名称不能为空")
    private String system;

    @ApiModelProperty("微信Code")
    @NotEmpty(message = "微信Code不能为空")
    private String code;


}