package org.example.Test.sort;

import org.example.entity.Student;
import org.example.sort.impl.SortByRecordBookNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SortByRecordBookNumberTest {
    private SortByRecordBookNumber sorter;

    @BeforeEach
    public void setUp() {
        sorter = new SortByRecordBookNumber();
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
    void testSort_WithTwoStudents_DifferentRecordBooks_ShouldSortAscending() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR1", 8.5, 200);
        Student studentB = new Student("GR1", 8.5, 100);
        studentList.add(studentA);
        studentList.add(studentB);

        List<Student> result = sorter.sort(studentList);

        assertEquals(100, result.get(0).getRecordBookNumber());
        assertEquals(200, result.get(1).getRecordBookNumber());
    }

    @Test
    void testSort_WithMultipleStudents_DifferentRecordBooks_ShouldSortCorrectly() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR1", 8.5, 500);
        Student studentB = new Student("GR1", 8.5, 200);
        Student studentC = new Student("GR1", 8.5, 800);
        Student studentD = new Student("GR1", 8.5, 100);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);
        studentList.add(studentD);

        List<Student> result = sorter.sort(studentList);

        assertEquals(100, result.get(0).getRecordBookNumber());
        assertEquals(200, result.get(1).getRecordBookNumber());
        assertEquals(500, result.get(2).getRecordBookNumber());
        assertEquals(800, result.get(3).getRecordBookNumber());
    }

    @Test
    void testSort_WithRecordBooksInDescendingOrder_ShouldSortAscending() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR1", 8.5, 1000);
        Student studentB = new Student("GR1", 8.5, 900);
        Student studentC = new Student("GR1", 8.5, 800);
        Student studentD = new Student("GR1", 8.5, 700);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);
        studentList.add(studentD);

        List<Student> result = sorter.sort(studentList);

        assertEquals(700, result.get(0).getRecordBookNumber());
        assertEquals(800, result.get(1).getRecordBookNumber());
        assertEquals(900, result.get(2).getRecordBookNumber());
        assertEquals(1000, result.get(3).getRecordBookNumber());
    }

    @Test
    void testSort_WithRecordBooksInAscendingOrder_ShouldRemainSame() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR1", 8.5, 100);
        Student studentB = new Student("GR1", 8.5, 200);
        Student studentC = new Student("GR1", 8.5, 300);
        Student studentD = new Student("GR1", 8.5, 400);

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
    void testSort_WithNullRecordBookNumbers_NullsShouldStayAtBeginning() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR1", 8.5, null);
        Student studentB = new Student("GR1", 8.5, 200);
        Student studentC = new Student("GR1", 8.5, null);
        Student studentD = new Student("GR1", 8.5, 100);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);
        studentList.add(studentD);

        List<Student> result = sorter.sort(studentList);

        // Null значения должны быть в начале
        assertNull(result.get(0).getRecordBookNumber());
        assertNull(result.get(1).getRecordBookNumber());
        assertEquals(100, result.get(2).getRecordBookNumber());
        assertEquals(200, result.get(3).getRecordBookNumber());
    }

    @Test
    void testSort_WithAllNullRecordBooks_ShouldNotChangeOrder() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR1", 8.5, null);
        Student studentB = new Student("GR1", 8.5, null);
        Student studentC = new Student("GR1", 8.5, null);

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
        Student studentD = new Student("GR1", 8.5, 100);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);
        studentList.add(studentD);

        List<Student> result = sorter.sort(studentList);

        // null студенты должны остаться в начале
        assertNull(result.get(0));
        assertNull(result.get(1));
        assertEquals(100, result.get(2).getRecordBookNumber());
        assertEquals(200, result.get(3).getRecordBookNumber());
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
    void testSort_WithMixOfNullAndValidRecordBooks_ComplexScenario() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR1", 8.5, null);
        Student studentB = null;
        Student studentC = new Student("GR1", 8.5, 500);
        Student studentD = new Student("GR1", 8.5, 200);
        Student studentE = null;
        Student studentF = new Student("GR1", 8.5, 800);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);
        studentList.add(studentD);
        studentList.add(studentE);
        studentList.add(studentF);

        List<Student> result = sorter.sort(studentList);

        // Проверяем, что null объекты и null значения в начале
        int firstValidIndex = -1;
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i) != null && result.get(i).getRecordBookNumber() != null) {
                firstValidIndex = i;
                break;
            }
        }

        // Первые элементы должны быть null
        assertTrue(firstValidIndex >= 2);

        // Проверяем сортировку валидных значений
        List<Integer> validRecordBooks = new ArrayList<>();
        for (Student s : result) {
            if (s != null && s.getRecordBookNumber() != null) {
                validRecordBooks.add(s.getRecordBookNumber());
            }
        }

        for (int i = 0; i < validRecordBooks.size() - 1; i++) {
            assertTrue(validRecordBooks.get(i) <= validRecordBooks.get(i + 1));
        }
    }

    @Test
    void testSort_ShouldModifyOriginalList() {
        List<Student> originalList = new ArrayList<>();
        Student studentA = new Student("GR1", 8.5, 200);
        Student studentB = new Student("GR1", 8.5, 100);
        originalList.add(studentA);
        originalList.add(studentB);

        List<Student> result = sorter.sort(originalList);

        // Проверяем, что исходный список изменён (сортировка на месте)
        assertEquals(100, originalList.get(0).getRecordBookNumber());
        assertEquals(200, originalList.get(1).getRecordBookNumber());

        // И возвращаемый результат - тот же самый список
        assertSame(originalList, result);
    }

    @Test
    void testSort_WithNegativeRecordBooks_ShouldSortCorrectly() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR1", 8.5, -50);
        Student studentB = new Student("GR1", 8.5, 0);
        Student studentC = new Student("GR1", 8.5, -100);
        Student studentD = new Student("GR1", 8.5, 50);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);
        studentList.add(studentD);

        List<Student> result = sorter.sort(studentList);

        assertEquals(-100, result.get(0).getRecordBookNumber());
        assertEquals(-50, result.get(1).getRecordBookNumber());
        assertEquals(0, result.get(2).getRecordBookNumber());
        assertEquals(50, result.get(3).getRecordBookNumber());
    }

    @Test
    void testSort_WithVeryLargeRecordBooks_ShouldSortCorrectly() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR1", 8.5, Integer.MAX_VALUE);
        Student studentB = new Student("GR1", 8.5, 0);
        Student studentC = new Student("GR1", 8.5, Integer.MIN_VALUE);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);

        List<Student> result = sorter.sort(studentList);

        assertEquals(Integer.MIN_VALUE, result.get(0).getRecordBookNumber());
        assertEquals(0, result.get(1).getRecordBookNumber());
        assertEquals(Integer.MAX_VALUE, result.get(2).getRecordBookNumber());
    }

    @Test
    void testSort_WithDuplicateRecordBooks_ShouldKeepRelativeOrder() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR1", 8.5, 100);
        Student studentB = new Student("GR2", 8.5, 100);
        Student studentC = new Student("GR3", 8.5, 100);

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

        // Создаём 50 студентов с разными номерами зачёток
        for (int i = 50; i > 0; i--) {
            studentList.add(new Student("GR1", 8.5, i * 10));
        }

        List<Student> result = sorter.sort(studentList);

        // Проверяем, что результат отсортирован по возрастанию
        for (int i = 0; i < result.size() - 1; i++) {
            Integer current = result.get(i).getRecordBookNumber();
            Integer next = result.get(i + 1).getRecordBookNumber();

            if (current != null && next != null) {
                assertTrue(current <= next,
                        "Record book numbers should be sorted in ascending order. Found " + current + " followed by " + next);
            }
        }
    }

    @Test
    void testSort_WithMixedGroupsAndGrades_ShouldSortOnlyByRecordBook() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR10", 9.5, 300);
        Student studentB = new Student("GR1", 7.5, 100);
        Student studentC = new Student("GR5", 8.5, 200);
        Student studentD = new Student("GR2", 6.5, 400);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);
        studentList.add(studentD);

        List<Student> result = sorter.sort(studentList);

        // Сортировка только по номеру зачётки, независимо от группы и оценки
        assertEquals(100, result.get(0).getRecordBookNumber());
        assertEquals(200, result.get(1).getRecordBookNumber());
        assertEquals(300, result.get(2).getRecordBookNumber());
        assertEquals(400, result.get(3).getRecordBookNumber());

        // Проверяем, что студенты с правильными номерами зачёток
        assertSame(studentB, result.get(0));
        assertSame(studentC, result.get(1));
        assertSame(studentA, result.get(2));
        assertSame(studentD, result.get(3));
    }

    @Test
    void testSort_WithZeroRecordBooks_ShouldHandleCorrectly() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR1", 8.5, 0);
        Student studentB = new Student("GR1", 8.5, 100);
        Student studentC = new Student("GR1", 8.5, 0);
        Student studentD = new Student("GR1", 8.5, 50);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);
        studentList.add(studentD);

        List<Student> result = sorter.sort(studentList);

        assertEquals(0, result.get(0).getRecordBookNumber());
        assertEquals(0, result.get(1).getRecordBookNumber());
        assertEquals(50, result.get(2).getRecordBookNumber());
        assertEquals(100, result.get(3).getRecordBookNumber());
    }

    @Test
    void testSort_WithConsecutiveNumbers_ShouldSortCorrectly() {
        List<Student> studentList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            studentList.add(new Student("GR1", 8.5, i));
        }

        // Перемешиваем список в обратном порядке
        List<Student> reversedList = new ArrayList<>();
        for (int i = 19; i >= 0; i--) {
            reversedList.add(studentList.get(i));
        }

        List<Student> result = sorter.sort(reversedList);

        for (int i = 0; i < result.size(); i++) {
            assertEquals(i + 1, result.get(i).getRecordBookNumber());
        }
    }

    @Test
    void testSort_WithNullValuesInMiddle_ShouldBringAllNullsToFront() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR1", 8.5, 500);
        Student studentB = new Student("GR1", 8.5, null);
        Student studentC = new Student("GR1", 8.5, 300);
        Student studentD = new Student("GR1", 8.5, null);
        Student studentE = new Student("GR1", 8.5, 100);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);
        studentList.add(studentD);
        studentList.add(studentE);

        List<Student> result = sorter.sort(studentList);

        // Все null должны быть в начале
        assertNull(result.get(0).getRecordBookNumber());
        assertNull(result.get(1).getRecordBookNumber());

        // Затем отсортированные не-null значения
        assertEquals(100, result.get(2).getRecordBookNumber());
        assertEquals(300, result.get(3).getRecordBookNumber());
        assertEquals(500, result.get(4).getRecordBookNumber());
    }

    @Test
    void testSort_WithPartiallySortedData_ShouldCompleteSorting() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR1", 8.5, 100);
        Student studentB = new Student("GR1", 8.5, 500);
        Student studentC = new Student("GR1", 8.5, 300);
        Student studentD = new Student("GR1", 8.5, 800);
        Student studentE = new Student("GR1", 8.5, 200);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);
        studentList.add(studentD);
        studentList.add(studentE);

        List<Student> result = sorter.sort(studentList);

        assertEquals(100, result.get(0).getRecordBookNumber());
        assertEquals(200, result.get(1).getRecordBookNumber());
        assertEquals(300, result.get(2).getRecordBookNumber());
        assertEquals(500, result.get(3).getRecordBookNumber());
        assertEquals(800, result.get(4).getRecordBookNumber());
    }

    @Test
    void testSort_ShouldBeStable_WhenRecordBooksEqual() {
        List<Student> studentList = new ArrayList<>();

        // Создаём студентов с одинаковыми номерами зачёток, но разными группами и оценками
        Student studentA = new Student("GR1", 9.5, 100);
        Student studentB = new Student("GR2", 8.5, 100);
        Student studentC = new Student("GR3", 7.5, 100);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);

        List<Student> result = sorter.sort(studentList);

        // Проверяем, что порядок сохранился (стабильная сортировка)
        assertSame(studentA, result.get(0), "First student should stay first");
        assertSame(studentB, result.get(1), "Second student should stay second");
        assertSame(studentC, result.get(2), "Third student should stay third");

        // Проверяем, что номера зачёток одинаковые
        assertEquals(100, result.get(0).getRecordBookNumber());
        assertEquals(100, result.get(1).getRecordBookNumber());
        assertEquals(100, result.get(2).getRecordBookNumber());
    }

    @Test
    void testSort_WithRandomOrder_ShouldSortAllElements() {
        List<Student> studentList = new ArrayList<>();
        Integer[] randomNumbers = {42, 7, 99, 23, 1, 88, 15, 3, 56, 34};

        for (int num : randomNumbers) {
            studentList.add(new Student("GR1", 8.5, num));
        }

        List<Student> result = sorter.sort(studentList);

        for (int i = 0; i < result.size() - 1; i++) {
            assertTrue(result.get(i).getRecordBookNumber() <= result.get(i + 1).getRecordBookNumber());
        }
    }

    @Test
    void testSort_WithBoundaryValues_ShouldHandleCorrectly() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR1", 8.5, Integer.MIN_VALUE);
        Student studentB = new Student("GR1", 8.5, -1);
        Student studentC = new Student("GR1", 8.5, 0);
        Student studentD = new Student("GR1", 8.5, 1);
        Student studentE = new Student("GR1", 8.5, Integer.MAX_VALUE);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);
        studentList.add(studentD);
        studentList.add(studentE);

        List<Student> result = sorter.sort(studentList);

        assertEquals(Integer.MIN_VALUE, result.get(0).getRecordBookNumber());
        assertEquals(-1, result.get(1).getRecordBookNumber());
        assertEquals(0, result.get(2).getRecordBookNumber());
        assertEquals(1, result.get(3).getRecordBookNumber());
        assertEquals(Integer.MAX_VALUE, result.get(4).getRecordBookNumber());
    }

    @Test
    void testSort_WithUnsortedLargeNumbers_ShouldSortCorrectly() {
        List<Student> studentList = new ArrayList<>();

        // Создаём список с перемешанными большими числами
        int[] largeNumbers = {1000000, 500, 999999, 1000, 75000, 250, 50000, 1, 999999999, 100};

        for (int num : largeNumbers) {
            studentList.add(new Student("GR1", 8.5, num));
        }

        List<Student> result = sorter.sort(studentList);

        // Проверяем сортировку
        for (int i = 0; i < result.size() - 1; i++) {
            assertTrue(result.get(i).getRecordBookNumber() <= result.get(i + 1).getRecordBookNumber());
        }

        // Проверяем конкретные значения
        assertEquals(1, result.get(0).getRecordBookNumber());
        assertEquals(100, result.get(1).getRecordBookNumber());
        assertEquals(250, result.get(2).getRecordBookNumber());
        assertEquals(500, result.get(3).getRecordBookNumber());
        assertEquals(1000, result.get(4).getRecordBookNumber());
        assertEquals(50000, result.get(5).getRecordBookNumber());
        assertEquals(75000, result.get(6).getRecordBookNumber());
        assertEquals(999999, result.get(7).getRecordBookNumber());
        assertEquals(1000000, result.get(8).getRecordBookNumber());
        assertEquals(999999999, result.get(9).getRecordBookNumber());
    }
}
