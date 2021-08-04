package com.gez.woodware.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class DzShortMessageYP {
    //
    // private static String URI_GET_USER_INFO =
    // "http://yunpian.com/v1/user/get.json";
    //
    // // 通用发送接口的http地址
    // private static String URI_SEND_SMS =
    // "http://yunpian.com/v1/sms/send.json";
    //
    // // 模板发送接口的http地址
    // private static String URI_TPL_SEND_SMS =
    // "http://yunpian.com/v1/sms/tpl_send.json";
    //
    // // 发送语音验证码接口的http地址
    // private static String URI_SEND_VOICE =
    // "http://yunpian.com/v1/voice/send.json";

    // 编码格式。发送编码格式统一用UTF-8
    public static  String ENCODING = "UTF-8";
    // 90a13a6a1536af6b7933b7c7bdab65f3
    private static String apikey   = "649efd7e29d7296666562e6229104c0e";


    /**
     * 发送单条短信
     *
     * @param apikey
     * @param text
     * @param mobile
     * @return
     */
    public static String singleSend(String apikey, String text, String mobile) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("text", text);
        params.put("mobile", mobile);
        return post("https://sms.yunpian.com/v2/sms/single_send.json", params);
    }

    /**
     * 指定模板群发
     *
     * @param apikey
     * @param text
     * @param mobile 单号码：15205201314 多号码：15205201314,15205201315
     * @return
     */
    public static String tpl_batch_send(String mobile, String tpl_value, long tpl_id) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("tpl_value", tpl_value);
        params.put("tpl_id", String.valueOf(tpl_id));
        params.put("mobile", mobile);

        System.out.println(params.toString());
        return post("https://sms.yunpian.com/v2/sms/tpl_single_send.json", params);


    }

    /**
     * 拼装数据
     *
     * @param apikey
     * @param text
     * @param mobile 单号码：15205201314 多号码：15205201314,15205201315
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String tpl_load_data(HashMap<String, String> map) throws UnsupportedEncodingException {

        String retval = "";

        for (Map.Entry<String, String> entry : map.entrySet()) {
//			System.out.println("Key = " + entry.getKey() + ", Value = "
//					+ entry.getValue());

            retval += URLEncoder.encode("#" + entry.getKey() + "#", "UTF-8") + "=" + URLEncoder.encode(entry.getValue(), "UTF-8");

        }

        return retval;

    }

    /**
     * 指定模板群发,用于测试，不会实际发送
     *
     * @param apikey
     * @param text
     * @param mobile 单号码：15205201314 多号码：15205201314,15205201315
     * @return
     */
    public static String tpl_batch_send_test(String mobile, String tpl_value, long tpl_id) {

        System.out.println("测试短信消息，不会实际发送");

        return null;
    }

    public static String post(String url, Map<String, String> paramsMap) {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            HttpPost method = new HttpPost(url);
            if (paramsMap != null) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
            }
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity, ENCODING);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        System.out.println("responseText" + responseText);
        return responseText;
    }
}
