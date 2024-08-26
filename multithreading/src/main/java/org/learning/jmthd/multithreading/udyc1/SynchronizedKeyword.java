package org.learning.jmthd.multithreading.udyc1;

public class SynchronizedKeyword {
    public static int counter1 = 0;
    public static int counter2 = 0;

    //Since the SynchronizedKeyword class has only one intrinsic lock, the thread
    //that reaches either of the two increment methods, acquires that lock and so,
    //the other increment method also can't be executed while a thread is executing
    //other increment method, even though the two methods are unrelated and update
    //completely different values.
    public static synchronized void increment1() {
        counter1 += 1;
    }

    public static synchronized void increment2() {
        counter2 += 1;
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<100; i++) {
                    increment1();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<100; i++) {
                    increment2();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t2.join();
            t1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Counter 1 is: " + counter1);
        System.out.println("Counter 2 is: " + counter2);
    }
}
