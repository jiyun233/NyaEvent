/*
 * Copyright (c) 2023. jiyun233 All rights reserved.
 * All code in this project has been written by Jiyun233 and is owned by Jiyun233.
 * There is no plagiarism, borrowing, etc.
 * If there is any similarity, it is purely coincidental
 * @create on: 4/23/23, 1:35 PM
 */

package cute.jiyun233.nya.interfaces;

import cute.jiyun233.nya.interfaces.event.Event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class ListenerMethod {

    private final Method method;

    private final EventHandler prams;

    private final EventListenerOwner father;

    private final Class<Event> eventClass;


    public ListenerMethod(Method method, EventHandler prams, EventListenerOwner father, Class<Event> eventClass) {
        this.method = method;
        this.prams = prams;
        this.father = father;
        this.eventClass = eventClass;
    }

    public Method getMethod() {
        return method;
    }

    public EventHandler getPrams() {
        return prams;
    }

    public EventListenerOwner getFather() {
        return father;
    }

    public Class<Event> getEventClass() {
        return eventClass;
    }

    public void invoke(Event event) {
        try {
            this.getMethod().setAccessible(false);
            this.getMethod().invoke(father,event);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListenerMethod)) return false;
        ListenerMethod that = (ListenerMethod) o;
        return Objects.equals(getMethod(), that.getMethod()) && Objects.equals(getPrams(), that.getPrams()) && Objects.equals(getFather(), that.getFather()) && Objects.equals(getEventClass(), that.getEventClass());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMethod(), getPrams(), getFather(), getEventClass());
    }

    @Override
    public String toString() {
        return "ListenerMethod{" +
                "method=" + method +
                ", prams=" + prams +
                ", father=" + father +
                ", eventClass=" + eventClass +
                '}';
    }
}
