package org.example.sort.impl;

import org.example.entity.Student;
import org.example.sort.Sort;

import java.util.List;

public class SortByAllFields implements Sort {

    @Override
    public List<Student> sort(List<Student> studentList) {
        if (studentList == null || studentList.size() < 2) {
            return studentList;
        }

        int n = studentList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                Student first = studentList.get(j);
                Student second = studentList.get(j + 1);

                // Получаем все поля
                String groupNumberFirst = (first != null) ? first.getGroupNumber() : null;
                String groupNumberSecond = (second != null) ? second.getGroupNumber() : null;
                Double averageGradeFirst = (first != null) ? first.getAverageGrade() : null;
                Double averageGradeSecond = (second != null) ? second.getAverageGrade() : null;
                Integer recordBookNumberFirst = (first != null) ? first.getRecordBookNumber() : null;
                Integer recordBookNumberSecond = (second != null) ? second.getRecordBookNumber() : null;

                boolean needSwap = compareAllFields(groupNumberFirst, groupNumberSecond,
                        averageGradeFirst, averageGradeSecond,
                        recordBookNumberFirst, recordBookNumberSecond) > 0;

                // Меняем местами объекты в studentList
                if (needSwap) {
                    Student temp = first;
                    studentList.set(j, second);
                    studentList.set(j + 1, temp);
                }
            }
        }
        return studentList;
    }

    // Сравнение  по всем полям: группа -> средний балл -> номер зачётки
    private int compareAllFields(String g1, String g2, Double d1, Double d2, Integer r1, Integer r2) {

        // Сравнение по группе
        int groupCmp = compareStudentsFields(g1, g2);
        if (groupCmp != 0) return groupCmp;

        // Сравнение по среднему баллу
        int gradeCmp = compareStudentsFields(d1, d2);
        if (gradeCmp != 0) return gradeCmp;

        // Сравнение по номеру зачётки
        return compareStudentsFields(r1, r2);
    }

    //Метод для сравнения полей Student
    public static <T extends Comparable<T>> int compareStudentsFields(T obj1, T obj2) {
        if (obj1 == null && obj2 == null) return 0;
        if (obj1 == null) return -1;
        if (obj2 == null) return 1;
        return obj1.compareTo(obj2);
    }
}
