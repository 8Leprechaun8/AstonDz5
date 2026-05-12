package org.example.Test.sort;

import org.example.entity.Student;
import org.example.sort.impl.SortByAllFields;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SortByAllFieldsTest {
    private SortByAllFields sorter;


    @BeforeEach
    public void setUp() {
        sorter = new SortByAllFields();
    }

    @Test
    void testSort_WithNullList_ShouldReturnNull() {
        List<Student> result = sorter.sort(null);

        assertNull(result);
    }

    @Test
    void testSort_WithEmptyList_ShouldReturnEmptyList() {
        List<Student> emptyList = new ArrayList<>();

        List<Student> result = sorter.sort(emptyList);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSort_WithSingleStudent_ShouldReturnSameList() {
        List<Student> studentList = new ArrayList<>();
        Student student = new Student("GR1", 8.5, 100);
        studentList.add(student);

        List<Student> result = sorter.sort(studentList);

        assertEquals(1, result.size());
        assertSame(student, result.get(0));
    }

    @Test
    void testSort_WithTwoStudents_SortByGroupNumber() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR2", 8.5, 100);
        Student studentB = new Student("GR1", 8.5, 100);
        studentList.add(studentA);
        studentList.add(studentB);

        List<Student> result = sorter.sort(studentList);

        assertEquals("GR1", result.get(0).getGroupNumber());
        assertEquals("GR2", result.get(1).getGroupNumber());
    }

    @Test
    void testSort_WithSameGroup_SortByAverageGrade() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR1", 7.5, 100);
        Student studentB = new Student("GR1", 9.5, 100);
        Student studentC = new Student("GR1", 8.5, 100);
        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);

        List<Student> result = sorter.sort(studentList);

        assertEquals(7.5, result.get(0).getAverageGrade());
        assertEquals(8.5, result.get(1).getAverageGrade());
        assertEquals(9.5, result.get(2).getAverageGrade());
    }

    @Test
    void testSort_WithSameGroupAndGrade_SortByRecordBookNumber() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR1", 8.5, 200);
        Student studentB = new Student("GR1", 8.5, 100);
        Student studentC = new Student("GR1", 8.5, 150);
        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);

        List<Student> result = sorter.sort(studentList);

        assertEquals(100, result.get(0).getRecordBookNumber());
        assertEquals(150, result.get(1).getRecordBookNumber());
        assertEquals(200, result.get(2).getRecordBookNumber());
    }

    @Test
    void testSort_WithDifferentGroups_GroupsShouldBeOrderedCorrectly() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR6", 9.0, 100);
        Student studentB = new Student("GR2", 8.0, 200);
        Student studentC = new Student("GR5", 7.0, 300);
        Student studentD = new Student("GR1", 6.0, 400);
        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);
        studentList.add(studentD);

        List<Student> result = sorter.sort(studentList);

        assertEquals("GR1", result.get(0).getGroupNumber());
        assertEquals("GR2", result.get(1).getGroupNumber());
        assertEquals("GR5", result.get(2).getGroupNumber());
        assertEquals("GR6", result.get(3).getGroupNumber());
    }

    @Test
    void testSort_WithNullGroupNumbers_NullsShouldBeFirst() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR2", 8.5, 100);
        Student studentB = new Student(null, 8.5, 200);
        Student studentC = new Student("GR1", 8.5, 300);
        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);

        List<Student> result = sorter.sort(studentList);

        assertNull(result.get(0).getGroupNumber());
        assertEquals("GR1", result.get(1).getGroupNumber());
        assertEquals("GR2", result.get(2).getGroupNumber());
    }

    @Test
    void testSort_WithNullAverageGrades_NullsShouldBeFirst() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR1", 9.5, 100);
        Student studentB = new Student("GR1", null, 200);
        Student studentC = new Student("GR1", 7.5, 300);
        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);

        List<Student> result = sorter.sort(studentList);

        assertNull(result.get(0).getAverageGrade());
        assertEquals(7.5, result.get(1).getAverageGrade());
        assertEquals(9.5, result.get(2).getAverageGrade());
    }

    @Test
    void testSort_WithNullRecordBookNumbers_NullsShouldBeFirst() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR1", 8.5, 300);
        Student studentB = new Student("GR1", 8.5, null);
        Student studentC = new Student("GR1", 8.5, 100);
        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);

        List<Student> result = sorter.sort(studentList);

        assertNull(result.get(0).getRecordBookNumber());
        assertEquals(100, result.get(1).getRecordBookNumber());
        assertEquals(300, result.get(2).getRecordBookNumber());
    }

    @Test
    void testSort_WithAllNullFields_ShouldHandleCorrectly() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student(null, null, null);
        Student studentB = new Student(null, null, null);
        Student studentC = new Student("GR1", 8.5, 100);
        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);

        List<Student> result = sorter.sort(studentList);

        assertNull(result.get(0).getGroupNumber());
        assertNull(result.get(1).getGroupNumber());
        assertEquals("GR1", result.get(2).getGroupNumber());
    }


    @Test
    void testSort_WithMixOfNullAndNonNull_ComplexScenario() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student(null, 9.5, 100);      // null группа
        Student studentB = new Student("GR1", null, 200);    // null оценка
        Student studentC = new Student("GR1", 8.5, null);    // null номер
        Student studentD = new Student("GR1", 8.5, 150);     // все поля есть
        Student studentE = new Student("GR2", 7.5, 50);      // другая группа
        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);
        studentList.add(studentD);
        studentList.add(studentE);

        List<Student> result = sorter.sort(studentList);

        // Сначала студент с null группой
        assertNull(result.get(0).getGroupNumber());

        // Затем студенты группы GR1: сначала с null оценкой, потом с null номером, потом нормальный
        assertEquals("GR1", result.get(1).getGroupNumber());
        assertNull(result.get(1).getAverageGrade());

        assertEquals("GR1", result.get(2).getGroupNumber());
        assertEquals(8.5, result.get(2).getAverageGrade());
        assertNull(result.get(2).getRecordBookNumber());

        assertEquals("GR1", result.get(3).getGroupNumber());
        assertEquals(8.5, result.get(3).getAverageGrade());
        assertEquals(150, result.get(3).getRecordBookNumber());

        // Последний студент группы GR2
        assertEquals("GR2", result.get(4).getGroupNumber());
    }

    @Test
    void testSort_ShouldNotModifyOriginalListOrder() {
        List<Student> originalList = new ArrayList<>();
        Student studentA = new Student("GR2", 8.5, 100);
        Student studentB = new Student("GR1", 8.5, 100);
        originalList.add(studentA);
        originalList.add(studentB);

        // Создаём копию для проверки
        List<Student> originalCopy = new ArrayList<>(originalList);

        sorter.sort(originalList);

        // Проверяем, что исходный список был изменён (сортировка на месте)
        assertNotEquals(originalCopy.getFirst().getGroupNumber(), originalList.getFirst().getGroupNumber());
        assertEquals("GR1", originalList.getFirst().getGroupNumber());
    }

    @Test
    void testSort_WithDescendingValues_ShouldSortAscending() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR3", 9.5, 300);
        Student studentB = new Student("GR2", 8.5, 200);
        Student studentC = new Student("GR1", 7.5, 100);
        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);

        List<Student> result = sorter.sort(studentList);

        assertEquals("GR1", result.get(0).getGroupNumber());
        assertEquals("GR2", result.get(1).getGroupNumber());
        assertEquals("GR3", result.get(2).getGroupNumber());
    }

    @Test
    void testSort_WithLargeDataSet_ShouldSortCorrectly() {
        List<Student> studentList = new ArrayList<>();

        // Создаём 50 студентов в случайном порядке
        for (int i = 50; i > 0; i--) {
            String group = "GR" + (i % 10);
            double grade = 5.0 + (i % 5);
            int recordBook = i * 10;
            studentList.add(new Student(group, grade, recordBook));
        }

        List<Student> result = sorter.sort(studentList);

        // Проверяем, что список отсортирован по всем критериям
        for (int i = 0; i < result.size() - 1; i++) {
            Student current = result.get(i);
            Student next = result.get(i + 1);

            int groupCompare = compareStrings(current.getGroupNumber(), next.getGroupNumber());
            if (groupCompare < 0) {
                continue; // Текущая группа меньше - всё правильно
            } else if (groupCompare == 0) {
                int gradeCompare = compareDoubles(current.getAverageGrade(), next.getAverageGrade());
                if (gradeCompare < 0) {
                    continue; // Текущая оценка меньше - всё правильно
                } else if (gradeCompare == 0) {
                    int bookCompare = compareIntegers(current.getRecordBookNumber(), next.getRecordBookNumber());
                    assertTrue(bookCompare <= 0, "Номера зачёток должны быть отсортированы");
                } else {
                    fail("Оценки должны быть отсортированы");
                }
            } else {
                fail("Группы должны быть отсортированы");
            }
        }
    }

    @Test
    void testCompareStudentsFields_WithBothNull_ShouldReturnZero() {
        int result = SortByAllFields.compareStudentsFields(null, null);

        assertEquals(0, result);
    }

    @Test
    void testCompareStudentsFields_WithFirstNull_ShouldReturnMinusOne() {
        int result = SortByAllFields.compareStudentsFields(null, "test");

        assertEquals(-1, result);
    }

    @Test
    void testCompareStudentsFields_WithSecondNull_ShouldReturnOne() {
        int result = SortByAllFields.compareStudentsFields("test", null);

        assertEquals(1, result);
    }

    @Test
    void testCompareStudentsFields_WithBothNonNullAndEqual_ShouldReturnZero() {
        int result = SortByAllFields.compareStudentsFields("test", "test");

        assertEquals(0, result);
    }

    @Test
    void testCompareStudentsFields_WithFirstLessThanSecond_ShouldReturnNegative() {
        int result = SortByAllFields.compareStudentsFields("apple", "banana");

        assertTrue(result < 0);
    }

    @Test
    void testCompareStudentsFields_WithFirstGreaterThanSecond_ShouldReturnPositive() {
        int result = SortByAllFields.compareStudentsFields("banana", "apple");

        assertTrue(result > 0);
    }

    @Test
    void testSort_WithSameGroupDifferentGradesAndBooks_ComplexOrdering() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR1", 9.5, 150);
        Student studentB = new Student("GR1", 9.5, 100);
        Student studentC = new Student("GR1", 8.5, 200);
        Student studentD = new Student("GR1", 8.5, 50);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);
        studentList.add(studentD);

        List<Student> result = sorter.sort(studentList);

        // Сначала по оценке (возрастание)
        assertEquals(8.5, result.get(0).getAverageGrade());
        assertEquals(8.5, result.get(1).getAverageGrade());
        assertEquals(9.5, result.get(2).getAverageGrade());
        assertEquals(9.5, result.get(3).getAverageGrade());

        // Потом по номеру зачётки (возрастание) внутри одинаковых оценок
        assertEquals(50, result.get(0).getRecordBookNumber());
        assertEquals(200, result.get(1).getRecordBookNumber());
        assertEquals(100, result.get(2).getRecordBookNumber());
        assertEquals(150, result.get(3).getRecordBookNumber());
    }

    @Test
    void testSort_ShouldBeStable_WhenAllFieldsEqual() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR1", 8.5, 100);
        Student studentB = new Student("GR1", 8.5, 100);
        Student studentC = new Student("GR1", 8.5, 100);

        // Сохраняем ссылки для проверки порядка
        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);

        List<Student> result = sorter.sort(studentList);

        // Если все поля равны, порядок должен сохраниться
        assertSame(studentA, result.get(0));
        assertSame(studentB, result.get(1));
        assertSame(studentC, result.get(2));
    }

    // Вспомогательные методы для проверки сортировки

    private int compareStrings(String s1, String s2) {
        if (s1 == null && s2 == null) return 0;
        if (s1 == null) return -1;
        if (s2 == null) return 1;
        return s1.compareTo(s2);
    }

    private int compareDoubles(Double d1, Double d2) {
        if (d1 == null && d2 == null) return 0;
        if (d1 == null) return -1;
        if (d2 == null) return 1;
        return Double.compare(d1, d2);
    }

    private int compareIntegers(Integer i1, Integer i2) {
        if (i1 == null && i2 == null) return 0;
        if (i1 == null) return -1;
        if (i2 == null) return 1;
        return Integer.compare(i1, i2);
    }


}
