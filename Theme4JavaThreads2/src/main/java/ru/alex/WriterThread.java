package ru.alex;

import java.util.Vector;
import java.util.Random;

class WriterThread extends Thread {
    private Vector<Double> vector;
    private Random random;

    public WriterThread(Vector<Double> vector) {
        this.vector = vector;
        this.random = new Random();
    }

    @Override
    public void run() {
        for (int i = 0; i < vector.size(); i++) {
            double value = random.nextDouble() * 100;  // Генерируем случайное значение
            vector.set(i, value);
            System.out.println("Write: " + value + " to position " + i);
            try {
                Thread.sleep(100);  // Пауза для наглядности
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}