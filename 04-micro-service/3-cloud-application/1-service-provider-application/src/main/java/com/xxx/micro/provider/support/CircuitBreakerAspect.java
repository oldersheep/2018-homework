package com.xxx.micro.provider.support;

import com.xxx.micro.provider.annotation.SemaphoreCircuitBreaker;
import com.xxx.micro.provider.annotation.TimeoutCircuitBreaker;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.*;

/**
 * @Description 熔断切面
 * @Date 2019/7/25 21:49
 * @Version 1.0
 */
@Aspect
@Component
public class CircuitBreakerAspect {

    private ExecutorService executorService = Executors.newFixedThreadPool(20);
    private volatile Semaphore semaphore = null;

    @Around("execution(* com.xxx.micro.provider.controller.HelloController.say5(..)) && args(message) ")
    public Object advancedSayInTimeout(ProceedingJoinPoint point, String message) throws Throwable {

        return doInvoke(point, message, 100);
    }

    //@Around("execution(* com.xxx.micro.hystrix.controller.HelloController.say6(..)) && args(message) && @annotation(circuitBreaker)")
    @Around("args(message) && @annotation(circuitBreaker)")
    public Object advancedSayInTimeout(ProceedingJoinPoint point, String message, TimeoutCircuitBreaker circuitBreaker) throws Throwable {
        /*long timeout = -1;
        if (point instanceof MethodInvocationProceedingJoinPoint) {
            MethodInvocationProceedingJoinPoint proceedingPoint = (MethodInvocationProceedingJoinPoint) point;
            MethodSignature signature = (MethodSignature)proceedingPoint.getSignature();
            Method method = signature.getMethod();
            CircuitBreaker circuitBreaker = method.getAnnotation(CircuitBreaker.class);
            timeout = circuitBreaker.timeout();
        }*/
        long timeout = circuitBreaker.timeout();

        return doInvoke(point, message, timeout);
    }

    @Around("execution(* com.xxx.micro.provider.controller.HelloController.say7(..))" +
            " && args(message)" +
            " && @annotation(circuitBreaker) ")
    public Object advancedSay3InSemaphore(ProceedingJoinPoint point,
                                          String message,
                                          SemaphoreCircuitBreaker circuitBreaker) throws Throwable {
        int value = circuitBreaker.value();
        if (semaphore == null) {
            semaphore = new Semaphore(value);
        }
        Object returnValue = null;
        try {
            if (semaphore.tryAcquire()) {
                returnValue = point.proceed(new Object[]{message});
                Thread.sleep(1000);
            } else {
                returnValue = error("");
            }
        } finally {
            semaphore.release();
        }

        return returnValue;

    }


    private Object doInvoke(ProceedingJoinPoint point,
                            String message, long timeout) throws Throwable {

        Future<Object> future = executorService.submit(() -> {
            Object returnValue = null;
            try {
                returnValue = point.proceed(new Object[]{message});
            } catch (Throwable ex) {
            }
            return returnValue;
        });

        Object returnValue = null;
        try {
            returnValue = future.get(timeout, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            future.cancel(true); // 取消执行
            returnValue = error("");
        }
        return returnValue;
    }

    String error(String message) {
        return "fault";
    }

    @PreDestroy
    public void destroy() {
        executorService.shutdown();
    }
}
