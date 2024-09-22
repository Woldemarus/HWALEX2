package ru.alex;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.nio.ByteBuffer;

public class Main {

    public static void main(final String[] args) {
        System.out.println("Theme3 - Java NIO - 2");

        String filename = "integers.dat";

        // Пример списка целых чисел для записи
        int[] integers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // Запись целых чисел в файл
        writeIntegersToFile(filename, integers);

        // Обработка файла и вывод результатов
        try {
            processFile(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Удаление файла после выполнения программы (опционально)
        new File(filename).delete();
    }

    public static void writeIntegersToFile(String filename, int[] integers) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename))) {
            for (int number : integers) {
                dos.writeInt(number);
                dos.writeBytes(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void processFile(String fileName) throws IOException {
        //Считываем из файла данные как поток байтов
        List<Integer> readed = getIntegersFromByteStream(fileName);
        System.out.println("Считанный массив: " + readed);

        // Рекуррентый расчет среднего
        Double srednee = getRecursiveAverage(readed);
        System.out.println("Рекурсивное среднее: " + srednee);

        // 1. Произведите расчет считанных элементов при чтении данных разного типа.
        int intSum = 0;
        int byteSum = 0;
        float floatSum = 0;
        assert readed != null;
        for (Integer number : readed) {
            intSum += number;
            byteSum += ByteBuffer.wrap(ByteBuffer.allocate(4).putInt(number).array()).getInt();
            floatSum += (float) number;
        }
        System.out.println("Сумма целых чисел: " + intSum);
        System.out.println("Сумма byte чисел: " + byteSum);
        System.out.println("Сумма float чисел: " + floatSum);

        // 2. Определите количество чисел типа int.
        int intCount = readed.size();
        System.out.println("Количество целых чисел: " + intCount);

        // 3. Рассчитайте среднее второй половины, третьей четверти.
        int halfIndex = intCount / 2;
        int threeQuarterIndex = (3 * intCount) / 4;

        List<Integer> secondHalf = readed.subList(halfIndex, intCount);
        List<Integer> thirdQuarter = readed.subList(threeQuarterIndex, intCount);

        double secondHalfAvg = getRecursiveAverage(secondHalf);
        double thirdQuarterAvg = getRecursiveAverage(thirdQuarter);

        System.out.println("Среднее второй половины: " + secondHalfAvg);
        System.out.println("Среднее третьей четверти: " + thirdQuarterAvg);
    }

    private static List<Integer> getIntegersFromByteStream(String fileName) throws IOException {

        // Чтение содержимого файла в массив байтов
        byte[] fileContent = null;
        try {
            fileContent = Files.readAllBytes(Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (
                // Создание ByteArrayInputStream из массива байтов
                ByteArrayInputStream bais = new ByteArrayInputStream(fileContent);

                // Использование BufferedReader для чтения данных из ByteArrayInputStream
                BufferedReader reader = new BufferedReader(new InputStreamReader(bais));

        ) {

            // Список для хранения чисел из файла
            List<Integer> numbers = new ArrayList<>();
            byte[] buf = new byte[4];
            int cursor = 0;
            while (true) {

                int r = reader.read();
                if (r == -1) {
                    break;
                } else if (r == 32) {
                    ByteBuffer byteBuffer = ByteBuffer.wrap(buf);
                    numbers.add(byteBuffer.getInt());
                    cursor = 0;
                } else {
                    buf[cursor] = (byte) r;
                    cursor++;
                }
            }
            // Вывод списка
            for (Integer i : numbers) {
                System.out.println(i);
            }

            return numbers;

        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IOException("Ошибка считывания файла");
    }

    private static Double getRecursiveAverage(List<Integer> numbers) {
        int sum = calculateSum(numbers, numbers.size());
        return (double) sum / numbers.size();
    }

    private static int calculateSum(List<Integer> numbers, int n) {
        // Базовый случай: если список пуст или достигнут конец списка
        if (n == 0) {
            return 0;
        }
        // Рекурсивный случай: сумма текущего элемента и суммы оставшихся элементов
        return numbers.get(n - 1) + calculateSum(numbers, n - 1);
    }
}
