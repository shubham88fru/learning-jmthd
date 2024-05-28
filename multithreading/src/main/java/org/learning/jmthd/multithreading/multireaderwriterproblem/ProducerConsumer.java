package org.learning.jmthd.multithreading.multireaderwriterproblem;

public class ProducerConsumer {
    private static final Object lock = new Object();
    private static boolean[] buffer;
    private static int buff_sz ;

    public static void main(String[] args) throws InterruptedException {
        buffer = new boolean[10];
        buff_sz = 0;

        Runnable p1 = ProducerConsumer::producer;
        Runnable c1 = ProducerConsumer::consumer;

        Thread pThread1 = new Thread(p1);
        Thread cThread1 = new Thread(c1);

        pThread1.start();
        cThread1.start();

        pThread1.join();
        cThread1.join();
    }

    public static void producer() {
        while (true) {
            synchronized (lock) {
                while (buff_sz == buffer.length) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                buffer[buff_sz++] = true; //producer to q.
                System.out.println("Producer write, buffer size: " + buff_sz);
                lock.notifyAll();
            }
        }

    }

    public static void consumer() {
        while (true) {
            synchronized (lock) {
                while (buff_sz == 0) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                buffer[--buff_sz] = false;
                System.out.println("Consumer read, buffer size: " + buff_sz);

                lock.notifyAll();
            }
        }
    }
}
