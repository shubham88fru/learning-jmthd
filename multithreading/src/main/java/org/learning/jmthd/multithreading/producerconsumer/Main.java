package org.learning.jmthd.multithreading.producerconsumer;

public class Main {
    public static void main(String[] args) {

        Buffer buffer = new Buffer(10);

        Thread producer = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                buffer.put(i);
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                buffer.get();
            }
        });

        consumer.setName("consumer");
        producer.setName("producer");

        consumer.start();
        producer.start();
    }
}
