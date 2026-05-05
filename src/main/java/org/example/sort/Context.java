package org.example.sort;

import org.example.entity.Student;

import java.util.List;

public class Context {

    private Sort sort;

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public void printAllSortedStudents(List<Student> studentList) {
        List<Student> studentListSorted = sort.sort(studentList);
        System.out.println("---------");
        for(Student student : studentListSorted) {
            System.out.println(student);
        }
        System.out.println("---------");
    }
}
