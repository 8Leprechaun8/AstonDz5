package org.example.Test.sort;

import org.example.entity.Student;
import org.example.sort.impl.SortByAverageGrade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SortByAverageGradeTest {

    private SortByAverageGrade sorter;

    @BeforeEach
    public void setUp() {
        sorter = new SortByAverageGrade();
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
    void testSort_WithTwoStudents_DifferentGrades_ShouldSortAscending() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR1", 9.5, 100);
        Student studentB = new Student("GR1", 7.5, 200);
        studentList.add(studentA);
        studentList.add(studentB);

        List<Student> result = sorter.sort(studentList);

        assertEquals(7.5, result.get(0).getAverageGrade());
        assertEquals(9.5, result.get(1).getAverageGrade());
    }

    @Test
    void testSort_WithTwoStudents_EqualGrades_ShouldNotSwap() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR1", 8.5, 100);
        Student studentB = new Student("GR1", 8.5, 200);
        studentList.add(studentA);
        studentList.add(studentB);

        List<Student> result = sorter.sort(studentList);

        assertSame(studentA, result.get(0));
        assertSame(studentB, result.get(1));
    }

    @Test
    void testSort_WithMultipleStudents_DifferentGrades_ShouldSortCorrectly() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR1", 9.5, 100);
        Student studentB = new Student("GR1", 7.5, 200);
        Student studentC = new Student("GR1", 8.5, 300);
        Student studentD = new Student("GR1", 6.5, 400);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);
        studentList.add(studentD);

        List<Student> result = sorter.sort(studentList);

        assertEquals(6.5, result.get(0).getAverageGrade());
        assertEquals(7.5, result.get(1).getAverageGrade());
        assertEquals(8.5, result.get(2).getAverageGrade());
        assertEquals(9.5, result.get(3).getAverageGrade());
    }

    @Test
    void testSort_WithGradesInDescendingOrder_ShouldSortAscending() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR1", 10.0, 100);
        Student studentB = new Student("GR1", 9.0, 200);
        Student studentC = new Student("GR1", 8.0, 300);
        Student studentD = new Student("GR1", 7.0, 400);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);
        studentList.add(studentD);

        List<Student> result = sorter.sort(studentList);

        assertEquals(7.0, result.get(0).getAverageGrade());
        assertEquals(8.0, result.get(1).getAverageGrade());
        assertEquals(9.0, result.get(2).getAverageGrade());
        assertEquals(10.0, result.get(3).getAverageGrade());
    }

    @Test
    void testSort_WithGradesInAscendingOrder_ShouldRemainSame() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR1", 5.0, 100);
        Student studentB = new Student("GR1", 6.0, 200);
        Student studentC = new Student("GR1", 7.0, 300);
        Student studentD = new Student("GR1", 8.0, 400);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);
        studentList.add(studentD);

        List<Student> result = sorter.sort(studentList);

        assertSame(studentA, result.get(0));
        assertSame(studentB, result.get(1));
        assertSame(studentC, result.get(2));
        assertSame(studentD, result.get(3));
    }

    @Test
    void testSort_WithNullGrades_NullsShouldStayAtBeginning() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR1", null, 100);
        Student studentB = new Student("GR1", 8.5, 200);
        Student studentC = new Student("GR1", null, 300);
        Student studentD = new Student("GR1", 6.5, 400);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);
        studentList.add(studentD);

        List<Student> result = sorter.sort(studentList);

        // Null значения должны быть в начале
        assertNull(result.get(0).getAverageGrade());
        assertNull(result.get(1).getAverageGrade());
        assertEquals(6.5, result.get(2).getAverageGrade());
        assertEquals(8.5, result.get(3).getAverageGrade());
    }

    @Test
    void testSort_WithAllNullGrades_ShouldNotChangeOrder() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR1", null, 100);
        Student studentB = new Student("GR1", null, 200);
        Student studentC = new Student("GR1", null, 300);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);

        List<Student> result = sorter.sort(studentList);

        assertSame(studentA, result.get(0));
        assertSame(studentB, result.get(1));
        assertSame(studentC, result.get(2));
    }

    @Test
    void testSort_WithNullStudents_NullStudentsShouldBeHandled() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = null;
        Student studentB = new Student("GR1", 8.5, 200);
        Student studentC = null;
        Student studentD = new Student("GR1", 6.5, 400);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);
        studentList.add(studentD);

        List<Student> result = sorter.sort(studentList);

        // null студенты должны остаться в начале
        assertNull(result.get(0));
        assertNull(result.get(1));
        assertEquals(6.5, result.get(2).getAverageGrade());
        assertEquals(8.5, result.get(3).getAverageGrade());
    }

    @Test
    void testSort_WithAllNullStudents_ShouldReturnSameList() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(null);
        studentList.add(null);
        studentList.add(null);

        List<Student> result = sorter.sort(studentList);

        assertNull(result.get(0));
        assertNull(result.get(1));
        assertNull(result.get(2));
    }

    @Test
    void testSort_WithMixOfNullAndValidStudents_ComplexScenario() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR1", null, 100);
        Student studentB = null;
        Student studentC = new Student("GR1", 9.5, 300);
        Student studentD = new Student("GR1", 7.5, 400);
        Student studentE = null;
        Student studentF = new Student("GR1", 8.5, 600);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);
        studentList.add(studentD);
        studentList.add(studentE);
        studentList.add(studentF);

        List<Student> result = sorter.sort(studentList);

        // Проверяем, что null объекты и null значения в начале
        int nonNullIndex = 0;
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i) != null && result.get(i).getAverageGrade() != null) {
                nonNullIndex = i;
                break;
            }
        }

        // После null должны идти отсортированные значения
        assertTrue(result.get(0) == null || result.get(0).getAverageGrade() == null);
        assertTrue(result.get(1) == null || result.get(1).getAverageGrade() == null);

        // Проверяем сортировку валидных значений
        List<Double> validGrades = new ArrayList<>();
        for (Student s : result) {
            if (s != null && s.getAverageGrade() != null) {
                validGrades.add(s.getAverageGrade());
            }
        }

        for (int i = 0; i < validGrades.size() - 1; i++) {
            assertTrue(validGrades.get(i) <= validGrades.get(i + 1));
        }
    }

    @Test
    void testSort_ShouldModifyOriginalList() {
        List<Student> originalList = new ArrayList<>();
        Student studentA = new Student("GR1", 9.5, 100);
        Student studentB = new Student("GR1", 7.5, 200);
        originalList.add(studentA);
        originalList.add(studentB);

        List<Student> result = sorter.sort(originalList);

        // Проверяем, что исходный список изменён (сортировка на месте)
        assertEquals(7.5, originalList.get(0).getAverageGrade());
        assertEquals(9.5, originalList.get(1).getAverageGrade());

        // И возвращаемый результат - тот же самый список
        assertSame(originalList, result);
    }

    @Test
    void testSort_WithNegativeGrades_ShouldSortCorrectly() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR1", -5.5, 100);
        Student studentB = new Student("GR1", 0.0, 200);
        Student studentC = new Student("GR1", -2.5, 300);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);

        List<Student> result = sorter.sort(studentList);

        assertEquals(-5.5, result.get(0).getAverageGrade());
        assertEquals(-2.5, result.get(1).getAverageGrade());
        assertEquals(0.0, result.get(2).getAverageGrade());
    }

    @Test
    void testSort_WithVeryLargeGrades_ShouldSortCorrectly() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR1", 1000000.0, 100);
        Student studentB = new Student("GR1", 0.0, 200);
        Student studentC = new Student("GR1", 999999.0, 300);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);

        List<Student> result = sorter.sort(studentList);

        assertEquals(0.0, result.get(0).getAverageGrade());
        assertEquals(999999.0, result.get(1).getAverageGrade());
        assertEquals(1000000.0, result.get(2).getAverageGrade());
    }

    @Test
    void testSort_WithDuplicateGrades_ShouldKeepRelativeOrder() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR1", 8.5, 100);
        Student studentB = new Student("GR1", 8.5, 200);
        Student studentC = new Student("GR1", 8.5, 300);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);

        List<Student> result = sorter.sort(studentList);

        // Порядок одинаковых элементов должен сохраниться (стабильная сортировка)
        assertSame(studentA, result.get(0));
        assertSame(studentB, result.get(1));
        assertSame(studentC, result.get(2));
    }

    @Test
    void testSort_WithLargeDataSet_ShouldSortCorrectly() {
        List<Student> studentList = new ArrayList<>();

        // Создаём 50 студентов со случайными оценками
        for (int i = 50; i > 0; i--) {
            double grade = 5.0 + (i % 45) / 10.0;
            studentList.add(new Student("GR1", grade, i));
        }

        List<Student> result = sorter.sort(studentList);

        // Проверяем, что результат отсортирован по возрастанию
        for (int i = 0; i < result.size() - 1; i++) {
            Double current = result.get(i).getAverageGrade();
            Double next = result.get(i + 1).getAverageGrade();

            if (current != null && next != null) {
                assertTrue(current <= next,
                        "Grades should be sorted in ascending order. Found " + current + " followed by " + next);
            }
        }
    }

    @Test
    void testSort_WithStudentsHavingSameGradesButDifferentGroups_ShouldSortOnlyByGrade() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR10", 8.5, 100);
        Student studentB = new Student("GR1", 7.5, 200);
        Student studentC = new Student("GR5", 8.5, 300);
        Student studentD = new Student("GR2", 7.5, 400);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);
        studentList.add(studentD);

        List<Student> result = sorter.sort(studentList);

        // Сортировка только по оценке, независимо от группы
        assertEquals(7.5, result.get(0).getAverageGrade());
        assertEquals(7.5, result.get(1).getAverageGrade());
        assertEquals(8.5, result.get(2).getAverageGrade());
        assertEquals(8.5, result.get(3).getAverageGrade());
    }

    @Test
    void testSort_WithBoundaryValues_ShouldHandleCorrectly() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR1", Double.MIN_VALUE, 100);
        Student studentB = new Student("GR1", 0.0, 200);
        Student studentC = new Student("GR1", Double.MAX_VALUE, 300);
        Student studentD = new Student("GR1", -Double.MAX_VALUE, 400);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);
        studentList.add(studentD);

        List<Student> result = sorter.sort(studentList);

        assertEquals(-Double.MAX_VALUE, result.get(0).getAverageGrade());
        assertEquals(0.0, result.get(1).getAverageGrade());
        assertEquals(Double.MIN_VALUE, result.get(2).getAverageGrade());
        assertEquals(Double.MAX_VALUE, result.get(3).getAverageGrade());
    }

    @Test
    void testSort_ShouldBeStable_WhenGradesEqual() {
        List<Student> studentList = new ArrayList<>();

        // Создаём студентов с одинаковыми оценками, но разными номерами зачёток
        Student studentA = new Student("GR1", 8.5, 100);
        Student studentB = new Student("GR2", 8.5, 200);
        Student studentC = new Student("GR3", 8.5, 300);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);

        List<Student> result = sorter.sort(studentList);

        // Проверяем, что порядок сохранился (стабильная сортировка)
        assertSame(studentA, result.get(0));
        assertSame(studentB, result.get(1));
        assertSame(studentC, result.get(2));
    }


}
