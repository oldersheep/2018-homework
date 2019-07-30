package com.xxx.micro.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Description 服务熔断
 * @Date 2019/7/25 20:49
 * @Version 1.0
 */
@SpringBootApplication
@EnableHystrixDashboard
@EnableHystrix
public class HystrixClientApplication {

    public static void main(String[] args) {

        SpringApplication.run(HystrixClientApplication.class, args);
    }
}
