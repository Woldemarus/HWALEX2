package ru.alex;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

//Сделали класс сериализуемым
public class LinkedListVector implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Double> elements;

    public LinkedListVector() {
        elements = new LinkedList<>();
    }

    public void addElement(double value) {
        elements.add(value);
    }

    public double getElement(int index) {
        return elements.get(index);
    }

    public int size() {
        return elements.size();
    }

    @Override
    public String toString() {
        return elements.toString();
    }
}

