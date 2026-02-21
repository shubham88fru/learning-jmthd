package org.learning.jmthd.multithreading.revision;

public class PrintNumberSeries {
    public static void main(String[] args) {
        NumberSeries ns = new NumberSeries(87);
        Thread t1 = new Thread(ns::zero, "[ZERO]");
        Thread t2 = new Thread(ns::odd, "[ODD]");
        Thread t3 = new Thread(ns::even, "[EVEN]");

        t1.start();
        t2.start();
        t3.start();
    }
}

class NumberSeries {
    private final int N;
    int n = 1;
    boolean zero = true;
    public NumberSeries(int N) {
        this.N = N;
    }

    public synchronized void zero() {
        while (n < N) {
            while (!zero) {
                try {
                    wait();
                } catch (InterruptedException e) {

                }
            }
//            System.out.println(Thread.currentThread().getName() + ":" + n);
            System.out.print(0);
            zero = false;
            notifyAll();
        }
    }

    public synchronized void even() {
        while (n < N) {
            while (zero || n%2 != 0) {
                try {
                    wait();
                } catch (InterruptedException e) {}
            }

//            System.out.println(Thread.currentThread().getName() + ":" + n);
            System.out.print(n);
            n += 1;
            zero = true;
            notifyAll();
        }
    }

    public synchronized void odd() {
        while (n < N) {
            while (zero || n%2 == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {}
            }

//            System.out.println(Thread.currentThread().getName() + ":" + n);
            System.out.print(n);
            n += 1;
            zero = true;
            notifyAll();
        }
    }
}
