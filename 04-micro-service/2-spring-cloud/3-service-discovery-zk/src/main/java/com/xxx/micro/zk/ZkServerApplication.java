package com.xxx.micro.zk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description zookeeper作为服务发现服务端启动类
 * @Date 2019/7/24 20:19
 * @Version 1.0
 */
@SpringBootApplication
public class ZkServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZkServerApplication.class, args);
    }
}
