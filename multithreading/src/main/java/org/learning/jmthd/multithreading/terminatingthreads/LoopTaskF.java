package org.learning.jmthd.multithreading.terminatingthreads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LoopTaskF implements Runnable {
    private static int count = 0;
    private int instanceNumber;

    private String taskId;

    private final int DATASET_SIZE = 1000000;

    public LoopTaskF() {
        this.instanceNumber = ++count;
        this.taskId = "LoopTaskF" + instanceNumber;
    }

    @Override
    public void run() {
        String currentThreadName = Thread.currentThread().getName();

        System.out.println("########## [" + currentThreadName + "] -" + taskId + " is starting #########" );
        for (int i = 1; ; i++) {
            System.out.println("[" + currentThreadName + "] -" + taskId + " is running " + i);
            doComplexCalculation();
            if (Thread.interrupted()) {
                System.out.println("[" + currentThreadName + "] <" + taskId + "> Interrupted. Cancelling...");
                break;
            }
        }

        System.out.println("[" + currentThreadName + "] <" + taskId + "> Retrieving 'INTERRUPTED' status again: "
                + Thread.interrupted());
        System.out.println("*********** [" + currentThreadName + "] -" + taskId + " is done ***********" );
    }

    private List<Integer> generateDataset() {
        List<Integer> intList = new ArrayList<>();
        Random random = new Random();

        for (int i=0; i < DATASET_SIZE; i++) {
            intList.add(random.nextInt(DATASET_SIZE));
        }

        return intList;
    }

    private void doComplexCalculation() {
        for (int i=0; i<2; i++) {
            Collections.sort(generateDataset());
        }
    }

}
