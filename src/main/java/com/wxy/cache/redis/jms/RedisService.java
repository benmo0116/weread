package com.wxy.cache.redis.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author wxy
 * @create 2018-03-27 16:59
 * @desc 使用reidsTemplate向通道发送消息
 **/
@Service
public class RedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //向通道发送消息的方法

    public void sendChannelMess(String channel, String message) {
        stringRedisTemplate.convertAndSend(channel, message);
    }
}
