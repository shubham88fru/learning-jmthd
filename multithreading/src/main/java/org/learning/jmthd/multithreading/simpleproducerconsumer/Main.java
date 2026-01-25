package org.learning.jmthd.multithreading.simpleproducerconsumer;

public class Main {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();
        Thread producer = new Thread(() -> {
            /**** To make consumer wait since nothing's produced ****/
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sharedResource.addItem();
        });

        Thread consumer = new Thread(() -> {
            sharedResource.consumeItem();
        });

        producer.setName("producer");
        consumer.setName("consumer");

        producer.start();
        consumer.start();
    }
}
