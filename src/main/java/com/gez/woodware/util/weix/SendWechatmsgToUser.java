package com.gez.woodware.util.weix;

import com.alibaba.fastjson.JSONObject;
import com.gez.woodware.entity.basics.WechatMould;
import com.gez.woodware.entity.basics.WxxcxzhData;
import com.gez.woodware.util.HttpsUtil;


import java.util.Iterator;
import java.util.Map;

public class SendWechatmsgToUser {


    public static String ajax(WxxcxzhData wxzh, String openid, Map<String, Object> data) {
        String template_id = "W-zPJ5E8Be2o9lHbMchqMKsUX1IbMz1sg39VRJWVNac";
        WechatMould mould = new WechatMould();
//        String TEMPLATE_URL = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN";


        try {


            String token = new DzWxPulic(wxzh).getToken(); // 微信凭证，access_token

            System.out.println("token = " + token);
            String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + token;
            System.out.println("url = " + url);

            JSONObject json = new JSONObject();
            json.put("touser", openid);
            json.put("page", "/pages/jyz_jiayjl/index");
            json.put("template_id", template_id);
            json.put("data", getNewOrderMould(data));
            System.out.println("json  : " + json.toString());


            String result = HttpsUtil.httpsRequestToString(url, "POST", json.toString());

            System.out.println(result);


            JSONObject jsonObject = JSONObject.parseObject(result);

            String errmsg = jsonObject.getString("errmsg");
            if (!"ok".equals(errmsg)) {// 如果为errmsg为ok，则代表发送成功。

                return "error";
            }
        } catch (Exception e) {
            System.out.println("发送消息");
            System.out.println(e.getMessage());
        }
        return "success";
    }


    public static JSONObject getNewOrderMould(Map<String, Object> maps) {

        JSONObject data = new JSONObject(); // 这个只是模板中的data属性，因为他是嵌套型的所以把他单独拿出来，这个data也是一个单独的类，具体的属性就是下面的这些
        try {

            String key;
            Object value;
            Iterator it = maps.keySet().iterator();

            while (it.hasNext()) {
                key = (String) it.next();
                value = maps.get(key);
                JSONObject data2 = new JSONObject();
                data2.put("value", value);
                data.put(key, data2);
            }

            // JSONObject data2 = new JSONObject();
            // data2.put("value", "商品");
            //
            // data.put("keyword2", data2);
            //
            // data.put("keyword2", data2);
            //
            // data.put("keyword2", data2);

        } catch (Exception e) {
            System.out.println("json数据出错");
        }
        return data;

    }
}
