package ru.alex;

import java.io.*;

public class Main {

    public static void main(final String[] args) {
        System.out.println("Theme3 - Java NIO - 4");

        // Проверяем, что символ был передан в качестве аргумента командной строки
        if (args.length != 2) {
            System.out.println("Usage: java CountCharacterInFile <file-path> <character>");
            return;
        }

        String filePath = args[0];
        char targetChar = args[1].charAt(0);  // Получаем символ из аргумента командной строки

        //String filePath = "doc.txt"; // Путь к вашему текстовому файлу

        // Получение ClassLoader
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        // Чтение 1 файла из ресурсов
        try (
                InputStream inputStream1 = classLoader.getResourceAsStream(filePath);
                Reader fileReader1 = new InputStreamReader(inputStream1);
                BufferedReader reader = new BufferedReader(fileReader1);
        ) {
            int count = 0;
            int c;

            // Читаем файл символ за символом
            while ((c = reader.read()) != -1) {
                if (c == targetChar) {
                    count++;
                }
            }

            System.out.println("Символ '" + targetChar + "' встречается " + count + " раз в файле " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
