package org.learning.jmthd.multithreading.udyc1.sum.sequential;

public class SequentialSum {

    //O(N)
    public int sum(int[] nums) {
        int sum = 0;
        for (int i=0; i<nums.length;i++) {
            sum += nums[i];
        }

        return sum;
    }
}
