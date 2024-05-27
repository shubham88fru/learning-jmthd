package org.learning.jmthd.multithreading.threadcommunication;

import java.util.concurrent.locks.Lock;

public class Notifier implements Runnable {
    Lock lock;
    public Notifier(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {

        }
        lock.notify(); //notify any wait threads that object is available to be locked.
    }
}
