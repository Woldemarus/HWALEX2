package ru.alex;

import java.io.*;

public class Main {

    public static void main(final String[] args) {
        System.out.println("Theme3 - Java NIO - 3");

        String filePath = "documentation.txt"; // Путь к вашему текстовому файлу

        // Получение ClassLoader
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        // Чтение 1 файла из ресурсов
        try (
                InputStream inputStream1 = classLoader.getResourceAsStream(filePath);
                Reader fileReader1 = new InputStreamReader(inputStream1);
                BufferedReader reader = new BufferedReader(fileReader1);
        ) {
            StringBuilder content = new StringBuilder();
            String line;
            int javaCount = 0;

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
                javaCount += countOccurrences(line, "java");
                if (javaCount >= 3) {
                    break;
                }
            }

            System.out.println("Содержимое файла до третьего вхождения слова 'java':");
            System.out.println(content.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static int countOccurrences(String line, String word) {
        int count = 0;
        int index = 0;

        while ((index = line.indexOf(word, index)) != -1) {
            count++;
            index += word.length();
        }

        return count;
    }
}
