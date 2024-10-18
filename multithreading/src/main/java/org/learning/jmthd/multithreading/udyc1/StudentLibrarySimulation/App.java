package org.learning.jmthd.multithreading.udyc1.StudentLibrarySimulation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) {

        Student[] students = null;
        Book[] books = null;
        ExecutorService executorService = Executors.newFixedThreadPool(Constants.NUM_OF_STUDENTS);

        try {
            books = new Book[Constants.NUM_OF_BOOKS];
            students = new Student[Constants.NUM_OF_STUDENTS];

            for (int i=0; i<Constants.NUM_OF_BOOKS; i++) {
                books[i] = new Book(i+1);
            }

            for (int i=0; i<Constants.NUM_OF_STUDENTS; i++) {
                students[i] = new Student(i+1, books);
                executorService.execute(students[i]);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
