package io.hongting.api;

import io.hongting.crowd.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.TimeUnit;

/**
 * @author hongting
 * @create 2021 10 14 3:19 PM
 */

@FeignClient("crowd-redis")
public interface RedisRemoteService {

    @RequestMapping("/set/redis/key/value/remote")
    R<String> setRedisKeyValueRemote(@RequestParam("key") String key, @RequestParam("value") String value);

    @RequestMapping("/set/redis/key/value/with/timeout/remote")
    R<String> setRedisKeyValueWithTimeoutRemote(
            @RequestParam("key") String key,
            @RequestParam("value") String value,
            @RequestParam("time") long time,
            @RequestParam("timeUnit") TimeUnit timeUnit
    );

    @RequestMapping("/get/redis/value/by/key/remote")
    R<String> getRedisValueByKeyRemote(@RequestParam("key") String key);

    @RequestMapping("/remove/redis/key/by/key/remote")
    R<String> RemoveRedisKeyByKeyRemote(@RequestParam("key") String key);

}
