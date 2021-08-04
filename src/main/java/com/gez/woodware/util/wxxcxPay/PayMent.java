package com.gez.woodware.util.wxxcxPay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

public class PayMent {


    //    微信小程序 appid
    static String appid = "wx9ea2a061439451a2";


    // 商户号的id
    static String mchId = "1564196711";
    //商户号的key 和 微信小程序无关
    static String key   = "118e5424eebd45b7ae093af1a30d1547";


    public PayMent(String appid) {
        this.appid = appid;
    }

    /**
     * 微信小程序胡字符
     *

     * @param openid       用户openid
     * @return
     * @throws Exception
     * @auth 何志江
     * @dateTime 2019-11-27 下午2:34:34
     * @explain
     */
    public static Map<String, String> wxXcxPay(String body,String dingdbh, String dingdje, String openid, String id) throws Exception {


        String payUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder"; // 统一下单接口  // 商品名称
        String notify_url = "https://chengp.luxunda.cn/WeChatuser/wxNotify";
        String nonceStr = PayMentUtil.getRandom32(32);
        int jine = (int) (Double.valueOf(dingdje) * 100);


        String total_fee = String.valueOf(jine);

        // 组装参数，用户生成统一下单接口的签名
        Map<String, String> packageParams = new HashMap<String, String>();


        packageParams.put("appid", appid);
        packageParams.put("body", body);
        packageParams.put("mch_id", mchId);
        packageParams.put("nonce_str", nonceStr);
        packageParams.put("notify_url", notify_url);// 支付成功后的回调地址
        packageParams.put("out_trade_no", dingdbh);// 商户订单号
        packageParams.put("sign_type", "MD5");
        packageParams.put("spbill_create_ip", PayMentUtil.getIp());// 用户ip
        packageParams.put("total_fee", total_fee);// 支付金额，这边需要转成字符串类型，否则后面的签名会失败
        packageParams.put("trade_type", "JSAPI");// 支付方式
        packageParams.put("openid", openid);
        packageParams.put("prepay_id", dingdbh);

        String prestr = PayMentUtil.createLinkString(packageParams); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串

        // MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
        String mysign = PayMentUtil.sign(prestr, key, "utf-8").toUpperCase();

        // System.out.println(mysign);
        //
        // mysign =WXPayUtil.generateSignature(packageParams, key,
        // SignType.MD5);

        System.out.println(mysign);
        // // 拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去

        packageParams.put("sign", mysign);

        String xml = PayMentUtil.mapToXml(packageParams);

        System.out.println("调试模式_统一下单接口 请求XML数据：" + xml);

        String res = PayMentUtil.httpRequest(payUrl, "POST", xml);

        System.out.println("调试模式_统一下单接口 请求res数据：" + res);
        // 将解析结果存储在HashMap中
        Map map = PayMentUtil.doXMLParse(res);

        String return_code = (String) map.get("return_code");// 返回状态码

        Map<String, String> result = new HashMap<String, String>();// 返回给小程序端需要的参数
        String prepay_id = null;
        if (return_code == "SUCCESS" || return_code.equals(return_code)) {
            prepay_id = (String) map.get("prepay_id");// 返回的预付单信息

            result.put("nonceStr", nonceStr);
//			result.put("package", prepay_id);
            result.put("package", "prepay_id=" + prepay_id);
            Long timeStamp = System.currentTimeMillis() / 1000;
            result.put("timeStamp", timeStamp + "");// 这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误
            // 拼接签名需要的参数
            String stringSignTemp = "appId=" + appid + "&nonceStr=" + nonceStr + "&package=prepay_id=" + prepay_id + "&signType=MD5&timeStamp=" + timeStamp;
            // 再次签名，这个签名用于小程序端调用wx.requesetPayment方法
            String paySign = PayMentUtil.sign(stringSignTemp, key, "utf-8").toUpperCase();

            result.put("paySign", paySign);
        }
        result.put("appid", appid);
        result.put("id", id);

        return result;
    }


    public static String WxNotfily(HttpServletRequest request) throws Exception {

        String SUCCESSBianh = "";
        BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        String notityXml = sb.toString();
        if (notityXml == null) {
            return null;
        }

        System.out.println("接收到的报文：" + notityXml);


        String resXml = "";

        Map map = PayMentUtil.doXMLParse(notityXml);

        String returnCode = (String) map.get("return_code");
        if ("SUCCESS".equals(returnCode)) {


            //验证签名是否正确
            Map<String, String> validParams = PayMentUtil.paraFilter(map);  //回调验签时需要去除sign和空值参数

//            
            String validStr = PayMentUtil.createLinkString(validParams); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串

            // MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
            String sign = PayMentUtil.sign(validStr, key, "utf-8").toUpperCase();


            // 因为微信回调会有八次之多,所以当第一次回调成功了,那么我们就不再执行逻辑了

            //根据微信官网的介绍，此处不仅对回调的参数进行验签，还需要对返回的金额与系统订单的金额进行比对等
            if (sign.equals(map.get("sign"))) {


                SUCCESSBianh = (String) map.get("out_trade_no");


                System.out.println(SUCCESSBianh);

                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
            } else {
                System.out.println("微信支付回调失败!签名不一致");
            }
        } else {
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }
        System.out.println(resXml);

        br.close();

        return SUCCESSBianh;
    }

    /**
     * 微信小程序 企业付款到用户的零钱，不可退款
     *
     * @param zhifms       支付描述
     * @param out_trade_no 订单编号
     * @param total_fee    金额
     * @param openid       目标用户的openId，收款人
     * @return true/false
     * @throws Exception
     * @author 何志江
     * @serialData 2020-04-14 10:11:24
     */
    public boolean wxXcxPayBusinesToChange(String zhifms, String out_trade_no, String total_fee, String openid) throws Exception {


        String payUrl = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";

        int jine = (int) (Double.valueOf(total_fee) * 100);


        // 组装参数，用户生成统一下单接口的签名
        Map<String, String> packageParams = new HashMap<String, String>();
        packageParams.put("mch_appid", appid);
        packageParams.put("mchid", mchId);
        packageParams.put("nonce_str", PayMentUtil.getRandom32(32));
        packageParams.put("partner_trade_no", out_trade_no);// 商户订单号 可以是数字或字符串
        packageParams.put("openid", openid);
        packageParams.put("check_name", "NO_CHECK");  //校验用户姓名选项
        packageParams.put("amount", String.valueOf(jine));// 支付金额，	企业付款金额，单位为分

        packageParams.put("desc", zhifms); //企业付款备注

        packageParams.put("spbill_create_ip", PayMentUtil.getIp());// 用户ip

        String prestr = PayMentUtil.createLinkString(packageParams); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串


        // MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
        String mysign = PayMentUtil.sign(prestr, key, "utf-8").toUpperCase();


        packageParams.put("sign", mysign);

        String xml = PayMentUtil.mapToXml(packageParams);
        System.out.println(payUrl);

        System.out.println(xml);
        String res = CertHttpUtil.postData(payUrl, xml, mchId);
        System.out.println(res);
        // 将解析结果存储在HashMap中
        Map map = PayMentUtil.doXMLParse(res);

        String return_code = (String) map.get("return_code");// 返回状态码


        if (return_code == "SUCCESS" || return_code.equals(return_code)) {
            return true;
        }
        return false;
    }
}
