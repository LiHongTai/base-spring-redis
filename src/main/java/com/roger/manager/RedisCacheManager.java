package com.roger.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Component
public class RedisCacheManager<T> {

    @Autowired
    private RedisTemplate redisTemplate;

    public boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }

    public <T> T get(String key){
        ValueOperations<String,T> valueOperation = redisTemplate.opsForValue();
        return valueOperation.get(key);
    }

    public void set(String key,T value){
        set(key,value,-1);
    }

    public void set(String key,T value,long time){
        redisTemplate.opsForValue().set(key,value);
        expire(key,time,TimeUnit.SECONDS);
    }

    public boolean setNX(String key,T value){
        return setNX(key,value,-1);
    }

    public boolean setNX(String key,T value,long time){
      boolean ifAbsent = redisTemplate.opsForValue().setIfAbsent(key,value);
      if(ifAbsent) {
          expire(key, time, TimeUnit.SECONDS);
      }
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

    public void setBit(String key,long id,boolean state){
        redisTemplate.opsForValue().setBit(key,id,state);
    }

    public boolean getBit(String key,long id){
        return redisTemplate.opsForValue().getBit(key,id);
    }
}
