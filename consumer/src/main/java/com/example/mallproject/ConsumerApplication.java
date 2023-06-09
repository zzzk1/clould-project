package com.example.mallproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zzzk1
 */
@EnableFeignClients
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class ConsumerApplication {
    public static void main( String[] args ) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
