package com.xxx.micro.provider.support;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 * @Date 2019/7/25 21:16
 * @Version 1.0
 */
public class CircuitBreakerHandlerInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {

        /*if ("/say2" .equals(request.getRequestURI()) && ex instanceof TimeoutException) {
            PrintWriter writer = response.getWriter();
            writer.write(error(""));
        }*/
    }

    private String error(String message) {
        return "fault" + message;
    }
}
