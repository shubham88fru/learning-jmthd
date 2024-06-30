package org.learning.jmthd.multithreading.namingthreads;

import java.util.concurrent.ThreadFactory;

public class NamedThreadsFactory implements ThreadFactory {

    private static int count = 0;
    private static String NAME = "PoolWorker-";
    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, NAME + ++count);
    }
}
