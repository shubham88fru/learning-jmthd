package org.learning.jmthd.multithreading.terminatingthreads;

import org.learning.jmthd.multithreading.namingthreads.NamedThreadsFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TerminatingExecutorTasksFirstTechnique {

    public static void main(String[] args) throws InterruptedException {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("[" + currentThreadName + "] main thread starts here...");

        ExecutorService service = Executors.newCachedThreadPool(new NamedThreadsFactory());

        LoopTaskE task1 = new LoopTaskE();
        FactorialTaskA task2 = new FactorialTaskA(30, 1000);
        service.execute(task1);
        service.submit(task2);

        service.shutdown();

        TimeUnit.MILLISECONDS.sleep(3000);
        task1.cancel();
        task2.cancel();

        System.out.println("[" + currentThreadName + "] main thraed ends here..");
    }
}
