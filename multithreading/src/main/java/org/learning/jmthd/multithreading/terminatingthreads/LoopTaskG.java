package org.learning.jmthd.multithreading.terminatingthreads;

import java.util.concurrent.TimeUnit;

public class LoopTaskG implements Runnable {
    private static int count = 0;
    private int instanceNumber;

    private String taskId;

    public LoopTaskG() {
        this.instanceNumber = ++count;
        this.taskId = "LoopTaskG" + instanceNumber;
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
                System.out.println("*********** [" + currentThreadName + "] -" + taskId + " Sleep interrupted. Cancelling ***********" );
                break;
            }

        }
        System.out.println("*********** [" + currentThreadName + "] -" + taskId + " is done ***********" );
    }

}
