package org.learning.jmthd.multithreading.namingthreads;

import java.util.concurrent.TimeUnit;

public class LoopTaskB implements Runnable {
    private static int count = 0;
    private int instanceNumber;

    private String taskId;

    public LoopTaskB() {
        this.instanceNumber = ++count;
        this.taskId = "LoopTaskB" + instanceNumber;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("Worker-"+instanceNumber);
        String currentThreadName = Thread.currentThread().getName();

        System.out.println("########## [" + currentThreadName + "] -" + taskId + " is starting #########" );
        for (int i = 10; i > 0; i--) {
            System.out.println("[" + currentThreadName + "] -" + taskId + " is running " + i);
            try {
                TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("*********** [" + currentThreadName + "] -" + taskId + " is done ***********" );
    }
}
