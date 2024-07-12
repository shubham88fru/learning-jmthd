package org.learning.jmthd.multithreading.daemonthreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DaemonThreadsUsingExecutorsAPI {
    public static void main(String[] args) {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("[" + currentThreadName + "] Main thread starts here...");
        ExecutorService service = Executors.newCachedThreadPool(
                new NamedDaemonThreadsFactory()
        );
        service.execute(new LoopTaskD(100));
        service.execute(new LoopTaskD(200));
        service.execute(new LoopTaskD(100));
        service.execute(new LoopTaskD(200));

        service.shutdown();
        System.out.println("[" + currentThreadName + "] Main thread ends here...");
    }
}
