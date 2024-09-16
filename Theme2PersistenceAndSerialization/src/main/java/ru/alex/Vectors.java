package ru.alex;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class Vectors {

    // Метод умножения вектора на скаляр
    public static Vector multiplyByScalar(Vector vector, double scalar) {
        Integer dimension = vector.getDim();
        List<Double> resultVector = new ArrayList<>();
        resultVector.add(dimension.doubleValue());

        for (int i = 1; i <= dimension; i++) {
            resultVector.add(vector.getCoordById(i) * scalar);
        }
        return new Vector(resultVector);
    }

    // Метод сложения двух векторов
    public static Vector addVectors(Vector vector1, Vector vector2) throws IllegalArgumentException {
        Integer dimension1 = vector1.getDim();
        Integer dimension2 = vector2.getDim();

        if (!Objects.equals(dimension1, dimension2)) {
            throw new IllegalArgumentException("Vectors must have the same dimension.");
        }
        List<Double> result = new ArrayList<>();
        result.add(dimension1.doubleValue()); // Добавляем размерность

        for (int i = 1; i <= dimension1; i++) {
            result.add(vector1.getCoordById(i) + vector2.getCoordById(i));
        }
        return new Vector(result);
    }

    // Метод нахождения скалярного произведения двух векторов
    public static double dotProduct(Vector vector1, Vector vector2) throws IllegalArgumentException {
        Integer dimension1 = vector1.getDim();
        Integer dimension2 = vector2.getDim();

        if (!Objects.equals(dimension1, dimension2)) {
            throw new IllegalArgumentException("Vectors must have the same dimension.");
        }
        double result = 0.0;
        // a · b = ax · bx + ay · by
        for (int i = 1; i <= dimension1; i++) {
            result += vector1.getCoordById(i) * vector2.getCoordById(i);
        }
        return result;
    }

    // Метод чтения вектора из символьного потока
    public static List<Double> readVector(Reader reader) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(reader);
        StreamTokenizer tokenizer = new StreamTokenizer(bufferedReader);

        tokenizer.whitespaceChars(' ', ' ');

        tokenizer.eolIsSignificant(true);

        int dimensionToken = tokenizer.nextToken();

        if (dimensionToken != StreamTokenizer.TT_NUMBER) {
            throw new IllegalArgumentException("Expected a number for dimension");
        }
        int dimension = (int) tokenizer.nval;

        System.out.printf("dim: %d%n", dimension);

        List<Double> vector = new ArrayList<>();
        vector.add((double) dimension); // Добавляем размерность

        while (true) {
            tokenizer.nextToken();
            if (tokenizer.ttype == StreamTokenizer.TT_EOL || tokenizer.ttype == StreamTokenizer.TT_EOF) {
                break;
            } else {
                if (tokenizer.ttype == StreamTokenizer.TT_NUMBER) {
                    System.out.println("Number: " + tokenizer.nval);
                    vector.add(tokenizer.nval);
                } else {
                    throw new IOException("Unexpected token type: " + tokenizer.ttype);
                }
            }
        }

        return vector;
    }

    // Метод записи вектора в символьный поток
    public static void writeVector(Vector vector, Writer writer) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        for (int i = 0; i <= vector.getDim(); i++) {
            if (i != 0) {
                bufferedWriter.write(vector.getCoordById(i) + " ");
            } else {
                bufferedWriter.write(vector.getDim() + " ");
            }

        }
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }

    // Функция для записи вектора в байтовый поток
    public static void outputVector(Vector v, OutputStream out) throws IOException {
        DataOutputStream dataOut = new DataOutputStream(out);
        dataOut.writeInt(v.getDim());
        List<Double> coordinates = v.getCoordList();
        for (Double coordinate : coordinates) {
            dataOut.writeDouble(coordinate);
        }
        dataOut.flush();
    }

    // Функция для чтения вектора из байтового потока
    public static Vector inputVector(InputStream in) throws IOException {
        DataInputStream dataIn = new DataInputStream(in);
        int dimension = dataIn.readInt();
        List<Double> resultVector = new ArrayList<>();
        resultVector.add((double) dimension);

        for (int i = 0; i < dimension; i++) {
            resultVector.add(dataIn.readDouble());
        }

        return new Vector(resultVector);
    }
}

