package com.gez.woodware.entity.basics;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "存入redis")
public class RedisParams {

    @NotNull
    @ApiModelProperty("key")
    private String key;

    @NotNull
    @ApiModelProperty("值")
    private String value;
}
