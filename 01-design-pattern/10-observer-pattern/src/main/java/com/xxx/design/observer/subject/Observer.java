package com.xxx.design.observer.subject;

import com.xxx.design.observer.core.Event;

public class Observer {

    public void advice(Event event){
        System.out.println("观察者，我看到你执行了！！！" + event);
    }
}
