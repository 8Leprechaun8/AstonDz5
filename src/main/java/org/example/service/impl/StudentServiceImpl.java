package org.example.service.impl;

import org.example.entity.Student;
import org.example.repository.StudentRepository;
import org.example.repository.impl.FileManagerStudent;
import org.example.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private static StudentServiceImpl instance;

    private StudentRepository studentRepository;

    private StudentServiceImpl() {
        studentRepository = FileManagerStudent.getInstance();
    }

    public static StudentServiceImpl getInstance() {
        if (instance == null) {
            instance = new StudentServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.getStudentList();
    }

    @Override
    public void appendSortedStudentsToTxtFile(List<Student> studentList,  String filePath) {
        studentRepository.appendSortedStudentsToTxtFile(studentList,  filePath);
    }

    @Override
    public void appendFoundStudentsToTxtFile(List<Student> students, String filePath) {
        studentRepository.appendFoundStudentsToTxtFile(students, filePath);
    }

    public void appendSortedStudentsToCsvFile(List<Student> students, String filePath) {
        studentRepository.appendSortedStudentsToCsvFile(students, filePath);
    }

    public void appendFoundStudentsToCsvFile(List<Student> students, String filePath) {
        studentRepository.appendFoundStudentsToCsvFile(students, filePath);
    }
}
