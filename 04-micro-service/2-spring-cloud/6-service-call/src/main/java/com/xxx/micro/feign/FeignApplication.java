package com.xxx.micro.feign;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Description
 * @Date 2019/7/28 15:54
 * @Version 1.0
 */
@EnableFeignClients
@SpringBootApplication
public class FeignApplication {

    /**
     * 这里写一些内容吧，在使用eureka作为服务发现的时候（其实zk也是一样的），如果直接进行服务调用，例如服务提供方提供了
     * 一个请求的URL为“/say?message=hi”。服务消费方需要通过RestTemplate的方式进行调用，此时有以下几种方式：
     *
     * 一、直接new RestTemplate()，然后 restTemplate.getForObject("http://127.0.0.1:8085/msg", String.class);
     *     这种方式和传统意义上的httpclient的方式没有差别，也没有利用到服务发现所带来的好处
     *
     * 二、通过注入 LoadBalancerClient 来获取URL，然后再通过restTemplate进行调用，这里的好处就是用到了一点eureka
     *     ServiceInstance product = loadBalancerClient.choose("PRODUCT");
     *     String url = String.format("http://%s:%s/msg",product.getHost(), product.getPort());
     *
     * 三、就是通过注入RestTemplate并配合LoadBalanced注解，来完成负载均衡与调用，这里的调用方式呢，就是rest调用
     *     http://{服务提供方的服务名}/say?message=hi
     *
     *  这时，如果使用了Feign作为服务调用，可以提供一个feign的客户端，通过标注@FeignClient("服务提供方服务名")，
     *  然后定义一些与服务提供方的URL一致，参数一致的方法，服务消费端直接注入此客户端即可。
     *  当然，还可以配合Hystrix进行容错机制。
     *
     *  这里代码就不写那么多了。。。
     */

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
