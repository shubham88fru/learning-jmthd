package org.learning.jmthd.multithreading.udyc1.sum;

import org.learning.jmthd.multithreading.udyc1.sum.parallel.ParallelSummer;
import org.learning.jmthd.multithreading.udyc1.sum.sequential.SequentialSum;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();

        /*
            Parallel algo beats the sequential
            algo only for huge (literally massive) array size (like below)
            For smaller size uptill even millions, sequential sum beat
            the parallel easily.
         */
        int[] nums = new int[1000000000];
        for (int i=0; i<1000000000; i++) {
            nums[i] = random.nextInt(10);
        }

        int n = Runtime.getRuntime().availableProcessors();

        SequentialSum sequentialSum = new SequentialSum();

        long start = System.currentTimeMillis();
        System.out.println(sequentialSum.sum(nums));
        long end = System.currentTimeMillis();
        System.out.println("Seq took: " + (end-start));

        ParallelSummer parallelSummer = new ParallelSummer(n);

        start = System.currentTimeMillis();
        System.out.println(parallelSummer.sum(nums));
        end = System.currentTimeMillis();
        System.out.println("Para took: " + (end-start));
    }
}
