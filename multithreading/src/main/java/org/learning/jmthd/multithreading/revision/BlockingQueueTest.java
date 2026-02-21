package org.learning.jmthd.multithreading.revision;

public class BlockingQueueTest {
    public static void main(String[] args) {
        BlockingQueue<Integer> bq = new BlockingQueue<>(5);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                bq.put(i);
            }
        }, "producer");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                bq.get();
            }
        }, "consumer");

        t1.start();
        t2.start();
    }
}
