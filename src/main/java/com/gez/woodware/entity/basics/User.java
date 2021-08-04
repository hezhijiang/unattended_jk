package com.gez.woodware.entity.basics;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.*;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
@ApiModel(description = "用户实体")
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;



    @NotNull
    @ApiModelProperty("用户编号")
    private  String id;

    @ApiModelProperty("用户账号")
    private String yonghzh;



    @ApiModelProperty("用户姓名")
    private String yonghxm;

    @NotNull
    @ApiModelProperty("角色id")
    private String jiaosid;

    @NotNull
    @ApiModelProperty("角色名称")
    private String jiaosmc;

    @NotNull
    @ApiModelProperty("部门id")
    private String bumid;

    @NotNull
    @ApiModelProperty("部门名称")
    private String bummc;

    @ApiModelProperty("token")
    private String token;

    @ApiModelProperty("微信用户的openId")
    private String openId;



    @ApiModelProperty("司机状态")
    private String sijzt;
}





