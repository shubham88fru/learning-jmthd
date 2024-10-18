package org.learning.jmthd.multithreading.udyc1.StudentLibrarySimulation;

import java.util.Arrays;
import java.util.Random;

public class Student implements Runnable {
    private int id;
    private Book[] books;

    private final Random random = new Random();

    public Student(int id, Book[] books) {
        this.id = id;
        this.books = books;
    }

    @Override
    public void run() {

        while (true) {
            int bookId = random.nextInt(Constants.NUM_OF_BOOKS);
            try {
                books[bookId].read(this);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String toString() {
        return "Student#{" +
                "id=" + id +
                '}';
    }
}
