package org.learning.jmthd.multithreading.creation;

import java.util.concurrent.TimeUnit;

class SecondTask extends Thread {

    private static int count = 0;
    private int id;

    @Override
    public void run() {
        for (int i=0; i<10; i++) {
            System.out.println("<" + id + ">TICK TICK - " + i);

            try {
                TimeUnit.MICROSECONDS.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public SecondTask() {
        this.id = ++count;
    }
}

public class SecondTechnique {
    public static void main(String[] args) {
        System.out.println("Main thread start here..");

        new SecondTask().start();

        Thread t = new SecondTask();
        t.start();

        System.out.println("Main thread ends here..");
    }
}
