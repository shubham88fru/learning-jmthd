package org.learning.jmthd.multithreading.executorsapi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UsingSingleThreadExecutor {
    public static void main(String[] args) {
        System.out.println("Main thread starts here...");

        //Single thread executor is a special case of fixed thread pool
        //executor in that it has just one thread in the pool.
        ExecutorService service = Executors.newSingleThreadExecutor();

        for (int i=0; i<5; i++) {
            service.execute(new LoopTaskA());
        }

        service.shutdown();

        System.out.println("Main thread ends here...");
    }
}
