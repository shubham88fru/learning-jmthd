package org.learning.jmthd.multithreading.alternatenaturalnumprint;

/**
 * Problem statement -
 * We have three threads T1, T2, and T3.
 * T1 prints a num, goes to wait,
 * Then T2 prints next num, goes to wait,
 * and then T3 prints next num, goes to wait,
 * finally T1 starts again and the process repeats until N numbers are printed.
 *
 * This is my implementation and not as
 * clean as Ravi's
 * @see org.learning.jmthd.multithreading.PrintNaturalNumsUsingThreads.PrintSequenceOfNaturalNumsUsingThreeThreads;
 */
class AlternateNaturalNumberPrint {
    public static void main(String[] args) {

        Data o = new Data(1, 1, 100);
        Thread t1 = new Thread(new Th1(o));
        t1.setName("T1");

        Thread t2 = new Thread(new Th2(o));
        t2.setName("T2");

        Thread t3 = new Thread(new Th3(o));
        t3.setName("T3");

        t1.start(); t2.start(); t3.start();
    }
}

class Data {
    int turn;
    int n;
    int N;
    boolean end;
    public Data (int turn, int n, int N) {
        this.turn = turn;
        this.n = n;
        this.N = N;
    }
}

class Th1 implements Runnable {
    final Data o;

    public Th1(Data lock) {
        this.o = lock;
    }

    public void run() {
        while (true) {
            synchronized (o) {


                while (o.turn != 1) {
                    if (o.end) break;
                    try {
                        o.wait();
                    } catch (Exception e) {}
                }

                if (o.N == o.n) {
                    o.end = true;
                    o.notifyAll();
                    break;
                }

                System.out.println(Thread.currentThread().getName() + " - " + o.n);
                o.turn = 2;
                o.n += 1;
                o.notifyAll();
            }
        }
    }
}

class Th2 implements Runnable {
    final Data o;

    public Th2(Data lock) {
        this.o = lock;
    }

    public void run() {
        while (true) {
            synchronized (o) {


                while (o.turn != 2) {
                    if (o.end) break;
                    try {
                        o.wait();
                    } catch (Exception e) {}
                }

                if (o.N == o.n) {
                    o.end = true;
                    o.notifyAll();
                    break;
                }

                System.out.println(Thread.currentThread().getName() + " - " + o.n);
                o.turn = 3;
                o.n += 1;
                o.notifyAll();
            }
        }
    }
}

class Th3 implements Runnable {
    final Data o;

    public Th3(Data lock) {
        this.o = lock;
    }

    public void run() {
        while (true) {
            synchronized (o) {

                while (o.turn != 3) {
                    if (o.end) break;
                    try {
                        o.wait();
                    } catch (Exception e) {}

                }

                if (o.N == o.n) {
                    o.end = true;
                    o.notifyAll();
                    break;
                }

                System.out.println(Thread.currentThread().getName() + " - " + o.n);
                o.turn = 1;
                o.n += 1;
                o.notifyAll();
            }
        }
    }
}