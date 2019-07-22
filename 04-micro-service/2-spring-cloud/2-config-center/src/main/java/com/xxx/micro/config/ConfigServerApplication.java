package com.xxx.micro.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 配置中心服务端
 * @Date 2019/7/22 20:19
 * @Version 1.0
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }

    @Bean
    public EnvironmentRepository environmentRepository() {

        return (application, profile, label) -> {
            Environment environment = new Environment("default", profile);

            Map<Object, Object> source = new HashMap<>();
            source.put("name", "custom-environment");

            PropertySource propertySource = new PropertySource("map", source);

            List<PropertySource> propertySources = environment.getPropertySources();
            propertySources.add(propertySource);

            return environment;
        };
    }
}
