package com.xxx.spring.framework.context.support;

import com.xxx.spring.framework.beans.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultListableBeanFactory extends AbstractApplicationContext {

    // 保存配置信息
    protected final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    @Override
    protected void onRefresh() throws Exception {

    }

    @Override
    protected void refreshBeanFactory() throws Exception {

    }
}
