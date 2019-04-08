package com.xxx.spring.framework.context.support;

public abstract class AbstractApplicationContext {

    protected void onRefresh() throws Exception {

    }

    protected abstract void refreshBeanFactory() throws Exception;
}
