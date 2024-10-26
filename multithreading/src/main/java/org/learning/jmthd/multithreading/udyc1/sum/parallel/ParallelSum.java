package org.learning.jmthd.multithreading.udyc1.sum.parallel;

public class ParallelSum extends Thread {
    private int[] nums;
    private int low;
    private int high;
    private int partialSum;

    public ParallelSum(int[] nums, int low, int high) {
        this.nums = nums;
        this.low = low;
        this.high = high;
    }

    @Override
    public void run() {
        partialSum = 0;
        for (int i=low; i<high; i++) partialSum += nums[i];
    }

    public int getPartialSum() {
        return partialSum;
    }


}
