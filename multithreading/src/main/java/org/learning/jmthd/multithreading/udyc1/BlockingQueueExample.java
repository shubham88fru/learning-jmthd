package org.learning.jmthd.multithreading.udyc1;

/*
    Queue has a FIFO structure (first in first out) but it is not a synchronized
    data structure.

    BlockingQueue -> An interface that represents a queue that is thread safe..

 */

import org.springframework.cglib.core.Block;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class FirstWorker implements Runnable {

    private BlockingQueue<Integer> bq;

    public FirstWorker(BlockingQueue<Integer> bq) {
        this.bq = bq;
    }

    @Override
    public void run() {
        int counter = 0;
        while (true) {
            try {
                bq.put(counter);
                System.out.println("Putting item into the queue.. " + counter);
                counter += 1;
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


class SecondWorker implements Runnable {

    private BlockingQueue<Integer> bq;

    public SecondWorker(BlockingQueue<Integer> bq) {
        this.bq = bq;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int counter = bq.take();
                System.out.println("Taking item from the queue.. " + counter);
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
public class BlockingQueueExample {

    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
        FirstWorker fw = new FirstWorker(blockingQueue);
        SecondWorker sw = new SecondWorker(blockingQueue);

        new Thread(fw).start();
        new Thread(sw).start();

    }
}
