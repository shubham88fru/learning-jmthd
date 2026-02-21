package org.learning.jmthd.multithreading.revision;

import java.util.ArrayDeque;
import java.util.Deque;

public class BlockingQueue<T> {
    private final Deque<T> q = new ArrayDeque<>();
    private final int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(T t) {
        while (q.size() == capacity) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }

        if (q.isEmpty()) {
            notifyAll();
        }
        System.out.println("Thread " + Thread.currentThread().getName() + " put " + t);
        q.addLast(t);
    }

    public synchronized T get() {
        while (q.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }

        if (q.size() == capacity) {
            notifyAll();
        }

        System.out.println("Thread " + Thread.currentThread().getName() + " get " + q.peek());
        return q.removeFirst();
    }
}
