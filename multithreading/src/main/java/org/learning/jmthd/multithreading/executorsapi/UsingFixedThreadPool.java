package org.learning.jmthd.multithreading.executorsapi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UsingFixedThreadPool {
    public static void main(String[] args) {
        System.out.println("Main thread starts here...");

        //Executor service instantiated with a thread pool of 3 threads.
        //Note that we can submit as many tasks as possible to this service
        //(i.e. not limited to 3 tasks). Just that they'll be queued.
        ExecutorService service = Executors.newFixedThreadPool(3);

        //submit 3 tasks to the executor service.
        for (int i=0; i < 3; i++) {
            service.execute(new LoopTaskA());
        }
        //IMPORTANT:
        //tell the service to shutdown when done completion.
        //Failing this, will keep the JVM running coz executor service
        //will keep waiting for more tasks.
        service.shutdown();

        System.out.println("Main thread ends here...");
    }
}

