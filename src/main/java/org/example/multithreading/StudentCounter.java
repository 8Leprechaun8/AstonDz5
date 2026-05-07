package org.example.multithreading;

import org.example.entity.Student;

import java.util.ArrayList;
import java.util.Objects;

public class StudentCounter {

    public static void countOccurencess(ArrayList<Student> list, Object target) throws InterruptedException {
        if (list == null || list.isEmpty()) {
            System.out.println("Коллекция пуста. Вхождений: 0");
            return;
        }

        int size = list.size();
        // Массив объектов для хранения значений нужного поля
        Object[] extractedValues = new Object[size];

        // Заполнение массива
        if (target instanceof String) {
            for (int i = 0; i < size; i++) {
                extractedValues[i] = list.get(i).getGroupNumber();
            }
        } else if (target instanceof Double) {
            for (int i = 0; i < size; i++) {
                extractedValues[i] = list.get(i).getAverageGrade();
            }
        } else if (target instanceof Integer) {
            for (int i = 0; i < size; i++) {
                extractedValues[i] = list.get(i).getRecordBookNumber();
            }
        } else {
            throw new IllegalArgumentException("Неподдерживаемый тип target: " +
                    (target == null ? "null" : target.getClass().getSimpleName()));
        }

        // Многопоточный подсчёт колличества вхождений target
        int threadCount = Runtime.getRuntime().availableProcessors();
        int chunkSize = size / threadCount;
        int[] threadResults = new int[threadCount];
        Thread[] workers = new Thread[threadCount];

        for (int t = 0; t < threadCount; t++) {
            final int idx = t;
            final int start = t * chunkSize;
            final int end = (t == threadCount - 1) ? size : Math.min(start + chunkSize, size);

            workers[t] = new Thread(() -> {
                int localCount = 0;
                for (int i = start; i < end; i++) {
                    // Сравние элементов массива с target
                    if (Objects.equals(extractedValues[i], target)) {
                        localCount++;
                    }
                }
                // Запись подсчёта вхождений каждого потока
                threadResults[idx] = localCount;
            });
            workers[t].start();
        }


        //Ожидание завершения всех потоков
        for (Thread worker : workers) {
            worker.join();
        }

        //Подсчёт вхождений всем потокам
        int totalCount = 0;
        for (long count : threadResults) {
            totalCount += count;
        }

        System.out.println("Значение '" + target + "' встречается " + totalCount + " раз.");
    }

}
