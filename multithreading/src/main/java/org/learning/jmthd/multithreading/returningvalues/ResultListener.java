package org.learning.jmthd.multithreading.returningvalues;

public interface ResultListener<T> {
    void notifyResult(T result);
}
