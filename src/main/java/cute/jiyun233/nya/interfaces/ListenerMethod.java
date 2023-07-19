/*
 * Copyright (c) 2023. jiyun233 All rights reserved.
 * All code in this project has been written by Jiyun233 and is owned by Jiyun233.
 * There is no plagiarism, borrowing, etc.
 * If there is any similarity, it is purely coincidental
 * @create on: 4/23/23, 1:35 PM
 */

package cute.jiyun233.nya.interfaces;

import cute.jiyun233.nya.helpers.ListenerPriority;
import cute.jiyun233.nya.interfaces.event.Event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class ListenerMethod {

    private final EventRunnable runnable;

    private final EventListenerOwner father;

    private final ListenerPriority priority;

    private final boolean ignoreCancel;

    private final Class<Event> eventClass;


    public ListenerMethod(EventRunnable runnable, ListenerPriority priority,boolean ignoreCancel, EventListenerOwner father, Class<Event> eventClass) {
        this.runnable = runnable;
        this.priority = priority;
        this.ignoreCancel = ignoreCancel;
        this.father = father;
        this.eventClass = eventClass;
    }

    public EventRunnable getRunnable() {
        return runnable;
    }

    public EventListenerOwner getFather() {
        return father;
    }

    public Class<Event> getEventClass() {
        return eventClass;
    }

    public void invoke(Event event) {
        runnable.run(event);
    }

    public ListenerPriority getPriority() {
        return priority;
    }

    public boolean isIgnoreCancel() {
        return ignoreCancel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListenerMethod)) return false;
        ListenerMethod that = (ListenerMethod) o;
        return ignoreCancel == that.ignoreCancel && Objects.equals(runnable, that.runnable) && Objects.equals(father, that.father) && priority == that.priority && Objects.equals(eventClass, that.eventClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(runnable, father, priority, ignoreCancel, eventClass);
    }

    @Override
    public String toString() {
        return "ListenerMethod{" +
                "runnable=" + runnable +
                ", father=" + father +
                ", priority=" + priority +
                ", ignoreCancel=" + ignoreCancel +
                ", eventClass=" + eventClass +
                '}';
    }
}
