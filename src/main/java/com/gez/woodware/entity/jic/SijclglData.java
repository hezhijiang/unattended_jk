package com.gez.woodware.entity.jic;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.*;
import lombok.Data;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.gez.woodware.entity.basics.*;

@Data
@ApiModel(description = "司机车辆管理返回对象类")
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class SijclglData {

   @ApiModelProperty("主键标识")
	private String id;

   @ApiModelProperty("司机姓名")
	private String drivername;

   @ApiModelProperty("司机电话")
	private String driverphonenumber;

   @ApiModelProperty("车牌号")
	private String carnumber;

   @ApiModelProperty("车型")
	private String model;

   @ApiModelProperty("方量")
	private String volume;

   @ApiModelProperty("卡号")
	private String cardnumber;

   @ApiModelProperty("车队")
	private String fleet;

   @ApiModelProperty("承运商")
	private String carrier;

   @ApiModelProperty("同步编号")
	private String synchronizationnumber;

   @ApiModelProperty("状态")
	private String drivervehiclestatus;

}
