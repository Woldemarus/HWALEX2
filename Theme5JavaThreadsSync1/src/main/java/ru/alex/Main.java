package ru.alex;

import java.util.Vector;

public class Main {

    public static void main(final String[] args) {
        System.out.println("Theme4JavaThreads - 4");

        Vector<Integer> vector = new Vector<>();
        Vector<Integer> synchronizedVector = VectorUtils.synchronizedVector(vector);

        // Теперь synchronizedVector потокобезопасен
        synchronizedVector.add(1);
        synchronizedVector.add(2);
        synchronizedVector.add(3);

        System.out.println(synchronizedVector);


    }
}
