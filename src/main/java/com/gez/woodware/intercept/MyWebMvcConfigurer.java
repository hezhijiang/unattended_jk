package com.gez.woodware.intercept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {


    //组装拦截器，不然还redis不能使用
    @Bean
    public UserIntercept getAccessLimitIntercept() {
        return new UserIntercept();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {


        // 添加一个拦截器，连接以/admin为前缀的 url路径
        InterceptorRegistration interceptor = registry.addInterceptor(getAccessLimitIntercept());

        interceptor.excludePathPatterns("/loginRedis");
        interceptor.excludePathPatterns("/loginSession");
        interceptor.excludePathPatterns("/loginout");
        interceptor.excludePathPatterns("/error");
        interceptor.excludePathPatterns("/resources/**");


        //不拦截 swagger
        interceptor.excludePathPatterns("/swagger-resources/**");
        interceptor.excludePathPatterns("/user/**");
        // 拦截配置
        interceptor.addPathPatterns("/**");

    }
}