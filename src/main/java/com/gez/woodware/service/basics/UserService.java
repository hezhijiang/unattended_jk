package com.gez.woodware.service.basics;

import com.gez.woodware.entity.basics.*;


import com.gez.woodware.mapper.basics.UserMapper;
import com.gez.woodware.mapper.basics.WeChatUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.UUID;


@Slf4j
@Service
public class UserService extends BaseService {


    @Autowired
    private UserMapper mapper;

    @Autowired
    private WeChatUserMapper ztmapper;

    public User getUserByYonghzhmm(UserParams data) {
        User u = mapper.getUserByYonghzhmm(data);

        u.setOpenId(mapper.getOpenId(u.getId()));
        u = SetUser(u, "yonghzh > " + data.getYonghzh());
        log(u.getId(), "接口登录", "用户调用前端接口进行登录");


        return u;
    }


    public boolean checkVerificationCode(wxRegisterParam params) {
        return params.getVerificationCode().equals(mapper.getyanzm(params.getShoujh()));
    }

    public User getUserById(String id) {
        User u = mapper.getUserById(id);
        return SetUser(u, "yonghid > " + id);

    }

    public User getUserByOpenId(String id) {
        User u = mapper.getUserByOpenId(id);
        return SetUser(u, "yonghid > " + id);

    }

    private User SetUser(User u, String message) {


        if (u == null) {
            log.debug(message + " 登录失败");
        } else {

            u.setToken(request.getSession().getId());
            try {
//                u.setSijzt(ztmapper.getsjzt(u.getYonghzh()));

                redisTemplate.opsForValue().set(u.getToken(), u);

            } catch (Exception e) {
                log.error(message + "登录,存入redis失败 ", e.getMessage());
            } finally {
                request.getSession().setAttribute("user", u);
            }
        }

        return u;
    }


    public void logout() {
        redisTemplate.opsForValue().set("user", null);
        request.getSession().setAttribute("user", null);
    }


    public String register(UserParams params) {
        String id = "error";

        if (mapper.getUserByYonghzh(params.getYonghzh()) == null) {

            HashMap<String, Object> registerData = new HashMap<String, Object>();

            id = UUID.randomUUID().toString();
            registerData.put("shoujh", params.getYonghzh());
            registerData.put("yonghmm", params.getYonghmm());
            registerData.put("yonghid", id);


            mapper.insert_yongh(registerData);
        }


        return id;
    }
}
