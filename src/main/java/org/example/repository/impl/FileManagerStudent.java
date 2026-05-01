package org.example.repository.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.Student;
import org.example.repository.StudentRepository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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
