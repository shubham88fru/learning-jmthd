package org.learning.jmthd.multithreading.alivecheck;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class CalculationTaskA implements Callable<Integer> {

    private int a;
    private int b;
    private long sleepTime;

    private static int count = 0;
    private int instanceNumber;
    private String taskId;

    public CalculationTaskA(int a, int b, long sleepTime) {
        this.a = a;
        this.b = b;
        this.sleepTime = sleepTime;
        this.instanceNumber = ++count;
        this.taskId = "CalculationTaskA-" + instanceNumber;
    }

    @Override
    public Integer call() throws Exception {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("###########[" + currentThreadName +  " - " + taskId + "] value thread starts here...");

        TimeUnit.MILLISECONDS.sleep(sleepTime);

        System.out.println("###########[" + currentThreadName +  " - " + taskId + "] value thread ends here...");

        return a + b;
    }
}
