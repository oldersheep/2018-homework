package com.xxx.design.observer.event.mouse;

import com.xxx.design.observer.event.core.Event;

public class MouseEventCallback {

    public void onClick(Event e) {
        System.out.println("触发鼠标点击事件" + e);
    }

    public void onDoubleClick(Event e) {
        System.out.println("触发鼠标双击事件" + e);
    }

    public void onUp(Event e) {
        System.out.println("触发鼠标弹起事件" + e);
    }

    public void onDown(Event e) {
        System.out.println("触发鼠标按下事件" + e);
    }

    public void onMove(Event e) {
        System.out.println("触发鼠标移动事件" + e);
    }

    public void onWheel(Event e) {
        System.out.println("触发鼠标滚动事件" + e);
    }

    public void onOver(Event e) {
        System.out.println("触发鼠标悬停事件" + e);
    }
}
