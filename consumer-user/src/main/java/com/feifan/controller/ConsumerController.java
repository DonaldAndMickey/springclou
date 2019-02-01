package com.feifan.controller;

import com.feifan.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Donald
 * @create 2019-01-30 23:22
 */
@RestController
public class ConsumerController {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Autowired//spring提供的一个访问rest 接口的模板
    private RestTemplate restTemplate;

    private String url ="http://localhost:8080/user/";


    @GetMapping("order/{id}")
    public User getOrder(@PathVariable("id") int id){
        url = "http://119.23.224.88:8080/user/";
        //访问提供者获取数据
        User user = restTemplate.getForObject(url+id, User.class);
        return user;
    }
}
