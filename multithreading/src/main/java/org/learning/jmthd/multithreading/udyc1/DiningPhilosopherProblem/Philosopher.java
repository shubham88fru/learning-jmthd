package org.learning.jmthd.multithreading.udyc1.DiningPhilosopherProblem;

import java.util.Random;

public class Philosopher implements Runnable {
    private int id;
    private volatile boolean isFull;
    private Chopstick leftChopStick;
    private Chopstick rightChopStick;
    private Random random;
    private int eatingCounter;

    public Philosopher(int id, Chopstick leftChopStick, Chopstick rightChopStick) {
        this.id = id;
        this.leftChopStick = leftChopStick;
        this.rightChopStick = rightChopStick;

        this.random = new Random();
    }

    @Override
    public void run() {
        try {
            while (!isIsFull()) {
                think();
                if (leftChopStick.pickUp(this, State.LEFT)) {
                    //the philosopher is able to acquire the left chopstick. so now try to acquire right.
                    if (rightChopStick.pickUp(this, State.RIGHT)) {
                        eat();
                        rightChopStick.putDown(this, State.RIGHT);
                    }
                    leftChopStick.putDown(this, State.LEFT);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void think() throws InterruptedException {
        System.out.println(this + " is thinking...");
        Thread.sleep(random.nextInt(1000)); //think for a random time.
    }

    private void eat() throws InterruptedException {
        System.out.println(this + " is eating...");
        eatingCounter += 1;
        Thread.sleep(random.nextInt(1000));
    }

    public void setFull(boolean isFull) {
        this.isFull = isFull;
    }

    public boolean isIsFull() {
        return this.isFull;
    }

    public int getEatingCounter() {
        return this.eatingCounter;
    }

    @Override
    public String toString() {
        return "Philosopher " + id;
    }
}
