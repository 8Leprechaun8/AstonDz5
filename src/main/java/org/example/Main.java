package org.example;


import org.example.collection.*;
import org.example.entity.Student;
import org.example.exception.AverageGradeIsOutOfBoundsException;
import org.example.exception.RecordBookNumberIsFoundException;
import org.example.exception.RecordBookNumberIsInvalidException;
import org.example.multithreading.StudentCounter;
import org.example.repository.impl.FileManagerStudent;
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

                        if (studentList == null || studentList.isEmpty()) {
                            System.out.println("Сначала создайте список студентов (выберите 1, 2 или 3).");
                            break;
                        }

                        // Выбираем сортировку
                        System.out.println("Выберите критерий сортировки:");
                        System.out.println("1. По всем полям");
                        System.out.println("2. По среднему баллу");
                        System.out.println("3. По номеру группы");
                        System.out.println("4. По номеру зачётной книжки");
                        System.out.print("Введите номер: ");
                        int sortChoice = inStudent.nextInt();
                        inStudent.nextLine();

                        List<Student> sortedStudents;

                        // Сортируем студентов в зависимости от выбора
                        switch (sortChoice) {
                            case 1:
                                context.setSort(new SortByAllFields());
                                sortedStudents = context.sort(studentList);
                                break;
                            case 2:
                                context.setSort(new SortByAverageGrade());
                                sortedStudents = context.sort(studentList);
                                break;
                            case 3:
                                context.setSort(new SortByGroupNumber());
                                sortedStudents = context.sort(studentList);
                                break;
                            case 4:
                                context.setSort(new SortByRecordBookNumber());
                                sortedStudents = context.sort(studentList);
                                break;
                            default:
                                System.out.println("Некорректный выбор. Попробуйте ещё раз.");
                                continue; // Выбираем заново если ошибся
                        }

                        // Запрашиваем имя файла
                        System.out.print("Введите имя TXT файла (без расширения): ");
                        String fileName = inStudent.nextLine();
                        String filePath = fileName + ".txt";

                        // Записываем отсортированных студентов в файл
                        studentService.appendSortedStudentsToTxtFile(sortedStudents, filePath);
                        System.out.println("Отсортированные студенты записаны в файл: " + filePath);
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
                        System.out.println("(1 - groupNumber, 2 - averageGrade, 3 - recordBookNumber, 4 - student)");
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
                            case 4:
                                System.out.println("Введите student");
                                System.out.println("Введите groupNumber");
                                inStudent.nextLine();
                                groupNumberTarget = inStudent.nextLine();
                                System.out.println("Введите averageGrade");
                                averageGradeTarget = inStudent.nextDouble();
                                System.out.println("Введите recordBookNumber");
                                recordBookNumberTarget = inStudent.nextInt();
                                StudentCounter.countOccurencess(studentList, new Student(groupNumberTarget,
                                        averageGradeTarget, recordBookNumberTarget));
                                break;
                            default:
                                break;
                        }
                        break;
                    case 9:
                        if (studentList == null || studentList.isEmpty()) {
                            System.out.println("Сначала создайте список студентов (выберите 1, 2 или 3).");
                            break;
                        }

                        FileManagerStudent fileManager = FileManagerStudent.getInstance();

                        System.out.println("Выберите критерий поиска:");
                        System.out.println("1. По номеру группы");
                        System.out.println("2. По среднему баллу (>=)");
                        System.out.println("3. По номеру зачётной книжки");
                        System.out.print("Введите номер: ");
                        int searchChoice = inStudent.nextInt();
                        inStudent.nextLine(); // Очищаем буфер

                        List<Student> foundStudents = null;
                        String searchParameterFoundStudents = ""; // Для хранения параметра поиска (группа, балл, ID)

                        switch (searchChoice) {
                            case 1:
                                // Поиск по номеру группы
                                System.out.print("Введите номер группы: ");
                                searchParameterFoundStudents = inStudent.nextLine();
                                foundStudents = fileManager.findStudentsByGroupNumber(searchParameterFoundStudents);
                                break;
                            case 2:
                                // Поиск по среднему баллу
                                System.out.print("Введите минимальный средний балл: ");
                                double minGrade = inStudent.nextDouble();
                                inStudent.nextLine();
                                foundStudents = fileManager.findStudentsByAverageGrade(minGrade);
                                searchParameterFoundStudents = String.valueOf(minGrade); // Сохраняем балл как строку
                                break;
                            case 3:
                                // Поиск по номеру зачётной книжки
                                System.out.print("Введите номер зачётной книжки: ");
                                int recordBookNumber = inStudent.nextInt();
                                inStudent.nextLine();
                                foundStudents = fileManager.findStudentsByRecordBookNumber(recordBookNumber);
                                searchParameterFoundStudents = String.valueOf(recordBookNumber); // Сохраняем ID как строку
                                break;
                            default:
                                System.out.println("Некорректный выбор.");
                                break;
                        }

                        // Проверяем, есть ли найденные студенты
                        if (foundStudents != null && !foundStudents.isEmpty()) {
                            // Запрашиваем имя файла
                            System.out.print("Введите имя TXT файла (без расширения): ");
                            String fileNameToFound = inStudent.nextLine();
                            String filePathToFound = fileNameToFound + ".txt";

                            // Записываем найденных студентов в файл
                            studentService.appendFoundStudentsToTxtFile(foundStudents, filePathToFound);
                            System.out.println("Найденные студенты записаны в файл: " + filePathToFound);

                        } else {
                            System.out.println("Нет студентов, соответствующих критериям поиска.");
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