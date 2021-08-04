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
@ApiModel(description = "删除运单页面返回对象类")
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class ShancydymData {

    @ApiModelProperty("标识")
	private String id;

}
