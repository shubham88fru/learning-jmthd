package org.learning.jmthd.multithreading.simulatedcas;

public class NonBlockingCounter {
    private SimulatedCAS count = new SimulatedCAS(0);

    public long get() {
        return count.get();
    }

    public void increment() {
        long currentCount;

        do {
            currentCount = get();
        } while (currentCount != count.compareAndSwap(currentCount, currentCount + 1));
    }
}
