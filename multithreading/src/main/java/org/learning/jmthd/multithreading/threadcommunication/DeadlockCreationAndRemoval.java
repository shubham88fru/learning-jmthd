package org.learning.jmthd.multithreading.threadcommunication;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class CommonLock {}
public class DeadlockCreationAndRemoval {
    public static void main(String[] args) {
        CommonLock lock1 = new CommonLock();
        CommonLock lock2 = new CommonLock();

        //t1
        Waiter objWaiter = new Waiter(lock1, lock2);
        new Thread(objWaiter).start();

        //t2
        Notifier objNotifier = new Notifier(lock1, lock2);
        new Thread(objNotifier).start();

        //There is a possibility of deadlock creation
        //when there is a nested synchronized block.
        //In this example, deadlock can occur when order
        //of locks in t1 and t2 is opposite.
        //To remove the chance of a possible deadlock,
        //switch the order of locks to be same in both the threads.
    }

    static class Waiter implements Runnable {
        final CommonLock lock1;
        final CommonLock lock2;

        public Waiter(CommonLock lock1, CommonLock lock2) {
            this.lock1 = lock1;
            this.lock2 = lock2;
        }

        @Override
        public void run() {
            //t1 lock order - lock1 and then lock2.
            synchronized (lock1) {
                synchronized (lock2) {

                }
            }
        }
    }

    static class Notifier implements Runnable {
        final CommonLock lock1;
        final CommonLock lock2;

        public Notifier(CommonLock lock1, CommonLock lock2) {
            this.lock1 = lock1;
            this.lock2 = lock2;
        }

        @Override
        public void run() {
            //t2 lock order - lock2 and then lock1.
            synchronized (lock2) {
                synchronized (lock1) {

                }
            }
        }
    }
}


