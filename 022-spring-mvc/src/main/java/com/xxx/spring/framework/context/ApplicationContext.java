package com.xxx.spring.framework.context;

import com.xxx.spring.framework.annotation.Autowired;
import com.xxx.spring.framework.annotation.Controller;
import com.xxx.spring.framework.annotation.Service;
import com.xxx.spring.framework.beans.BeanDefinition;
import com.xxx.spring.framework.beans.BeanPostProcessor;
import com.xxx.spring.framework.beans.BeanWrapper;
import com.xxx.spring.framework.context.support.BeanDefinitionReader;
import com.xxx.spring.framework.core.BeanFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext implements BeanFactory {

    private String[] configLocations;

    private BeanDefinitionReader reader;

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    // 用来保证注册式单例的容器
    private Map<String, Object> beanCacheMap = new HashMap<>();
    // 存储所有被代理过的对象
    private Map<String, BeanWrapper> beanWrapperMap = new ConcurrentHashMap<>();

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
        doAutowired();

        System.out.println("======搞定收工======");
    }

    private void doAutowired() {

        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : this.beanDefinitionMap.entrySet()) {
            String beanName = beanDefinitionEntry.getKey();
            if (!beanDefinitionEntry.getValue().isLazyInit()) {
                getBean(beanName);
            }
        }
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
        BeanDefinition beanDefinition = this.beanDefinitionMap.get(name);
        String className = beanDefinition.getBeanClassName();
        try {

            // 生成通知事件
            BeanPostProcessor beanPostProcessor = new BeanPostProcessor();

            // 单例
            Object instance = createInstance(beanDefinition);

            // 初始化之前调用
            beanPostProcessor.postProcessBeforeInitialization(instance, name);

            if (instance == null) { return null;}

            BeanWrapper beanWrapper = new BeanWrapper(instance);
            beanWrapper.setPostProcessor(beanPostProcessor);
            this.beanWrapperMap.put(name, beanWrapper);

            // 实例化之后调用
            beanPostProcessor.postProcessAfterInitialization(instance, name);

            populateBean(name, instance);

            // 这里返回的内容，是被代理过的
            return this.beanWrapperMap.get(name).getWrappedInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 根据BeanDefinition获取Bean实例
    private Object createInstance(BeanDefinition beanDefinition) {
        Object instance;
        String className = beanDefinition.getBeanClassName();
        try {
            // 根据class去确定一个类的实例是否被创建
            if (this.beanCacheMap.containsKey(className)) {
                instance = this.beanCacheMap.get(className);
            } else {
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();
                this.beanCacheMap.put(className, instance);
            }
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 依赖注入
    private void populateBean(String beanName, Object instance) {
        Class<?> clazz = instance.getClass();
        if (!clazz.isAnnotationPresent(Controller.class) && !clazz.isAnnotationPresent(Service.class)) {
            return;
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            try {
                if (!field.isAnnotationPresent(Autowired.class)) {continue;}
                Autowired autowired = field.getAnnotation(Autowired.class);
                String autowiredBeanName = autowired.value().trim();
                if ("".equalsIgnoreCase(autowiredBeanName)) {
                    autowiredBeanName = field.getType().getName();
                }
                field.setAccessible(true);
                // 当需要注入的类还不存在时，需要做的事情
                if (!beanWrapperMap.containsKey(autowiredBeanName)) {
                    getBean(autowiredBeanName);
                }
                BeanWrapper beanWrapper = this.beanWrapperMap.get(autowiredBeanName);
                field.set(instance, beanWrapper.getWrappedInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
