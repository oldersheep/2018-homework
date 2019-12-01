package com.xxx.micro.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Description
 * @Date 2019/7/30 22:42
 * @Version 1.0
 */
@EnableDiscoveryClient // 激活服务发现客户端
@EnableHystrix         // 激活 Hystrix
@EnableAspectJAutoProxy(proxyTargetClass = true) // 激活 AOP
@SpringBootApplication
public class ServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceProviderApplication.class, args);
    }
}
