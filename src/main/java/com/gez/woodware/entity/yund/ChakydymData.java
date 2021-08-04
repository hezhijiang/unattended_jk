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
@ApiModel(description = "查看运单页面返回对象类")
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class ChakydymData {

    @ApiModelProperty("标识")
	private String id;

    @ApiModelProperty("编号")
	private String number;

    @ApiModelProperty("项目")
	private String project;

    @ApiModelProperty("装货地")
	private String placeOfLoading;

    @ApiModelProperty("卸货地")
	private String placeOfDischarge;

    @ApiModelProperty("方量")
	private String volume;

    @ApiModelProperty("运费")
	private String freight;

    @ApiModelProperty("车头")
	private String headstock;

    @ApiModelProperty("车厢")
	private String carriage;

    @ApiModelProperty("来源")
	private String source;

    @ApiModelProperty("卡号")
	private String cardNumber;

    @ApiModelProperty("车牌号")
	private String carNumber;

    @ApiModelProperty("持卡人")
	private String cardholder;

    @ApiModelProperty("状态")
	private String state;

    @ApiModelProperty("备注")
	private String remarks;

    @ApiModelProperty("添加时间")
	private String addTime;

}
