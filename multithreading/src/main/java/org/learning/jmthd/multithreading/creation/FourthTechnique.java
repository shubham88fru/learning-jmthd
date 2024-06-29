package org.learning.jmthd.multithreading.creation;

import java.util.concurrent.TimeUnit;

public class FourthTechnique {
    public static void main(String[] args) {
        System.out.println("Main thread starts here..");

        Thread t1 = new Thread(new FourthTask());
        t1.start();

        System.out.println("Main thread ends here..");
    }
}


class FourthTask implements Runnable {
    private static int count = 0;
    private int id;

    public FourthTask() {
        this.id = ++count;
    }

    @Override
    public void run() {
        for (int i=10; i>0; i--) {
            System.out.println("<" + id + "> TICK TICK - " + i);
            try {
                TimeUnit.MILLISECONDS.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
