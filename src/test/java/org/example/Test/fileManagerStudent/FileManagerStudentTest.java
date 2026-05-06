package org.example.Test.fileManagerStudent;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.Student;
import org.example.repository.impl.FileManagerStudent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

public class FileManagerStudentTest {
    @TempDir
    Path tempDir;

    private FileManagerStudent fileManagerStudent;
    private File testStudentFile;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() throws Exception {
        // Сбрасываем синглтон для изоляции тестов
        resetSingleton();

        // Создаём тестовый файл во временной директории
        testStudentFile = tempDir.resolve("student.txt").toFile();

        // Получаем экземпляр
        fileManagerStudent = FileManagerStudent.getInstance();

        // Подменяем файл на тестовый через рефлексию
        Field fileField = FileManagerStudent.class.getDeclaredField("fileStudent");
        fileField.setAccessible(true);
        fileField.set(fileManagerStudent, testStudentFile);

        // Инициализируем пустым массивом
        objectMapper = new ObjectMapper();
        objectMapper.writeValue(testStudentFile, new ArrayList<Student>());
    }

    @Test
    void testGetInstance_ShouldReturnSameInstance() {
        FileManagerStudent instance1 = FileManagerStudent.getInstance();
        FileManagerStudent instance2 = FileManagerStudent.getInstance();

        assertNotNull(instance1);
        assertSame(instance1, instance2, "Должен возвращаться тот же самый экземпляр");
    }

    @Test
    void testSaveStudent_ShouldAddNewStudent() throws IOException {
        // Создаем тестовые данные
        List<Student> initialStudents = new ArrayList<>();
        Student existingStudent = new Student("GR1", 9.5, 100);
        initialStudents.add(existingStudent);

        // Записываем начальные данные в файл
        objectMapper.writeValue(testStudentFile, initialStudents);

        // Сохраняем нового студента
        Student newStudent = new Student("GR10", 8.5, 50);
        Student savedStudent = fileManagerStudent.saveStudent(newStudent);

        // Проверяем результат
        assertNotNull(savedStudent);
        assertEquals(newStudent, savedStudent);

        // Проверяем, что студент был добавлен в файл
        List<Student> resultStudents = objectMapper.readValue(testStudentFile, new TypeReference<>() {
        });
        assertEquals(2, resultStudents.size());
        assertTrue(resultStudents.contains(newStudent));
        assertTrue(resultStudents.contains(existingStudent));
    }

    @Test
    void testSaveStudent_ShouldNotAddDuplicateStudent() throws IOException {
        // Создаем тестовые данные
        List<Student> initialStudents = new ArrayList<>();
        Student existingStudent = new Student("GR1", 9.5, 100);
        initialStudents.add(existingStudent);

        // Записываем начальные данные в файл
        objectMapper.writeValue(testStudentFile, initialStudents);

        // Пытаемся добавить существующего студента
        Student duplicateStudent = new Student("GR1", 9.5, 100);
        Student savedStudent = fileManagerStudent.saveStudent(duplicateStudent);

        // Проверяем, что дубликат не был добавлен
        List<Student> resultStudents = objectMapper.readValue(testStudentFile, new TypeReference<>() {
        });
        assertEquals(1, resultStudents.size(), "Не должен добавлять дубликат студента");
    }

    @Test
    void testSaveStudent_WhenFileIsEmpty_ShouldCreateNewList() throws IOException {
        // Создаем пустой список в файле
        objectMapper.writeValue(testStudentFile, new ArrayList<Student>());

        Student newStudent = new Student("GR20", 7.5, 30);
        Student savedStudent = fileManagerStudent.saveStudent(newStudent);

        assertNotNull(savedStudent);

        List<Student> resultStudents = objectMapper.readValue(testStudentFile, new TypeReference<>() {
        });
        assertEquals(1, resultStudents.size());
        assertEquals(newStudent, resultStudents.get(0));
    }

    @Test
    void testGetStudentList_ShouldReturnListOfStudents() throws IOException {
        // Подготовка тестовых данных
        List<Student> expectedStudents = List.of(
                new Student("GR1", 9.5, 100),
                new Student("GR8", 10.0, 145),
                new Student("GR4", 5.3, 10)
        );

        objectMapper.writeValue(testStudentFile, expectedStudents);

        List<Student> resultStudents = fileManagerStudent.getStudentList();

        assertNotNull(resultStudents);
        assertEquals(expectedStudents.size(), resultStudents.size());
        assertEquals(expectedStudents, resultStudents);
    }

    @Test
    void testGetStudentList_WhenFileIsEmpty_ShouldReturnEmptyList() throws IOException {
        // Записываем пустой список
        objectMapper.writeValue(testStudentFile, new ArrayList<Student>());

        List<Student> resultStudents = fileManagerStudent.getStudentList();

        assertNotNull(resultStudents);
        assertTrue(resultStudents.isEmpty());
    }

    @Test
    void testGetStudentList_WhenIOExceptionOccurs_ShouldThrowRuntimeException() throws IOException {
        // Создаем файл, но делаем его недоступным для чтения
        if (testStudentFile.exists()) {
            boolean isReadable = testStudentFile.setReadable(false);
        }

        assertThrows(RuntimeException.class, () -> fileManagerStudent.getStudentList());
    }

    @Test
    void testSaveStudent_WhenFileIsReadOnly_ShouldThrowRuntimeException() throws Exception {
        // 1. Создаём файл с данными
        File readOnlyFile = tempDir.resolve("readonly.txt").toFile();
        objectMapper.writeValue(readOnlyFile, new ArrayList<Student>());

        // 2. Делаем файл только для чтения (это вызовет IOException при записи)
        boolean setReadOnly = readOnlyFile.setReadOnly();
        assertTrue(readOnlyFile.canRead());
        assertFalse(readOnlyFile.canWrite());

        // 3. Подменяем файл в репозитории
        Field fileField = FileManagerStudent.class.getDeclaredField("fileStudent");
        fileField.setAccessible(true);
        File originalFile = (File) fileField.get(fileManagerStudent);
        fileField.set(fileManagerStudent, readOnlyFile);

        try {
            // 4. Пытаемся сохранить студента
            Student student = new Student("GR99", 6.0, 25);

            // 5. Проверяем, что выбрасывается RuntimeException
            RuntimeException exception = assertThrows(RuntimeException.class,
                    () -> fileManagerStudent.saveStudent(student)
            );

            // 6. Проверяем, что причина - IOException
            assertNotNull(exception.getCause());
            assertInstanceOf(IOException.class, exception.getCause(), "Причина должна быть IOException, но было: " + exception.getCause());

        } finally {
            // Восстанавливаем оригинальный файл
            fileField.set(fileManagerStudent, originalFile);
        }
    }

    @Test
    void testSaveStudent_WhenFileIsCorrupted_ShouldThrowRuntimeException() throws IOException {
        // Записываем некорректный JSON в файл
        Files.writeString(testStudentFile.toPath(), "{ corrupted json [ }");

        Student student = new Student("GR99", 6.0, 25);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> fileManagerStudent.saveStudent(student)
        );

        assertNotNull(exception.getCause());
        assertInstanceOf(IOException.class, exception.getCause());
    }

    @Test
    void testGetStudentList_WhenFileIsCorrupted_ShouldThrowRuntimeException() throws IOException {
        // Записываем некорректный JSON
        Files.writeString(testStudentFile.toPath(), "not a valid json");

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> fileManagerStudent.getStudentList()
        );

        assertNotNull(exception.getCause());
        assertInstanceOf(IOException.class, exception.getCause());
    }

    private void resetSingleton() throws Exception {
        Field instanceField = FileManagerStudent.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, null);
    }

    @AfterEach
    void cleanUp() throws Exception {
        resetSingleton();
    }


}
