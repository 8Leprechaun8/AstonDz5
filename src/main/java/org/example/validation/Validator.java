package org.example.validation;

import org.example.entity.Student;
import org.example.exception.AverageGradeIsOutOfBoundsException;
import org.example.exception.RecordBookNumberIsFoundException;
import org.example.exception.RecordBookNumberIsInvalidException;

import java.util.List;

public interface Validator {

    void validateRecordBookNumber(Student student, List<Student> studentList)
            throws RecordBookNumberIsFoundException, RecordBookNumberIsInvalidException;

    void validateAverageGrade(Student student) throws AverageGradeIsOutOfBoundsException;
}
