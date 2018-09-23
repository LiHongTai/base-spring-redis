package com.roger.manager;

import com.sun.corba.se.impl.oa.toa.TOA;
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

    public boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }

    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public void set(String key,Object value){
        set(key,value,-1);
    }

    public void set(String key,Object value,long time){
        redisTemplate.opsForValue().set(key,value);
        expire(key,time,TimeUnit.SECONDS);
    }

    public boolean setNX(String key,Object value){
        return setNX(key,value,-1);
    }

    public boolean setNX(String key,Object value,long time){
      boolean ifAbsent = redisTemplate.opsForValue().setIfAbsent(key,value);
      expire(key,time,TimeUnit.SECONDS);
      return ifAbsent;
    }

    public long del(String... key){
        if(StringUtils.isEmpty(key))
            return 0;
       return redisTemplate.delete(CollectionUtils.arrayToList(key));
    }

    public boolean expire(String key,long time,TimeUnit timeUnit){
     if(time <= 0)
     return true;
     return redisTemplate.expire(key,time,timeUnit);
     }

     /**
     *
     * @return 返回0 代表永久有效
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }
}
