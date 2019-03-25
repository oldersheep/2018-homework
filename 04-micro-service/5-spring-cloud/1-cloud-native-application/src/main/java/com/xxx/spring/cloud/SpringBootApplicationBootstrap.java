package com.xxx.spring.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
public class SpringBootApplicationBootstrap {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
        parentContext.registerBean("helloWorld", String.class, "HelloWorld");
        parentContext.setId("我不想那么狼狈！");
        parentContext.refresh();

        new SpringApplicationBuilder(SpringBootApplicationBootstrap.class)
                .parent(parentContext)
                .run(args);
        // SpringApplication.run(SpringBootApplicationBootstrap.class, args);
    }

    @Autowired
    @Qualifier("helloWorld")
    private String message;

    @RequestMapping("")
    public String index() {
        return message;
    }
}
