package com.xxx.design.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Service implements InvocationHandler {

    private Dao dao;

    public Object getInstance(Dao dao) {
        this.dao = dao;
        Class<?> clazz = dao.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if ("modify".equals(method.getName())) {
            System.out.println("Service层需要开启事务...");

            method.invoke(dao, args);

            System.out.println("事务提交，如有异常，回滚起来！");
        } else {
            System.out.println("查询不需要开启事务！");
            method.invoke(dao,args);
        }

        return null;
    }
}
