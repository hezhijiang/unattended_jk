package com.gez.woodware.util.weix;

import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.gez.woodware.entity.basics.DecodeWxAppPhoneData;
import com.gez.woodware.entity.basics.WxxcxzhData;
import com.gez.woodware.util.HttpsUtil;
import com.gez.woodware.util.wxxcxPay.PayMentUtil;
import org.apache.xerces.impl.dv.util.Base64;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;


public class DzWxPulic {

    private        RedisTemplate redisTemplate;
    private static String        wxxcs_appid  = "";
    private static String        wxxcs_SECRET = "";

    private String SystemName;

    public DzWxPulic(WxxcxzhData data) {
        wxxcs_appid = data.getAppid();
        wxxcs_SECRET = data.getSecert();

        System.out.println(data.getSecert());
        this.SystemName = data.getSystem();
        this.redisTemplate = data.getRedisTemplate();
    }



    public DzWxPulic(RedisTemplate redisTemplate) {

        this.redisTemplate = redisTemplate;
    }




    public String decodeWxAppPhoneOpenId(String encrypted, String iv, String openId) throws Exception {

        String session_key = (String) redisTemplate.opsForValue().get(openId + "session_key");
        String phoneNumber = "";
        DecodeWxAppPhoneData phoneData = new DecodeWxAppPhoneData();
        // 如果JasonObject或opeid为空则登录失败
        if (session_key != null && session_key.length() > 0) {

            byte[] encrypData = Base64.decode(encrypted);
            byte[] ivData = Base64.decode(iv);
            byte[] sessionKey = Base64.decode(session_key);
            String resultString = null;
            AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivData);
            SecretKeySpec keySpec = new SecretKeySpec(sessionKey, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            resultString = new String(cipher.doFinal(encrypData), "UTF-8");


            JSONObject object = JSONObject.parseObject(resultString);
            // 拿到手机号码
            phoneNumber = object.getString("phoneNumber");

            System.out.println(phoneNumber);

        }

        return phoneNumber;
    }


    public String getOpenid(String code) {
        // 获取Web_access_tokenhttps的请求地址
        String Web_access_tokenhttps = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

        String openId = "";

        String tokenurl = String.format(Web_access_tokenhttps, wxxcs_appid, wxxcs_SECRET, code);


        // 通过https方式请求获得web_access_token并获得小程序的返回
        String token_response = HttpsUtil.httpsRequestToString(tokenurl, "GET", null);

        // 通过JsonObject解析小程序返回数据
        JSONObject jsonObject = JSON.parseObject(token_response);

        // 如果JasonObject或opeid为空则登录失败
        if (null != jsonObject && jsonObject.getString("openid") != null) {

            // 从jsonObject中获取sessionKey的值
            String session_key = jsonObject.getString("session_key");
            openId = jsonObject.getString("openid");

            String key = openId + "session_key";

            if (redisTemplate != null) {
                redisTemplate.opsForValue().set(key, session_key);

                System.out.println("---------------->>>>>>>>>>.." + redisTemplate.opsForValue().get(key));
            }


        }

        return openId;
    }


    /**
     * 生成微信小程序吗
     * https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/qr-code/wxacode.getUnlimited.html
     *
     * @param page
     * @param scene
     * @return
     */
    public boolean wxacodeget(String page, String scene, String fileName, String path) {

        boolean retCode = false;
        String TEMPLATE_URL = "https://api.weixin.qq.com/wxa/getwxacode?access_token=ACCESS_TOKEN";

        String token = getToken(); // 微信凭证，access_token
//		String token = getTokenOfwx();
        String url = TEMPLATE_URL.replace("ACCESS_TOKEN", token);

        JSONObject json = new JSONObject();
        try {
            json.put("scene", "123");

            retCode = HttpsUtil.postWxImage(url, json.toString(), fileName, path);

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        return retCode;
    }

    /**
     * 生成微信小程序吗
     * https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/qr-code/wxacode.getUnlimited.html
     * 通过该接口生成的小程序码，永久有效，数量暂无限制。
     *
     * @param page
     * @param scene
     * @return
     */
    public boolean getwxacodeunlimit(String page, String scene, String fileName, String path) {

        boolean retCode = false;
        String TEMPLATE_URL = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=ACCESS_TOKEN";


        String ACCESS_TOKEN = getToken();


        String url = TEMPLATE_URL.replace("ACCESS_TOKEN", ACCESS_TOKEN);

        JSONObject json = new JSONObject();
        try {
            json.put("scene", scene);

            retCode = HttpsUtil.postWxImage(url, json.toString(), fileName, path);

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        return retCode;
    }

    // 获取微信的token，传入系统中，获取到token以后存入redis中，失效时间为7100.
    public String getToken() {
        String rediskey = SystemName + "Token";
        String token = (String) redisTemplate.opsForValue().get(rediskey);

        System.out.println(rediskey + " get token " + token);
        if (token == null) {

            token = getTokenOfwx();
            System.out.println("get token " + token);
            if (token != "") {
                setToken(token);
            }
        }
        System.out.println("token = "+token);
        return token;

    }

    private String getTokenOfwx() {

        // 凭证获取（GET）
        String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

        String requestUrl = token_url.replace("APPID", wxxcs_appid).replace("APPSECRET", wxxcs_SECRET);

        // 通过https方式请求获得web_access_token并获得小程序的返回
        String token_response = HttpsUtil.httpsRequestToString(requestUrl, "GET", null);


        // 通过JsonObject解析小程序返回数据
        JSONObject jsonObject = JSON.parseObject(token_response);

        if (null != jsonObject) {
            try {
                System.out.println("获取token =" + jsonObject.getString("access_token"));
                return jsonObject.getString("access_token");
            } catch (JSONException e) {
                System.out.println(e.getMessage());
            }
        }
        return "";
    }

    private void setToken(String token) {
        String rediskey = SystemName + "Token";

        redisTemplate.opsForValue().set(rediskey, token);


    }


}
