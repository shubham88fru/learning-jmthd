package org.learning.jmthd.multithreading.fizzbuzz;

/**
 * My soln for fizz buzz problem -
 * https://www.educative.io/courses/java-multithreading-for-senior-engineering-interviews/fizz-buzz-problem
 *
 * Prints correctly but the program isn't
 * terminating.
 */
public class FizzBuzz {
    int num = 1;
    final int N;

    public FizzBuzz(int n) {
        this.N = n;
    }

    public void num() {
        synchronized (this) {
            while (num < N) {
                while (num%3 == 0 || num%5 == 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {}
                }

                System.out.println(num);
                num++;
                notifyAll();
            }
        }
    }

    public void fizz() {
        synchronized (this) {
            while (num < N) {
                while (num%3 != 0 || num%5 == 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {}
                }

                System.out.println(Thread.currentThread().getName() + ": " + num);
                num++;
                notifyAll();

            }
        }
    }

    public void buzz() {
        synchronized (this) {
            while (num < N) {
                while (num%5 != 0 || num%3 == 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {}
                }

                System.out.println(Thread.currentThread().getName() + ": " + num);
                num++;
                notifyAll();

            }
        }
    }

    public void fizzbuzz() {
        synchronized (this) {
            while (num < N) {
                while (num%15 != 0) { //simplified instead of checking with 3 and 5 separately.
                    try {
                        wait();
                    } catch (InterruptedException e) {}
                }

                System.out.println(Thread.currentThread().getName() + ": " + num);
                num++;
                notifyAll();
            }
        }
    }
}

class Test {
    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(15);
        Thread t1 = new Thread(fizzBuzz::num);
        t1.setName("Thread-1");
        Thread t2 = new Thread(fizzBuzz::fizz);
        t2.setName("fizz");
        Thread t3 = new Thread(fizzBuzz::buzz);
        t3.setName("buzz");
        Thread t4 = new Thread(fizzBuzz::fizzbuzz);
        t4.setName("fizzbuzz");

        t1.start(); t2.start(); t3.start(); t4.start();

    }
}
