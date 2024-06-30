package org.learning.jmthd.multithreading.namingthreads;

//Naming threads during creation (and not in tasks) - Good approach.
public class SecondTechnique {
    public static void main(String[] args) {
        String currentThreadName = Thread.currentThread().getName();

        System.out.println("[" + currentThreadName + "] thread starts here..." );

        new Thread(new LoopTaskC(), "Worker-1").start();
        Thread t2 = new Thread(new LoopTaskC(), "Worker-2");
        t2.start();

        System.out.println("[" + currentThreadName + "] thread ends here..." );
    }
}
