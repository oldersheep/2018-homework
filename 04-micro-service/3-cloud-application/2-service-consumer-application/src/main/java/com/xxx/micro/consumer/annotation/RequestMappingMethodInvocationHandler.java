package com.xxx.micro.consumer.annotation;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;

/**
 * @Description
 * @Date 2019/7/30 20:33
 * @Version 1.0
 */
public class RequestMappingMethodInvocationHandler implements InvocationHandler {

    // 参数发现器
    private final ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

    private final String serviceName;
    private final BeanFactory beanFactory;
    public RequestMappingMethodInvocationHandler(String serviceName, BeanFactory beanFactory) {
        this.serviceName = serviceName;
        this.beanFactory = beanFactory;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 这里一定要用RequestMapping
        RequestMapping requestMapping = findAnnotation(method, RequestMapping.class);

        if (requestMapping != null) {
            // 得到URI
            String[] uri = requestMapping.value();
            StringBuilder urlBuilder = new StringBuilder("http://").append(serviceName).append(uri[0]).append("/");
            int count = method.getParameterCount();

            // 所有参数
            String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
            // 参数类型
            Class<?>[] parameterTypes = method.getParameterTypes();
            // 参数注解
            Annotation[][] annotations = method.getParameterAnnotations();

            StringBuilder queryStringBuilder = new StringBuilder();

            // 方法参数是有顺序的
            for (int i = 0; i< count; i++) {
                Annotation[] parameterAnnotations = annotations[i];

                Class<?> parameterType = parameterTypes[i];
                // 这里及其粗糙
                RequestParam requestParam = (RequestParam)parameterAnnotations[0];//parameterType.getAnnotation(RequestParam.class);
                if (requestParam != null) {

                    String parameterName = ""; //parameterNames[i];
                    String requestParameterName = StringUtils.hasText(requestParam.value()) ? requestParam.value() : parameterName;
                    String requestParameterValue = String.class.equals(parameterType) ? (String) args[i] : String.valueOf(args[i]);

                    queryStringBuilder.append("&")
                            .append(requestParameterName)
                            .append("=")
                            .append(requestParameterValue);
                }
            }
            String queryString = queryStringBuilder.toString();
            if (StringUtils.hasText(queryString)) {
                urlBuilder.append("?").append(queryString);
            }

            // 获取负载均衡的RestTemplate
            RestTemplate restTemplate = beanFactory.getBean("loadBalancedRestTemplate", RestTemplate.class);
            return restTemplate.getForObject(urlBuilder.toString(), method.getReturnType());
        }
        return null;
    }
}
