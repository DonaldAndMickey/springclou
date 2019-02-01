package com.feifan.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Donald
 * @create 2019-01-28 22:17
 */
@Component
public class ActiveMQSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send()
    {
        amqpTemplate.convertAndSend("hel","你好呀大兄弟");
    }
}
