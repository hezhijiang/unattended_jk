package com.gez.woodware.entity.basics;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
public class wxUserParams {


    @NotNull
    @ApiModelProperty("微信openId")
    private String openId;

    @NotNull
    @ApiModelProperty("昵称")
    private String nic;


    @NotNull
    @ApiModelProperty("头像")
    private String toux;



    public void setNic(String nic) throws UnsupportedEncodingException {
        this.nic = URLEncoder.encode(nic, "utf-8");
    }
}
