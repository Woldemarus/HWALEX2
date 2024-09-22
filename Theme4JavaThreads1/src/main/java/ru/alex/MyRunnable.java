package ru.alex;

public class MyRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("Поток MyRunnable запущен.");
    }
}
