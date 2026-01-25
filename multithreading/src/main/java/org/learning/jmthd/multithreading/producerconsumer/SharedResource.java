package org.learning.jmthd.multithreading.producerconsumer;

public class SharedResource {
    private boolean isItemPresent = false;

    public synchronized void addItem() {
        isItemPresent = true;
        System.out.println(Thread.currentThread().getName() + " produced and notifying..");

        notifyAll();
    }

    public synchronized void consumeItem() {
        System.out.println(Thread.currentThread().getName() + " inside consume item");
        while (!isItemPresent) {
            try {
                System.out.println(Thread.currentThread().getName() + " waiting..");
                wait();
            } catch (InterruptedException e) {
                //exception handling.
            }
        }
        System.out.println(Thread.currentThread().getName() + " consumed..");
        isItemPresent = false;
    }
}
