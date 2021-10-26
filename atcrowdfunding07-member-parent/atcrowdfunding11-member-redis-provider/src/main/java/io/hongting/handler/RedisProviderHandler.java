package io.hongting.handler;

import io.hongting.crowd.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author hongting
 * @create 2021 10 14 3:11 PM
 */

@RestController
public class RedisProviderHandler {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping("/set/redis/key/value/remote")
    R<String> setRedisKeyValueRemote(@RequestParam("key") String key, @RequestParam("value") String value){
        try {
          redisTemplate.opsForValue().set(key, value);
            return R.success();
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }

    }

    @RequestMapping("/set/redis/key/value/with/timeout/remote")
    R<String> setRedisKeyValueWithTimeoutRemote(
            @RequestParam("key") String key,
            @RequestParam("value") String value,
            @RequestParam("time") long time,
            @RequestParam("timeUnit") TimeUnit timeUnit){
        try {
            redisTemplate.opsForValue().set(key,value,time,timeUnit);
            return R.success();
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }

    }


    @RequestMapping("/get/redis/value/by/key/remote")
    R<String> getRedisValueByKeyRemote(@RequestParam("key") String key){

        try {
            String value = (String) redisTemplate.opsForValue().get(key);
            return R.success(value);
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }


    }


    @RequestMapping("/remove/redis/key/by/key/remote")
    R<String> RemoveRedisKeyByKeyRemote(@RequestParam("key") String key){
        try {
            redisTemplate.delete(key);
            return R.success();
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }

    }


}

