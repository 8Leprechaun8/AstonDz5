package org.example.Test.validate;

import org.example.entity.Student;
import org.example.exception.AverageGradeIsOutOfBoundsException;
import org.example.exception.RecordBookNumberIsFoundException;
import org.example.exception.RecordBookNumberIsInvalidException;
import org.example.validation.Validator;
import org.example.validation.impl.ValidatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ValidatorTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        validator = ValidatorImpl.getInstance();
    }

    @Test
    void validateRecordBookNumber_NewStudentWithUniqueRecordBookNumber_ShouldNotThrowException() {
        Student student = new Student("G1", 5.0, 100);
        List<Student> existingStudents = List.of(
                new Student("G2", 7.9, 112),
                new Student("G3", 8.0, 113)
        );


        assertDoesNotThrow(() ->
                validator.validateRecordBookNumber(student, existingStudents, false)
        );

    }

    @Test
    void validateRecordBookNumber_FromFileWithSameRecordBookNumber_ShouldNotThrowException() {
        Student student = new Student("G1", 6.0, 113);
        List<Student> existingStudents = List.of(
                new Student("G1", 6.0, 113),
                new Student("G3", 5.7, 115)
        );


        assertDoesNotThrow(() ->
                validator.validateRecordBookNumber(student, existingStudents, true)
        );
    }

    @Test
    void validateRecordBookNumber_DuplicateRecordBookNumberNewStudent_ShouldThrowException() {
        Student student = new Student("G2", 4.4, 111);
        List<Student> existingStudents = List.of(
                new Student("G3", 5.0, 111),
                new Student("G4", 4.5, 112)
        );

        assertThrows(RecordBookNumberIsFoundException.class, () ->
                validator.validateRecordBookNumber(student, existingStudents, false)
        );
    }

    @Test
    void validateRecordBookNumber_FromFileWithDuplicateRecordBookNumber_ShouldThrowException() {
        Student student = new Student("G1", 7.7, 100);
        List<Student> existingStudents = List.of(
                new Student("G1", 7.5, 100),
                new Student("G1", 8.7, 100)
        );

        assertThrows(RecordBookNumberIsFoundException.class, () ->
                validator.validateRecordBookNumber(student, existingStudents, true)
        );
    }

    @Test
    void validateRecordBookNumber_NegativeRecordBookNumber_ShouldThrowException() {
        Student student = new Student("G1", 7.6, -5);
        List<Student> existingStudents = List.of();

        assertThrows(RecordBookNumberIsInvalidException.class, () ->
                validator.validateRecordBookNumber(student, existingStudents, false)
        );
    }

    @Test
    void validateRecordBookNumber_ZeroRecordBookNumber_ShouldBeValid() {
        Student student = new Student("G1", 8.0, 0);
        List<Student> existingStudents = List.of();

        assertDoesNotThrow(() ->
                validator.validateRecordBookNumber(student, existingStudents, false)
        );

    }

    @Test
    void validateAverageGrade_ValidGrade_ShouldNotThrowException() {
        Student student = new Student("G1", 7.5, 111);

        assertDoesNotThrow(() -> validator.validateAverageGrade(student));
    }

    @Test
    void validateAverageGrade_BoundaryMinGrade_ShouldNotThrowException() {
        Student student = new Student("G1", 0.0, 111);

        assertDoesNotThrow(() -> validator.validateAverageGrade(student));
    }

    @Test
    void validateAverageGrade_BoundaryMaxGrade_ShouldNotThrowException() {
        Student student = new Student("G1", 10.0, 111);

        assertDoesNotThrow(() -> validator.validateAverageGrade(student));
    }

    @Test
    void validateAverageGrade_NegativeGrade_ShouldThrowException() {
        Student student = new Student("G1", -0.1, 111);

        assertThrows(AverageGradeIsOutOfBoundsException.class,
                () -> validator.validateAverageGrade(student)
        );
    }

    @Test
    void validateAverageGrade_GradeAboveMax_ShouldThrowException() {
        Student student = new Student("G1", 10.1, 111);

        assertThrows(AverageGradeIsOutOfBoundsException.class,
                () -> validator.validateAverageGrade(student)
        );
    }

    @Test
    void validateAverageGrade_VeryHighGrade_ShouldThrowException() {
        Student student = new Student("G1", 100.0, 111);

        assertThrows(AverageGradeIsOutOfBoundsException.class,
                () -> validator.validateAverageGrade(student)
        );
    }

    @Test
    void getInstance_ShouldReturnSameInstance() {
        ValidatorImpl instance1 = ValidatorImpl.getInstance();
        ValidatorImpl instance2 = ValidatorImpl.getInstance();

        assertSame(instance1, instance2, "Должен возвращать один и тот же экземпляр");
    }

}
