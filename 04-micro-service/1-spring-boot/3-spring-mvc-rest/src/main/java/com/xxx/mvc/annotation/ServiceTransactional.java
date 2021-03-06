package com.xxx.mvc.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Service
@Transactional
public @interface ServiceTransactional {

    @AliasFor(annotation = Service.class)
    String value(); // 服务名称 Compent不能复写value属性

    @AliasFor(annotation = Transactional.class, attribute = "value")
    String txName();
}
