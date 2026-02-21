package org.learning.jmthd.multithreading.a12b3c;

/*
Write a multithreaded Java program that prints the sequence A1B2C3...Z26.

One thread should print letters (A, B, C, …, Z).
Another thread should print numbers (1, 2, 3, …, 26).
 */
public class A12B3CPattern {
    public static void main(String[] args) {
        PatternPrinter printer = new PatternPrinter();
        Thread t1 = new Thread(printer::ch, "[CHAR]");
        Thread t2 = new Thread(printer::num, "[NUM]");
        t1.start();
        t2.start();
    }
}

class PatternPrinter {
    int counter = 1;
    boolean flag = true;

    public synchronized void ch() {
        while (counter <= 26) {
            while (!flag) {
                try {
                    wait();
                } catch (InterruptedException e) {}
            }

                if (counter > 26) return;
//            System.out.println(Thread.currentThread().getName() + ": " + (char)(96+counter));
            System.out.print((char)(96+counter));
            flag = false;
            notifyAll();
        }
    }

    public synchronized void num() {
        while (counter <= 26) {
            while (flag) {
                try {
                    wait();
                } catch (InterruptedException e) {}
            }


//            System.out.println(Thread.currentThread().getName() + ": " + counter);
            System.out.print(counter);
            counter++;
            flag = true;
            notifyAll();
            if (counter > 26) return;
        }
    }

}
