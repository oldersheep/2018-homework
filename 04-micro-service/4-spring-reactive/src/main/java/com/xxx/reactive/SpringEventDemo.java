package com.xxx.reactive;

import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.GenericApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SpringEventDemo {

    public static void main(String[] args) {
//        GenericApplicationContext context = new GenericApplicationContext();
//
//        context.addApplicationListener(event -> {
//            System.out.printf("[线程:%s] event: %s\n", Thread.currentThread().getName(), event);
//        });
//        context.refresh(); // 启动
//
//        context.close(); // 关闭

        // 默认同步非阻塞
        SimpleApplicationEventMulticaster multicaster = new SimpleApplicationEventMulticaster();

        // 切换成异步
        ExecutorService executor = Executors.newSingleThreadExecutor();
        multicaster.setTaskExecutor(executor);

        multicaster.addApplicationListener(event -> {
            System.out.printf("[线程:%s] event: %s\n", Thread.currentThread().getName(), event);
        });
        multicaster.multicastEvent(new PayloadApplicationEvent("hello word", "hello word"));

        executor.shutdown();
    }
}
