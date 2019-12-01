package com.xxx.spring.framework.aop;

import com.sun.istack.internal.Nullable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// 默认使用JDK的动态代理
public class AopProxy implements InvocationHandler {

    private AopConfig config;

    private Object target;

    // 传入原生对象，生成代理对象
    public Object getProxy(Object instance) {
        this.target = instance;
        Class<?> clazz = instance.getClass();

        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 代理的方法
        Method m = this.target.getClass().getMethod(method.getName(), method.getParameterTypes());

        // 执行before
        if (config.contains(m)) { // 这里要通过代理的方法去找
            AopConfig.Aspect aspect = config.get(m);
            aspect.getPoints()[0].invoke(aspect.getAspect());
        }

        // 执行方法
        Object result = method.invoke(this.target, args);

        // 执行after
        if (config.contains(m)) {
            AopConfig.Aspect aspect = config.get(m);
            aspect.getPoints()[1].invoke(aspect.getAspect());
        }
        // 将结果方法的执行结果返回
        return result;
    }

    public void setConfig(AopConfig config) {
        this.config = config;
    }
}
