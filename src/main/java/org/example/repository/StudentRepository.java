package org.example.repository;

import org.example.entity.Student;

import java.util.List;

public interface StudentRepository {

    Student saveStudent(Student student);

    List<Student> getStudentList();

    void appendSortedStudentsToTxtFile(List<Student> studentList,  String filePath);

    void appendFoundStudentsToTxtFile(List<Student> students, String filePath);
}
