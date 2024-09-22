package ru.alex;

import java.io.*;

public class Main {

    public static void main(final String[] args) {
        System.out.println("Theme4JavaThreads - 3");

        ReadWriteSync sync = new ReadWriteSync();
        Thread writeThread = new Thread(new WriteThread(sync));
        Thread readThread = new Thread(new ReadThread(sync));

        writeThread.start();
        readThread.start();

        try {
            writeThread.join();
            readThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
