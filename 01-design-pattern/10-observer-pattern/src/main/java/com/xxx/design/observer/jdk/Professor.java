package com.xxx.design.observer.jdk;

import java.util.Observable;
import java.util.Observer;

/**
 * 大牛，属于观察者
 *
 * @author old boy
 * @version 1.0.0
 * @since 1.0.0
 */
public class Professor implements Observer {

    private String name = "天涯论坛";

    public Professor(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {

        Forum forum = (Forum) o;
        Question question = (Question) arg;

        System.out.println("======================");
        System.out.println(name + "您好！");
        System.out.println("有人在“" + forum.getName() + "”邀请您回答：");
        System.out.println(question.getContent());

        System.out.println("提问者：" + question.getNickName());

    }
}
