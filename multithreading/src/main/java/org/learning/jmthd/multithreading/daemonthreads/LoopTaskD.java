package org.learning.jmthd.multithreading.daemonthreads;

import java.util.concurrent.TimeUnit;

public class LoopTaskD implements Runnable {
    private static int count = 0;
    private int instanceNumber;

    private String taskId;

    private long sleepTime;

    public LoopTaskD(long sleepTime) {
        this.sleepTime = sleepTime;
        this.instanceNumber = ++count;
        this.taskId = "LoopTaskD" + instanceNumber;
    }

    @Override
    public void run() {
        boolean isDaemon = Thread.currentThread().isDaemon();
        String threadType = isDaemon ? "DAEMON": "USER";

        String currentThreadName = Thread.currentThread().getName();

        System.out.println("########## [" + currentThreadName + "] -" + threadType + " " + taskId + " is starting #########" );
        for (int i = 10; i > 0; i--) {
            System.out.println("[" + currentThreadName + "] -" + threadType + " "  + taskId + " is running " + i);
            try {
                TimeUnit.MILLISECONDS.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("*********** [" + currentThreadName + "] -" + threadType + " " + taskId + " is done ***********" );
    }
}
