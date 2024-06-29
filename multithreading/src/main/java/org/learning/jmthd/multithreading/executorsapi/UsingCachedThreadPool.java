package org.learning.jmthd.multithreading.executorsapi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UsingCachedThreadPool {
    public static void main(String[] args) {
        System.out.println("Main thread starts here...");

        //Differs from a fixed thread pool executor in the sense that
        //a cached thread pool executor doesn't maintain a fixed sized
        //pool of threads. Instead, when new tasks are submitted and it
        //doesn't have a available thread in the pool to execute the task,
        //it creates a new thread on-demand to execute the submitted task.
        ExecutorService service = Executors.newCachedThreadPool();

        for (int i=0; i<5; i++) {
            service.execute(new LoopTaskA());
        }

        service.shutdown();

        System.out.println("Main thread ends here...");
    }
}
