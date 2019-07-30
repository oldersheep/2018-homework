package com.xxx.micro.hystrix.controller;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @Description
 * @Date 2019/7/25 20:50
 * @Version 1.0
 */
@RestController
public class HelloController {

    @HystrixCommand(fallbackMethod = "error", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")
    })
    @GetMapping("/say")
    public String say(@RequestParam String message) throws InterruptedException {
        int value = new Random().nextInt(200);
        System.out.println("say() costs " + value + "ms.");

        Thread.sleep(value);
        return "hello " + message;
    }

    @GetMapping("/say-a")
    public String sayA(@RequestParam String message) throws InterruptedException {

        return new OneCommand().execute();
    }

    private class OneCommand extends com.netflix.hystrix.HystrixCommand<String> {

        protected OneCommand() {
            super(HystrixCommandGroupKey.Factory.asKey("Hello"), 100);

        }

        @Override
        protected String run() throws Exception {
            return "hello world";
        }

        protected String fallback() {
            return "fault";
        }
    }

    public String error(String message) {
        return "fault";
    }

}
