package org.learning.jmthd.multithreading.revision;

public class OrderedPrinting {
    public static void main(String[] args) {
        OrderedPrinter o = new OrderedPrinter();
        Thread t1 = new Thread(o::first, "First");
        Thread t2 = new Thread(o::second, "Second");
        Thread t3 = new Thread(o::third, "Third");

        t1.start();
        t2.start();
        t3.start();

    }
}

class OrderedPrinter {
    int counter = 0;

    public void first() {
        while (true) {
            synchronized (this) {
                while (counter != 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {}
                }

                System.out.println(Thread.currentThread().getName() + ": first");
                counter += 1;

                notifyAll();
            }
        }
    }

    public void second() {
        while (true) {
            synchronized (this) {
                while (counter != 1) {
                    try {
                        wait();
                    } catch (InterruptedException e) {}
                }

                System.out.println(Thread.currentThread().getName() + ": second");
                counter += 1;
                notifyAll();
            }
        }

    }

    public void third() {
        while (true) {
            synchronized (this) {
                while (counter != 2) {
                    try {
                        wait();
                    } catch (InterruptedException e) {}
                }

                System.out.println(Thread.currentThread().getName() + ": third");
                counter = 0;
                notifyAll();
            }
        }

    }
}
