package com.feifan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = "com.feifan")
public class ProduceUser extends SpringBootServletInitializer
{
    public static void main( String[] args )
    {
        SpringApplication.run(ProduceUser.class,args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources( ProduceUser.class);
    }
}
