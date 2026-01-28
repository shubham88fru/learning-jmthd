package org.learning.jmthd.multithreading.printnumseries;

/**
 * My soln to problem -
 * https://www.educative.io/courses/java-multithreading-for-senior-engineering-interviews/printing-number-series-zero-even-odd
 *
 * Edctv solved it using semaphores.
 */
public class PrintNumberSeries {
    private final int n;
    boolean printZero = true;
    int num = 1;
    public PrintNumberSeries(int n) {
        this.n = n;
    }

    public void PrintZero() {
        synchronized (this) {
            while (num < n) {
                while (!printZero) {
                    try {
                        wait();
                    } catch (InterruptedException e) {}
                }

//                System.out.println( Thread.currentThread().getName() + " - " + 0);
                System.out.print(0);
                printZero = false;
                notifyAll();
            }
        }
    }

    public void PrintOdd() {
        synchronized (this) {
            while (num < n) {
                while ((num%2 == 0 )|| printZero) {
                    try {
                        wait();
                    } catch (InterruptedException e) {}
                }

//                System.out.println( Thread.currentThread().getName() + " - " + num);
                System.out.print(num);
                num += 1;
                printZero = true;
                notifyAll();
            }
        }
    }

    public void PrintEven() {
        synchronized (this) {
            while (num < n) {
                while ((num%2 != 0 )|| printZero) {
                    try {
                        wait();
                    } catch (InterruptedException e) {}
                }

//                System.out.println( Thread.currentThread().getName() + " - " + num);
                System.out.print(num);
                num += 1;
                printZero = true;
                notifyAll();
            }
        }
    }
}

class Test {
    public static void main(String[] args) {
        PrintNumberSeries series = new PrintNumberSeries(1000);
        Thread t1 = new Thread(series::PrintZero);
        t1.setName("[Zero]");

        Thread t2 = new Thread(series::PrintEven);
        t2.setName("[Even]");

        Thread t3 = new Thread(series::PrintOdd);
        t3.setName("[Odd]");


        t1.start(); t2.start(); t3.start();
    }
}