package com.xxx.mybatis.executor;

public interface Executor {

    <T> T query(String statement, Object[] parameter, Class pojo);
}
