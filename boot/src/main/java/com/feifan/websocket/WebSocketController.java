package com.feifan.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Donald
 * @create 2019-01-30 1:08
 */
@Controller
public class WebSocketController {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH;mm:ss");

    @MessageMapping(value = "/getServerTime")
    //当浏览器向服务器发送STOMP 请求时，通过@messageMapping 注解来映射/getServerTime 地址
    @SendTo(value = "/topic/getResponse")
    //当服务器端有消息是，会对订阅了@sendto 中的路径的客户端发送消息
    public SocketResponse seerverTime(SocketMessage message){
        return new SocketResponse(message.getMessage() + sdf.format(new Date()));
    }

    @RequestMapping("page")
    public String page(){
        return "socket";
    }
}
