package com.xxx.design.observer.guava;

import com.google.common.eventbus.EventBus;

/**
 * 测试类
 *
 * @author old boy
 * @version 1.0.0
 * @since 1.0.0
 */
public class GuavaEventTest {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();

        eventBus.register(new GuavaEvent());
        eventBus.post("这里是参数吧");
    }
}
