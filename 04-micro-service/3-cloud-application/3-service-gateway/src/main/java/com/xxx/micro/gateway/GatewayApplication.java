package com.xxx.micro.gateway;

import com.xxx.micro.gateway.loadbalancer.ZookeeperLoadBalancer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * TODO 写一些简单的注释吧
 *
 * @author old boy
 * @since 1.0
 * @version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@ServletComponentScan(basePackages = "com.xxx.micro.gateway.servlet")
@EnableScheduling
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public ZookeeperLoadBalancer zookeeperLoadBalancer(DiscoveryClient discoveryClient) {
        return new ZookeeperLoadBalancer(discoveryClient);
    }
}
