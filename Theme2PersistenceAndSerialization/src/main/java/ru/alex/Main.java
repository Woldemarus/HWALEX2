package ru.alex;


import static ru.alex.Vectors.*;

public class Main {

    @SuppressWarnings({"java:S106", "java:S1192"})
    public static void main(final String[] args) {
        System.out.println("Theme2 - Persistence @ Serialization");

// Пример использования метода
        double[] vector1 = {1.0, 2.0, 3.0};
        double[] vector2 = {4.0, 5.0, 6.0};

        double[] scaledVector = multiplyByScalar(vector1, 2);
        System.out.print("Скалярно умноженный вектор: ");
        for (double v : scaledVector) {
            System.out.print(v + " ");
        }
        System.out.println();

        double[] addedVectors = addVectors(vector1, vector2);
        System.out.print("Сложенные векторы: ");
        for (double v : addedVectors) {
            System.out.print(v + " ");
        }
        System.out.println();

        double scalarProduct = dotProduct(vector1, vector2);
        System.out.println("Скалярное произведение: " + scalarProduct);




    }
}
