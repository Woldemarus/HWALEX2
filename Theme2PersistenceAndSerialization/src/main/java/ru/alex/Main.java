package ru.alex;


import javax.sound.sampled.AudioFormat;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import static ru.alex.Vectors.*;

public class Main {

    public static void main(final String[] args) throws IOException {
        System.out.println("Theme2 - Persistence @ Serialization");


        try {
            //  ---
            //  Пример использования методов с потоками System.in и System.out
            System.out.println("---------------------------------");
            System.out.println("Пример использования методов с потоками System.in и System.out");
            System.out.println("---------------------------------");
            //  ---
            System.out.println("Введите вектор (размерность и координаты через пробел):");
            Vector vector1 = new Vector(readVector(new InputStreamReader(System.in)));

            System.out.println("Введите второй вектор (размерность и координаты через пробел):");
            Vector vector2 = new Vector(readVector(new InputStreamReader(System.in)));

            //  ---
            //  Сложение векторов
            //  ---
            Vector sumVector = addVectors(vector1, vector2);
            System.out.println("Сумма векторов:");
            writeVector(sumVector, new OutputStreamWriter(System.out));

            //  ---
            //  Умножение вектора на скаляр
            //  ---
            System.out.println("Введите скаляр (double):");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            double scalar = Double.parseDouble(reader.readLine());
            Vector scaledVector = multiplyByScalar(vector1, scalar);
            System.out.println("Первый вектор после умножения на скаляр:");
            writeVector(scaledVector, new OutputStreamWriter(System.out));

            //  ---
            //  Скалярное произведение векторов
            //  ---
            double dotProductResult = dotProduct(vector1, vector2);
            System.out.println("Скалярное произведение векторов: " + dotProductResult);

        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }





        //  ---
        //  Использование файловый поток в символьном потоке
        System.out.println("---------------------------------");
        System.out.println("Использование файловый поток в символьном потоке");
        System.out.println("---------------------------------");
        //  ---
        String fileName1 = "vector1.txt";
        String fileName2 = "vector2.txt";
        // Получение ClassLoader
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        // Чтение 1 файла из ресурсов
        try (
                InputStream inputStream1 = classLoader.getResourceAsStream(fileName1);
                InputStream inputStream2 = classLoader.getResourceAsStream(fileName2);
                Reader fileReader1 = new InputStreamReader(inputStream1);
                Reader fileReader2 = new InputStreamReader(inputStream2);
                BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
                BufferedReader bufferedReader2 = new BufferedReader(fileReader2);
        ) {
            Vector vectorFromFile1 = new Vector(readVector(bufferedReader1));
            System.out.println("Вектор вычитанный из первого файла:");
            writeVector(vectorFromFile1, new OutputStreamWriter(System.out));
            Vector vectorFromFile2 = new Vector(readVector(bufferedReader2));
            System.out.println("Вектор вычитанный из второго файла:");
            writeVector(vectorFromFile2, new OutputStreamWriter(System.out));
            Vector sumVectorFromFile = addVectors(vectorFromFile1, vectorFromFile2);
            System.out.println("Сумма векторов вычитанных из двух файлов:");
            writeVector(sumVectorFromFile, new OutputStreamWriter(System.out));
            //Записываем во внешний файл
            Writer fileWriter = new FileWriter("result.txt");
            writeVector(sumVectorFromFile, fileWriter);

        } catch (Exception e) {
            e.printStackTrace();
        }


        //  ---
        //  Использование байтовый поток запись в файл
        System.out.println("---------------------------------");
        System.out.println("Использование байтовый поток запись в файл");
        System.out.println("---------------------------------");
        //  ---
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            Vector vector = new Vector(new ArrayList<>(Arrays.asList(3.0, 1.0, 2.0, 3.0)));

            // Запись вектора в байтовый поток
            outputVector(vector, baos);

            // Запись вектора в байтовый поток в файл
            // Преобразуем данные в массив байтов
            byte[] byteData = baos.toByteArray();
            // Записываем массив байтов в файл
            try (FileOutputStream fos = new FileOutputStream("outputByteVector.txt")) {
                fos.write(byteData);
            }
            System.out.println("Данные успешно записаны в файл.");

            // Чтение вектора из байтового потока
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            Vector readVector = inputVector(bais);
            // Проверка результата
            System.out.println("Размерность вектора напрямую из байтового потока: " + readVector.getDim());
            System.out.println("Координаты вектора напрямую из байтового потока: " + readVector.getCoordList());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        //  ---
        //  Использование байтовый поток чтение вектора из файла
        System.out.println("---------------------------------");
        System.out.println("Использование байтовый поток чтение вектора из файла");
        System.out.println("---------------------------------");
        //  ---
        // Шаг 1: Загрузите файл из ресурсов
        try (InputStream resourceStream = Main.class.getResourceAsStream("/inputByteVector.txt")) {
            if (resourceStream == null) {
                System.out.println("Файл не найден в ресурсах.");
                return;
            }

            // Шаг 2: Прочитайте содержимое файла в массив байтов
            ByteArrayOutputStream baosReaderFile = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = resourceStream.read(buffer)) != -1) {
                baosReaderFile.write(buffer, 0, bytesRead);
            }
            byte[] byteData = baosReaderFile.toByteArray();

            // Шаг 3: Создайте ByteArrayInputStream, используя массив байтов
            ByteArrayInputStream bais = new ByteArrayInputStream(byteData);

            Vector readVector = inputVector(bais);
            // Проверка результата
            System.out.println("Размерность вектора из файла из байтового потока: " + readVector.getDim());
            System.out.println("Координаты вектора из файла из байтового потока: " + readVector.getCoordList());

        } catch (IOException e) {
            e.printStackTrace();
        }





        //  ---
        //  Сериализация и десериализация классов ArrayVector и LinkedListVector
        System.out.println("---------------------------------");
        System.out.println("Сериализация и десериализация классов ArrayVector и LinkedListVector");
        System.out.println("---------------------------------");
        //  ---

        // Создание и инициализация объектов
        ArrayVector arrayVector = new ArrayVector(3);
        arrayVector.setElement(0, 1.1);
        arrayVector.setElement(1, 2.2);
        arrayVector.setElement(2, 3.3);

        LinkedListVector linkedListVector = new LinkedListVector();
        linkedListVector.addElement(4.4);
        linkedListVector.addElement(5.5);
        linkedListVector.addElement(6.6);

        // Сериализация объектов
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("vectors.ser"))) {
            oos.writeObject(arrayVector);
            oos.writeObject(linkedListVector);
            System.out.println("Объекты успешно сериализованы.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Десериализация объектов
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("vectors.ser"))) {
            ArrayVector deserializedArrayVector = (ArrayVector) ois.readObject();
            LinkedListVector deserializedLinkedListVector = (LinkedListVector) ois.readObject();

            // Сравнение исходных и десериализованных объектов
            System.out.println("Исходный ArrayVector: " + arrayVector);
            System.out.println("Десериализованный ArrayVector: " + deserializedArrayVector);

            System.out.println("Исходный LinkedListVector: " + linkedListVector);
            System.out.println("Десериализованный LinkedListVector: " + deserializedLinkedListVector);

            System.out.println("ArrayVector равен десериализованному: " + arrayVector.toString().equals(deserializedArrayVector.toString()));
            System.out.println("LinkedListVector равен десериализованному: " + linkedListVector.toString().equals(deserializedLinkedListVector.toString()));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }





        //  ---
        //  Сериализация и десериализация объекта класса в отдельном классе
        System.out.println("---------------------------------");
        System.out.println("Сериализация и десериализация в отдельном классах");
        System.out.println("---------------------------------");
        //  ---
        String fileName = "myObject1.ser";

        // Создание объекта
        MyClassToBePersisted myObject = new MyClassToBePersisted("Profile1", "GroupA");

        SerializeMyClassToBePersisted.serialize(myObject, fileName);

        // Десериализация объекта
        MyClassToBePersisted deserializedObject = DeserializeMyClassToBePersisted.deserialize(fileName);
        // Сравнение исходного и десериализованного объекта
        System.out.println("Исходный объект         : " + myObject);
        System.out.println("Десериализованный объект: " + deserializedObject);
        System.out.println("Объекты равны: " + myObject.equals(deserializedObject));
    }
}
