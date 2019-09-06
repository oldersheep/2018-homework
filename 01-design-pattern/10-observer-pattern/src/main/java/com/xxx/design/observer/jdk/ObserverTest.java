package com.xxx.design.observer.jdk;

/**
 * 测试类
 *
 * @author old boy
 * @version 1.0.0
 * @since 1.0.0
 */
public class ObserverTest {

    public static void main(String[] args) {
        Forum forum = Forum.getInstance();
        Professor professor = new Professor("秦时明月");

        Question question = new Question();
        question.setNickName("一直笑呵呵");
        question.setContent("如何看待 明亡于万历");

        forum.addObserver(professor);
        forum.publisher(question);

    }
}
