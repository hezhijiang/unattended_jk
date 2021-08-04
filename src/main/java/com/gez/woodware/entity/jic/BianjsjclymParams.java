package com.gez.woodware.entity.jic;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.*;
import lombok.Data;
import com.gez.woodware.entity.basics.*;
import javax.validation.constraints.*;

@Data
@ApiModel(description = "编辑司机车辆页面传入参数对象类")
public class BianjsjclymParams {

   @ApiModelProperty("标识")
   private String id;

   @Size(min=0, max=50,message = "司机姓名最大长度为50")
   @ApiModelProperty("司机姓名")
   private String driverName;

   @NotEmpty(message = "司机电话不能为空")
   @Size(min=0, max=11,message = "司机电话最大长度为11")
   @ApiModelProperty("司机电话")
   private String driverPhoneNumber;

   @NotEmpty(message = "车牌号不能为空")
   @Size(min=0, max=7,message = "车牌号最大长度为7")
   @ApiModelProperty("车牌号")
   private String carNumber;

   @Size(min=0, max=50,message = "车型最大长度为50")
   @ApiModelProperty("车型")
   private String model;

   @Size(min=0, max=50,message = "方量最大长度为50")
   @ApiModelProperty("方量")
   private String volume;

   @Size(min=0, max=50,message = "卡号最大长度为50")
   @ApiModelProperty("卡号")
   private String cardNumber;

   @Size(min=0, max=50,message = "车队最大长度为50")
   @ApiModelProperty("车队")
   private String fleet;

   @Size(min=0, max=50,message = "承运商最大长度为50")
   @ApiModelProperty("承运商")
   private String carrier;

   @ApiModelProperty("身份证")
   private String iDCard;

   @ApiModelProperty("驾驶证")
   private String drivingLicense;

   @ApiModelProperty("行驶证")
   private String vehicleLicense;

   @ApiModelProperty("营运证")
   private String taxiLicense;

   @ApiModelProperty("卡面")
   private String cardFace;

   @Size(min=0, max=50,message = "修改时间最大长度为50")
   @ApiModelProperty("修改时间")
   private String revisionTime;

   @NotEmpty(message = "状态不能为空")
   @ApiModelProperty("状态")
   private String stage;

}
