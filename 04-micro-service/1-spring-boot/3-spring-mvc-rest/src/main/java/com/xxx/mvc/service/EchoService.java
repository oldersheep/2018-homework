package com.xxx.mvc.service;

import com.xxx.mvc.annotation.ServiceTransactional;

@ServiceTransactional(value = "echoService-10years", txName = "myTxName")
public class EchoService {

    public void echo(String message){
        System.out.println(message);
    }

}
