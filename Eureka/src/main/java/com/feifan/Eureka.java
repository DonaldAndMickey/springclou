package com.feifan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaServer//将当前项目标记为 eurekaserver
public class Eureka
{
    public static void main( String[] args )
    {
        SpringApplication.run(Eureka.class,args);
    }
}
