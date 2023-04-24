/*
 * Copyright (c) 2023. jiyun233 All rights reserved.
 * All code in this project has been written by Jiyun233 and is owned by Jiyun233.
 * There is no plagiarism, borrowing, etc.
 * If there is any similarity, it is purely coincidental
 * @create on: 4/23/23, 1:49 PM
 */

package cute.jiyun233.nya.interfaces.event;

public interface Cancellable {

    public boolean isCanceled();

    public void cancel();

}
