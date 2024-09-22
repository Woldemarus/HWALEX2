package ru.alex;

public class WriteThread implements Runnable {
    private final ReadWriteSync sync;

    public WriteThread(ReadWriteSync sync) {
        this.sync = sync;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) { // Выполнить 5 операций записи
                sync.write();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}