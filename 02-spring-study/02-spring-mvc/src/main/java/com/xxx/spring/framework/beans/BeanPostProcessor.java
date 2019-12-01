package com.xxx.spring.framework.beans;

import com.sun.istack.internal.Nullable;

// 用来做事件监听
public class BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    //为在Bean的初始化之后提供回调入口
    @Nullable
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
