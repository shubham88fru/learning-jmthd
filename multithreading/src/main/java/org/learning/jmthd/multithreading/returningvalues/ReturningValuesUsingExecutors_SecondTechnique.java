package org.learning.jmthd.multithreading.returningvalues;

import org.learning.jmthd.multithreading.namingthreads.NamedThreadsFactory;

import java.util.concurrent.*;

public class ReturningValuesUsingExecutors_SecondTechnique {
    public static void main(String[] args) {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("[" + currentThreadName + "] Main thread starts here..");

        ExecutorService service = Executors.newCachedThreadPool(new NamedThreadsFactory());

        CompletionService<TaskResult<String, Integer>> completionService = new ExecutorCompletionService<>(service);

        completionService.submit(new CalculationTaskB(2, 3, 2000));
        completionService.submit(new CalculationTaskB(3, 4, 1000));
        completionService.submit(new CalculationTaskB(4, 5, 500));

        service.shutdown();

        for (int i=0; i<3; i++) {
            try {
                System.out.println(completionService.take().get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("[" + currentThreadName + "] Main thread ends here..");
    }
}
