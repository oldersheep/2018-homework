package com.xxx.distributed;

/**
 * @Description
 * @Date 2019/5/27 20:58
 * @Version 1.0
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {

        return "hello, " + name;
    }
}
