package ru.alex;

import java.io.Serializable;
import java.util.Arrays;

// Класс ArrayVector, сделали реализуемм
public class ArrayVector implements Serializable {
    private static final long serialVersionUID = 1L;
    private double[] elements;

    public ArrayVector(int size) {
        elements = new double[size];
    }

    public void setElement(int index, double value) {
        elements[index] = value;
    }

    public double getElement(int index) {
        return elements[index];
    }

    public int size() {
        return elements.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }
}