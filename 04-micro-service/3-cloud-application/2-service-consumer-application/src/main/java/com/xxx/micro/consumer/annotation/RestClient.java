package com.xxx.micro.consumer.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface RestClient {
    String value() default "";
}
