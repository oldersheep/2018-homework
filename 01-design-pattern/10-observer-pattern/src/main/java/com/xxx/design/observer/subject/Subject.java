package com.xxx.design.observer.subject;

import com.xxx.design.observer.core.EventListener;

public class Subject extends EventListener {

    public void add() {
        System.out.println("添加一些东西。。");
        trigger(SubjectEnumType.ON_ADD);
    }

    public void remove() {
        System.out.println("删除一些内容。。");
        trigger(SubjectEnumType.ON_REMOVE);
    }
}
