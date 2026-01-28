package org.learning.jmthd.multithreading.foobar;

/**
 * My soln to problem statement -
 * https://www.educative.io/courses/java-multithreading-for-senior-engineering-interviews/printing-foo-bar-n-times
 */
public class FooBar {
    final int n;
    boolean flag;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo() {
        for (int i = 0; i < n; i++) {
            synchronized (this) {
                while (flag) {
                    try {
                        wait();
                    } catch (InterruptedException e) {

                    }
                }
                System.out.print("FOO-");
                flag = true;
                notifyAll();
            }
        }
    }

    public void bar() {
        for (int i = 0; i < n; i++) {
            synchronized (this) {
                while (!flag) {
                    try {
                        wait();
                    } catch (InterruptedException e) {

                    }
                }
                System.out.println("BAR");
                flag = false;
                notifyAll();
            }
        }
    }
}

class Test {
    public static void main(String[] args) {
        FooBar fooBar = new FooBar(100);

        Thread t1 = new Thread(fooBar::foo);
        Thread t2 = new Thread(fooBar::bar);
        t1.start(); t2.start();
    }

}
