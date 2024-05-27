package org.learning.jmthd.multithreading.threadcommunication;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Client {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        //Two threads spawned, sharing same lock.

        //t1
        Waiter objeWaiter = new Waiter(lock);
        new Thread(objeWaiter).start();

        //t2
        Notifier objNotifier = new Notifier(lock);
        new Thread(objNotifier).start();
    }
}
