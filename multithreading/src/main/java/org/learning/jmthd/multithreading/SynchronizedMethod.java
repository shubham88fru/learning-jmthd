package org.learning.jmthd.multithreading;

public class SynchronizedMethod {
    public static void main(String[] args) {
        SomeClass inst = new SomeClass();
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                inst.method();
            }
        };

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                inst.method();
            }
        };

        Thread t1 = new Thread(r1, "T1");
        Thread t2 = new Thread(r2, "T2");
        t1.start();
        t2.start();
    }
}

class SomeClass {

    //method level synchronization.
    //Entire code inside the method is protected/synchronized.
    public synchronized void method() {
        System.out.println("Synchronized method." + Thread.currentThread().getName());
    }
}
