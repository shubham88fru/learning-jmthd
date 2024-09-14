package org.learning.jmthd.multithreading.udyc1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Deadlock {
    private Lock lock1 = new ReentrantLock(true);
    private Lock lock2 = new ReentrantLock(true);

    public static void main(String[] args) {
        Deadlock deadlock = new Deadlock();

        //Creates a deadlock.
        //Soln: Acquire the locks in same order in both the workers.
        new Thread(deadlock::worker1, "worker1").start();
        new Thread(deadlock::worker2, "worker2").start();
    }

    public void worker1() {
        lock1.lock(); //acquires lock1 and then lock2
        System.out.println("Worker 1 acquires the lock1...");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        lock2.lock();
        System.out.println("Worker 1 acquires the lock2...");

        lock1.unlock();
        lock2.unlock();
    }

    public void worker2() {
        lock2.lock(); //acquires lock2 and then lock1
        System.out.println("Worker 2 acquires the lock2...");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        lock1.lock();
        System.out.println("Worker 2 acquires the lock1...");

        lock2.unlock();
        lock1.unlock();
    }

}
