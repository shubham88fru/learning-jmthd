package org.learning.jmthd.multithreading.udyc1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolExecutor {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5); //5 threads in the pool.
        for (int i=0; i<10; i++) { //10 tasks.
            executor.execute(new Task(i));
        }
        executor.shutdown();
    }
}
