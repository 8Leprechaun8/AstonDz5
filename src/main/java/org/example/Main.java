package org.example;


import org.example.collection.*;
import org.example.entity.Student;
import org.example.exception.AverageGradeIsOutOfBoundsException;
import org.example.exception.RecordBookNumberIsFoundException;
import org.example.exception.RecordBookNumberIsInvalidException;
import org.example.multithreading.StudentCounter;
import org.example.service.StudentService;
import org.example.service.impl.StudentServiceImpl;
import org.example.sort.Context;
import org.example.sort.impl.*;
import org.example.validation.Validator;
import org.example.validation.impl.ValidatorImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        StudentService studentService = StudentServiceImpl.getInstance();
        Validator validator = ValidatorImpl.getInstance();
        List<Student> studentList = null;
        Context context = new Context();


        Scanner inStudent = new Scanner(System.in);
        mainMenu:
        while (true) {
            try {
                System.out.println("Введите способ, которым будет происходить ввод данных:\n" +
                        "1. Вручную\n" +
                        "2. Рандом\n" +
                        "3. Из файла\n" +
                        "0. Выход\n");
                int num1 = inStudent.nextInt();
                switch (num1) {
                    case 1:
                        System.out.println("Введите количество студентов: ");
                        int n = inStudent.nextInt();
                        studentList = new ArrayList<>();
                        for (int i = 0; i < n; ++i) {
                            System.out.println("---");
                            System.out.println("Студент " + (i + 1) + ": ");
                            System.out.println("groupNumber:");
                            inStudent.nextLine();
                            String groupNumber = inStudent.nextLine();
                            System.out.println("averageGrade:");
                            Double averageGrade = inStudent.nextDouble();
                            System.out.println("recordBookNumber:");
                            Integer recordBookNumber = inStudent.nextInt();
                            System.out.println("---");
                            Student student = (new Student.StudentBuilder())
                                    .groupNumber(groupNumber)
                                    .averageGrade(averageGrade)
                                    .recordBookNumber(recordBookNumber)
                                    .build();
                            validator.validateAverageGrade(student);
                            validator.validateRecordBookNumber(student, studentList, false);
                            studentList.add(student);
                        }
                        break;
                    case 2:
                        System.out.println("Введите количество студентов: ");
                        n = inStudent.nextInt();
                        studentList = new ArrayList<>();
                        for (int i = 0; i < n; ++i) {
                            String groupNumber = "GR" + (int) (Math.random() * 9 + 1);
                            Double averageGrade = Math.random() * 10;
                            Integer recordBookNumber = i;
                            Student student = (new Student.StudentBuilder())
                                    .groupNumber(groupNumber)
                                    .averageGrade(averageGrade)
                                    .recordBookNumber(recordBookNumber)
                                    .build();
                            validator.validateAverageGrade(student);
                            validator.validateRecordBookNumber(student, studentList, false);
                            studentList.add(student);
                            System.out.println("---");
                            System.out.println(student);
                            System.out.println("---");
                        }
                        break;
                    case 3:
                        studentList = studentService.findAll();
                        for (Student student : studentList) {
                            validator.validateAverageGrade(student);
                            validator.validateRecordBookNumber(student, studentList, true);
                        }
                        break;
                    case 0:
                    default:
                        break mainMenu;
                }
                System.out.println(
                        "======== Выберите действие: ========\n" +
                                "1. Сортировка по трем полям одновременно\n" +
                                "2. Сортировка по groupNumber\n" +
                                "3. Сортировка по averageGrade\n" +
                                "4. Сортировка по recordBookNumber\n" +
                                "5. Сортировка по recordBookNumber (Дополнительное задание 1)\n" +
                                "6. Запись отсортированных коллекций/найденных значений в файл в режиме добавления данных (Дополнительное задание 2)\n" +
                                "7. Заполнение коллекций посредством стримов (Дополнительное задание 3)\n" +
                                "8. Многопоточный метод, подсчитывающий количество вхождений элемента N в коллекцию и выводящий результат в консоль. (Дополнительное задание 4)\n" +
                                "0. Выход\n" +
                                "Выберите действие:");
                int num2 = inStudent.nextInt();
                switch (num2) {
                    case 1:
                        context.setSort(new SortByAllFields());
                        context.printAllSortedStudents(studentList);
                        break;
                    case 2:
                        context.setSort(new SortByGroupNumber());
                        context.printAllSortedStudents(studentList);
                        break;
                    case 3:
                        context.setSort(new SortByAverageGrade());
                        context.printAllSortedStudents(studentList);
                        break;
                    case 4:
                        context.setSort(new SortByRecordBookNumber());
                        context.printAllSortedStudents(studentList);
                        break;
                    case 5:
                        context.setSort(new SortByRecordBookNumberSpecial());
                        context.printAllSortedStudents(studentList);
                        break;
                    case 6:
                        //ToDo
                        break;
                    case 7:
                        Stream<Student> studentStream = studentList.stream();
                        CustomArrayList<Student> customList = studentStream.collect(CustomArrayList.toCustomArrayList());
                        SortCustomCollection sorter = new SortCustomCollection();
                        CustomCollection<Student> sorted = sorter.sort(customList, Comparator.comparing(Student::getAverageGrade));
                        sorter.printSortedStudents(sorted);
                        break;
                    case 8:
                        System.out.println("Выберите, поиск по какому элементу производится");
                        System.out.println("(1 - groupNumber, 2 - averageGrade, 3 - recordBookNumber)");
                        int num3 = inStudent.nextInt();
                        switch (num3) {
                            case 1:
                                System.out.println("Введите groupNumber");
                                inStudent.nextLine();
                                String groupNumberTarget = inStudent.nextLine();
                                StudentCounter.countOccurencess(studentList, groupNumberTarget);
                                break;
                            case 2:
                                System.out.println("Введите averageGrade");
                                Double averageGradeTarget = inStudent.nextDouble();
                                StudentCounter.countOccurencess(studentList, averageGradeTarget);
                                break;
                            case 3:
                                System.out.println("Введите recordBookNumber");
                                Integer recordBookNumberTarget = inStudent.nextInt();
                                StudentCounter.countOccurencess(studentList, recordBookNumberTarget);
                                break;
                            default:
                                break;
                        }
                        break;
                    case 0:
                    default:
                        break mainMenu;
                }
            } catch (AverageGradeIsOutOfBoundsException |
                     RecordBookNumberIsFoundException |
                     RecordBookNumberIsInvalidException exception) {
                System.out.println(exception.getMessage());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}