package ru.alex;

// Расширение класса Thread
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Поток MyThread запущен.");
    }
}

