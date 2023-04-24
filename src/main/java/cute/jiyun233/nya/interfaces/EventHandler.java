/*
 * Copyright (c) 2023. jiyun233 All rights reserved.
 * All code in this project has been written by Jiyun233 and is owned by Jiyun233.
 * There is no plagiarism, borrowing, etc.
 * If there is any similarity, it is purely coincidental
 * @create on: 4/23/23, 1:36 PM
 */

package cute.jiyun233.nya.interfaces;

import cute.jiyun233.nya.helpers.ListenerPriority;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {

    ListenerPriority priority() default ListenerPriority.NORMAL;

    boolean ignoreCancel() default true;

}
