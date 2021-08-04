package com.gez.woodware.service.basics;

import com.gez.woodware.entity.basics.*;
import com.gez.woodware.mapper.basics.WeChatUserMapper;


import com.gez.woodware.util.DzShortMessageYP;
import com.gez.woodware.util.weix.DzWxPulic;
import com.gez.woodware.util.ParamsUtil;
import com.gez.woodware.util.weix.SendWechatmsgToUser;

import org.springframework.beans.factory.annotation.Autowired;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.net.URLEncoder;

@Slf4j
@Service
public class WeChatUserService extends BaseService {

    @Autowired
    private WeChatUserMapper mapper;




    /**
     * 微信用户授权注册
     *
     * @param params
     * @return
     */
    public User authorizedLogin(wxRegisterPhoneParams params) throws Exception {
        //获取Appid
        WxxcxzhData data = mapper.getAppid(params.getSystem());
        data.setRedisTemplate(redisTemplate);
        //获取openid和手机号
        String shoujh = new DzWxPulic(data).decodeWxAppPhoneOpenId(params.getEncrypted(), params.getIv(), params.getOpenId());


        //注册
        User u = Register(shoujh, params.getOpenId(), params.getSystem());
        u = SetUser(u, "微信用户通过授权注册 > " + shoujh);


        if (u.getBummc().equals("注册")) {
            log(u.getId(), "授权注册", "微信用户通过微信授权方式进行注册");
        } else {
            log(u.getId(), "授权登录", "微信用户通过微信授权方式进行登录");
        }

        return u;

    }

    /**
     * 微信用户注册
     *
     * @param params
     * @return
     */
    public User register(wxRegisterParam params) {


        if (mapper.getyanzm(params) > 0) {
            User u = Register(params.getShoujh(), params.getOpenId(), params.getSystemName());


            u = SetUser(u, "微信用户注册 > " + params.getShoujh());

            if (u.getBummc().equals("注册")) {
                log(u.getId(), "手机号注册", "微信用户通过手机号和验证码方式进行注册");
            } else {
                log(u.getId(), "手机号登录", "微信用户通过手机号和验证码方式进行登录");
            }


            return u;
        } else {
            return null;
        }


    }

    public void userInfo(wxUserParams params) {
        mapper.userInfo(ParamsUtil.getUserParam(request, params));
    }

    public WxUserData getuserInfo(String userId) {
        return mapper.getuserInfo(userId);
    }


    public void logout(String openid) {

        log(openid, "解除绑定", "微信用户" + openid + " 解除与当前手机号的绑定关系");
        redisTemplate.delete(request.getSession().getId());
        mapper.delete_Weixyh_openid(openid);

    }


    private User Register(String shoujh, String openId, String systemName) {
        String leix = "注册";

        User u = mapper.getUserByYonghzh(shoujh);


        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("shoujh", shoujh);
        map.put("yonghmm", "123456");
        map.put("openId", openId);

        map.put("systemName", systemName);

        if (u == null) {
            u = new User();
            String yonghid = UUID.randomUUID().toString();
            u.setId(yonghid);
            map.put("yonghid", u.getId());
            mapper.insert_yongh(map);

            u = mapper.getUserByYonghzh(shoujh);

        } else {
//            String yonghid = UUID.randomUUID().toString();
//            u.setId(yonghid);
            map.put("yonghxm", u.getYonghxm());
            map.put("yonghid", u.getId());
//            mapper.insert_gongr_sij(map);

//            map.put("yonghid", u.getId());

            leix = "登录";
        }
        if (u != null) {
        mapper.insert_Weixyh(map);

        u.setOpenId(openId);
        u.setBummc(leix);
        }
        return u;

    }


    public RetResponse Login(WeChatUserLoginParam param) {
        WxxcxzhData data = mapper.getAppid(param.getSystem());

        data.setRedisTemplate(redisTemplate);

        String openId = new DzWxPulic(data).getOpenid(param.getCode());
        if (openId != null && openId.length() > 5) {
            User u = mapper.getUserByOpenId(openId);
            if (u != null && u.getId() != null) {
                u.setOpenId(openId);


                u = SetUser(u, "openId > " + openId);

                log(openId, "微信登录", "微信用户" + openId + " 通过微信code进行登录");

                return new RetResponse(u);
            }
        }

        return new RetResponse(false, openId);
    }


    private User SetUser(User u, String message) {
        if (u == null) {
            log.debug(message + " 登录失败");
        } else {
            u.setToken(request.getSession().getId());
            try {


                redisTemplate.opsForValue().set(u.getToken(), u);


            } catch (Exception e) {
                log.error(message + "登录,存入redis失败 ", e.getMessage());
            } finally {
                request.getSession().setAttribute("user", u);
            }
        }

        return u;
    }

    public String authorizedLogin(String shoujh) throws UnsupportedEncodingException {

        if (mapper.getTodayYanzm(shoujh) > 10) {
            return "一个手机号一天只能获取10条验证码";
        } else {
            String yanzm = ParamsUtil.getRandomFour();
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("shoujh", shoujh);
            map.put("yanzm", yanzm);
            mapper.insert_shoujyzm(map);


            String tplvalue = URLEncoder.encode("#code#", "UTF-8") + "=" + URLEncoder.encode(yanzm, "UTF-8");
            DzShortMessageYP.tpl_batch_send(shoujh, tplvalue, 3570740);
            System.out.println("3570740 发送验证码");

            return yanzm;
        }


    }


    public boolean zhif(String zhifbh) throws IOException {

        if (zhifbh != null && zhifbh.length() > 0) {

            String redisbh1 = (String) redisTemplate.opsForValue().get(zhifbh);

            if(redisbh1!=null){
                System.out.println("支付成功，进行回调  = " + redisbh1);
                redisTemplate.opsForValue().set(zhifbh,null);


            }


            return true;
        } else {
            return false;
        }

    }


    public RetResponse message(String systemName) {
        WxxcxzhData wxzh = mapper.getAppid(systemName);

        wxzh.setRedisTemplate(redisTemplate);

        Map<String, Object> dataparam = new HashMap<String, Object>();


        dataparam.put("name1", "何志江");// 货主
        dataparam.put("amount2", 100.00);// 线路方向
        dataparam.put("date4", "2019-12-07 11:11:11");// 订单内容
        dataparam.put("thing5", "气物资调拨以后给员");// 订单内容


        //        form.getOpenid(),
//						form.getFormid(), "新订单通知v1", dataparam);


//        return new RetResponse(SendWechatmsgToUser.getNewOrderMould(dataparam));
        return new RetResponse(SendWechatmsgToUser.ajax(wxzh, "o7ogP5EBq5hGBGawfelNn4VSVULQ", dataparam));
    }


    public RetResponse wxXcxPayBusinesToChange(String systemName) throws Exception {

//         PayMent.wxXcxPayBusinesToChange("测试","2012cs11541316451161","0.3","o7ogP5EBq5hGBGawfelNn4VSVULQ");


        redisTemplate.opsForList().leftPush("list", "a");


        return new RetResponse("发送成功");


    }

}
