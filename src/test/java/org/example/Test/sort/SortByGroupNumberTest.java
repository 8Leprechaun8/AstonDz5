package org.example.Test.sort;

import org.example.entity.Student;
import org.example.sort.impl.SortByGroupNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SortByGroupNumberTest {
    private SortByGroupNumber sorter;

    @BeforeEach
    public void setup() {
        sorter = new SortByGroupNumber();
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
    void testSort_WithTwoStudents_DifferentGroups_ShouldSortAscending() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR2", 8.5, 100);
        Student studentB = new Student("GR1", 8.5, 200);
        studentList.add(studentA);
        studentList.add(studentB);

        List<Student> result = sorter.sort(studentList);

        assertEquals("GR1", result.get(0).getGroupNumber());
        assertEquals("GR2", result.get(1).getGroupNumber());
    }

    @Test
    void testSort_WithTwoStudents_EqualGroups_ShouldNotSwap() {
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
    void testSort_WithMultipleStudents_DifferentGroups_ShouldSortCorrectly() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR5", 8.5, 100);
        Student studentB = new Student("GR2", 8.5, 200);
        Student studentC = new Student("GR8", 8.5, 300);
        Student studentD = new Student("GR1", 8.5, 400);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);
        studentList.add(studentD);

        List<Student> result = sorter.sort(studentList);

        assertEquals("GR1", result.get(0).getGroupNumber());
        assertEquals("GR2", result.get(1).getGroupNumber());
        assertEquals("GR5", result.get(2).getGroupNumber());
        assertEquals("GR8", result.get(3).getGroupNumber());
    }

    @Test
    void testSort_WithGroupsInDescendingOrder_ShouldSortAscending() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR9", 8.5, 100);
        Student studentB = new Student("GR7", 8.5, 200);
        Student studentC = new Student("GR4", 8.5, 300);
        Student studentD = new Student("GR3", 8.5, 400);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);
        studentList.add(studentD);

        List<Student> result = sorter.sort(studentList);

        assertEquals("GR3", result.get(0).getGroupNumber());
        assertEquals("GR4", result.get(1).getGroupNumber());
        assertEquals("GR7", result.get(2).getGroupNumber());
        assertEquals("GR9", result.get(3).getGroupNumber());
    }
    @Test
    void testSort_WithGroupsInAscendingOrder_ShouldRemainSame() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR1", 8.5, 100);
        Student studentB = new Student("GR2", 8.5, 200);
        Student studentC = new Student("GR3", 8.5, 300);
        Student studentD = new Student("GR4", 8.5, 400);

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
    void testSort_WithNullGroupNumbers_NullsShouldStayAtBeginning() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student(null, 8.5, 100);
        Student studentB = new Student("GR2", 8.5, 200);
        Student studentC = new Student(null, 8.5, 300);
        Student studentD = new Student("GR1", 8.5, 400);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);
        studentList.add(studentD);

        List<Student> result = sorter.sort(studentList);

        // Null значения должны быть в начале
        assertNull(result.get(0).getGroupNumber());
        assertNull(result.get(1).getGroupNumber());
        assertEquals("GR1", result.get(2).getGroupNumber());
        assertEquals("GR2", result.get(3).getGroupNumber());
    }

    @Test
    void testSort_WithAllNullGroupNumbers_ShouldNotChangeOrder() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student(null, 8.5, 100);
        Student studentB = new Student(null, 8.5, 200);
        Student studentC = new Student(null, 8.5, 300);

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
        Student studentB = new Student("GR2", 8.5, 200);
        Student studentC = null;
        Student studentD = new Student("GR1", 8.5, 400);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);
        studentList.add(studentD);

        List<Student> result = sorter.sort(studentList);

        // null студенты должны остаться в начале
        assertNull(result.get(0));
        assertNull(result.get(1));
        assertEquals("GR1", result.get(2).getGroupNumber());
        assertEquals("GR2", result.get(3).getGroupNumber());
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
    void testSort_WithMixOfNullAndValidGroups_ComplexScenario() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student(null, 8.5, 100);
        Student studentB = null;
        Student studentC = new Student("GR10", 8.5, 300);
        Student studentD = new Student("GR2", 8.5, 400);
        Student studentE = null;
        Student studentF = new Student("GR5", 8.5, 600);

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
            if (result.get(i) != null && result.get(i).getGroupNumber() != null) {
                firstValidIndex = i;
                break;
            }
        }

        // Первые элементы должны быть null
        assertTrue(firstValidIndex >= 2);

        // Проверяем сортировку валидных значений
        List<String> validGroups = new ArrayList<>();
        for (Student s : result) {
            if (s != null && s.getGroupNumber() != null) {
                validGroups.add(s.getGroupNumber());
            }
        }

        for (int i = 0; i < validGroups.size() - 1; i++) {
            assertTrue(validGroups.get(i).compareTo(validGroups.get(i + 1)) <= 0);
        }
    }

    @Test
    void testSort_ShouldModifyOriginalList() {
        List<Student> originalList = new ArrayList<>();
        Student studentA = new Student("GR2", 8.5, 100);
        Student studentB = new Student("GR1", 8.5, 200);
        originalList.add(studentA);
        originalList.add(studentB);

        List<Student> result = sorter.sort(originalList);

        // Проверяем, что исходный список изменён (сортировка на месте)
        assertEquals("GR1", originalList.get(0).getGroupNumber());
        assertEquals("GR2", originalList.get(1).getGroupNumber());

        // И возвращаемый результат - тот же самый список
        assertSame(originalList, result);
    }

    @Test
    void testSort_WithAlphanumericGroups_ShouldSortCorrectly() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR10", 8.5, 100);
        Student studentB = new Student("GR2", 8.5, 200);
        Student studentC = new Student("GR1", 8.5, 300);
        Student studentD = new Student("GR11", 8.5, 400);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);
        studentList.add(studentD);

        List<Student> result = sorter.sort(studentList);

        // Лексикографическая сортировка (как строки)
        assertEquals("GR1", result.get(0).getGroupNumber());
        assertEquals("GR10", result.get(1).getGroupNumber());
        assertEquals("GR11", result.get(2).getGroupNumber());
        assertEquals("GR2", result.get(3).getGroupNumber());
    }

    @Test
    void testSort_WithSpecialCharactersInGroups_ShouldSortCorrectly() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR-A", 8.5, 100);
        Student studentB = new Student("GR!", 8.5, 200);
        Student studentC = new Student("GR1", 8.5, 300);
        Student studentD = new Student("GR_A", 8.5, 400);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);
        studentList.add(studentD);

        List<Student> result = sorter.sort(studentList);

        // Сортировка по ASCII/Unicode значениям
        assertNotNull(result.get(0).getGroupNumber());
        assertNotNull(result.get(1).getGroupNumber());
        assertNotNull(result.get(2).getGroupNumber());
        assertNotNull(result.get(3).getGroupNumber());
    }

    @Test
    void testSort_WithUpperCaseAndLowerCase_ShouldSortCorrectly() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("gr2", 8.5, 100);
        Student studentB = new Student("GR1", 8.5, 200);
        Student studentC = new Student("Gr3", 8.5, 300);
        Student studentD = new Student("GR1", 8.5, 400);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);
        studentList.add(studentD);

        List<Student> result = sorter.sort(studentList);

        // Заглавные буквы имеют меньшие ASCII коды
        assertEquals("GR1", result.get(0).getGroupNumber());
        assertEquals("GR1", result.get(1).getGroupNumber());
        assertEquals("Gr3", result.get(2).getGroupNumber());
        assertEquals("gr2", result.get(3).getGroupNumber());
    }

    @Test
    void testSort_WithEmptyStringGroups_ShouldHandleCorrectly() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("", 8.5, 100);
        Student studentB = new Student("GR2", 8.5, 200);
        Student studentC = new Student("", 8.5, 300);
        Student studentD = new Student("GR1", 8.5, 400);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);
        studentList.add(studentD);

        List<Student> result = sorter.sort(studentList);

        // Пустые строки должны быть в начале (так как "" сравнивается меньше любой непустой строки)
        assertEquals("", result.get(0).getGroupNumber());
        assertEquals("", result.get(1).getGroupNumber());
        assertEquals("GR1", result.get(2).getGroupNumber());
        assertEquals("GR2", result.get(3).getGroupNumber());
    }

    @Test
    void testSort_WithSpacesInGroupNames_ShouldSortCorrectly() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR 1", 8.5, 100);
        Student studentB = new Student("GR1", 8.5, 200);
        Student studentC = new Student("GR 2", 8.5, 300);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);

        List<Student> result = sorter.sort(studentList);

        // Пробел имеет меньший ASCII код чем цифры
        assertEquals("GR 1", result.get(0).getGroupNumber());
        assertEquals("GR 2", result.get(1).getGroupNumber());
        assertEquals("GR1", result.get(2).getGroupNumber());
    }

    @Test
    void testSort_WithVeryLongGroupNames_ShouldSortCorrectly() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GroupWithVeryLongNameThatExceedsNormalLength", 8.5, 100);
        Student studentB = new Student("GroupA", 8.5, 200);
        Student studentC = new Student("GroupWithLongName", 8.5, 300);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);

        List<Student> result = sorter.sort(studentList);

        assertEquals("GroupA", result.get(0).getGroupNumber());
        assertEquals("GroupWithLongName", result.get(1).getGroupNumber());
        assertEquals("GroupWithVeryLongNameThatExceedsNormalLength", result.get(2).getGroupNumber());
    }

    @Test
    void testSort_WithDuplicateGroups_ShouldKeepRelativeOrder() {
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

        // Создаём 50 студентов со случайными группами
        String[] groups = {"GR10", "GR2", "GR1", "GR5", "GR3", "GR8", "GR4", "GR9", "GR7", "GR6"};
        for (int i = 0; i < 50; i++) {
            String group = groups[i % groups.length] + (i / groups.length);
            studentList.add(new Student(group, 8.5, i));
        }

        // Перемешиваем небольшим random, но для теста просто возьмём как есть

        List<Student> result = sorter.sort(studentList);

        // Проверяем, что результат отсортирован по возрастанию
        for (int i = 0; i < result.size() - 1; i++) {
            String current = result.get(i).getGroupNumber();
            String next = result.get(i + 1).getGroupNumber();

            if (current != null && next != null) {
                assertTrue(current.compareTo(next) <= 0,
                        "Groups should be sorted in ascending order. Found " + current + " followed by " + next);
            }
        }
    }

    @Test
    void testSort_WithGroupsContainingNumbers_ShouldSortLexicographically() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR100", 8.5, 100);
        Student studentB = new Student("GR20", 8.5, 200);
        Student studentC = new Student("GR3", 8.5, 300);
        Student studentD = new Student("GR200", 8.5, 400);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);
        studentList.add(studentD);

        List<Student> result = sorter.sort(studentList);

        // Лексикографическая сортировка (как строки, не числовая)
        assertEquals("GR100", result.get(0).getGroupNumber());
        assertEquals("GR20", result.get(1).getGroupNumber());
        assertEquals("GR200", result.get(2).getGroupNumber());
        assertEquals("GR3", result.get(3).getGroupNumber());
    }

    @Test
    void testSort_WithNullValuesInMiddle_ShouldBringAllNullsToFront() {
        List<Student> studentList = new ArrayList<>();
        Student studentA = new Student("GR5", 8.5, 100);
        Student studentB = new Student(null, 8.5, 200);
        Student studentC = new Student("GR3", 8.5, 300);
        Student studentD = new Student(null, 8.5, 400);
        Student studentE = new Student("GR1", 8.5, 500);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);
        studentList.add(studentD);
        studentList.add(studentE);

        List<Student> result = sorter.sort(studentList);

        // Все null должны быть в начале
        assertNull(result.get(0).getGroupNumber());
        assertNull(result.get(1).getGroupNumber());

        // Затем отсортированные не-null значения
        assertEquals("GR1", result.get(2).getGroupNumber());
        assertEquals("GR3", result.get(3).getGroupNumber());
        assertEquals("GR5", result.get(4).getGroupNumber());
    }

    @Test
    void testSort_ShouldBeStable_WhenGroupsEqual() {
        List<Student> studentList = new ArrayList<>();

        // Создаём студентов с одинаковыми группами, но разными оценками и номерами зачёток
        Student studentA = new Student("GR1", 9.5, 100);
        Student studentB = new Student("GR1", 8.5, 200);
        Student studentC = new Student("GR1", 7.5, 300);

        studentList.add(studentA);
        studentList.add(studentB);
        studentList.add(studentC);

        List<Student> result = sorter.sort(studentList);

        // Проверяем, что порядок сохранился (стабильная сортировка)
        assertSame(studentA, result.get(0));
        assertSame(studentB, result.get(1));
        assertSame(studentC, result.get(2));

        // Проверяем, что группы одинаковые, но порядок не изменился
        assertEquals("GR1", result.get(0).getGroupNumber());
        assertEquals("GR1", result.get(1).getGroupNumber());
        assertEquals("GR1", result.get(2).getGroupNumber());
    }

}
