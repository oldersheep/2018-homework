package com.xxx.design.observer.core;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class EventListener {

    protected Map<Enum, Event> events = new HashMap<Enum, Event>();

    public void addListener(Enum eventType, Object target, Method callback) {
        events.put(eventType, new Event(target, callback));
    }

    private void trigger(Event event) {
        event.setSource(this);
        event.setTime(System.currentTimeMillis());
        try {
            event.getCallback().invoke(event.getTarget(), event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void trigger(Enum call) {
        if (!this.events.containsKey(call)) {
            return ;
        }
        trigger(this.events.get(call).setTrigger(call.toString()));
    }
}
