package com.gez.woodware.entity.yund;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.*;
import lombok.Data;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.gez.woodware.entity.basics.*;

@Data
@ApiModel(description = "异常管理返回对象类")
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class YicglData {

   @ApiModelProperty("主键标识")
	private String id;

   @ApiModelProperty("类型")
	private String tyep;

   @ApiModelProperty("卡号")
	private String cardnumber;

   @ApiModelProperty("持卡人")
	private String cardholder;

   @ApiModelProperty("车牌号")
	private String carnumber;

   @ApiModelProperty("描述")
	private String details;

}
