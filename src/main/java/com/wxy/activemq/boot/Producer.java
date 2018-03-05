package com.wxy.activemq.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;

/**
 * @author wxy
 * @create 2018-03-05 10:04
 * @desc ${DESCRIPTION}
 **/
@RestController
public class Producer {
    @Autowired //也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
    private JmsMessagingTemplate jmsMessagingTemplate;

//    @Autowired
//    private Queue queue;
//
//    @RequestMapping("/sendMsg")
//    public void send(String msg) {
//        this.jmsMessagingTemplate.convertAndSend(this.queue, msg);
//    }

    // 发送消息，destination是发送到的队列，message是待发送的消息
    public void sendMessage(Destination destination, final String message){
        jmsMessagingTemplate.convertAndSend(destination, message);
    }

    @JmsListener(destination="out.queue")
    public void consumerMessage(String text){
        System.out.println("从out.queue队列收到的回复报文为:"+text);
    }
}
