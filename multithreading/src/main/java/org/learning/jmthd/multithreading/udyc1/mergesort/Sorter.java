package org.learning.jmthd.multithreading.udyc1.mergesort;

import java.util.Arrays;
import java.util.Random;

public class Sorter {
    public static void main(String[] args) {
        int maxThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("Num of cores: " + maxThreads);

        int[] numbers1 = createArray(100000000);
        int[] numbers2 = Arrays.copyOf(numbers1, numbers1.length);

        //Parallel merge sort.
        long startTime = System.currentTimeMillis();
        org.learning.jmthd.multithreading.udyc1.mergesort.parallel.MergeSort parallelSort =
                new org.learning.jmthd.multithreading.udyc1.mergesort.parallel.MergeSort(numbers1, maxThreads);
        parallelSort.sort();
        long endTime = System.currentTimeMillis();

        System.out.println("Parallel took: " + (endTime-startTime));

        startTime = System.currentTimeMillis();
        org.learning.jmthd.multithreading.udyc1.mergesort.sequential.MergeSort sequentialSort =
                new org.learning.jmthd.multithreading.udyc1.mergesort.sequential.MergeSort(numbers2);
        sequentialSort.sort();
        endTime = System.currentTimeMillis();

        System.out.println("Sequential took: " + (endTime-startTime));
    }

    private static int[] createArray(int n) {
        Random random = new Random();
        int[] a = new int[n];

        for (int i=0; i<n; i++) {
            a[i] = random.nextInt();

        }

        return a;
    }
}
