package com.xxx.design.observer.event.mouse;

import com.xxx.design.observer.event.core.Event;

import java.lang.reflect.Method;

public class MouseTest {

    public static void main(String[] args) throws NoSuchMethodException {

        MouseEventCallback callback = new MouseEventCallback();
        Method onClick = MouseEventCallback.class.getMethod("onClick",new Class<?>[]{Event.class});

        Mouse mouse = new Mouse();
        mouse.addListener(MouseEventType.ON_CLICK,callback,onClick);
        mouse.click();
    }
}
