/*
 * Copyright (c) 2023. jiyun233 All rights reserved.
 * All code in this project has been written by Jiyun233 and is owned by Jiyun233.
 * There is no plagiarism, borrowing, etc.
 * If there is any similarity, it is purely coincidental
 * @create on: 7/19/23, 8:05 PM
 */

@file:Suppress("UNCHECKED_CAST")

package cute.jiyun233.nya.extend

import cute.jiyun233.nya.helpers.ListenerPriority
import cute.jiyun233.nya.interfaces.EventListenerOwner
import cute.jiyun233.nya.interfaces.EventRunnable
import cute.jiyun233.nya.interfaces.ListenerMethod
import cute.jiyun233.nya.interfaces.event.Event
import java.util.concurrent.CopyOnWriteArrayList

@JvmOverloads
inline fun <reified E: Event> EventListenerOwner.listener(
    listenerPriority: ListenerPriority = ListenerPriority.NORMAL,
    ignoredCancel: Boolean = true,
    listener: EventRunnable
) {
    if (!EventListenerOwner.kotlinRunnable.containsKey(this)) {
        EventListenerOwner.kotlinRunnable[this] = CopyOnWriteArrayList()
    }
    EventListenerOwner.kotlinRunnable[this]!!.add(
        ListenerMethod(
            listener,
            listenerPriority,
            ignoredCancel,
            this,
            E::class.java as Class<Event>
        )
    )
}