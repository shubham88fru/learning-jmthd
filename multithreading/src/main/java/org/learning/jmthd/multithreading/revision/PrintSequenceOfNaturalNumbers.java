package org.learning.jmthd.multithreading.revision;

public class PrintSequenceOfNaturalNumbers {
    public static void main(String[] args) {
        SequenceOfNaturalNumbers sqn = new SequenceOfNaturalNumbers(100);

        Thread t1 = new Thread(() -> {sqn.print(1);}, "T1");
        Thread t2 = new Thread(() -> {sqn.print(2);}, "T2");
        Thread t3 = new Thread(() -> {sqn.print(0);}, "T3");
        t1.start();
        t2.start();
        t3.start();
    }
}

class SequenceOfNaturalNumbers {
    private int n;
    private final int N;
    public SequenceOfNaturalNumbers(int N) {
        this.N = N;
    }

    public void print(int mod) {
        while (true) {
            synchronized (this) {
                while (n%3 != mod) {
                    try {
                        wait();
                    } catch (InterruptedException e) {}
                }

                notifyAll();
                System.out.println("Thread " + Thread.currentThread().getName() + " " + n);
                n += 1;

                if (n >= N)  return;
            }
        }
    }
}