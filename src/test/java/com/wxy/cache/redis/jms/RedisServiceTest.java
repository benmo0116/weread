package com.wxy.cache.redis.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wxy
 * @create 2018-03-27 17:15
 * @desc ${DESCRIPTION}
 **/
@RunWith(SpringRunner.class)//SpringRunner是SpringJUnit4ClassRunner的一个别名。
@SpringBootTest
public class RedisServiceTest {
    @Autowired
    RedisService redisService;

    @Test
    public void testJvm(){
        redisService.sendChannelMess("ch1","发布一条消息");
        redisService.sendChannelMess("ch2","发布二条消息");
    }
}