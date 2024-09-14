package org.learning.jmthd.multithreading.udyc1;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Worker {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void produce() throws InterruptedException {
        lock.lock();
        System.out.println("Producer method...");

        /*
            Will release the lock
            and go to wait state and wait
            to be notified for a lock release
            by some other thread.
         */
        condition.await(); //similar to `wait()`
        System.out.println("Again producer...");
        lock.unlock();
    }

    public void consume() throws InterruptedException {
        //let the producer start first.
        Thread.sleep(2000);

        lock.lock();
        System.out.println("Consumer method...");
        Thread.sleep(3000);

        /*
            Will notify any waiting thread
            that they can now try acquiring
            the lock.
         */
        condition.signal(); //similar to `notify()`
        lock.unlock();
    }
}
public class ProducerConsumerWithLocks {

    public static void main(String[] args) {

        Worker worker = new Worker();
        Thread t1 = new Thread(() -> {
            try {
                worker.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                worker.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
