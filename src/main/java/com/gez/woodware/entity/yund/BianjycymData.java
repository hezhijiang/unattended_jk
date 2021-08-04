package com.gez.woodware.entity.yund;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.*;
import lombok.Data;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.*;
import com.gez.woodware.util.*;
import com.gez.woodware.entity.basics.*;

@Data
@ApiModel(description = "编辑异常页面返回对象类")
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class BianjycymData {

    @ApiModelProperty("标识")
	private String id;

    @ApiModelProperty("类型")
	private String tyep;

    @ApiModelProperty("卡号")
	private String cardNumber;

    @ApiModelProperty("持卡人")
	private String cardholder;

    @ApiModelProperty("车牌号")
	private String carNumber;

    @ApiModelProperty("描述")
	private String details;

    @ApiModelProperty("添加时间")
	private String addTime;

}
