package com.feifan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Donald
 * @create 2019-01-25 23:02
 */
@SpringBootApplication(scanBasePackages = {"com.feifan"})
@EntityScan("pojo.feifan.entity")
@EnableJpaRepositories(basePackages = "com.feifan.dao")
//@EnableCaching//开启缓存
public class Run extends SpringBootServletInitializer {

    private static  final Logger LOGGER = LoggerFactory.getLogger(Run.class);
    public static void main(String[] args) {
        SpringApplication.run(Run.class,args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Run.class);
    }
}
