package com.feifan.websocket;

/**
 * @author Donald
 * @create 2019-01-30 1:03
 */
//浏览器向服务器发送消息实体类
public class SocketMessage {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
