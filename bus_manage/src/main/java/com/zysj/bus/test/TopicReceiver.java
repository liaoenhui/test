package com.zysj.bus.test;

import com.zysj.bus.test.entity.User;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicReceiver {
    // queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitConfig.TOPIC_QUEUE1)
    public void receiveTopic1(User user) {
        System.out.println("【receiveTopic1监听到消息】" + user.toString());
    }

    @RabbitListener(queues = RabbitConfig.TOPIC_QUEUE_ETL_SCRIPT)
    public void receiveTopic2(String objectId) {
        System.out.println("【receiveTopic2监听到消息】,主体id[" + objectId+"]");
    }




}