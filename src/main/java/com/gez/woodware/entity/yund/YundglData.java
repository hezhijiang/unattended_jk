package com.gez.woodware.entity.yund;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.*;
import lombok.Data;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.gez.woodware.entity.basics.*;

@Data
@ApiModel(description = "运单管理返回对象类")
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class YundglData {

   @ApiModelProperty("主键标识")
	private String id;

   @ApiModelProperty("编号")
	private String number;

   @ApiModelProperty("项目")
	private String project;

   @ApiModelProperty("方量")
	private String volume;

   @ApiModelProperty("运费")
	private String freight;

   @ApiModelProperty("来源")
	private String source;

   @ApiModelProperty("卡号")
	private String cardnumber;

   @ApiModelProperty("车牌号")
	private String carnumber;

   @ApiModelProperty("持卡人")
	private String cardholder;

   @ApiModelProperty("状态")
	private String waybillstatus;

   @ApiModelProperty("添加时间")
	private String addtime;

}
