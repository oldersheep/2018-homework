package com.xxx.application.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAnnotationDemo {

    public static void main(String[] args) {
        // XML配置文件启动 ClassPathXmlApplicationContext
        // Annotation 驱动
        // 找BeanDefination
        // @Bean @Configuration
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册一个Configuration
        context.register(SpringAnnotationDemo.class);
        // 上下文启动
        context.refresh();

        System.out.println(context.getBean(SpringAnnotationDemo.class));

    }
}
