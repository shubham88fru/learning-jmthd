package org.learning.jmthd.multithreading.udyc1;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * In this example the producer will producer
 * 5 elements to the q and then the consumer will
 * consumer 5. This process of producing 5 and then
 * consuming 5 will keep happening and switching
 * back and forth.
 */
class Processor {

    private final Deque<Integer> threadSafeQ = new ArrayDeque<>();
    private static final int MAX_SZ = 5;

    public void producer() throws InterruptedException  {
        synchronized (threadSafeQ) {
            while (true) {
                if (threadSafeQ.size() == MAX_SZ) {
                    System.out.println("PRODUCER: Q full. Waiting for items to be consumed.");
                    threadSafeQ.wait();
                } else {
                    System.out.println("PRODUCER: Adding " + threadSafeQ.size() + " to Q.");
                    threadSafeQ.addLast(threadSafeQ.size());
                    threadSafeQ.notify();
                }

                Thread.sleep(1000);
            }
        }
    }

    public void consumer() throws InterruptedException {
        synchronized (threadSafeQ) {
            while (true) {
                if (threadSafeQ.isEmpty()) {
                    System.out.println("CONSUMER: Q empty. Waiting for items to be added.");
                    threadSafeQ.wait();
                } else {
                    System.out.println("CONSUMER: Consuming " + threadSafeQ.peekFirst() + " from Q.");
                    threadSafeQ.removeFirst();
                    threadSafeQ.notify();
                }
                Thread.sleep(1000);
            }
        }
    }
}

public class ProducerConsumer {


    public static void main(String[] args) {
        Processor p = new Processor();
        Thread producer = new Thread(() -> {
            try {
                p.producer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                p.consumer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        producer.start();
        consumer.start();
    }
}
