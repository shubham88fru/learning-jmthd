package org.learning.jmthd.multithreading.creation;

import java.util.concurrent.TimeUnit;

public class FifthTechnique {
    public static void main(String[] args) {
        System.out.println("Main thread starts here...");

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=10; i>0; i--) {
                    System.out.println("TICK TICK - " + i);
                    try {
                        TimeUnit.MILLISECONDS.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        System.out.println("Main thread ends here...");
    }
}
