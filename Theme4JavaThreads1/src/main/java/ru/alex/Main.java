package ru.alex;

public class Main {

    public static void main(final String[] args) {
        System.out.println("Theme4JavaThreads - 1");


        MyThread myThread = new MyThread();
        myThread.start();  // Запускаем поток

        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();  // Запускаем поток

    }
}
