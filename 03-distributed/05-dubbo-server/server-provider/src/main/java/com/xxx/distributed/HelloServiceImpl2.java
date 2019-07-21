package com.xxx.distributed;

/**
 * @Description
 * @Date 2019/6/2 20:09
 * @Version 1.0
 */
public class HelloServiceImpl2 implements HelloService {

    @Override
    public String sayHello(String name) {
        return "hello2," + name;
    }
}
