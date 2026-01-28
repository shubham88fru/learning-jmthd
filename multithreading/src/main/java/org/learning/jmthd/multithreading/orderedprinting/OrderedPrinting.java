package org.learning.jmthd.multithreading.orderedprinting;

/**
 * My implementation for problem
 * statement - https://www.educative.io/courses/java-multithreading-for-senior-engineering-interviews/ordered-printing
 */
public class OrderedPrinting {

    public static void main(String[] args) {

        Thread t1 = new Thread(new Worker(0));
        t1.setName("Thread-1");

        Thread t2 = new Thread(new Worker(1));
        t2.setName("Thread-2");

        Thread t3 = new Thread(new Worker(2));
        t3.setName("Thread-3");

        t1.start(); t2.start(); t3.start();

    }

}

class Worker implements Runnable {
    private final int mod;

    private static int idx = 0;

    public Worker(int mod) {
        this.mod = mod;
    }

    @Override
    public void run() {
        synchronized (Worker.class) {
            while (true) {
                while (idx%3 != mod) {
                    try {
                        Worker.class.wait();
                    } catch (InterruptedException e) {}
                }

                if (idx == 0) {
                    System.out.println(Thread.currentThread().getName() + " : " + "FIRST");
                    idx = 1;
                } else if (idx == 1) {
                    System.out.println(Thread.currentThread().getName() + " : " + "SECOND");
                    idx = 2;
                } else {
                    System.out.println(Thread.currentThread().getName() + " : " + "THIRD");
                    idx = 0;
                }

                Worker.class.notifyAll();
            }
        }
    }
}