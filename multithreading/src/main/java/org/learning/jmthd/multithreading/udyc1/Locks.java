package org.learning.jmthd.multithreading.udyc1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Locks {
    /*
     * ReentrantLock
     *  - It has the same behavior as the "synchronized approach"
     *  - Of course it has some additional features.
     *      new ReentrantLock(boolean fairness)
     *          If the fairness parameter is set to be TRUE then the longest
     *          waiting thread will get the lock
     *          If fairness is FALSE then there is no access order.
     *      IMPORTANT: A good approach is to use try-catch-finally blocks
     *      when doing the critical section and call unlock() in the finally block.
     */
    private static int counter = 0;
    private static Lock lock = new ReentrantLock();

    private static void increment() {
        lock.lock();

        try {
            for (int i=0; i<10000; i++) {
                counter += 1;
            }
        } finally {
            lock.unlock(); //no matter what, unlock the lock.
        }

    }
    public static void main(String[] args) {
        Thread t1 = new Thread(Locks::increment);
        Thread t2 = new Thread(Locks::increment);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(counter);
    }
}