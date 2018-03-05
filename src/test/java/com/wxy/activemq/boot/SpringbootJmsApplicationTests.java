package com.wxy.activemq.boot;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;

/**
 * @author wxy
 * @create 2018-03-05 10:27
 * @desc ${DESCRIPTION}
 **/
@RunWith(SpringRunner.class)//SpringRunner是SpringJUnit4ClassRunner的一个别名。
@SpringBootTest
public class SpringbootJmsApplicationTests {

    @Autowired
    private Producer producer;

    @Test
    public void contextLoads() throws InterruptedException {
        Destination destination = new ActiveMQQueue("sample.queue");
        Destination destinationQueue2 = new ActiveMQQueue("sample.queue2");
        Destination destinationTopic = new ActiveMQTopic("sample.topic");
        for(int i=0; i<10; i++){
            producer.sendMessage(destination, "生产一队新消息了！ " + i);
            producer.sendMessage(destinationQueue2, "生产二队新消息了！ " + i);
        }
        for(int i=0; i<5; i++){
            producer.sendMessage(destinationTopic, "有新主题了！ " + i);
        }
    }

}
