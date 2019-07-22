package com.xxx.application.events;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.GenericApplicationContext;

/**
 *
 */
public class SpringEventListenerDemo {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();

        // 添加事件监听器
        context.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent applicationEvent) {
                System.err.println("监听事件：" + applicationEvent);
            }
        });

//        context.addApplicationListener(new ClosedListener());
//        context.addApplicationListener(new RefreshedListener());
        // 启动应用上下文
        context.refresh();

        // Spring应用上下文发布事件
        // ContextRefreshedEvent
        // PayloadApplicationEvent
        context.publishEvent("Hello World"); // 因为是Object，所以 会有一个Payload
        // MyEvent
        context.publishEvent(new MyEvent("自定义事件"));

        // ContextClosedEvent
        context.close();
    }

    private static class ClosedListener implements ApplicationListener<ContextClosedEvent> {

        @Override
        public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
            System.err.println("关闭上下文：" + contextClosedEvent);
        }
    }

    private static class RefreshedListener implements ApplicationListener<ContextRefreshedEvent> {

        @Override
        public void onApplicationEvent(ContextRefreshedEvent contextClosedEvent) {
            System.err.println("上下文启动：" + contextClosedEvent);
        }
    }

    private static class MyEvent extends ApplicationEvent {

        public MyEvent(Object source) {
            super(source);
        }
    }
}
