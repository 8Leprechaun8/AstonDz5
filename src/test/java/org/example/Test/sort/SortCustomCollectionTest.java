package org.example.Test.sort;

import org.example.collection.CustomArrayList;
import org.example.collection.CustomCollection;
import org.example.entity.Student;
import org.example.sort.impl.SortCustomCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class SortCustomCollectionTest {
    private SortCustomCollection sorter;
    private CustomCollection<Student> studentCollection;

    @BeforeEach
    void setUp() {
        sorter = new SortCustomCollection();
        studentCollection = new CustomArrayList<>();
    }
    
    @Test
    void testSort_WithEmptyCollection_ShouldReturnEmptyCollection() {
        CustomCollection<Student> result = sorter.sort(studentCollection, Comparator.comparing(Student::getGroupNumber));

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void testSort_WithSingleStudent_ShouldReturnSameStudent() {
        Student student = new Student("GR1", 8.5, 100);
        studentCollection.add(student);

        CustomCollection<Student> result = sorter.sort(studentCollection, Comparator.comparing(Student::getGroupNumber));

        assertEquals(1, result.size());
        assertEquals(student.getGroupNumber(), result.get(0).getGroupNumber());
        assertEquals(student.getAverageGrade(), result.get(0).getAverageGrade());
        assertEquals(student.getRecordBookNumber(), result.get(0).getRecordBookNumber());
    }

    @Test
    void testSort_ByGroupNumber_Ascending() {
        Student student1 = new Student("GR5", 8.5, 100);
        Student student2 = new Student("GR2", 8.5, 200);
        Student student3 = new Student("GR8", 8.5, 300);
        Student student4 = new Student("GR1", 8.5, 400);

        studentCollection.add(student1);
        studentCollection.add(student2);
        studentCollection.add(student3);
        studentCollection.add(student4);

        CustomCollection<Student> result = sorter.sort(studentCollection, Comparator.comparing(Student::getGroupNumber));

        assertEquals(4, result.size());
        assertEquals("GR1", result.get(0).getGroupNumber());
        assertEquals("GR2", result.get(1).getGroupNumber());
        assertEquals("GR5", result.get(2).getGroupNumber());
        assertEquals("GR8", result.get(3).getGroupNumber());
    }

    @Test
    void testSort_ByAverageGrade_Ascending() {
        Student student1 = new Student("GR1", 9.5, 100);
        Student student2 = new Student("GR1", 7.5, 200);
        Student student3 = new Student("GR1", 8.5, 300);
        Student student4 = new Student("GR1", 6.5, 400);

        studentCollection.add(student1);
        studentCollection.add(student2);
        studentCollection.add(student3);
        studentCollection.add(student4);

        CustomCollection<Student> result = sorter.sort(studentCollection, Comparator.comparing(Student::getAverageGrade));

        assertEquals(6.5, result.get(0).getAverageGrade());
        assertEquals(7.5, result.get(1).getAverageGrade());
        assertEquals(8.5, result.get(2).getAverageGrade());
        assertEquals(9.5, result.get(3).getAverageGrade());
    }

    @Test
    void testSort_ByRecordBookNumber_Ascending() {
        Student student1 = new Student("GR1", 8.5, 500);
        Student student2 = new Student("GR1", 8.5, 200);
        Student student3 = new Student("GR1", 8.5, 800);
        Student student4 = new Student("GR1", 8.5, 100);

        studentCollection.add(student1);
        studentCollection.add(student2);
        studentCollection.add(student3);
        studentCollection.add(student4);

        CustomCollection<Student> result = sorter.sort(studentCollection, Comparator.comparing(Student::getRecordBookNumber));

        assertEquals(100, result.get(0).getRecordBookNumber());
        assertEquals(200, result.get(1).getRecordBookNumber());
        assertEquals(500, result.get(2).getRecordBookNumber());
        assertEquals(800, result.get(3).getRecordBookNumber());
    }

    @Test
    void testSort_ByGroupNumberReversed_Descending() {
        Student student1 = new Student("GR1", 8.5, 100);
        Student student2 = new Student("GR5", 8.5, 200);
        Student student3 = new Student("GR3", 8.5, 300);

        studentCollection.add(student1);
        studentCollection.add(student2);
        studentCollection.add(student3);

        CustomCollection<Student> result = sorter.sort(studentCollection,
                Comparator.comparing(Student::getGroupNumber).reversed());

        assertEquals("GR5", result.get(0).getGroupNumber());
        assertEquals("GR3", result.get(1).getGroupNumber());
        assertEquals("GR1", result.get(2).getGroupNumber());
    }

    @Test
    void testSort_ByAverageGradeReversed_Descending() {
        Student student1 = new Student("GR1", 6.5, 100);
        Student student2 = new Student("GR1", 9.5, 200);
        Student student3 = new Student("GR1", 7.5, 300);

        studentCollection.add(student1);
        studentCollection.add(student2);
        studentCollection.add(student3);

        CustomCollection<Student> result = sorter.sort(studentCollection,
                Comparator.comparing(Student::getAverageGrade).reversed());

        assertEquals(9.5, result.get(0).getAverageGrade());
        assertEquals(7.5, result.get(1).getAverageGrade());
        assertEquals(6.5, result.get(2).getAverageGrade());
    }

    @Test
    void testSort_ByMultipleFields_GroupThenGrade() {
        Student student1 = new Student("GR2", 8.5, 100);
        Student student2 = new Student("GR1", 9.5, 200);
        Student student3 = new Student("GR2", 7.5, 300);
        Student student4 = new Student("GR1", 8.5, 400);

        studentCollection.add(student1);
        studentCollection.add(student2);
        studentCollection.add(student3);
        studentCollection.add(student4);

        CustomCollection<Student> result = sorter.sort(studentCollection,
                Comparator.comparing(Student::getGroupNumber)
                        .thenComparing(Student::getAverageGrade));

        assertEquals("GR1", result.get(0).getGroupNumber());
        assertEquals(8.5, result.get(0).getAverageGrade());

        assertEquals("GR1", result.get(1).getGroupNumber());
        assertEquals(9.5, result.get(1).getAverageGrade());

        assertEquals("GR2", result.get(2).getGroupNumber());
        assertEquals(7.5, result.get(2).getAverageGrade());

        assertEquals("GR2", result.get(3).getGroupNumber());
        assertEquals(8.5, result.get(3).getAverageGrade());
    }

    @Test
    void testSort_ByMultipleFields_GroupThenGradeThenRecordBook() {
        Student student1 = new Student("GR1", 8.5, 300);
        Student student2 = new Student("GR2", 7.5, 200);
        Student student3 = new Student("GR1", 8.5, 100);
        Student student4 = new Student("GR1", 7.5, 400);
        Student student5 = new Student("GR2", 7.5, 500);

        studentCollection.add(student1);
        studentCollection.add(student2);
        studentCollection.add(student3);
        studentCollection.add(student4);
        studentCollection.add(student5);

        CustomCollection<Student> result = sorter.sort(studentCollection,
                Comparator.comparing(Student::getGroupNumber)
                        .thenComparing(Student::getAverageGrade)
                        .thenComparing(Student::getRecordBookNumber));

        // Группа GR1, оценка 7.5
        assertEquals("GR1", result.get(0).getGroupNumber());
        assertEquals(7.5, result.get(0).getAverageGrade());
        assertEquals(400, result.get(0).getRecordBookNumber());

        // Группа GR1, оценка 8.5, по номеру зачётки 100, затем 300
        assertEquals("GR1", result.get(1).getGroupNumber());
        assertEquals(8.5, result.get(1).getAverageGrade());
        assertEquals(100, result.get(1).getRecordBookNumber());

        assertEquals("GR1", result.get(2).getGroupNumber());
        assertEquals(8.5, result.get(2).getAverageGrade());
        assertEquals(300, result.get(2).getRecordBookNumber());

        // Группа GR2, оценка 7.5, по номеру зачётки 200, затем 500
        assertEquals("GR2", result.get(3).getGroupNumber());
        assertEquals(7.5, result.get(3).getAverageGrade());
        assertEquals(200, result.get(3).getRecordBookNumber());

        assertEquals("GR2", result.get(4).getGroupNumber());
        assertEquals(7.5, result.get(4).getAverageGrade());
        assertEquals(500, result.get(4).getRecordBookNumber());
    }

    @Test
    void testSort_WithNullGroupNumbers_NullsShouldBeFirst() {
        Student student1 = new Student("GR2", 8.5, 100);
        Student student2 = new Student(null, 8.5, 200);
        Student student3 = new Student("GR1", 8.5, 300);

        studentCollection.add(student1);
        studentCollection.add(student2);
        studentCollection.add(student3);

        CustomCollection<Student> result = sorter.sort(studentCollection,
                Comparator.nullsLast(Comparator.comparing(Student::getGroupNumber,
                        Comparator.nullsFirst(String::compareTo))));

        assertNull(result.get(0).getGroupNumber());
        assertEquals("GR1", result.get(1).getGroupNumber());
        assertEquals("GR2", result.get(2).getGroupNumber());
    }

    @Test
    void testSort_WithNullGroupNumbers_NullsShouldBeLast() {
        Student student1 = new Student("GR2", 8.5, 100);
        Student student2 = new Student(null, 8.5, 200);
        Student student3 = new Student("GR1", 8.5, 300);

        studentCollection.add(student1);
        studentCollection.add(student2);
        studentCollection.add(student3);

        CustomCollection<Student> result = sorter.sort(studentCollection,
                Comparator.nullsLast(Comparator.comparing(Student::getGroupNumber,
                        Comparator.nullsLast(String::compareTo))));

        assertEquals("GR1", result.get(0).getGroupNumber());
        assertEquals("GR2", result.get(1).getGroupNumber());
        assertNull(result.get(2).getGroupNumber());
    }

    @Test
    void testSort_WithNullAverageGrades_NullsHandledCorrectly() {
        Student student1 = new Student("GR1", 9.5, 100);
        Student student2 = new Student("GR1", null, 200);
        Student student3 = new Student("GR1", 7.5, 300);

        studentCollection.add(student1);
        studentCollection.add(student2);
        studentCollection.add(student3);

        CustomCollection<Student> result = sorter.sort(studentCollection,
                Comparator.nullsFirst(Comparator.comparing(Student::getAverageGrade,
                        Comparator.nullsFirst(Double::compareTo))));

        assertNull(result.get(0).getAverageGrade());
        assertEquals(7.5, result.get(1).getAverageGrade());
        assertEquals(9.5, result.get(2).getAverageGrade());
    }

    @Test
    void testSort_WithNullRecordBookNumbers_NullsHandledCorrectly() {
        Student student1 = new Student("GR1", 8.5, 300);
        Student student2 = new Student("GR1", 8.5, null);
        Student student3 = new Student("GR1", 8.5, 100);

        studentCollection.add(student1);
        studentCollection.add(student2);
        studentCollection.add(student3);

        CustomCollection<Student> result = sorter.sort(studentCollection,
                Comparator.nullsFirst(Comparator.comparing(Student::getRecordBookNumber,
                        Comparator.nullsFirst(Integer::compareTo))));

        assertNull(result.get(0).getRecordBookNumber());
        assertEquals(100, result.get(1).getRecordBookNumber());
        assertEquals(300, result.get(2).getRecordBookNumber());
    }

    @Test
    void testSort_WithDuplicateValues_ShouldMaintainRelativeOrder() {
        Student student1 = new Student("GR1", 8.5, 100);
        Student student2 = new Student("GR1", 8.5, 100);
        Student student3 = new Student("GR1", 8.5, 100);

        studentCollection.add(student1);
        studentCollection.add(student2);
        studentCollection.add(student3);

        CustomCollection<Student> result = sorter.sort(studentCollection,
                Comparator.comparing(Student::getGroupNumber));

        // Проверяем, что все элементы присутствуют
        assertEquals(3, result.size());

        // Проверяем, что порядок стабильный (элементы с одинаковыми ключами сохраняют порядок)
        assertSame(student1, result.get(0));
        assertSame(student2, result.get(1));
        assertSame(student3, result.get(2));
    }

    @Test
    void testSort_OriginalCollectionShouldNotBeModified() {
        Student student1 = new Student("GR2", 8.5, 100);
        Student student2 = new Student("GR1", 8.5, 200);

        studentCollection.add(student1);
        studentCollection.add(student2);

        // Создаём копию оригинальной коллекции для сравнения
        CustomCollection<Student> originalCopy = new CustomArrayList<>();
        originalCopy.add(student1);
        originalCopy.add(student2);

        CustomCollection<Student> result = sorter.sort(studentCollection,
                Comparator.comparing(Student::getGroupNumber));

        // Проверяем, что результат отсортирован
        assertEquals("GR1", result.get(0).getGroupNumber());
        assertEquals("GR2", result.get(1).getGroupNumber());

        // Проверяем, что оригинальная коллекция не изменилась
        assertEquals("GR2", studentCollection.get(0).getGroupNumber());
        assertEquals("GR1", studentCollection.get(1).getGroupNumber());
    }

    @Test
    void testSort_WithLargeCollection_ShouldSortCorrectly() {
        // Добавляем 100 студентов в обратном порядке
        for (int i = 100; i > 0; i--) {
            studentCollection.add(new Student("GR" + i, i % 10 + 5.0, i));
        }

        CustomCollection<Student> result = sorter.sort(studentCollection,
                Comparator.comparing(Student::getGroupNumber));

        assertEquals(100, result.size());

        // Проверяем, что результат отсортирован
        for (int i = 0; i < result.size() - 1; i++) {
            String current = result.get(i).getGroupNumber();
            String next = result.get(i + 1).getGroupNumber();
            assertTrue(current.compareTo(next) <= 0,
                    "Collection should be sorted: " + current + " > " + next);
        }
    }

    @Test
    void testSort_WithCustomComparator_StringLength() {
        Student student1 = new Student("GR123", 8.5, 100);
        Student student2 = new Student("GR1", 8.5, 200);
        Student student3 = new Student("GR45", 8.5, 300);

        studentCollection.add(student1);
        studentCollection.add(student2);
        studentCollection.add(student3);

        // Сортируем по длине строки группы
        CustomCollection<Student> result = sorter.sort(studentCollection,
                Comparator.comparing(s -> s.getGroupNumber().length()));

        assertEquals(3, result.size());
        assertEquals(3, result.get(0).getGroupNumber().length()); // "GR1" -> 3
        assertEquals(4, result.get(1).getGroupNumber().length()); // "GR45" -> 4
        assertEquals(5, result.get(2).getGroupNumber().length()); // "GR123" -> 5
    }

    @Test
    void testSort_WithNumericGroupNames_ShouldSortLexicographically() {
        Student student1 = new Student("GR100", 8.5, 100);
        Student student2 = new Student("GR20", 8.5, 200);
        Student student3 = new Student("GR3", 8.5, 300);

        studentCollection.add(student1);
        studentCollection.add(student2);
        studentCollection.add(student3);

        CustomCollection<Student> result = sorter.sort(studentCollection,
                Comparator.comparing(Student::getGroupNumber));

        // Лексикографическая сортировка (как строки)
        assertEquals("GR100", result.get(0).getGroupNumber());
        assertEquals("GR20", result.get(1).getGroupNumber());
        assertEquals("GR3", result.get(2).getGroupNumber());
    }

    @Test
    void testSort_WithMixedCaseGroupNames_ShouldSortByASCII() {
        Student student1 = new Student("gr2", 8.5, 100);
        Student student2 = new Student("GR1", 8.5, 200);
        Student student3 = new Student("Gr3", 8.5, 300);

        studentCollection.add(student1);
        studentCollection.add(student2);
        studentCollection.add(student3);

        CustomCollection<Student> result = sorter.sort(studentCollection,
                Comparator.comparing(Student::getGroupNumber));

        // Заглавные буквы имеют меньшие ASCII коды
        assertEquals("GR1", result.get(0).getGroupNumber());
        assertEquals("Gr3", result.get(1).getGroupNumber());
        assertEquals("gr2", result.get(2).getGroupNumber());
    }

    @Test
    void testPrintSortedStudents_ShouldNotThrowException() {
        Student student1 = new Student("GR2", 8.5, 100);
        Student student2 = new Student("GR1", 8.5, 200);

        studentCollection.add(student1);
        studentCollection.add(student2);

        CustomCollection<Student> sorted = sorter.sort(studentCollection,
                Comparator.comparing(Student::getGroupNumber));

        // Проверяем, что метод не выбрасывает исключение
        assertDoesNotThrow(() -> sorter.printSortedStudents(sorted));
    }

    @Test
    void testPrintSortedStudents_WithEmptyCollection_ShouldNotThrowException() {
        assertDoesNotThrow(() -> sorter.printSortedStudents(studentCollection));
    }

    @Test
    void testSort_WithNegativeAverageGrades_ShouldSortCorrectly() {
        Student student1 = new Student("GR1", -5.5, 100);
        Student student2 = new Student("GR1", 0.0, 200);
        Student student3 = new Student("GR1", -2.5, 300);

        studentCollection.add(student1);
        studentCollection.add(student2);
        studentCollection.add(student3);

        CustomCollection<Student> result = sorter.sort(studentCollection,
                Comparator.comparing(Student::getAverageGrade));

        assertEquals(-5.5, result.get(0).getAverageGrade());
        assertEquals(-2.5, result.get(1).getAverageGrade());
        assertEquals(0.0, result.get(2).getAverageGrade());
    }

    @Test
    void testSort_WithBoundaryIntegerValues_RecordBookNumbers() {
        Student student1 = new Student("GR1", 8.5, Integer.MAX_VALUE);
        Student student2 = new Student("GR1", 8.5, Integer.MIN_VALUE);
        Student student3 = new Student("GR1", 8.5, 0);

        studentCollection.add(student1);
        studentCollection.add(student2);
        studentCollection.add(student3);

        CustomCollection<Student> result = sorter.sort(studentCollection,
                Comparator.comparing(Student::getRecordBookNumber));

        assertEquals(Integer.MIN_VALUE, result.get(0).getRecordBookNumber());
        assertEquals(0, result.get(1).getRecordBookNumber());
        assertEquals(Integer.MAX_VALUE, result.get(2).getRecordBookNumber());
    }

    @Test
    void testSort_WithComplexComparator_MultipleFieldsWithNulls() {
        Student student1 = new Student(null, 9.5, 100);
        Student student2 = new Student("GR2", null, 200);
        Student student3 = new Student("GR1", 8.5, null);
        Student student4 = new Student("GR1", 8.5, 300);
        Student student5 = new Student("GR2", 7.5, 400);
        Student student6 = new Student(null, 9.0, 500);

        studentCollection.add(student1);
        studentCollection.add(student2);
        studentCollection.add(student3);
        studentCollection.add(student4);
        studentCollection.add(student5);
        studentCollection.add(student6);

        CustomCollection<Student> result = sorter.sort(studentCollection,
                Comparator.nullsFirst(Comparator.comparing(Student::getGroupNumber,
                                Comparator.nullsFirst(String::compareTo)))
                        .thenComparing(Comparator.nullsFirst(Comparator.comparing(Student::getAverageGrade,
                                Comparator.nullsFirst(Double::compareTo))))
                        .thenComparing(Comparator.nullsFirst(Comparator.comparing(Student::getRecordBookNumber,
                                Comparator.nullsFirst(Integer::compareTo)))));

        // Проверяем, что null группы в начале
        assertNull(result.get(0).getGroupNumber());
        assertNull(result.get(1).getGroupNumber());

        // Затем группа GR1
        assertEquals("GR1", result.get(2).getGroupNumber());
        assertNull(result.get(2).getRecordBookNumber()); // null record book

        assertEquals("GR1", result.get(3).getGroupNumber());
        assertEquals(300, result.get(3).getRecordBookNumber()); // 300 record book

        // Затем группа GR2
        assertEquals("GR2", result.get(4).getGroupNumber());
        assertNull(result.get(4).getAverageGrade()); // null grade

        assertEquals("GR2", result.get(5).getGroupNumber());
        assertEquals(400, result.get(5).getRecordBookNumber()); // 400 record book
    }

    @Test
    void testSort_ResultTypeIsCustomArrayList() {
        Student student1 = new Student("GR2", 8.5, 100);
        Student student2 = new Student("GR1", 8.5, 200);

        studentCollection.add(student1);
        studentCollection.add(student2);

        CustomCollection<Student> result = sorter.sort(studentCollection,
                Comparator.comparing(Student::getGroupNumber));

        assertInstanceOf(CustomArrayList.class, result);
    }

    @Test
    void testSort_WithSameValues_DifferentObjects() {
        Student student1 = new Student("GR1", 8.5, 100);
        Student student2 = new Student("GR1", 8.5, 100);

        studentCollection.add(student1);
        studentCollection.add(student2);

        CustomCollection<Student> result = sorter.sort(studentCollection,
                Comparator.comparing(Student::getGroupNumber)
                        .thenComparing(Student::getAverageGrade)
                        .thenComparing(Student::getRecordBookNumber));

        assertEquals(2, result.size());
        // Проверяем, что оба студента присутствуют (могут быть разными объектами с одинаковыми значениями)
        assertNotSame(student1, student2);
    }
}

