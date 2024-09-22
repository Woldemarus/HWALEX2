package ru.alex;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

//Класс десериализации класса из файла
public class DeserializeMyClassToBePersisted {
    public static MyClassToBePersisted deserialize(String fileName) {
        MyClassToBePersisted myObject = null;
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            myObject = (MyClassToBePersisted) in.readObject();
            System.out.println("Объект был десериализован из " + fileName);
            System.out.println("Данные объекта: " + myObject);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return myObject;
    }
}
