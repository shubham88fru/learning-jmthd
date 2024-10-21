package org.learning.jmthd.multithreading.udyc1.mergesort.sequential;

public class MergeSort {
    private int[] nums;

    private int[] temp;

    public MergeSort(int[] nums) {
        this.nums = nums;
        this.temp = new int[nums.length]; //temp array used during merge op - Merge sort isn't in-place.
    }

    public void sort() {
        mergeSort(0, nums.length-1);
    }

    private void mergeSort(int low, int high) {
        if (low >= high) return;

        int middleIdx = (low + high) / 2;
        mergeSort(low, middleIdx);
        mergeSort(middleIdx+1, high);

        merge(low, middleIdx, high);
    }

    private void merge(int low, int middle, int high) {
        for (int i=low; i<=high; i++) {
            temp[i] = nums[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;

        //low ... middle --> left sorted sub array.
        //middle+1 ... high --> right sorted sub array.
        /*
            Typical merge two sorted arrays.
         */
        while (i <= middle && j <= high) {
            if (temp[i] < temp[j]) {
                nums[k] = temp[i];
                i += 1;
            } else {
                nums[k] = temp[j];
                j += 1;
            }

            k += 1;
        }

        //copy remaining items from either arrays.
        while (i <= middle) {
            nums[k] = temp[i];
            i += 1;
            k += 1;
        }

        while (j <= high) {
            nums[k] = temp[j];
            j += 1;
            k += 1;
        }
    }

    private void showArray() {
        for (int i=0; i<nums.length; ++i) {
            System.out.print(nums[i] + " ");
        }
    }

}
