package com.xxx.rmi.rpc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description 标注 需要发布的服务
 * @Date 2019/5/21 20:52
 * @Version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RemoteService {

    /**
     * 对外发布的服务地址
     * @return
     */
    Class<?> value();

    String version() default "";
}
