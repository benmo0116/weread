package com.wxy.mongodb;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.wxy.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

/**
 * @author wxy
 * @create 2018-03-24 16:23
 * @desc 在你所需要测试的类或者接口名称上按该快捷键：ctrl+shift+t  --> create new test
 **/
@RunWith(SpringRunner.class)//SpringRunner是SpringJUnit4ClassRunner的一个别名。
@SpringBootTest
public class MongoDBDaoTest {

    @Autowired
    MongoDBDaoImpl mongoDBDao;

    @Test
    public void connection() {
        try {
            // 连接到 mongodb 服务
            Mongo mongo = new Mongo("192.168.1.71", 27017);
            //根据mongodb数据库的名称获取mongodb对象 ,
            DB db = mongo.getDB("FirstTest");
            Set<String> collectionNames = db.getCollectionNames();
            // 打印出test中的集合
            for (String name : collectionNames) {
                System.out.println("collectionName===" + name);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void insert() {
        User user = new User();
        user.setUserid(1);
        user.setUsername("mongodb_name");
        user.setPassword("123456");
        user.setNickname("mongodb_nickname");
        mongoDBDao.insert(user);
    }

    @Test
    public void query() {
        List<User> users = mongoDBDao.findAll();
        System.out.println(users);
    }
}