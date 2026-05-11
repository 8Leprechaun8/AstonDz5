package org.example.service;

import org.example.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> findAll();

    void appendSortedStudentsToTxtFile(List<Student> studentList,  String filePath);
}
