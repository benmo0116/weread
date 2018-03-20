package com.wxy.task;

import com.wxy.activemq.boot.Producer;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author wxy
 * @create 2018-03-05 14:23
 * @desc @Scheduled 在Springboot中使用 在Application添加@EnableScheduling 即可
 * @Scheduled(cron = "0/1 * * * * ? ") 所在类不可以继承另一个类？？why?
 * <p>
 * spring的定时任务默认是单线程，多个任务执行起来时间会有问题
 **/
@Component
public class TaskDemo {
    //    @Scheduled  注解可以和 触发元数据（trigger metadata）一起添加在方法上
//    @Scheduled(fixedDelay=5000)
//    @Scheduled(fixedRate=5000)
//    @Scheduled(initialDelay=1000, fixedRate=5000)
//    @Scheduled(cron="*/5 * * * * MON-FRI")// 只需要在工作日之时才定期执行的代码
/*
    1.        Seconds
    2.        Minutes
    3.        Hours
    4.        Day-of-Month
    5.        Month
    6.        Day-of-Week
    7.        Year (可选字段)
*/
    @Autowired
    private Producer producer;

    @Scheduled(cron = "0 0 */1 * * ? ")   //每1小时执行一次
    public void productQueue() throws InterruptedException {
        Destination destination = new ActiveMQQueue("sample.queue2");
        producer.sendMessage(destination, "生产一队新消息了！ ");

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(DateTime.now().toDate()) + "----productQueue");
    }
    @Scheduled(cron = "0 0 */2 * * ? ")   //每2小时执行一次
    public void productTopic() throws InterruptedException {
        Destination destinationTopic = new ActiveMQTopic("sample.topic");
        producer.sendMessage(destinationTopic, "有新主题了！ ");

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(DateTime.now().toDate()) + "----productTopic");
    }
    //  @Scheduled(cron = "0/10 * *  * * ? ")   //每10秒执行一次
//    public void aTask() {
//        try {
//            TimeUnit.SECONDS.sleep(20);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("======================================================");
//        Thread current = Thread.currentThread();
//        System.out.println("定时任务1:" + current.getId());
//        System.out.println("ScheduledTest.executeFileDownLoadTask 定时任务1:" + current.getId() + ",name:" + current.getName());
//        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(sdf.format(DateTime.now().toDate()) + "*********A任务每10秒执行一次进入测试");
//    }

//    @Scheduled(cron="0/5 * *  * * ? ")   //每10秒执行一次
//    public void bTask(){
//        try {
//            TimeUnit.SECONDS.sleep(20);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Thread current = Thread.currentThread();
//        System.out.println("======================================================");
//        System.out.println("定时任务2:"+current.getId());
//        System.out.println("ScheduledTest.executeFileDownLoadTask 定时任务2:"+current.getId()+ ",name:"+current.getName());
//        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(sdf.format(DateTime.now().toDate())+"*********B任务每5秒执行一次进入测试");
//    }
}
