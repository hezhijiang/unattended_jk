package com.gez.woodware.controller.basics;


import com.gez.woodware.entity.basics.RedisParams;
import com.gez.woodware.entity.basics.RetResponse;

import com.gez.woodware.service.basics.UserService;

import com.gez.woodware.util.ResourceUtil;

import com.gez.woodware.util.baidu.SpeechSynthesis;
import com.gez.woodware.util.weix.SendWechatmsgToUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.*;
import javax.validation.Valid;
import java.io.*;

import java.util.*;

/**
 * 资源获取.
 */
@Api(tags = "3 资源管理")
@Slf4j
@RestController
@RequestMapping("/resources")
public class ResourcesController {

    @Autowired
    private RedisTemplate redisTemplate;



    @GetMapping("/url")
    @ApiOperation(value = "3.1 通过URL获取资源", notes = "获取资源地址")
    public RetResponse url(HttpServletRequest request) throws UnsupportedEncodingException {

        String file = request.getParameter("file");
        if (file != null) {
            return new RetResponse(ResourceUtil.getResourceUrl(file));
        } else {
            return new RetResponse(false, "文件不能为空");
        }
    }

    @GetMapping("/files")
    @ApiOperation(value = "3.2 通过URL获取资源", notes = "获取资源内容")
    public void files(HttpServletRequest request, HttpServletResponse response) {


        String tsPath = request.getParameter("file");
        if (null != tsPath) {

            try {
                String realPath = ResourceUtil.getResourceRealPath(tsPath);

                System.out.println(realPath);
                File file = new File(realPath);
                response.setContentLength((int) file.length());
                InputStream inputStream = new FileInputStream(file);
                OutputStream os = response.getOutputStream();
                byte[] b = new byte[1024];
                int length;
                while ((length = inputStream.read(b)) > 0) {
                    os.write(b, 0, length);
                }
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

    }


    @PostMapping("/upload")
    @ApiOperation(value = "3.3 上传文件", notes = "单文件多文件、图片均用本接口")
    public RetResponse upload(HttpServletRequest request) {
        try {
            List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");

            List<Object> list = new ArrayList<Object>();

            for (MultipartFile f : files) {

                list.add(ResourceUtil.upload(f));
            }
            return new RetResponse(list);


        } catch (Exception e) {
            e.printStackTrace();
            return new RetResponse(false, "上传文件失败");
        }
    }

//    @GetMapping("/weixPay")
//    @ApiOperation(value = "3.4 上传文件", notes = "单文件多文件、图片均用本接口")
//    public RetResponse weixPay(String out_trade_no) {
//
//        try{
//
//
//            return new RetResponse(PayMent.getJSApiErwmUrl(out_trade_no));
//        }catch (Exception e){
//            return new RetResponse(false, "上传文件失败");
//        }
//
//
//    }

    @GetMapping("/wxxcxRefundSuccess")
    @ApiOperation(value = "3.4 上传文件", notes = "测试微信支付")
    public RetResponse wxxcxRefundSuccess(String payCode) {

        try {
            return new RetResponse(payCode);
        } catch (Exception e) {
            return new RetResponse(false, "获取paycode失败");
        }


    }



    @DeleteMapping("/deleteRedis{key}")
    @ApiOperation(value = "3.5 根据key删除redis", notes = "删除redis")
    public RetResponse deleteRedis(@PathVariable String key) {
        redisTemplate.delete(key);
        return new RetResponse("删除成功");
    }


    @PostMapping("/setRedis")
    @ApiOperation(value = "3.5 存入redis", notes = "存入redis中的值")
    public RetResponse setRedis(@Valid @RequestBody RedisParams param) {
        redisTemplate.opsForValue().set(param.getKey(), param.getValue());
        return new RetResponse("存入成功");
    }

    @GetMapping("/getRedis/{key}")
    @ApiOperation(value = "3.5 存入redis", notes = "获取redis中的值")
    public RetResponse setRedis(@PathVariable String key) {
        if(redisTemplate.opsForValue().get("zyzt") !=null){
            return new RetResponse(false,"暂停作业，不能获取数据");
        }else{
            return new RetResponse(redisTemplate.opsForValue().get(key));
        }
    }


    @PostMapping("/setRedisLpop")
    @ApiOperation(value = "3.6 存入redis序列", notes = "存入redis中的值")
    public RetResponse setRedisLpop(@Valid @RequestBody RedisParams param) {
        redisTemplate.opsForList().leftPush(param.getKey(), param.getValue());
        return new RetResponse("存入成功");
    }

    @GetMapping("/getRedisPop/{key}")
    @ApiOperation(value = "3.7 获取redis序列中的值", notes = "获取redis中的值")
    public RetResponse getRedisPop(@PathVariable String key) {

            return new RetResponse(redisTemplate.opsForList().rightPop(key));


    }


//    @GetMapping("/orderquery")
//    @ApiOperation(value = "3.9 orderquery", notes = "单文件多文件、图片均用本接口")
//    public RetResponse orderquery(String out_trade_no) {
//
//        try{
//            return new RetResponse(PayMent.orderquery(out_trade_no));
//        }catch (Exception e){
//            return new RetResponse(false,"获取paycode失败");
//        }
//
//
//    }

}
