package com.xxx.micro.provider.support;

import com.xxx.micro.provider.controller.HelloController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.TimeoutException;

/**
 * @Description 熔断通知
 * @Date 2019/7/25 21:33
 * @Version 1.0
 */
@RestControllerAdvice(assignableTypes = HelloController.class)
public class CircuitBreakerControllerAdvice {

    @ExceptionHandler(TimeoutException.class)
    public String onTimeOutException(TimeoutException e) {
        return error("");
    }

    private String error(String message) {
        return "fault" + message;
    }
}
