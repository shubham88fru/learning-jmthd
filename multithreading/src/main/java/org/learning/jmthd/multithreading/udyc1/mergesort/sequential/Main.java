package org.learning.jmthd.multithreading.udyc1.mergesort.sequential;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] nums = new int[] { -1, 0, -2, 0, 0, 5, 10, 6 };
        MergeSort mergeSort = new MergeSort(nums);
        mergeSort.sort();
        System.out.println(Arrays.toString(nums));
    }
}
