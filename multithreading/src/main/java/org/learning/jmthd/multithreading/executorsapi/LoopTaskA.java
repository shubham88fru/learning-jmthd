package org.learning.jmthd.multithreading.executorsapi;

import java.util.concurrent.TimeUnit;

class LoopTaskA implements Runnable {
    private static int count = 0;
    private int id;

    public LoopTaskA() {
        this.id = ++count;
    }

    @Override
    public void run() {
        System.out.println("############ TASK " + id + " starting ############");
        for (int i = 10; i > 0; i--) {
            System.out.println("<" + id + "> TICK TICK - " + i);
            try {
                TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("************ TASK " + id + " end ***************");
    }
}
