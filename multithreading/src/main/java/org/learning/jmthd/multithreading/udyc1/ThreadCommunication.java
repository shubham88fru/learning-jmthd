package org.learning.jmthd.multithreading.udyc1;

/**
 * Threads that are locking on the same intrinsic lock (monitor) can release
 * the lock until the other thread calls notify. feat. wait() & notify()
 *
 * The wait() and notify() methods can be used and called from synchronized
 * methods or block exclusively, and are called on the monitor object.
 */
class Process {
    public void consume() throws InterruptedException {
        Thread.sleep(1000);
        synchronized (this) {
            System.out.println("Consume method is executed...");
            notify();
        }
    }

    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Running the produce method...");
            wait();
            System.out.println("Again in the produce method...");
        }
    }
}

public class ThreadCommunication {
    public static void main(String[] args) {
        Process p = new Process();
        Thread t1 = new Thread(() -> {
            try {
                p.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                p.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();
    }
}
