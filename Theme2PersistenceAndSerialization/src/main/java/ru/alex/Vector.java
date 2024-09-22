package ru.alex;

import java.util.ArrayList;
import java.util.List;

public class Vector {

    private int dim;

    private List<Double> coord = new ArrayList<>();

    //конструктор
    public Vector(List<Double> _vector) {
        this.dim = _vector.get(0).intValue();
        coord = new ArrayList<>();
        for (int i = 1; i < _vector.size(); i++) {
            coord.add(_vector.get(i));
        }
    }

    public List<Double> getCoordList() {
        return coord;
    }

    public Double getCoordById(int id) {
        return coord.get(id - 1);
    }

    public void addCoordInOrder(Double coord) {
        this.coord.add(coord);
    }

    public int getDim() {
        return dim;
    }

    public void setDim(Double dim) {
        this.dim = dim.intValue();
    }

    public List<Double> getVector() {
        List<Double> vector = new ArrayList<>();
        vector.add((double) dim);
        for (int i = 1; i < this.coord.size(); i++) {
            vector.add(this.coord.get(i));
        }
        return vector;
    }
}

