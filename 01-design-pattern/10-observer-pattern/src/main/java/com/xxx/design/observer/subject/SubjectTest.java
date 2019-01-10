package com.xxx.design.observer.subject;

import com.xxx.design.observer.core.Event;

import java.lang.reflect.Method;

public class SubjectTest {

    public static void main(String[] args) {
        try {
            Observer observer = new Observer();
            Method advice = Observer.class.getMethod("advice", new Class<?>[]{Event.class});

            Subject subject = new Subject();
            subject.addListener(SubjectEnumType.ON_ADD, observer, advice);
            subject.add();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
