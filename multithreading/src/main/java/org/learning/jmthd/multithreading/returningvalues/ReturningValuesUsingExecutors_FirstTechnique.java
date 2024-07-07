package org.learning.jmthd.multithreading.returningvalues;

import org.learning.jmthd.multithreading.namingthreads.NamedThreadsFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ReturningValuesUsingExecutors_FirstTechnique {
    public static void main(String[] args) {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("[" + currentThreadName + "] Main thread starts here..");

        ExecutorService service = Executors.newCachedThreadPool(new NamedThreadsFactory());

        Future<Integer> result1 = service.submit(new CalculationTaskA(2, 3, 2000));
        Future<Integer> result2 = service.submit(new CalculationTaskA(3, 4, 1000));
        Future<Integer> result3 = service.submit(new CalculationTaskA(4, 5, 500));

        service.shutdown();

        try {
            System.out.println("Result 1: " + result1.get());
            System.out.println("Result 2: " + result2.get());
            System.out.println("Result 3: " + result3.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("[" + currentThreadName + "] Main thread ends here..");
    }
}
