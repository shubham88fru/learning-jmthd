package org.learning.jmthd.multithreading;

public class SynchronizedBlock {
    public static void main(String[] args) {
        SynchronizedBlock lock = new SynchronizedBlock();
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                //synchronized block.
                //Only the block is synchronized.
                //If we had some code above this block,
                //both threads could have executed it concurrently.
                //Then as soon as they hit the synch block, the first one
                //to his will take the lock, and the other will go in wait state
                //untill the one that took the lock, finished and unlock the lock object.
                synchronized (lock) {
                    System.out.println("T1 Holding lock..");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }
            }
        };

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                //synchronized block.
                synchronized (lock) {
                    System.out.println("T2 Holding lock..");
                }
            }
        };

        /**
         * Two threads with different critical sections
         * but sharing a common lock.
         */
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
    }
}
