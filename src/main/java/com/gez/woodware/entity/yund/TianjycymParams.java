package com.gez.woodware.entity.yund;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.*;
import lombok.Data;
import com.gez.woodware.entity.basics.*;
import javax.validation.constraints.*;

@Data
@ApiModel(description = "添加异常页面传入参数对象类")
public class TianjycymParams {

   @ApiModelProperty("标识")
   private String id;

   @Size(min=0, max=50,message = "类型最大长度为50")
   @ApiModelProperty("类型")
   private String tyep;

   @Size(min=0, max=50,message = "卡号最大长度为50")
   @ApiModelProperty("卡号")
   private String cardNumber;

   @Size(min=0, max=50,message = "持卡人最大长度为50")
   @ApiModelProperty("持卡人")
   private String cardholder;

   @Size(min=0, max=50,message = "车牌号最大长度为50")
   @ApiModelProperty("车牌号")
   private String carNumber;

   @Size(min=0, max=500,message = "描述最大长度为500")
   @ApiModelProperty("描述")
   private String details;

   @ApiModelProperty("添加时间")
   private String addTime;

}
