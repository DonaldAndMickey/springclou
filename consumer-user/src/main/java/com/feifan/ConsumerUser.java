package com.feifan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = "com.feifan")
public class ConsumerUser
{
    public static void main( String[] args )
    {
        SpringApplication.run(ConsumerUser.class,args);
    }
}
