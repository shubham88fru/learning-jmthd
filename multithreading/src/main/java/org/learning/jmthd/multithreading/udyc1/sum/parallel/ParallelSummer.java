package org.learning.jmthd.multithreading.udyc1.sum.parallel;

public class ParallelSummer {
    private ParallelSum[] workers;
    private int numOfThreads;

    public ParallelSummer(int numOfThreads) {
        this.numOfThreads = numOfThreads;
        this.workers = new ParallelSum[numOfThreads];
    }

    public int sum(int[] nums) throws InterruptedException {
        int size = (int) Math.ceil((nums.length*1.0)/numOfThreads);
//        System.out.println(size);
        for (int i=0; i<Math.min(nums.length, numOfThreads); i++) {
            workers[i] = new ParallelSum(nums, i*size, (i*size)+size);
            workers[i].start();
            workers[i].join();
        }

        int ts = 0;
        for (int i=0; i<Math.min(nums.length, numOfThreads); i++) {
            ts += workers[i].getPartialSum();
        }

        return ts;

    }
}
