package com.wxy.activemq.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * @author wxy
 * @create 2018-03-05 10:04
 * @desc Springboot 注释模式消费者写法
 **/
@Component
public class Consumer {

    //JmsListener是spring-jms提供的一个注解，会实例化一个Jms的消息监听实例，也就是一个异步的消费者。
    //使用JmsListener配置消费者监听的队列，其中text是接收到的消息
    @JmsListener(destination = "sample.queue")
    public void receiveQueue(String text) {
        System.out.println("PRINT:" + "queue-Consumer1收到的报文为:" + text);
    }

    @JmsListener(destination = "sample.queue")
    public void receiveMessage(Message message) {
        //Message:TextMessage,StreamMessage,MapMessage,ObjectMessage,BytesMessage
        if (message instanceof TextMessage) {
            TextMessage tm = (TextMessage) message;
            try {
                System.out.println("from get textMessage：\t" + tm.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        System.out.println("PRINT:" + "queue-Consumer1收到的报文为:" + message.toString());
    }

    //需要给topic定义独立的JmsListenerContainer
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(activeMQConnectionFactory);
        return bean;
    }

    @JmsListener(destination = "sample.topic", containerFactory = "jmsListenerContainerTopic")
    public void receiveTopic(String text) {
        System.out.println("PRINT:" + "topic-Consumer1订阅主题为：" + text);
    }
}
