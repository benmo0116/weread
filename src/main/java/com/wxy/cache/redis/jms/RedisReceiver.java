package com.wxy.cache.redis.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author wxy
 * @create 2018-03-27 16:52
 * @desc ${DESCRIPTION}
 **/
@Component
public class RedisReceiver {

    public void receiveMessage(String message) {
        //这里是收到通道的消息之后执行的方法
        System.out.println("PRINT: "+message);
    }
}
