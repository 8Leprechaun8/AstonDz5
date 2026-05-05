package org.example.validation.impl;

import org.example.entity.Student;
import org.example.exception.AverageGradeIsOutOfBoundsException;
import org.example.exception.RecordBookNumberIsFoundException;
import org.example.exception.RecordBookNumberIsInvalidException;
import org.example.validation.Validator;

import java.util.List;

public class ValidatorImpl implements Validator {

    private static ValidatorImpl instance;

    private ValidatorImpl() {
    }

    public static ValidatorImpl getInstance() {
        if (instance == null) {
            instance = new ValidatorImpl();
        }
        return instance;
    }

    @Override
    public void validateRecordBookNumber(Student student, List<Student> studentList, boolean isFromFile)
            throws RecordBookNumberIsFoundException, RecordBookNumberIsInvalidException {
        Integer id = student.getRecordBookNumber();
        long n = studentList.stream()
                .filter(s -> id.equals(s.getRecordBookNumber()))
                .count();
        if (!isFromFile && (n > 0) || isFromFile && (n > 1)) {
            throw new RecordBookNumberIsFoundException("Номер зачетной книжки не уникален");
        }
        if (id < 0) {
            throw new RecordBookNumberIsInvalidException("Номер зачетной книжки не является валидным");
        }
    }

    @Override
    public void validateAverageGrade(Student student) throws AverageGradeIsOutOfBoundsException {
        Double grade = student.getAverageGrade();
        if (grade < 0 || grade > 10) {
            throw new AverageGradeIsOutOfBoundsException("Средний балл студента выходит за пределы интервала [0; 10]");
        }
    }
}
