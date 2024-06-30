package org.learning.jmthd.multithreading.namingthreads;


//Naming the thread from within the task. Generally a
//bad idea, coz a thread might execute many tasks and if each
//tasks name the thread on its own, it will be very difficult
//for debugging purposes.
public class FirstTechnique {
    public static void main(String[] args) {
        String currentThreadName = Thread.currentThread().getName();

        System.out.println("[" + currentThreadName + "] thread starts here..." );

        new Thread(new LoopTaskB()).start();
        Thread t2 = new Thread(new LoopTaskB());
        t2.start();

        System.out.println("[" + currentThreadName + "] thread ends here..." );
    }
}
