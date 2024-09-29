package org.learning.jmthd.multithreading.udyc1;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
    Latch -> a single thread can wait for other threads.
    CyclicBarrier -> multiple threads can wait for each other.

    A CyclicBarrier is used in situations where you want to create a group of tasks to
    perform work in a concurrent manner + wait until they are all finished before moving on to
    the next step.
        -> Somewhat like join()
        -> Somewhat like CountDownLatch()

     CountDownLatch: one-shot event
     CyclicBarrier: it can be reused over and over again
                + CyclicBarrier has a barrier action: a runnable, that will run
                    automatically when the count reaches 0!!
     new CyclicBarrier(N) -> N threads will wait for each other.
     We cannot reuse latches but we can reuse CyclicBarriers --> reset() !!!
 */
public class CBarrier {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);
        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() { //this will run after barrier threads have finished.
            @Override
            public void run() {
                System.out.println("All tasks have been finished...");
            }
        });

        for (int i=0; i<5; i++)
            service.execute(new Wkr(i+1, barrier));

        service.shutdown();
    }
}


class Wkr implements Runnable {

    private int id;
    private Random random;
    private CyclicBarrier barrier;

    public Wkr(int id, CyclicBarrier barrier) {
        this.id = id;
        this.random = new Random();
        this.barrier = barrier;
    }

    @Override
    public void run() {
        doWork();
    }

    private void doWork() {
        System.out.println("Thread with ID: " + this.id + " starts the work");
        try {
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            //wait for all the remaining threads to call await.
            //when all the threads that are part of the barrier have
            //invoked await(), the barrier is broken and threads can
            //move on from this point.
            barrier.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}