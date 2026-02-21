package org.learning.jmthd.multithreading.revision;

public class SimpleProducerConsumer {
    public static void main(String[] args) {
        ProducerConsumer pdc = new ProducerConsumer();
        Thread producer = new Thread(pdc::produce);
        Thread consumer = new Thread(pdc::consume);

        producer.start();
        consumer.start();
    }
}

class ProducerConsumer {
    private boolean available = false;

    public synchronized void produce() {
        while (available) {
            try {
                wait();
            } catch(Exception e) {}
        }

        System.out.println("Thread : " + Thread.currentThread().getName() + " producing..");
        notify();
        available = true;
    }

    public synchronized void consume() {
        while (!available) {
            try {
                wait();
            } catch (Exception e) {}
        }

        System.out.println("Thread : " + Thread.currentThread().getName() + " consuming..");
        notify();
        available = false;
    }
}