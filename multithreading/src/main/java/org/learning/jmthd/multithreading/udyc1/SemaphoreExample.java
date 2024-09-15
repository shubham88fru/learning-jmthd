package org.learning.jmthd.multithreading.udyc1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphores are used to control access to a shared resource
 * that uses a counter variable. Semaphore maintains a set of permits.
 *
 * If a permit is available, it can acquired using -- acquire()
 * If an acquired permits has to be released back, use the release() method.
 *
 * Semaphore just keeps a count of the number of permits available:
 *  new Semaphore(int permits, boolean fairness);
 */
public class SemaphoreExample {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i=0; i<12; i++) {
            service.execute(Downloader.INSTANCE::download);
        }
        service.shutdown();
    }
}


enum Downloader { //singleton
    INSTANCE;
    private Semaphore semaphore = new Semaphore(3,true); //3 permits.

    public void download() {
        try {
            semaphore.acquire(); //acquire a permit.
            downloadData();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(); //release the acquired permit.
        }
    }

    //upto 3 parallel downloads.
    private void downloadData() {
        try {
            System.out.println("Downloading data from the web...");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
