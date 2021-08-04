package com.gez.woodware.mapper.basics;

import com.gez.woodware.entity.basics.*;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;


@Mapper
public interface WeChatUserMapper {

    void delete_Weixyh_openid(String openid);

    User getUserByOpenId(String openid);

    User getUserByYonghzh(String yonghzh);



    String getsjzt(String yonghzh);




    WxxcxzhData getAppid(String systemName);

    int getyanzm(wxRegisterParam params);

    void wxcfe3344d5b75bfb4(HashMap<String, Object> params);

    void userInfo(HashMap<String, Object> params);


    void insert_Weixyh(HashMap<String, Object> params);
    void insert_shoujyzm(HashMap<String, Object> params);

    void update_shoujyzm(String phone);

    int getTodayYanzm(String phone);


    WxUserData getuserInfo(String userId);

    void insert_yongh(HashMap<String, Object> map);
    void insert_gongr_sij(HashMap<String, Object> map);

}
