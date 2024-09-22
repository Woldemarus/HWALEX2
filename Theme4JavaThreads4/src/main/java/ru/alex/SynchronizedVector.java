package ru.alex;

import java.util.Vector;
import java.util.Enumeration;
import java.util.List;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

public class SynchronizedVector<E> extends Vector<E> {
    private final Vector<E> vector;

    public SynchronizedVector(Vector<E> vector) {
        this.vector = vector;
    }

    @Override
    public synchronized int size() {
        return vector.size();
    }

    @Override
    public synchronized boolean isEmpty() {
        return vector.isEmpty();
    }

    @Override
    public synchronized Enumeration<E> elements() {
        return vector.elements();
    }

    @Override
    public synchronized boolean contains(Object o) {
        return vector.contains(o);
    }

    @Override
    public synchronized int indexOf(Object o) {
        return vector.indexOf(o);
    }

    @Override
    public synchronized int indexOf(Object o, int index) {
        return vector.indexOf(o, index);
    }

    @Override
    public synchronized int lastIndexOf(Object o) {
        return vector.lastIndexOf(o);
    }

    @Override
    public synchronized int lastIndexOf(Object o, int index) {
        return vector.lastIndexOf(o, index);
    }

    @Override
    public synchronized E elementAt(int index) {
        return vector.elementAt(index);
    }

    @Override
    public synchronized E firstElement() {
        return vector.firstElement();
    }

    @Override
    public synchronized E lastElement() {
        return vector.lastElement();
    }

    @Override
    public synchronized void setElementAt(E obj, int index) {
        vector.setElementAt(obj, index);
    }

    @Override
    public synchronized void removeElementAt(int index) {
        vector.removeElementAt(index);
    }

    @Override
    public synchronized void insertElementAt(E obj, int index) {
        vector.insertElementAt(obj, index);
    }

    @Override
    public synchronized void addElement(E obj) {
        vector.addElement(obj);
    }

    @Override
    public synchronized boolean removeElement(Object obj) {
        return vector.removeElement(obj);
    }

    @Override
    public synchronized void removeAllElements() {
        vector.removeAllElements();
    }

    @Override
    public synchronized Object clone() {
        return new SynchronizedVector<>((Vector<E>) vector.clone());
    }

}
