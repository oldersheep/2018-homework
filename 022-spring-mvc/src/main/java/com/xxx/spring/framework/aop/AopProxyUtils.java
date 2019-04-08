package com.xxx.spring.framework.aop;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

public class AopProxyUtils {

    public static Object getTargetObject(Object proxy) throws Exception {
        // 先判断 proxy 是否是代理后的对象
        if (isAopProxy(proxy)) {
            Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
            h.setAccessible(true);
            AopProxy aopProxy = (AopProxy) h.get(proxy);
            Field target = aopProxy.getClass().getDeclaredField("target");
            target.setAccessible(true);

            return target.get(aopProxy);
        }
        // 否则直接返回
        return proxy;
    }

    private static boolean isAopProxy(Object object) {

        return Proxy.isProxyClass(object.getClass());
    }
}
