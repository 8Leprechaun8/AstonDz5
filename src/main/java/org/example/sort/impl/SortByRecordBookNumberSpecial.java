package org.example.sort.impl;

import org.example.entity.Student;
import org.example.sort.Sort;

import java.util.ArrayList;
import java.util.List;

public class SortByRecordBookNumberSpecial implements Sort {

    @Override
    public List<Student> sort(List<Student> studentList) {
        List<StudentWithNumber> listOdd = new ArrayList<>();
        List<StudentWithNumber> listEven = new ArrayList<>();
        //Создание двух списков: с нечетными значениями номера зачетки и с четными значениями.
        for (int i = 0; i < studentList.size(); ++i) {
            if (studentList.get(i).getRecordBookNumber() % 2 == 1) {
                listOdd.add(new StudentWithNumber(i, studentList.get(i)));
            } else {
                listEven.add(new StudentWithNumber(i, studentList.get(i)));
            }
        }
        //Преобразование листов в массивы
        StudentWithNumber[] arrayOdd = listOdd.toArray(new StudentWithNumber[listOdd.size()]);
        StudentWithNumber[] arrayEven = listEven.toArray(new StudentWithNumber[listEven.size()]);
        //Сортировка пузырьком массива с четными значениями RecordBookNumber
        for (int i = 0; i < arrayEven.length - 1; ++i) {
            for (int j = 0; j < arrayEven.length - 1 - i; ++j) {
                if (arrayEven[j + 1].getStudent().getRecordBookNumber() <
                        arrayEven[j].getStudent().getRecordBookNumber()) {
                    StudentWithNumber studentRab = arrayEven[j + 1];
                    arrayEven[j + 1] = arrayEven[j];
                    arrayEven[j] = studentRab;
                }
            }
        }
        //Формирование результирующего списка
        Student[] resultArray = new Student[studentList.size()];
        for(int i = 0; i < arrayOdd.length; ++i) {
            resultArray[arrayOdd[i].getI()] = arrayOdd[i].getStudent();
        }
        int iEven = 0;
        for (int i = 0; i < resultArray.length; ++i) {
            if (resultArray[i] == null) {
                resultArray[i] = arrayEven[iEven++].getStudent();
            }
        }
        List<Student> result = new ArrayList<>();
        for (int i = 0; i < resultArray.length; ++i) {
            result.add(resultArray[i]);
        }
        return result;
    }

    private class StudentWithNumber {
        private Integer i;
        private Student student;

        public StudentWithNumber(Integer i, Student student) {
            this.i = i;
            this.student = student;
        }

        public Integer getI() {
            return i;
        }

        public Student getStudent() {
            return student;
        }

        public void setI(Integer i) {
            this.i = i;
        }

        public void setStudent(Student student) {
            this.student = student;
        }

        @Override
        public String toString() {
            return "StudentWithNumber{" +
                    "i=" + i +
                    ", student=" + student +
                    '}';
        }
    }
}
