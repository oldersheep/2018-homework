package com.xxx.distributed;

/**
 * @Description
 * @Date 2019/6/2 21:07
 * @Version 1.0
 */
public class MockServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "系统繁忙，客官稍等!" + name;
    }
}
