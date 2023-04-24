/*
 * Copyright (c) 2023. jiyun233 All rights reserved.
 * All code in this project has been written by Jiyun233 and is owned by Jiyun233.
 * There is no plagiarism, borrowing, etc.
 * If there is any similarity, it is purely coincidental
 * @create on: 4/24/23, 12:45 PM
 */

package cute.jiyun233.nya.helpers;

public class EventException extends Exception {
    public EventException() {
    }

    public EventException(String message) {
        super(message);
    }

    public EventException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventException(Throwable cause) {
        super(cause);
    }
}
