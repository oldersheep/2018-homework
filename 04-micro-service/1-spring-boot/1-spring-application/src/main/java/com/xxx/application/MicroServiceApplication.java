package com.xxx.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class MicroServiceApplication {

    public static void main(String[] args) {
        // 1、默认情况下启动
//        SpringApplication.run(MicroServiceApplication.class, args);

        // 2、使用Builder方式创建
//        new SpringApplicationBuilder(MicroServiceApplication.class)
//                // 单元测试时 PORT=RANDOM 向操作系统要一个可用端口
//                .properties("server.port=0")
//                .run(args);

        // 3、 与1等价的方式
        SpringApplication application = new SpringApplication(MicroServiceApplication.class);
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("server.port",0);
        application.setDefaultProperties(properties);

        // 设置为非WEB项目启动
        application.setWebApplicationType(WebApplicationType.NONE);
        ConfigurableApplicationContext context = application.run(args);

        System.out.println(context.getBean(MicroServiceApplication.class));

        // org.springframework.context.annotation.AnnotationConfigApplicationContext
        System.out.println("当前spring的应用上下文：" + context.getClass().getName());

    }
}
