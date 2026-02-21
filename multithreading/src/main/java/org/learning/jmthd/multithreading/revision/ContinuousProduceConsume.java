package org.learning.jmthd.multithreading.revision;

public class ContinuousProduceConsume {
    public static void main(String[] args) {
        ProducerConsumer2 pdc = new ProducerConsumer2();
        Thread producer = new Thread(pdc::produce);
        Thread consumer = new Thread(pdc::consume);

        producer.start();
        consumer.start();
    }
}

class ProducerConsumer2 {
    private boolean available = false;

    public synchronized void produce() {
        while (true) {
            while (available) {
                try {
                    wait();
                } catch(Exception e) {}
            }

            System.out.println("Thread : " + Thread.currentThread().getName() + " producing..");
            notify();
            available = true;

            try {
                Thread.sleep(2000);
            } catch (Exception e) {}
        }

    }

    public synchronized void consume() {
        while (true) {
            while (!available) {
                try {
                    wait();
                } catch (Exception e) {}
            }

            System.out.println("Thread : " + Thread.currentThread().getName() + " consuming..");
            notify();
            available = false;

            try {
                Thread.sleep(2000);
            } catch (Exception e) {}
        }
    }
}