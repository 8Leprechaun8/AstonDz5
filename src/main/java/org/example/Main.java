package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner inLibrary = new Scanner(System.in);
        mainMenu: while(true) {
            System.out.println("Введите способ, которым будет происходить ввод данных:\n" +
                    "1. Вручную\n" +
                    "2. Рандом\n" +
                    "3. Из файла\n" +
                    "0. Выход\n");
            int num1 = inLibrary.nextInt();
            switch (num1) {
                case 1:
                    //ToDo
                    break;
                case 2:
                    //ToDo
                    break;
                case 3:
                    //Todo
                    break;
                case 0:
                default:
                    break mainMenu;
            }
            System.out.println(
                    "======== Выберите действие: ========\n" +
                    "1. Сортировка по трем полям одновременно\n" +
                    "2. Сортировка по groupNumber\n" +
                    "3. Сортировка по averageGrade\n" +
                    "4. Сортировка по recordBookNumber\n" +
                    "5. Сортировка по recordBookNumber (Дополнительное задание 1)\n" +
                    "6. Запись отсортированных коллекций/найденных значений в файл в режиме добавления данных (Дополнительное задание 2)\n" +
                    "7. Заполнение коллекций посредством стримов (Дополнительное задание 3)\n" +
                    "8. Многопоточный метод, подсчитывающий количество вхождений элемента N в коллекцию и выводящий результат в консоль. (Дополнительное задание 4)\n" +
                    "0. Выход\n" +
                    "Выберите действие:");
            int num2 = inLibrary.nextInt();
            switch (num2) {
                case 1:
                    //ToDo
                    break;
                case 2:
                    //ToDo
                    break;
                case 3:
                    //ToDo
                    break;
                case 4:
                    //ToDo
                    break;
                case 5:
                    //ToDo
                    break;
                case 6:
                    //ToDo
                    break;
                case 7:
                    //ToDo
                    break;
                case 8:
                    //ToDo
                    break;
                case 0:
                default:
                    break mainMenu;
            }
        }
    }
}