package com.xxx.design.observer.jdk;

import java.util.Observable;

/**
 * 论坛类, 属于一个被观察者
 *
 * @author old boy
 * @version 1.0.0
 * @since 1.0.0
 */
public class Forum extends Observable {

    private String name;

    private static Forum INSTANCE = null;

    private Forum() {
    }

    public static Forum getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Forum();
        }
        return INSTANCE;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void publisher(Question question) {

        System.out.println(question.getNickName() + "在" + name + "上提交了->" + question.getContent());
        setChanged();
        notifyObservers(question);
    }
}
