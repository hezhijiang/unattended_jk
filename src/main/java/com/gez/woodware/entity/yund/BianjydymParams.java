package com.gez.woodware.entity.yund;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.*;
import lombok.Data;
import com.gez.woodware.entity.basics.*;
import javax.validation.constraints.*;

@Data
@ApiModel(description = "编辑运单页面传入参数对象类")
public class BianjydymParams {

   @ApiModelProperty("标识")
   private String id;

   @Size(min=0, max=50,message = "编号最大长度为50")
   @ApiModelProperty("编号")
   private String number;

   @Size(min=0, max=50,message = "项目最大长度为50")
   @ApiModelProperty("项目")
   private String project;

   @Size(min=0, max=50,message = "装货地最大长度为50")
   @ApiModelProperty("装货地")
   private String placeOfLoading;

   @Size(min=0, max=50,message = "卸货地最大长度为50")
   @ApiModelProperty("卸货地")
   private String placeOfDischarge;

   @NotEmpty(message = "方量不能为空")
   @ApiModelProperty("方量")
   private String volume;

   @NotEmpty(message = "运费不能为空")
   @ApiModelProperty("运费")
   private String freight;

   @ApiModelProperty("车头")
   private String headstock;

   @ApiModelProperty("车厢")
   private String carriage;

   @Size(min=0, max=50,message = "来源最大长度为50")
   @ApiModelProperty("来源")
   private String source;

   @Size(min=0, max=50,message = "卡号最大长度为50")
   @ApiModelProperty("卡号")
   private String cardNumber;

   @Size(min=0, max=50,message = "车牌号最大长度为50")
   @ApiModelProperty("车牌号")
   private String carNumber;

   @Size(min=0, max=50,message = "持卡人最大长度为50")
   @ApiModelProperty("持卡人")
   private String cardholder;

   @NotEmpty(message = "状态不能为空")
   @ApiModelProperty("状态")
   private String state;

   @Size(min=0, max=50,message = "备注最大长度为50")
   @ApiModelProperty("备注")
   private String remarks;

   @Size(min=0, max=50,message = "添加时间最大长度为50")
   @ApiModelProperty("添加时间")
   private String addTime;

}
