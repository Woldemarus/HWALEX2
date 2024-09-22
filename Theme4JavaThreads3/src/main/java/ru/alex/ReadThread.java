package ru.alex;

public class ReadThread implements Runnable {
    private final ReadWriteSync sync;

    public ReadThread(ReadWriteSync sync) {
        this.sync = sync;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) { // Выполнить 5 операций чтения
                sync.read();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
