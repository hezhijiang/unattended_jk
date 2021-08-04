package com.gez.woodware.intercept;

import com.gez.woodware.entity.basics.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 * 何志江
 * 2019年12月13日
 */
@Slf4j
public class UserIntercept implements HandlerInterceptor {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info(request.getRequestURI(), request.getParameterMap());


        String url = request.getRequestURI().toString();

        String token = request.getHeader("token");
        System.out.println(" token  = "+ token);
        log.info(url);
        if (url.contains("swagger")) {
            return true;
        }

        //通过session获取用户数据
        User u = (User) request.getSession().getAttribute("user");

        if (u != null) {
            log.debug(u.getId(), "通过Session登录成功");
        } else if (u == null && token != null) {
            try {
                u = (User) redisTemplate.opsForValue().get(token);

            } catch (Exception e) {
                log.error("redis连接失败", e);
            }
        }

        if (u == null) {
            response.sendRedirect("/user/notLoggedIn");
            return false;
        } else {
            request.getSession().setAttribute("user", u);
            if (token != null) {
                redisTemplate.opsForValue().set(token, u);
            }
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
