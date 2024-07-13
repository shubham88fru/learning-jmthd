package org.learning.jmthd.multithreading.alivecheck;

import java.util.concurrent.TimeUnit;

public class LoopTaskC implements Runnable {
    private static int count = 0;
    private int instanceNumber;

    private String taskId;

    public LoopTaskC() {
        this.instanceNumber = ++count;
        this.taskId = "LoopTaskC" + instanceNumber;
    }

    @Override
    public void run() {
        String currentThreadName = Thread.currentThread().getName();

        System.out.println("########## [" + currentThreadName + "] - " + taskId + " is starting #########" );
        for (int i = 10; i > 0; i--) {
            System.out.println("[" + currentThreadName + "] - " + taskId + " is running " + i);
            try {
                TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("*********** [" + currentThreadName + "] - " + taskId + " is done ***********" );
    }
}
