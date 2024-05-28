package org.learning.jmthd.multithreading.PrintNaturalNumsUsingThreads;

/**
 * Problem statement -
 * We have three threads T1, T2, and T3.
 * T1 prints a num, goes to wait,
 * Then T2 prints next num, goes to wait,
 * and then T3 prints next num, goes to wait,
 * finally T1 starts again and the process repeats until N numbers are printed.
 */
public class PrintSequenceOfNaturalNumsUsingThreeThreads {
    public static void main(String[] args) {
        PrintSequence r1 = new PrintSequence(1);
        PrintSequence r2 = new PrintSequence(2);
        PrintSequence r3 = new PrintSequence(3);

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);

        t1.start(); t2.start(); t3.start();
    }
}

class PrintSequence implements Runnable {

    int id;

    //class level (not instance) level lock.
    //i.e. this lock will be shared by all threads of this class.
    static final Object lock = new Object();

    static int num = 1;

    int N = 100;

    public PrintSequence(int id) {
        this.id = id;
    }

    //check udemy video for a slightly different impl.
    @Override
    public void run() {
        synchronized (lock) {
            try {
                while (num <= N)
                    print();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void print() throws InterruptedException {
        if (num%3==0) {
            if (id == 3) {
                System.out.println(num + "- Thread: " + id);
                num += 1;
                lock.notifyAll();

            } else lock.wait();
        } else if (num%3 == 1) {
            if (id == 1) {
                System.out.println(num + "- Thread: " + id);
                num += 1;
                lock.notifyAll();

            } else lock.wait();
        } else if (num%3==2) {
            if (id==2) {
                System.out.println(num + "- Thread: " + id);
                num += 1;
                lock.notifyAll();

            } else lock.wait();
        }
    }
}
