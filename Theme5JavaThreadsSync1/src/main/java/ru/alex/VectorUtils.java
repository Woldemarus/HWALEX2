package ru.alex;

import java.util.Vector;

public class VectorUtils {

    public static <E> Vector<E> synchronizedVector(Vector<E> vector) {
        return new SynchronizedVector<>(vector);
    }
}
