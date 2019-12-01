package com.xxx.spring.framework.beans;

import com.sun.istack.internal.Nullable;

// 用来存储配置信息
public class BeanDefinition {

    private String beanClassName;
    private String factoryBeanName;
    private boolean lazyInit = false;

    public void setBeanClassName(@Nullable String beanClassName){
        this.beanClassName = beanClassName;
    }

    @Nullable
    public String getBeanClassName(){
        return this.beanClassName;
    }

    public void setFactoryBeanName(@Nullable String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }

    @Nullable
    public String getFactoryBeanName() {
        return this.factoryBeanName;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public boolean isLazyInit() {

        return this.lazyInit;
    }
}
