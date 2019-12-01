package com.xxx.spring.framework.context.support;

import com.xxx.spring.framework.context.ApplicationContext;

public interface ApplicationContextAware {

    void setApplicationContext(ApplicationContext applicationContext) throws Exception;
}
