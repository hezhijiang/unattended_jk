package com.gez.woodware.entity.basics;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Data
@ApiModel(description = "微信用户信息返回对象类")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class WxUserData {


    @ApiModelProperty("微信openId")
    private String openid;


    @ApiModelProperty("昵称")
    private String nic;

    @ApiModelProperty("订单id")
    private String dingdid;

    @ApiModelProperty("类型")
    private String leix;

    @ApiModelProperty("头像")
    private String toux;


    @ApiModelProperty("消息")
    private String xiaox;



    public void setNic(String nic) throws UnsupportedEncodingException {
        this.nic = URLDecoder.decode(nic, "utf-8");
    }
}
