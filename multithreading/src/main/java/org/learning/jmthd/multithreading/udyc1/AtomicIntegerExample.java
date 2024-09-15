package org.learning.jmthd.multithreading.udyc1;

import java.util.concurrent.atomic.AtomicInteger;

/*
    AtomicInteger class java is used to create
    integers that can be incremented/decremented in threadsafe
    manner without explicitly writing code for synchronization.

    If we directly use a simple integer, then we ourselves are responsible
    for writing the synchronization code as well.
 */
public class AtomicIntegerExample {

    private static AtomicInteger counter = new AtomicInteger();

    public static void main(String[] args) {
        Thread t1 = new Thread(AtomicIntegerExample::increment);

        Thread t2 = new Thread(AtomicIntegerExample::increment);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(counter.get());
    }

    private static void increment() { //note no synchronization needs to be added from our side.
        for (int i=0; i<100000; i++) {
            counter.getAndIncrement(); //increments the number atomically.
        }
    }
}
