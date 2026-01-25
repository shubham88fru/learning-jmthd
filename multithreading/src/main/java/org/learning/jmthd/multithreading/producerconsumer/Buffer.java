package org.learning.jmthd.multithreading.producerconsumer;

import java.util.ArrayDeque;
import java.util.Deque;

public class Buffer {
    private final Deque<Integer> q;
    private final int capacity;

    public Buffer(int capacity) {
        this.capacity = capacity;
        this.q = new ArrayDeque<>(capacity);
    }

    public synchronized void put(int value) {
        while (q.size() == capacity) {
            System.out.println(Thread.currentThread().getName() + " - queue is full. Waiting..");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        try {
            q.addLast(value);
            Thread.sleep((int)Math.ceil(Math.random()*3000));
            System.out.println(Thread.currentThread().getName() + " - produced value: " + value);
            notifyAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public synchronized int get() {
        while (q.isEmpty()) {
            System.out.println(Thread.currentThread().getName() + " - queue is empty. Waiting..");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        int value = q.removeFirst();
        try {
            Thread.sleep((int)Math.ceil(Math.random()*3000));
            System.out.println(Thread.currentThread().getName() + " - polled value: " + value);
            notifyAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        return value;
    }
}
