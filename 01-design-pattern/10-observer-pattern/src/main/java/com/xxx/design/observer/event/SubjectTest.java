package com.xxx.design.observer.event;

import com.xxx.design.observer.event.core.Event;
import com.xxx.design.observer.event.subject.Observer;
import com.xxx.design.observer.event.subject.Subject;
import com.xxx.design.observer.event.subject.SubjectEnumType;

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
