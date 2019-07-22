package com.xxx.micro.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description
 * @Date 2019/7/21 21:05
 * @Version 1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class UserProviderBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(UserProviderBootstrap.class, args);
    }
}
