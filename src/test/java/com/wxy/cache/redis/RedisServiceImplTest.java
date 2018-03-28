package com.wxy.cache.redis;

import com.wxy.model.User;
import com.wxy.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

/**
 * @author wxy
 * @create 2018-03-27 13:57
 * @desc ${DESCRIPTION}
 **/
@RunWith(SpringRunner.class)//SpringRunner是SpringJUnit4ClassRunner的一个别名。
@SpringBootTest
public class RedisServiceImplTest {
    @Autowired
    RedisServiceImpl<User> redisService;
    @Autowired
    UserService userService;

    @Test
    public void testRedis() {
        List<User> users = userService.findAll(0, 10);

        for (User user : users) {
            redisService.hput(user.getNickname(), user, -1);
        }
    }

    @Test
    public void getAllKey() {
        Set<String> keys = redisService.hgetKeys();
        System.out.println(keys);
    }

    @Test
    public void geth() {
        User user = redisService.hget("小魏");
        System.out.println(user);
    }

    //string 类型测试
    @Test
    public void get() {
        redisService.set("xiao", "是个天才");
        String res = redisService.get("xiao");
        System.out.println(res);
    }

    //list 类型测试
    @Test
    public void getl() {
        List<User> users = userService.findAll(0, 10);
        redisService.rightPushAll("userlist", users);
        User user = redisService.lpop("userlist");
        System.out.println(user);
    }

    //set 类型测试
    @Test
    public void gets() {
        List<User> users = userService.findAll(0, 10);
        for (User user : users) {
            redisService.sadd("userset", user);
        }
        User user = redisService.spop("userset");
        System.out.println(user);
    }
}