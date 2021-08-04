package com.gez.woodware.entity.tongj;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.*;
import lombok.Data;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.gez.woodware.entity.basics.*;

@Data
@ApiModel(description = "按车队统计运量管理返回对象类")
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class AncdtjylglData {

   @ApiModelProperty("主键标识")
	private String id;

   @ApiModelProperty("车队")
	private String ched;

   @ApiModelProperty("运量")
	private String yunl;

}
