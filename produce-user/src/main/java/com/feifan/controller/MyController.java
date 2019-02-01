package com.feifan.controller;

import com.feifan.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Donald
 * @create 2019-01-30 23:08
 */
@RestController
public class MyController {

    @GetMapping("user/{id}")
    public User getUser(@PathVariable("id") int id)
    {
        return new User(id,new Date());
    }

}
