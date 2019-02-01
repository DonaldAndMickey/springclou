package com.feifan.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import java.util.Map;

/**
 * @author Donald
 * @create 2019-01-27 22:23
 */
@Component
public class Produce {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    //发送 消息
    public void sendMessage(Destination des, String message){
        jmsMessagingTemplate.convertAndSend(des,message);
    }

    //发送复杂对象尝试
    public void sendPOJO(Destination des, Map<String, String> map){
        jmsMessagingTemplate.convertAndSend(des,map);
    }
}
