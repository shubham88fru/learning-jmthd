package org.learning.jmthd.multithreading.alivecheck;

import org.learning.jmthd.multithreading.daemonthreads.NamedDaemonThreadsFactory;

import java.util.concurrent.*;

public class ThreadsAliveCheckUsingExecutors {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("[" + currentThreadName + "] Main thread starts here...");

        ExecutorService service = Executors.newCachedThreadPool(new NamedDaemonThreadsFactory());

        Future<?> f1 = service.submit(new LoopTaskC());
        Future<Integer> f2 = service.submit(new CalculationTaskA(3, 4, 700));

        service.shutdown();
        for (int i=1; i<=5; i++) {
            TimeUnit.MILLISECONDS.sleep(300);

            System.out.println("[" + currentThreadName + "] ITR-" + i + "-> is 'LoopTaskC-1' done = " + f1.isDone());
            System.out.println("[" + currentThreadName + "] ITR-" + i + "-> is 'CalcTaskA-1' done = " + f2.isDone());
        }

        System.out.println("-------[" + currentThreadName + "] 'LoopTaskC-1' result = " + f1.get());
        System.out.println("-------[" + currentThreadName + "] 'CalcTaskA-1' result = " + f2.get());

        System.out.println("[" + currentThreadName + "] Main thread ends here...");
    }
}
