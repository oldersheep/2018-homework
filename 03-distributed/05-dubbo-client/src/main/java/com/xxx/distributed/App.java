package com.xxx.distributed;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-client.xml");
        HelloService helloService = (HelloService) context.getBean("helloService");

        System.out.println(helloService.sayHello("client"));
    }
}
