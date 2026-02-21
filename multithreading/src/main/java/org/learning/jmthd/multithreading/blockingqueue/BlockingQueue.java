package org.learning.jmthd.multithreading.blockingqueue;

import java.util.LinkedList;
import java.util.List;

public class BlockingQueue<T> {

    private List<T> queue = new LinkedList<T>();

    private int limit = 10;

    public synchronized void put(T item) {
        while (queue.size() == limit) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        //Consumers wake up! i'm adding an element
        if (queue.isEmpty()) {
            notifyAll();
        }
        queue.add(item);
    }

    public synchronized T take() throws InterruptedException {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }

        //Producers wake up! I'm removing an element.
        if (queue.size() == limit) {
            notifyAll();
        }
        return queue.remove(0);
    }

}
