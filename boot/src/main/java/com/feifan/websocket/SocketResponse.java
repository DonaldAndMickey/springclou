package com.feifan.websocket;

/**
 * @author Donald
 * @create 2019-01-30 1:06
 */
//服务器向浏览器相应数据的封装实体
public class SocketResponse {

    private String responseMessage;

    public String getResponseMessage() {
        return responseMessage;
    }

    public SocketResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
