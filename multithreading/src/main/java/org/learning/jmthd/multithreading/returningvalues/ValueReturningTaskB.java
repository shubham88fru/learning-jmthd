package org.learning.jmthd.multithreading.returningvalues;

import java.util.concurrent.TimeUnit;

public class ValueReturningTaskB implements Runnable {

    private int a;
    private int b;
    private long sleepTime;
    private int sum;

    private static int count = 0;
    private int instanceNumber;
    private String taskId;

    private ResultListener<Integer> callabck;
    public ValueReturningTaskB(int a, int b, long sleepTime, ResultListener<Integer> callback) {
        this.a = a;
        this.b = b;
        this.sleepTime = sleepTime;
        this.instanceNumber = ++count;
        this.taskId = "ValueReturningTaskB-" + instanceNumber;
        this.callabck = callback;
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

        callabck.notifyResult(sum);

    }


}
