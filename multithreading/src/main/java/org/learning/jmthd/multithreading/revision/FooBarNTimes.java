package org.learning.jmthd.multithreading.revision;

public class FooBarNTimes {
    public static void main(String[] args) {
        FooBarPrinter fb = new FooBarPrinter(10);
        Thread t1 = new Thread(fb::foo, "[First]");
        Thread t2 = new Thread(fb::bar, "[Second]");

        t1.start();
        t2.start();
    }
}

class FooBarPrinter {
    private int n;
    public FooBarPrinter(int n) {
        this.n = n;
    }

    public synchronized void foo() {
        while (n > 0) {
                while (n%2 == 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {}
                }

                System.out.println(Thread.currentThread().getName() + ": Foo");
                n -= 1;
                notifyAll();
        }
    }

    public synchronized void bar() {
        while (n > 0) {
                while (n%2 != 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {}
                }

                System.out.println(Thread.currentThread().getName() + ": Bar");
                n -= 1;
                notifyAll();
            }
    }
}
