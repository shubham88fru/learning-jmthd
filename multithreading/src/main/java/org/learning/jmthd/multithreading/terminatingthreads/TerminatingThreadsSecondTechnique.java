package org.learning.jmthd.multithreading.terminatingthreads;

import org.learning.jmthd.multithreading.alivecheck.LoopTaskC;

import java.util.concurrent.TimeUnit;

public class TerminatingThreadsSecondTechnique {
    public static void main(String[] args) throws InterruptedException {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("[" + currentThreadName + "] Main thread starts here...");

        Thread t1 = new Thread(new LoopTaskF(), "MyThread-1");
        t1.start();

        Thread t2 = new Thread(new LoopTaskF(), "MyThread-2");
        t2.start();

        Thread t3 = new Thread(new LoopTaskF(), "MyThread-3");
        t3.start();

        TimeUnit.MILLISECONDS.sleep(3000);

        System.out.println("[" + currentThreadName  + "] Interrupting " + t1.getName() + "....." );
        t1.interrupt();

        System.out.println("[" + currentThreadName  + "] Interrupting " + t2.getName() + "....." );
        t2.interrupt();

        System.out.println("[" + currentThreadName  + "] Interrupting " + t3.getName() + "....." );
        t3.interrupt();

        System.out.println("[" + currentThreadName + "] Main thread starts here...");
    }
}
