package org.learning.jmthd.multithreading.terminatingthreads;

import java.util.concurrent.TimeUnit;

public class LoopTaskE implements Runnable {
    private static int count = 0;
    private int instanceNumber;

    private String taskId;

    private volatile boolean shutdown = false;

    public LoopTaskE() {
        this.instanceNumber = ++count;
        this.taskId = "LoopTaskE" + instanceNumber;
    }

    @Override
    public void run() {
        String currentThreadName = Thread.currentThread().getName();

        System.out.println("########## [" + currentThreadName + "] -" + taskId + " is starting #########" );
        for (int i = 1; ; i++) {
            System.out.println("[" + currentThreadName + "] -" + taskId + " is running " + i);
            try {
                TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (this) {
                if (shutdown) break;
            }
        }
        System.out.println("*********** [" + currentThreadName + "] -" + taskId + " is done ***********" );
    }

    public void cancel() {
        System.out.println("********** [" + Thread.currentThread().getName() + "] <" + taskId + "> Shutting down...");

        synchronized (this) {
            this.shutdown = true;
        }

    }
}
