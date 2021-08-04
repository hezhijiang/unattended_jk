package com.gez.woodware.entity.basics;


import lombok.Data;
import org.springframework.data.redis.core.RedisTemplate;

@Data
public class WxxcxzhData {

    private String        system;
    private String        appid;
    private String        secert;
    private RedisTemplate redisTemplate;

}
