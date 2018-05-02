package com.xxx.design.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Service implements MethodInterceptor {

    public Object getInstance(Class<?> clazz){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);

        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        String methodName = method.getName();
        if("modify".equals(methodName)){
            System.out.println("开启事务");

            methodProxy.invokeSuper(o, objects);

            System.out.println("提交事务，若有异常，则回滚！");
        } else {
            System.out.println("不需要开启事务");
            methodProxy.invokeSuper(o, objects);
        }

        return null;
    }
}
