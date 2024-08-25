package org.learning.jmthd.multithreading.udyc1;


class Runner1 implements Runnable {
    @Override
    public void run() {
        for (int i=0; i<100; i++) {
            System.out.println("Runner 1 " + i);
        }
    }
}

class Runner2 implements Runnable {
    @Override
    public void run() {
        for (int i=0; i<100; i++) {
            System.out.println("Runner 2 " + i);
        }
    }
}

class Runner3 extends Thread {
    @Override
    public void run() {
        for (int i=0; i<100; i++) {
            System.out.println("Runner 3 " + i);
        }
    }
}
public class CreatingThreads {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runner1());
        Thread t2 = new Thread(new Runner2());
        Thread t3 = new Runner3();

        t1.start();
        t2.start();
        t3.start();
    }
}
