package org.learning.jmthd.multithreading.returningvalues;

import java.util.concurrent.TimeUnit;

public class ValueReturningTaskA implements Runnable {

    private int a;
    private int b;
    private long sleepTime;
    private int sum;

    private static int count = 0;
    private int instanceNumber;
    private String taskId;

    public volatile boolean done = false;

    public ValueReturningTaskA(int a, int b, long sleepTime) {
        this.a = a;
        this.b = b;
        this.sleepTime = sleepTime;
        this.instanceNumber = ++count;
        this.taskId = "ValueReturningTaskA-" + instanceNumber;
    }

    @Override
    public void run() {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("###########[" + currentThreadName +  " - " + taskId + "] value thread starts here...");

        try {
            TimeUnit.MILLISECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        sum = a + b;
        System.out.println("###########[" + currentThreadName +  " - " + taskId + "] value thread ends here...");
        done = true;
        synchronized (this) {
            System.out.println("[" + currentThreadName + "] Notifying ....");
            notify();
        }
    }

    public int getSum() {
        synchronized (this) {
            try {
                System.out.println("[" + Thread.currentThread().getName() + "] Waiting for results from " + taskId + "....");
                if (!done) wait();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sum;
    }
}
