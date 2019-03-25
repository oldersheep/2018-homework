package com.xxx.application.events;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

@EnableAutoConfiguration
public class SpringBootEventDemo {

    public static void main(String[] args) {
//        接收到监听事件：ApplicationEnvironmentPreparedEvent
//        接收到监听事件：ApplicationPreparedEvent
//        接收到监听事件：ContextRefreshedEvent
//        接收到监听事件：ReactiveWebServerInitializedEvent
//        接收到监听事件：ApplicationStartedEvent
//        接收到监听事件：ApplicationReadyEvent
        // EventPublisherRunListener 在这里面进行初始化
        // ConfigFileApplicationListener
        new SpringApplicationBuilder(SpringBootEventDemo.class)
                .listeners(event ->{
                    System.err.println("接收到监听事件：" +event.getClass().getSimpleName());
                })
                .run(args);
    }

}
