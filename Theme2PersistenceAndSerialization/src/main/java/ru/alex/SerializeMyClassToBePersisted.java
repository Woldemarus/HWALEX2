package ru.alex;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

//Класс сериалиации класса в файл
public class SerializeMyClassToBePersisted {
    public static void serialize(MyClassToBePersisted myObject, String fileName) {
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(myObject);
            System.out.println("Объект был сериализован и сохранен в " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
