package com.gez.woodware.entity.jic;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.*;
import lombok.Data;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.*;
import com.gez.woodware.util.*;
import com.gez.woodware.entity.basics.*;

@Data
@ApiModel(description = "查看司机车辆页面返回对象类")
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class ChaksjclymData {

    @ApiModelProperty("标识")
	private String id;

    @ApiModelProperty("司机姓名")
	private String driverName;

    @ApiModelProperty("司机电话")
	private String driverPhoneNumber;

    @ApiModelProperty("车牌号")
	private String carNumber;

    @ApiModelProperty("车型")
	private String model;

    @ApiModelProperty("方量")
	private String volume;

    @ApiModelProperty("卡号")
	private String cardNumber;

    @ApiModelProperty("车队")
	private String fleet;

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

    @ApiModelProperty("同步编号")
	private String synchronizationNumber;

    @ApiModelProperty("添加时间")
	private String addTime;

    @ApiModelProperty("修改时间")
	private String revisionTime;

    @ApiModelProperty("添加人")
	private String userId;

    @ApiModelProperty("状态")
	private String stage;

}
