package com.xxx.design.observer.guava;

import com.google.common.eventbus.Subscribe;

/**
 * 使用guava 声明一个事件
 *
 * @author old boy
 * @version 1.0.0
 * @since 1.0.0
 */
public class GuavaEvent {

    @Subscribe
    public void subscribe(String str) {
        System.out.println("执行subscribe方法，传入参数：" + str);
    }
}
