package ru.alex;

import java.io.*;
import java.util.Vector;

public class Main {

    public static void main(final String[] args) {
        System.out.println("Theme4JavaThreads - 2");

        Vector<Double> vector = new Vector<>(10);
        for (int i = 0; i < 10; i++) {
            vector.add(0.0);  // Инициализируем вектор нулями
        }

        WriterThread writerThread = new WriterThread(vector);
        ReaderThread readerThread = new ReaderThread(vector);

        writerThread.setPriority(Thread.MAX_PRIORITY);
        readerThread.setPriority(Thread.MIN_PRIORITY);

        writerThread.start();
        readerThread.start();

        try {
            writerThread.join();
            readerThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
