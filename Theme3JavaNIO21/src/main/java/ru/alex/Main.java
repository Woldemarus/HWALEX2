package ru.alex;

import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class Main {

    public static void main(final String[] args) {
        System.out.println("Theme3 - Java NIO - 1");

        try {


            // 1. Определите текущий каталог
            Path currentDir = Paths.get("").toAbsolutePath();
            System.out.println("Current directory: " + currentDir);

            // Путь к подпроекту
            String subprojectName = "Theme3JavaNIO21";
            Path subprojectDir = currentDir.resolve(subprojectName);
            System.out.println("Subproject directory: " + subprojectDir);

            // 2. Имя файла
            String fileName = "Main.java";
            System.out.println("File name: " + fileName);

            // Путь к каталогу src в подпроекте
            Path srcDir = subprojectDir.resolve("src");
            System.out.println("Source directory in subproject: " + srcDir);

            // Собираем полный путь
            Path fullPath = srcDir.resolve("main/java/ru/alex");
            System.out.println("Full path to Main.java file is: " + fullPath);

            // Проверка существования директории src
            if (Files.exists(fullPath) && Files.isDirectory(fullPath)) {
                System.out.println("Full path directory exists.");
            } else {
                System.out.println("Full path directory does not exist.");
            }

            // 4. Соберите имя файла с текстом программы (добавьте расширение *.java)
            Path filePath = fullPath.resolve(fileName);
            System.out.println("File path: " + filePath);

            if (Files.exists(filePath)) {
                // Чтение содержимого файла
                String content = Files.readString(filePath, StandardCharsets.UTF_8);
                System.out.println("----------------------------------------------");
                System.out.println("File content:");
                System.out.println(content);
                System.out.println("----------------------------------------------");
            } else {
                throw new IOException("File not fount, filepath: " + filePath);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
