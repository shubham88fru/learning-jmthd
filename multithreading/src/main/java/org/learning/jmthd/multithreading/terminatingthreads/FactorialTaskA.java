package org.learning.jmthd.multithreading.terminatingthreads;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class FactorialTaskA implements Callable<Long> {
    private static int count = 0;
    private int instanceNumber;

    private String taskId;

    private volatile boolean shutdown = false;

    private long a;
    private long sleepTime;
    private long factorial;

    public FactorialTaskA(long a, long sleepTime) {
        this.a = a;
        this.sleepTime = sleepTime;
        this.instanceNumber = ++count;
        this.taskId = "FactorialTasA" + instanceNumber;
    }

    @Override
    public Long call() throws Exception {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("########## [" + currentThreadName + "] -" + taskId + " is starting #########" );

        factorial = 1L;

        for (long i = 1; i<= a; i++) {
            factorial *= i;
            System.out.println("[" + currentThreadName + "] -" + taskId + " is running " + i + "Intermediate result: " + factorial);
            TimeUnit.MILLISECONDS.sleep(sleepTime);

            synchronized (this) {
                if (shutdown) {
                    factorial = -1;
                    break;
                }
            }
        }
        System.out.println("*********** [" + currentThreadName + "] -" + taskId + " is done ***********" );

        return factorial;
    }

    public void cancel() {
        System.out.println("********** [" + Thread.currentThread().getName() + "] <" + taskId + "> Shutting down...");

        synchronized (this) {
            this.shutdown = true;
        }

    }
}
