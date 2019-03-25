package com.xxx.spring.framework.context;

import com.xxx.spring.framework.beans.BeanDefinition;
import com.xxx.spring.framework.context.support.BeanDefinitionReader;
import com.xxx.spring.framework.core.BeanFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext implements BeanFactory {

    private String[] configLocations;

    private BeanDefinitionReader reader;

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public ApplicationContext(String... configLocations) {
        this.configLocations = configLocations;
        this.refresh();
    }

    public void refresh() {

        // 定位
        this.reader = new BeanDefinitionReader(configLocations);

        // 加载
        List<String> beanDefinitions = reader.loadDefinitions();
        // 注册
        doRegistry(beanDefinitions);

        // 依赖注入
        // 在这里调用getBean方法

    }

    // 将BeanDefinition注册到beanDefinitionMap（IOC容器）
    private void doRegistry(List<String> beanDefinitions) {

        try {
            for (String className: beanDefinitions) {

                Class<?> beanClass = Class.forName(className);

                /**
                 * 需考虑三种情况 1、默认首字母小写 2、自定义名称 3、接口注入
                 */
                if (beanClass.isInterface()) {
                    continue;
                }
                BeanDefinition beanDefinition = reader.registerBean(className);
                if (beanDefinition != null) {
                    beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
                }
                Class<?>[] interfaces = beanClass.getInterfaces();
                for (Class i : interfaces) {
                    beanDefinitionMap.put(i.getName(), beanDefinition);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 通过读取BeanDefinition中的信息，通过反射创建实例
    // spring不会将最原始的对象放进去，用BeanWrapper进行封装
    // 包装器模式：1、保留原始的oop 2、对其进行扩展，为以后的AOP做铺垫
    @Override
    public Object getBean(String name) {
        return null;
    }
}
