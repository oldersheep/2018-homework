package com.xxx.application.events;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

public class ApplicationEventMulticasterDemo {

    public static void main(String[] args) {
        ApplicationEventMulticaster multicaster = new SimpleApplicationEventMulticaster();

        multicaster.addApplicationListener(event->{
            if (event instanceof PayloadApplicationEvent) {
                System.out.println("接收到PayloadApplicationEvent："
                        + PayloadApplicationEvent.class.cast(event).getPayload());
            } else {
                System.out.println("接收到事件：" + event);
            }
        });
        multicaster.multicastEvent(new PayloadApplicationEvent<Object>("","HelloWorld"));
        multicaster.multicastEvent(new MyEvent("HelloWorld"));
    }

    private static class MyEvent extends ApplicationEvent {

        public MyEvent(Object source) {
            super(source);
        }
    }
}
