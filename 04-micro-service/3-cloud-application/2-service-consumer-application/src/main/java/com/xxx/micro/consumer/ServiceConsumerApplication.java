package com.xxx.micro.consumer;

import com.xxx.micro.consumer.annotation.EnableRestClient;
import com.xxx.micro.consumer.service.feign.clients.SayingService;
import com.xxx.micro.consumer.service.rest.clients.SayingRestService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Description
 * @Date 2019/7/30 22:52
 * @Version 1.0
 */
@EnableDiscoveryClient // 激活服务发现客户端
@EnableScheduling
@EnableFeignClients(clients = SayingService.class) // 引入 FeignClient
@EnableRestClient(clients = SayingRestService.class) // 引入 @RestClient
@SpringBootApplication
public class ServiceConsumerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ServiceConsumerApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
