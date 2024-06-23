package org.learning.jmthd.multithreading.creation;


class FirstTask extends Thread {

    //starts the thread as soon as this object is created.
    public FirstTask() {
        this.start();
    }

    @Override
    public void run() {
        for (int i=0; i<10; i++) {
            System.out.println("TICK TICK - " + i);
        }
    }
}
public class FirstTechnique {
    public static void main(String[] args) {
        new FirstTask();
    }
}
