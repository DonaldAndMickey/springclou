package com.feifan.activemq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feifan.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author Donald
 * @create 2019-01-27 22:27
 */
@Component
public class consumer {

    private static final Logger LOGGER  = LoggerFactory.getLogger(consumer.class);

    @JmsListener(destination = "myqueue")//时时刻刻监听消息队列,目的地名称额
    public void receviceMsg(String text){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("message:{}<<<<<<<:{}",text,Thread.currentThread().getName());
    }

    @JmsListener(destination = "myqueue")//时时刻刻监听消息队列,目的地名称额
    public void receviceMsg2(String text){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("message:{}>>>>>>>:2{}",text,Thread.currentThread().getName());
    }

    @Autowired
    private ObjectMapper objectMapper;

    @JmsListener(destination = "POJO")
    public void getUser(Map<String,String> map){
        LOGGER.info("{}",map);
        for (Map.Entry<String,String> entry:map.entrySet()) {
            try {
                User user = objectMapper.readValue(entry.getValue(), User.class);
                LOGGER.info("{}",user);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
