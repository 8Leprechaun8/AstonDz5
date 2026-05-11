package org.example.repository.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.Student;
import org.example.repository.StudentRepository;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class FileManagerStudent implements StudentRepository {

    private final static String FILE_STUDENT_TITLE = "student.txt";
    private final static String FILE_STUDENT_SEQUENCE_TITLE = "student_sequence.txt";

    private File fileStudent;

    private ObjectMapper objectMapper;

    private static FileManagerStudent instance;

    private FileManagerStudent() {
        objectMapper = new ObjectMapper();
        fileStudent = getFile(fileStudent, FILE_STUDENT_TITLE);
    }

    public static FileManagerStudent getInstance() {
        if (instance == null) {
            instance = new FileManagerStudent();
        }
        return instance;
    }

    @Override
    public Student saveStudent(Student student) {
        try {
            List<Student> studentList = objectMapper.readValue(fileStudent, new TypeReference<List<Student>>() {});
            if (!studentList.contains(student)) {
                studentList.add(student);
            }
            objectMapper.writeValue(fileStudent, studentList);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        return student;
    }

    @Override
    public List<Student> getStudentList() {
        List<Student> studentList = null;
        try {
            studentList = objectMapper.readValue(fileStudent, new TypeReference<List<Student>>() {});
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        return studentList;
    }

    // TODO: Методы поиска
    public List<Student> findStudentsByGroupNumber(String groupNumber, List<Student> studentList) {
        return studentList.stream()
                .filter(student -> student.getGroupNumber().equals(groupNumber))
                .collect(Collectors.toList());
    }

    public List<Student> findStudentsByAverageGrade(double minGrade, List<Student> studentList) {
        return studentList.stream()
                .filter(student -> student.getAverageGrade() >= minGrade)
                .collect(Collectors.toList());
    }

    public List<Student> findStudentsByRecordBookNumber(int recordBookNumber, List<Student> studentList) {
        return studentList.stream()
                .filter(student -> student.getRecordBookNumber() == recordBookNumber)
                .collect(Collectors.toList());
    }

    // TODO: Запись найденных студентов txt
    public void appendFoundStudentsToTxtFile(List<Student> students, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write("Найденные студенты:");
            writer.newLine();
            for (Student student : students) {
                String studentLine = String.format("%s, %.2f, %d",
                        student.getGroupNumber(),
                        student.getAverageGrade(),
                        student.getRecordBookNumber());
                writer.write(studentLine);
                writer.newLine();
            }
            System.out.println("Найденные студенты добавлены в TXT файл: " + filePath);
        } catch (IOException e) {
            System.err.println("Ошибка записи в TXT файл: " + e.getMessage());
        }
    }

    // TODO: Запись отсортированных студентов txt
    public void appendSortedStudentsToTxtFile(List<Student> students, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (Student student : students) {
                String studentLine = String.format("%s, %.2f, %d",
                        student.getGroupNumber(),
                        student.getAverageGrade(),
                        student.getRecordBookNumber());
                writer.write(studentLine);
                writer.newLine();
            }
            System.out.println("Отсортированные студенты добавлены в TXT файл: " + filePath);
        } catch (IOException e) {
            System.err.println("Ошибка записи в TXT файл: " + e.getMessage());
        }
    }

    // TODO Запись отсортированных студентов csv
    public void appendSortedStudentsToCsvFile(List<Student> students, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (Student student : students) {
                String csvLine = String.format("%s,%.2f,%d",
                        student.getGroupNumber(),
                        student.getAverageGrade(),
                        student.getRecordBookNumber());
                writer.write(csvLine);
                writer.newLine();
            }
            System.out.println("Отсортированные студенты добавлены в CSV файл: " + filePath);
        } catch (IOException e) {
            System.err.println("Ошибка записи в CSV файл: " + e.getMessage());
        }
    }

    // TODO: Запись найденных студентов csv
    public void appendFoundStudentsToCsvFile(List<Student> students, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (Student student : students) {
                String csvLine = String.format("%s,%.2f,%d",
                        student.getGroupNumber(),
                        student.getAverageGrade(),
                        student.getRecordBookNumber());
                writer.write(csvLine);
                writer.newLine();
            }
            System.out.println("Найденные студенты добавлены в CSV файл: " + filePath);
        } catch (IOException e) {
            System.err.println("Ошибка записи в CSV файл: " + e.getMessage());
        }
    }

    private File getFile(File file, String title) {
        if (file == null) {
            try {
                file = new File(title);
                if (file.createNewFile()) {
                    System.out.println("Файл " + title + " создан");
                    try {
                        FileWriter fileWriter = new FileWriter(title);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        if (FILE_STUDENT_SEQUENCE_TITLE.equals(title)) {
                            bufferedWriter.write("0");
                        } else {
                            bufferedWriter.write("[]");
                        }
                        bufferedWriter.close();
                    } catch (IOException ex) {
                        System.out.println("Неудачная запись в файл " + title);
                        ex.printStackTrace();
                    }
                } else {
                    System.out.println("Файл " + title + " уже существует");
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }

            init(file, title);

            return file;
        } else {
            return file;
        }
    }

    private void init(File file, String title) {
        Student st1 = new Student("GR1", 9.5, 100);
        Student st2 = new Student("GR8", 10.0, 145);
        Student st3 = new Student("GR4", 5.3, 10);
        Student st4 = new Student("GR7", 9.5, 67);
        Student st5 = new Student("GR4", 7.5, 17);

        List<Student> studentList = List.of(st1, st2, st3, st4, st5);

        try {
            objectMapper.writeValue(file, studentList);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
