package org.learning.jmthd.multithreading.revision;

public class FizzBuzz {
    public static void main(String[] args) {
        FizzBuzzPrinter fbz = new FizzBuzzPrinter(50);

        Thread t1 = new Thread(fbz::fizz, "Fizz");
        Thread t2 = new Thread(fbz::buzz, "Buzz");
        Thread t3 = new Thread(fbz::fizzBuzz, "FizzBuzz");
        Thread t4 = new Thread(fbz::number, "Number");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }
}

class FizzBuzzPrinter {
    private int num = 1;
    private final int N;
    public FizzBuzzPrinter(int N) {
        this.N = N;
    }

    public synchronized void fizz() {
        while (num <= N) {
            while (num%5 == 0 || num%3 != 0) {
                try {
                    wait();
                } catch (InterruptedException e) {}
            }

            if (num > N) return;
//            System.out.println(Thread.currentThread().getName() + ": Fizz" );
            System.out.println("Fizz");

            num += 1;
            notifyAll();
        }
    }

    public synchronized void buzz() {
        while (num <= N) {
            while (num%3 == 0 || num%5 != 0) {
                try {
                    wait();
                } catch (InterruptedException e) {}
            }

            if (num > N) return;
//            System.out.println(Thread.currentThread().getName() + ": Buzz");
            System.out.println("Buzz");
            num += 1;
            notifyAll();
        }
    }

    public synchronized void fizzBuzz() {
        while (num <= N) {
            while (num%15 != 0) {
                try {
                    wait();
                } catch (InterruptedException e) {}
            }

            if (num > N) return;
//            System.out.println(Thread.currentThread().getName() + ": FizzBuzz");
            System.out.println("FizzBuzz");
            num += 1;
            notifyAll();
        }
    }

    public synchronized void number() {
        while (num <= N) {
            while (num%3 == 0 || num%5 == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {}
            }

            if (num > N) return;
//            System.out.println(Thread.currentThread().getName() + ": " + num);
            System.out.println(num);
            num += 1;
            notifyAll();
        }
    }
}
