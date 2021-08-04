package com.gez.woodware.service.basics;


import com.gez.woodware.mapper.basics.BaseMapper;

import com.gez.woodware.util.ParamsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


@Service
public class BaseService {
    @Autowired
    protected RedisTemplate      redisTemplate;
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    private   BaseMapper         baseMapper;

    /**
     * 插入系统日志
     *
     * @param duixid 对象ID 不展示
     * @param caoz   操作
     * @param caozms 操作内容
     */
    public void log(String duixid, String caoz, String caozms) {
        if(ParamsUtil.getUser(request)!=null){
            String userId = ParamsUtil.getUser(request).getId();
            if (userId != null && userId.length() > 0) {
                HashMap<String, Object> params = new HashMap<String, Object>();
                params.put("duixid", duixid);
                params.put("caoz", caoz);
                params.put("caozms", caozms);
                params.put("userId", userId);

                baseMapper.log(params);
            }
        }



    }
}
