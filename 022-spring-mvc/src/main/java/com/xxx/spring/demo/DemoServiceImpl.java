package com.xxx.spring.demo;

import com.xxx.spring.framework.annotation.Service;

@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String get(String name) {

        return "My name is " + name;
    }
}
