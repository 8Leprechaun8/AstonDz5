package org.example.sort.impl;

import org.example.entity.Student;
import org.example.sort.Sort;

import java.util.List;

public class SortByGroupNumber implements Sort {

    @Override
    public List<Student> sort(List<Student> studentList) {
        if (studentList == null || studentList.size() < 2) {
            return studentList;
        }

        int listSize = studentList.size();
        for (int i = 0; i < listSize - 1; i++) {
            for (int j = 0; j < listSize - 1 - i; j++) {
                Student first = studentList.get(j);
                Student second = studentList.get(j + 1);
                //Защита от NullPointerException при first == null
                String firstValue = (first != null) ? first.getGroupNumber() : null;
                String secondValue = (second != null) ? second.getGroupNumber() : null;
                //объекты с null значением останутся вначале
                boolean needSwap = false;
                if (firstValue == null && secondValue == null) {
                    needSwap = false;
                } else if (firstValue == null) {
                    needSwap = false;
                } else if (secondValue == null) {
                    needSwap = true;
                } else if (firstValue.compareTo(secondValue) > 0) {
                    needSwap = true;
                }

                if (needSwap) {
                    Student temp = first;
                    studentList.set(j, second);
                    studentList.set(j + 1, temp);
                }
            }

        }

        return studentList;
    }
}
