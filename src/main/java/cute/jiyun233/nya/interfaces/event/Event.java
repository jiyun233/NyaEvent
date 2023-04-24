/*
 * Copyright (c) 2023. jiyun233 All rights reserved.
 * All code in this project has been written by Jiyun233 and is owned by Jiyun233.
 * There is no plagiarism, borrowing, etc.
 * If there is any similarity, it is purely coincidental
 * @create on: 4/23/23, 1:47 PM
 */

package cute.jiyun233.nya.interfaces.event;

import java.util.Objects;

public abstract class Event {

    private String eventName = this.getClass().getSimpleName();

    public Event() {
    }

    public Event(String eventName) {
        this.eventName = eventName;
    }

    public Event(int stage) {
        this.stage = stage;
    }

    private int stage = 0;

    public int getStage() {
        return stage;
    }

    public String getEventName() {
        return eventName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return getStage() == event.getStage() && Objects.equals(getEventName(), event.getEventName());
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventName='" + eventName + '\'' +
                ", stage=" + stage +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEventName(), getStage());
    }
}
