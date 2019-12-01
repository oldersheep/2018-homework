package com.xxx.micro.consumer.service.rest.clients;

import com.xxx.micro.consumer.annotation.RestClient;
import com.xxx.micro.consumer.controller.ClientController;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.stream.Stream;

@RestClient("${saying.rest.service.name}")
public interface SayingRestService {

    @RequestMapping(value = "/say", method = RequestMethod.GET)
    String say(@RequestParam("message") String message); // 请求参数和方法参数同名

    static void main(String[] args) throws Exception {
        // 这个地方不能使用接口
        Method method = ClientController.class.getMethod("restSay", String.class);
        Parameter parameter = method.getParameters()[0];
        System.out.println(parameter.getName());

        parameter.isNamePresent();

        DefaultParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();

        Stream.of(nameDiscoverer.getParameterNames(method)).forEach(System.out::println);
    }
}
