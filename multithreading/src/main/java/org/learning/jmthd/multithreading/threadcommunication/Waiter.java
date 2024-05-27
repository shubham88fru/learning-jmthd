package org.learning.jmthd.multithreading.threadcommunication;

import java.util.concurrent.locks.Lock;

public class Waiter implements Runnable {

    Lock lock;
    public Waiter(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            try {
                lock.wait(); //release lock and go to wait state. Will wake someone call notify()/notifyAll()
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
