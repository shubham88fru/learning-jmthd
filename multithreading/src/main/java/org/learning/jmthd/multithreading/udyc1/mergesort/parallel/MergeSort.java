package org.learning.jmthd.multithreading.udyc1.mergesort.parallel;

public class MergeSort {
    private int[] nums;

    private int[] temp;

    int numOfThreads;

    public MergeSort(int[] nums, int numOfThreads) {
        this.nums = nums;
        this.temp = new int[nums.length]; //temp array used during merge op - Merge sort isn't in-place.
        this.numOfThreads = numOfThreads;
    }

    private Thread createThread(int low, int high, int numOfThreads) {
        return new Thread() {
            @Override
            public void run() {
                parallelMergeSort(low, high, numOfThreads/2);
            }
        };
    }

    /*
        Idea is to create as many partitions as num of
        cores (cpus) and let each partition be sorted parallely
        on each core. Basically we are saving time by parallely
        sorting but merge is still sequential.
     */
    private void parallelMergeSort(int low, int high, int numOfThreads) {
        if (numOfThreads <= 1) {
            mergeSort(low, high); //if no more processor possible, use sequential sort.
            return;
        }

        int middle  = (low + high)/2;
        Thread leftSorter = createThread(low, middle, numOfThreads);
        Thread rightSorter = createThread(middle+1, high, numOfThreads);

        leftSorter.start();
        rightSorter.start();

        try {
            leftSorter.join();
            rightSorter.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        merge(low, middle, high);

    }

    public void sort() {
        parallelMergeSort(0, nums.length-1, numOfThreads);
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
