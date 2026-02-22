package org.learning.jmthd.multithreading.simulatedcas;

public class SimulatedCAS {
    private long value = 0;

    public SimulatedCAS(long initialValue) {
        this.value = initialValue;
    }

    public synchronized long get() {
        return value;
    }

    public synchronized long compareAndSwap(long expectedValue, long newValue) {
        if (value == expectedValue) {
            value = newValue;
            return expectedValue;
        }

        return value;
    }

    public synchronized boolean compareAndSet(long expectedValue, long newValue) {
        return compareAndSwap(expectedValue, newValue) == expectedValue;
    }
}
