package com.xxx.micro.consumer.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Description
 * @Date 2019/7/30 20:11
 * @Version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(RestClientRegistrar.class)
public @interface EnableRestClient {

    Class<?>[] clients() default {};
}
