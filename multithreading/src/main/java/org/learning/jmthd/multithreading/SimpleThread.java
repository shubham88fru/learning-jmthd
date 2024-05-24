package org.learning.jmthd.multithreading;

public class SimpleThread {

    public static void main(String[] args) {
        Test test = new Test();
        Thread t1 = new Thread(test); //one thread.
        t1.start();
    }

    private static class Test implements Runnable {

        //one critical section.
        @Override
        public void run() {
            System.out.println("Running thread..");
        }
    }
}
