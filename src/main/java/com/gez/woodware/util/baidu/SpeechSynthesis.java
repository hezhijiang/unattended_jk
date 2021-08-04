package com.gez.woodware.util.baidu;

import com.alibaba.fastjson.JSONObject;
import com.gez.woodware.util.DzHttpRequest;
import com.gez.woodware.util.ResourcesApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class SpeechSynthesis {


    private static RedisTemplate redisTemplate;

    //设置APPID/AK/SK
    public static String APP_ID       = "9089817";
    public static String API_KEY      = "mXCc3WIxqC4INH1VvvnpPqhoSr8XslYg";
    public static String SECRET_KEY   = "vMSMUxe1qwU4DN5Mtziwm14GSZB2DxaV";
    public static String access_token = "24.be9d517cbbc50607f757c7c23175d4dd.2592000.1605073815.282335-9089817";
    public static String path         = (String) ResourcesApplication.getCommonYml("resources.path");


    public SpeechSynthesis(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        access_token = (String) redisTemplate.opsForValue().get("baiduaccess_token");
        if (access_token == null) {
            getToken();
        }
    }

    public void getToken() {
        String url = "https://openapi.baidu.com/oauth/2.0/token";


        Map<String, String> params = new HashMap<String, String>();
        params.put("client_id", API_KEY);
        params.put("client_secret", SECRET_KEY);
        params.put("grant_type", "client_credentials");


        String ret = DzHttpRequest.sendGetParam(url, params);
        System.out.println("access_token = " + ret);
        JSONObject jsonObject = JSONObject.parseObject(ret);


        access_token = jsonObject.getString("access_token");


        redisTemplate.opsForValue().set("baiduaccess_token", access_token, 28, TimeUnit.DAYS);


    }


    public Object getaudio(String tex, String cuid) throws UnsupportedEncodingException {


        if (access_token != null) {


            String url = "https://tsn.baidu.com/text2audio";

            String urlString = URLEncoder.encode(tex, "UTF-8");

            Map<String, String> params = new HashMap<String, String>();
            params.put("tex", urlString);
            params.put("tok", access_token);
            params.put("cuid", cuid);
            params.put("ctp", "1");
            params.put("lan", "zh");
            params.put("vol", "10");
            params.put("spd", "5");
            params.put("pit", "5");
            params.put("per", "4"); //度小宇=1，度小美=0，度逍遥（基础）=3，度丫丫=4
            params.put("aue", "3");

            String retfileName = UUID.randomUUID().toString() + ".mp3";

            sendGetParam(url, params, path + "/" + retfileName);

            return retfileName;
        } else {
            return null;
        }

    }

    private String sendGetParam(String url, Map<String, String> params, String retfileName) {
        try {
            String parameters = "";
            for (String key : params.keySet()) {
                String value = URLEncoder.encode(params.get(key), "UTF-8");
                parameters += key + "=" + value + "&";
            }
            if (params.size() > 0) {
                parameters = parameters.substring(0, parameters.length() - 1);
            }
            return sendGet(url, parameters, retfileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "null";
    }


    private String sendGet(String url, String param, String retfileName) {
        String result = "";
        InputStream in = null;
        FileOutputStream outputStream = null;
        try {

            boolean isTrue = false;
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();

            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {

                if (map.get(key).contains("audio/mp3") && key.equals("Content-Type")) {
                    isTrue = true;
                    break;
                }
//                System.out.println(key + "--->" + map.get(key));
            }


            if (isTrue) {
                in = connection.getInputStream();
                System.out.println(retfileName);
                outputStream = new FileOutputStream(new File(retfileName));
                int length = 0;
                byte[] bytes = new byte[1024];
                while ((length = in.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, length);
                }
//            }else{
//                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                String line;
//                while ((line = in.readLine()) != null) {
//                    result += line;
//
//                }

            } else {
                System.out.println("获取数据失败");
            }

        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }

            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}
