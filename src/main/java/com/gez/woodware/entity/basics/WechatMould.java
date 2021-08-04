package com.gez.woodware.entity.basics;


import java.util.Iterator;
import java.util.Map;



import com.alibaba.fastjson.JSONObject;

public class WechatMould {

    // touser string 是 接收者（用户）的 openid
    // template_id string 是 所需下发的模板消息的id
    // page string 否
    // 点击模板卡片后的跳转页面，仅限本小程序内的页面。支持带参数,（示例index?foo=bar）。该字段不填则模板无跳转。
    // form_id string 是 表单提交场景下，为 submit 事件带上的 formId；支付场景下，为本次支付的 prepay_id
    // data Object 否 模板内容，不填则下发空模板。具体格式请参考示例。
    // emphasis_keyword string 否 模板需要放大的关键词，不填则默认无放大

    private String touser;
    private String template_id = "";
    private String page = "pages/public/authenticateUser/authenticateUser";
    private String form_id;
    private String emphasis_keyword = "";

    public String getTouser() {
        return touser;
    }

    public String getTemplate_id(String type) {

        if (type.equals("新订单通知")) {

            template_id = "tewxw23uJcQMIt6DhRm9246gVWhpkhMDXkpqMtDEsfg";
        } else if (type.equals("新订单通知v1")) {

            template_id = "tewxw23uJcQMIt6DhRm924ctdR3d5BXduSDw-Myroc4";

        }

        return template_id;
    }

    public String getPage() {
        return page;
    }

    public String getForm_id() {
        return form_id;
    }

    public String getEmphasis_keyword() {
        return emphasis_keyword;
    }

    // 新订单
    public JSONObject getNewOrderMould(Map<String, String> maps) {

        JSONObject data = new JSONObject(); // 这个只是模板中的data属性，因为他是嵌套型的所以把他单独拿出来，这个data也是一个单独的类，具体的属性就是下面的这些
        try {

            String key;
            String value;
            Iterator it = maps.keySet().iterator();

            while (it.hasNext()) {
                key = (String) it.next();
                value = maps.get(key);
                JSONObject data2 = new JSONObject();
                data2.put("value", maps.get(key));
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

    public JSONObject getWechatMould() {

        JSONObject data = new JSONObject(); // 这个只是模板中的data属性，因为他是嵌套型的所以把他单独拿出来，这个data也是一个单独的类，具体的属性就是下面的这些
        try {
            JSONObject data1 = new JSONObject();
            data1.put("value", "test");
            JSONObject data2 = new JSONObject();
            data2.put("value", "商品");

            data.put("keyword1", data1);
            data.put("keyword2", data2);

        } catch (Exception e) {
            System.out.println("json数据出错");
        }
        return data;

    }
}
