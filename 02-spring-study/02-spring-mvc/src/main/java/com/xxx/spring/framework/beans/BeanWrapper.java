package com.xxx.spring.framework.beans;

import com.xxx.spring.framework.aop.AopConfig;
import com.xxx.spring.framework.aop.AopProxy;
import com.xxx.spring.framework.core.FactoryBean;

public class BeanWrapper extends FactoryBean {

    private final AopProxy aop = new AopProxy();

    // 使用观察者模式，支持事件响应
    private BeanPostProcessor postProcessor;

    private Object wrapperInstance;
    // 原始的通过反射new出来的类，包装后存下来
    private Object originalInstance;

    public BeanWrapper(Object instance) {
        this.wrapperInstance = aop.getProxy(instance);
        this.originalInstance = instance;
    }

    public Object getWrappedInstance() {

        return this.wrapperInstance;
    }

    // 返回代理以后的Class
    // 在Spring中可能会是$proxy0
    public Class<?> getWrapperClass() {

        return this.wrapperInstance.getClass();
    }

    public BeanPostProcessor getPostProcessor() {
        return postProcessor;
    }

    public void setPostProcessor(BeanPostProcessor postProcessor) {
        this.postProcessor = postProcessor;
    }

    public void setAopConfig(AopConfig config) {
        aop.setConfig(config);
    }
}
