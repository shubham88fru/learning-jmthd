package org.learning.jmthd.multithreading.udyc1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedArrayList {
    public static void main(String[] args) {
        /**
         * Collections.synchronizedList() takes the intrinsic lock, i.e.
         * threads take a lock on the instance of the array list. This makes
         * Collections.synchronizedList() inefficient because other threads that
         * don't have to lock will have to wait for any operation on the list object
         * even though doing that operation concurrently won't cause any harm.
         *
         * Better solution - Concurrent Collections.
         */
        List<Integer> nums = Collections.synchronizedList(new ArrayList<>());

        Thread t1 = new Thread(() -> {
            for (int i=0; i<1000; i++) {
                nums.add(i);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i=0; i<1000; i++) {
                nums.add(i);
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(nums.size());
    }

}
