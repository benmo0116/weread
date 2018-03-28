package com.wxy;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.jms.Queue;
import javax.jms.Topic;

@SpringBootApplication
@MapperScan("com.wxy.mapper")//将项目中对应的mapper类的路径加进来就可以了
@ServletComponentScan("com.wxy.aop")//filter 配置
@EnableCaching// 开启缓存，需要显示的指定
@EnableScheduling //注解的作用是发现注解@Scheduled的任务并后台执行。
@EnableJms //@EnableJms会启动jms的注解扫描，相当于<jms:annotation-d riven/>
public class WereadApplication {
	@Bean
	public Queue queue() {
		return new ActiveMQQueue("sample.queue");
	}
	@Bean
	public Queue queue2() {
		return new ActiveMQQueue("sample.queue2");
	}
	@Bean
	public Topic topic() {
		return new ActiveMQTopic("sample.topic");
	}
	public static void main(String[] args) {
		SpringApplication.run(WereadApplication.class, args);
	}
}
