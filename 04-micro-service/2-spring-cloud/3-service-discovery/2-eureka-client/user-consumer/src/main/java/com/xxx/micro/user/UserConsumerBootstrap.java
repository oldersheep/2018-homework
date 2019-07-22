package com.xxx.micro.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Description
 * @Date 2019/7/21 20:42
 * @Version 1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class UserConsumerBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(UserConsumerBootstrap.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
