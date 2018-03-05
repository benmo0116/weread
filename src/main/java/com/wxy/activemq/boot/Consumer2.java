package com.wxy.activemq.boot;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author wxy
 * @create 2018-03-05 10:04
 * @desc ${DESCRIPTION}
 **/
@Component
public class Consumer2 {

    @JmsListener(destination = "sample.queue")
    public void receiveQueue(String text) {
        System.out.println("PRINT:" + "queue-Consumer2收到的报文为:" + text);
    }

    @JmsListener(destination = "sample.queue2")
    public void receiveQueue2(String text) {
        System.out.println("PRINT:" + "queue2-Consumer2收到的报文为:" + text);
    }

    @JmsListener(destination = "sample.topic", containerFactory = "jmsListenerContainerTopic")
    public void receiveTopic(String text) {
        System.out.println("PRINT:" + "topic-Consumer2订阅主题为：" + text);
    }
}
