/*
 * Copyright (c) 2023. jiyun233 All rights reserved.
 * All code in this project has been written by Jiyun233 and is owned by Jiyun233.
 * There is no plagiarism, borrowing, etc.
 * If there is any similarity, it is purely coincidental
 * @create on: 4/23/23, 1:29 PM
 */

package cute.jiyun233.nya;

import cute.jiyun233.nya.helpers.EventException;
import cute.jiyun233.nya.interfaces.EventHandler;
import cute.jiyun233.nya.interfaces.EventListenerOwner;
import cute.jiyun233.nya.interfaces.ListenerMethod;
import cute.jiyun233.nya.interfaces.event.Cancellable;
import cute.jiyun233.nya.interfaces.event.Event;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class NyaEventBus {

    private final HashMap<EventListenerOwner, CopyOnWriteArrayList<ListenerMethod>> listenerClasses = new HashMap<>();
    /**
     * add listener to listener list
     * automatic scan method annotated {@link EventHandler}
     * <pre>
     *     EVENT_BUS.register(listenerOwner)
     * </pre>
     * @param listenerOwner Listener Object Instance
     */
    public void registerListener(EventListenerOwner listenerOwner) {
        if (isRegistered(listenerOwner)) return;
        Method[] publicMethods = listenerOwner.getClass().getMethods();
        Method[] privateMethods = listenerOwner.getClass().getDeclaredMethods();
        Set<Method> methods;
        try {
            methods = new HashSet<>(publicMethods.length + privateMethods.length, 1.0f);
            Collections.addAll(methods, publicMethods);
            Collections.addAll(methods, privateMethods);
            CopyOnWriteArrayList<ListenerMethod> listenerMethods = new CopyOnWriteArrayList<>();
            for (Method method : methods) {
                if (!method.isAnnotationPresent(EventHandler.class)) continue;

                if (method.getParameterCount() != 1) {
                    System.out.println("Listener method only need 1 parameter");
                    continue;
                }

                Class<Event> eventClass = Event.class;
                Class<?> parameterType = method.getParameterTypes()[0];
                if (eventClass.isAssignableFrom(parameterType)) {
                    //noinspection unchecked
                    listenerMethods.add(
                            new ListenerMethod(
                                    method,
                                    method.getAnnotation(EventHandler.class),
                                    listenerOwner,
                                    (Class<Event>) parameterType
                            )
                    );
                } else {
                    throw new EventException("Listener method parameter only allow assignable from Event");
                }
            }
            listenerClasses.put(listenerOwner, listenerMethods);
        }catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Check listener object is registered
     * @param listenerOwner checked object
     * @return this listener is registered
     */
    public boolean isRegistered(EventListenerOwner listenerOwner) {
        return listenerClasses.containsKey(listenerOwner);
    }

    /**
     * remove form listener list
     * @param listenerOwner Listener object
     */
    public void unregisterListener(EventListenerOwner listenerOwner) {
        listenerClasses.remove(listenerOwner);
    }


    /**
     * call all this event method
     * @param event post event
     */
    public void postEvent(Event event) {
        for (Map.Entry<EventListenerOwner, CopyOnWriteArrayList<ListenerMethod>> entry :
                listenerClasses.entrySet()) {

            Set<ListenerMethod> sortedMethod = entry.getValue()
                    .stream()
                    .sorted(Comparator.comparing(it -> it.getPrams().priority().num * -1))
                    .collect(Collectors.toCollection(LinkedHashSet::new));

            for (ListenerMethod listenerMethod : sortedMethod) {
                EventHandler prams = listenerMethod.getPrams();
                if (listenerMethod.getEventClass() == event.getClass()) {
                    if (event instanceof Cancellable) {
                        Cancellable cancellable = (Cancellable) event;
                        if (cancellable.isCanceled() && prams.ignoreCancel()) continue;
                    }
                    listenerMethod.invoke(event);
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NyaEventBus)) return false;
        NyaEventBus eventBus = (NyaEventBus) o;
        return Objects.equals(listenerClasses, eventBus.listenerClasses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listenerClasses);
    }

    @Override
    public String toString() {
        return "EventBus{" +
                "listenerClasses=" + listenerClasses +
                '}';
    }


}
