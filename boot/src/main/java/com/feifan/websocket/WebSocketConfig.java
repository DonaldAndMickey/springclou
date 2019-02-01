package com.feifan.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * @author Donald
 * @create 2019-01-30 0:53
 */
@Configuration
//开启对WebSocket的支持
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    /**
     * 配置消息代理
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //配置一个广播式的消息代理
        registry.enableSimpleBroker("topic");
    }

    /**
     * 注册一个STOMP 协议的节点，并映射到指定的url
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //注册一个STOMP的endpoint 并指定使用sockjs协议
        registry.addEndpoint("/endpointSocket").withSockJS();
    }
}
