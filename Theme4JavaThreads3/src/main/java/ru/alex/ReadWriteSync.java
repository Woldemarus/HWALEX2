package ru.alex;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReadWriteSync {
    private final Lock lock = new ReentrantLock();
    private final Condition writeCondition = lock.newCondition();
    private final Condition readCondition = lock.newCondition();
    private boolean writeTurn = true;

    public void write() throws InterruptedException {
        lock.lock();
        try {
            while (!writeTurn) {
                writeCondition.await();
            }
            System.out.println("Write Operation");
            writeTurn = false;
            readCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void read() throws InterruptedException {
        lock.lock();
        try {
            while (writeTurn) {
                readCondition.await();
            }
            System.out.println("Read Operation");
            writeTurn = true;
            writeCondition.signal();
        } finally {
            lock.unlock();
        }
    }
}
