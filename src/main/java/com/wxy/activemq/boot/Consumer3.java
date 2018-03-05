package com.wxy.activemq.boot;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @author wxy
 * @create 2018-03-05 10:04
 * @desc ${DESCRIPTION}
 **/
@Component
public class Consumer3 {

    @JmsListener(destination = "sample.queue")
    @SendTo("out.queue")//转发至其它队列
    public String receiveQueue(String text) {
        System.out.println("PRINT:" + "queue-Consumer3收到的报文为:" + text);
        return "return message" + text;
    }

    @JmsListener(destination = "sample.topic", containerFactory = "jmsListenerContainerTopic")
    public void receiveTopic(String text) {
        System.out.println("PRINT:" + "topic-Consumer3订阅主题为：" + text);
    }
}
