package com.roger.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Component
public class RedisCacheManager {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public void set(String key,Object value){
        redisTemplate.opsForValue().set(key,value);
    }

    public boolean setNX(String key,Object value){
      return redisTemplate.opsForValue().setIfAbsent(key,value);
    }

    public long del(String... key){
        if(StringUtils.isEmpty(key))
            return 0;
       return redisTemplate.delete(CollectionUtils.arrayToList(key));
    }

    public boolean expire(String key,long time,TimeUnit timeUnit){
        return redisTemplate.expire(key,time,timeUnit);
    }
}
