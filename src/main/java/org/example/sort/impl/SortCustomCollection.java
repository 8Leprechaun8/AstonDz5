package org.example.sort.impl;
import org.example.collection.*;
import org.example.entity.Student;

import java.util.Comparator;
import java.util.List;
import java.util.stream.StreamSupport;

public class SortCustomCollection {


    public <T> CustomCollection<T> sort(CustomCollection<T> collection, Comparator<T> comparator) {
        return StreamSupport.stream(collection.spliterator(), false)
                .sorted(comparator)
                .collect(CustomArrayList.toCustomArrayList());
    }



    public void printSortedStudents(CustomCollection<Student> sortedCollection) {
        System.out.println("---------");
        for (Student student : sortedCollection) {
            System.out.println(student);
        }
        System.out.println("---------");
    }

}