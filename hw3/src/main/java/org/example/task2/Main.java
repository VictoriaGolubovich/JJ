package org.example.task2;

/**
 * Задание 2: Используя JPA, создайте базу данных для хранения объектов класса
 * Person. Реализуйте методы для добавления, обновления и удаления объектов Person.
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.task2.ListApp.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Person> persons;
        File file = new File(FILE_JSON);
        if (file.exists() && !file.isDirectory()){
            persons = loadPersonsFromFile(FILE_JSON);
        } else {
            persons = personList();
        }
        ListApp.savePersonToFile(FILE_JSON, persons);
        ListApp.savePersonToFile(FILE_BIN, persons);
        ListApp.savePersonToFile(FILE_XML, persons);

        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Выберите действие:");
            System.out.println("1. Весь список");
            System.out.println("2. Добавить нового человека");
            System.out.println("3. Удалить человека");
            System.out.println("4. Выйти");
            System.out.println();

            String choice = scanner.nextLine();
            switch (choice){
                case "1":
                    ListApp.showAll(persons);
                    break;
                case "2":
                    ListApp.addNewPerson(scanner, persons);
                    showAll(persons);
                    break;
                case "3":
                    ListApp.removePerson(scanner, persons);
                    showAll(persons);
                    break;
                case "4":
                    ListApp.savePersonToFile(FILE_JSON, persons);
                    ListApp.savePersonToFile(FILE_BIN, persons);
                    ListApp.savePersonToFile(FILE_XML, persons);
                    System.out.println("Список людей сохранен.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Некорректный ввод. Попробуйте снова");
                    break;
            }
        }

    }
    static List<Person> personList(){
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("Sam", 35));
        list.add(new Person("Susan", 41));
        list.add(new Person("Vicky", 28));
        return list;
    }
}