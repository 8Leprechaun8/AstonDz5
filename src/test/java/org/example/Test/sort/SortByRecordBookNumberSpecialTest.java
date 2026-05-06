package org.example.Test.sort;


import org.example.entity.Student;
import org.example.sort.impl.SortByRecordBookNumberSpecial;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class SortByRecordBookNumberSpecialTest {
    private SortByRecordBookNumberSpecial sorter;

    @BeforeEach
    public void setUp() throws Exception {
        sorter = new SortByRecordBookNumberSpecial();

    }

    @Test
    void sort_WithOddAndEvenNumbers_ShouldKeepOddPositionsAndSortEven() {
        List<Student> students = List.of(
                new Student("G1", 1.1, 5),  // 0 нечет
                new Student("G1", 1.2, 4),  // 1 чет
                new Student("G1", 4.5, 7),  // 2 нечет
                new Student("G1", 3.3, 2),  // 3 чет
                new Student("G1", 3.6, 1)   // 4 нечет
        );

        List<Student> result = sorter.sort(students);
        // 5, 7 , 1
        assertEquals(5, result.get(0).getRecordBookNumber());
        assertEquals(7, result.get(2).getRecordBookNumber());
        assertEquals(1, result.get(4).getRecordBookNumber());


        // 2 , 4
        assertEquals(2, result.get(1).getRecordBookNumber());
        assertEquals(4, result.get(3).getRecordBookNumber());
    }

    @Test
    void sort_WithOnlyEvenNumbers_ShouldSortAscending() {
        List<Student> students = List.of(
                new Student("G1", 10.0, 4), // 0
                new Student("G1", 10.0, 2), // 1
                new Student("G1", 10.0, 6), // 2
                new Student("G1", 10.0, 0) // 3
        );

        List<Student> result = sorter.sort(students);
        // 0 , 2 , 4 , 6
        assertEquals(0, result.get(0).getRecordBookNumber());
        assertEquals(2, result.get(1).getRecordBookNumber());
        assertEquals(4, result.get(2).getRecordBookNumber());
        assertEquals(6, result.get(3).getRecordBookNumber());
    }

    @Test
    void sort_WithMixedEvenNumbers_ShouldSortEvenCorrectly() {
        List<Student> students = List.of(
                new Student("G1", 10.0, 1), // 0 нечет
                new Student("G1", 10.0, 6), // 1 чет
                new Student("G1", 10.0, 4), // 2 чет
                new Student("G1", 10.0, 11), // 3 нечет
                new Student("G1", 10.0, 3), // 4 нечет
                new Student("G1", 10.0, 2) // 5 чет
        );

        List<Student> result = sorter.sort(students);

        // 1, 11, 3
        assertEquals(1, result.get(0).getRecordBookNumber());
        assertEquals(11, result.get(3).getRecordBookNumber());
        assertEquals(3, result.get(4).getRecordBookNumber());

        // 2, 4, 6
        assertEquals(2, result.get(1).getRecordBookNumber());
        assertEquals(4, result.get(2).getRecordBookNumber());
        assertEquals(6, result.get(5).getRecordBookNumber());
    }

    @Test
    void sort_WithEmptyListStudents_ShouldReturnEmptyList() {
        List<Student> students = new ArrayList<>();

        List<Student> result = sorter.sort(students);

        assertTrue(result.isEmpty());
    }

    @Test
    void sort_WithOneStudent_OddNumber_ShouldReturnSomeList() {
        List<Student> students = List.of(
                new Student("G1", 0.0, 1)
        );

        List<Student> result = sorter.sort(students);

        assertEquals(students, result);
    }

    @Test
    void sort_WithOneStudent_EvenNumber_ShouldReturnSameList() {
        // given
        List<Student> students = List.of(
                new Student("G1", 0.0, 2)
        );

        // when
        List<Student> result = sorter.sort(students);

        // then
        assertEquals(students, result);
    }

    @Test
    void sort_WithNegativeEvenNumbers_ShouldSortCorrectly() {
        List<Student> students = List.of(
                new Student("G1", 1.1, 1), // 0 нечет
                new Student("G1", 1.1, -2), // 1 чет
                new Student("G1", 1.1, 5), // 2 нечет
                new Student("G1", 1.1, -4), // 3 чет
                new Student("G1", 1.1, 7) // 4 нечет
        );

        List<Student> result = sorter.sort(students);

        assertEquals(-4, result.get(1).getRecordBookNumber());
        assertEquals(-2, result.get(3).getRecordBookNumber());

    }

    @Test
    void sort_WithZeroAsEvenNumber_ShouldSortCorrectly() {
        List<Student> students = List.of(
                new Student("G1", 1.1, 3), // 0
                new Student("G1", 1.1, 4), // 1
                new Student("G1", 1.1, 5), // 2
                new Student("G1", 1.1, 0), // 3
                new Student("G1", 1.1, 7) // 4
        );

        List<Student> result = sorter.sort(students);

        assertEquals(0, result.get(1).getRecordBookNumber());
        assertEquals(4, result.get(3).getRecordBookNumber());
    }

    @Test
    void sort_ShouldPreserveOriginalList() {
        List<Student> original = List.of(
                new Student("G1", 1.1, 5), // 0
                new Student("G1", 1.1, 2), // 1
                new Student("G1", 1.1, 7) // 2
        );

        List<Student> originalCopy = new ArrayList<>(original);

        sorter.sort(original);

        assertEquals(original, originalCopy);

    }

    @Test
    void sort_WithLargeDataSet_ShouldCompleteInReasonableTime() {
        // Проверка на скорость выполнения сортировки
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            students.add(new Student("G1", 1.1, i));
        }

        long startTime = System.currentTimeMillis();
        List<Student> result = sorter.sort(students);
        long endTime = System.currentTimeMillis();

        assertNotNull(result);
        assertEquals(1000, result.size());
        assertTrue(endTime - startTime < 1000, "Сортировка должна выполняться быстро");
    }
}
