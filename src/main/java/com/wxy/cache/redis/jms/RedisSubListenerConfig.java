package com.wxy.cache.redis.jms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @author wxy
 * @create 2018-03-27 16:58
 * @desc ${DESCRIPTION}
 **/
@Configuration
public class RedisSubListenerConfig {
    //初始化监听器
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter,
                                            MessageListenerAdapter listenerAdapter2,
                                            MessageListenerAdapter listenerAdapter3) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("ch1"));//这里是监听的通道的名字
        container.addMessageListener(listenerAdapter2, new PatternTopic("ch2"));//这里是监听的通道的名字
        container.addMessageListener(listenerAdapter3, new PatternTopic("ch1"));//这里是监听的通道的名字
        return container;
    }

    //利用反射来创建监听到消息之后的执行方法
    @Bean
    MessageListenerAdapter listenerAdapter(RedisReceiver redisReceiver) {
        return new MessageListenerAdapter(redisReceiver, "receiveMessage");
    }
    @Bean
    MessageListenerAdapter listenerAdapter3(RedisReceiver3 redisReceiver) {
        return new MessageListenerAdapter(redisReceiver, "receiveMessage");
    }
    @Bean
    MessageListenerAdapter listenerAdapter2(RedisReceiver2 redisReceiver) {
        return new MessageListenerAdapter(redisReceiver, "receiveMessage2");
    }

    //使用默认的工厂初始化redis操作模板
    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }
}
