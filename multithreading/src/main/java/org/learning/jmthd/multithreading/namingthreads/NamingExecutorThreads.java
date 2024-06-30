package org.learning.jmthd.multithreading.namingthreads;

import javax.naming.Name;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NamingExecutorThreads {
    public static void main(String[] args) {
        String currentThreadName = Thread.currentThread().getName();

        System.out.println("[" + currentThreadName + "] thread starts here..." );

        ExecutorService service = Executors.newCachedThreadPool(
                new NamedThreadsFactory()
        );
        for (int i=0; i<3; i++) {
            service.execute(new LoopTaskC());
        }
        service.shutdown();

        System.out.println("[" + currentThreadName + "] thread ends here..." );
    }
}
