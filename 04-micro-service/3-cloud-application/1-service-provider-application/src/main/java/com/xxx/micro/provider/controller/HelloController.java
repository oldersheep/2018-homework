package com.xxx.micro.provider.controller;

import com.xxx.micro.provider.annotation.SemaphoreCircuitBreaker;
import com.xxx.micro.provider.annotation.TimeoutCircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Description
 * @Date 2019/7/25 20:50
 * @Version 1.0
 */
@RestController
public class HelloController {

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public String error(String message) {
        return "fault";
    }

    @GetMapping("/say")
    public String say(@RequestParam String message) throws InterruptedException {
        // 如果随机时间 大于 100 ，那么触发容错
        int value = new Random().nextInt(200);

        System.out.println("say() costs " + value + " ms.");

        // > 100
        Thread.sleep(value);

        System.out.println("ServerController 接收到消息 - say : " + message);
        return "Hello, " + message;
    }


    // 简易版熔断 无容错实现
    @GetMapping("/say2")
    public String say2(@RequestParam String message) throws Exception {
        Future<String> future = executorService.submit(() -> doSay2(message));

        return future.get(100, TimeUnit.MILLISECONDS);
    }

    // 简易版熔断 添加简易容错实现
    @GetMapping("/say3")
    public String say3(@RequestParam String message) throws Exception {
        Future<String> future = executorService.submit(() -> doSay2(message));

        String result = null;
        try {
            result = future.get(100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            result = error(message);
        }

        return result;
    }

    /**
     * 中级版本熔断 配合RestControllerAdvice实现熔断
     */
    @GetMapping("/say4")
    public String say4(@RequestParam String message) throws Exception {
        Future<String> future = executorService.submit(() -> doSay2(message));

        String result = null;
        try {
            result = future.get(100, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            future.cancel(true);
            throw e;
        }

        return result;
    }

    /**
     * 切面实现
     */
    @GetMapping("/say5")
    public String say5(@RequestParam String message) throws Exception {

        return doSay2(message);
    }

    /**
     * 注解实现
     */
    @GetMapping("/say6")
    @TimeoutCircuitBreaker(timeout = 100)
    public String say6(@RequestParam String message) throws Exception {

        return doSay2(message);
    }

    /**
     * 高级版本 + 注解（信号量）
     */
    @GetMapping("/say7")
    @SemaphoreCircuitBreaker(1)
    public String say7(@RequestParam String message) throws Exception {
        return doSay2(message);
    }

    private String doSay2(String message) throws InterruptedException {
        int value = new Random().nextInt(200);
        System.out.println("say() costs " + value + "ms.");

        Thread.sleep(value);

        return "hello " + value;
    }
}
