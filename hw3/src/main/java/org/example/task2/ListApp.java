package org.example.task2;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.xml.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListApp {
    public static final String FILE_JSON = "persondata.json";
    public static final String FILE_BIN = "persondata.bin";
    public static final String FILE_XML = "persondata.xml";

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final XmlMapper xmlMapper = new XmlMapper();

    public static void addNewPerson(Scanner scanner, List<Person> persons){
        System.out.println("Введите имя: ");
        String newName = scanner.nextLine();
        System.out.println("Введите возраст: ");
        int newAge = scanner.nextInt();
        persons.add(new Person(newName, newAge));
        savePersonToFile(FILE_JSON, persons);
        savePersonToFile(FILE_BIN, persons);
        savePersonToFile(FILE_XML, persons);
        System.out.println("Запись успешно добавлена.");
    }

    public static void removePerson(Scanner scanner, List<Person> persons){
        System.out.println("Введите имя: ");
        String removeName = scanner.nextLine();
        for (int i = 0; i < persons.size(); i++) {
            Person person = persons.get(i);
            String name = person.getName();
            if (removeName.equals(name)){
                persons.remove(person);
            }
        }
        savePersonToFile(FILE_JSON, persons);
        savePersonToFile(FILE_BIN, persons);
        savePersonToFile(FILE_XML, persons);
        System.out.println("Запись успешно удалена.");
    }

    public static void savePersonToFile (String fileName, List<Person> persons){
        try{
            if(fileName.endsWith(".json")){
                objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                objectMapper.writeValue(new File(fileName), persons);
            } else if (fileName.endsWith(".bin")) {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))){
                    oos.writeObject(persons);
                }
            } else if (fileName.endsWith(".xml")) {
                xmlMapper.writeValue(new File(fileName), persons);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static List<Person> loadPersonsFromFile(String fileName){
        List<Person> persons = new ArrayList<>();
        File file = new File(fileName);
        if(file.exists()){
            try{
                if(fileName.endsWith(".json")){
                    persons = objectMapper.readValue(file, objectMapper.getTypeFactory().
                            constructCollectionType(List.class, Person.class));
                } else if (fileName.endsWith(".bin")){
                    try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
                        persons = (List<Person>) ois.readObject();
                    }
                } else if (fileName.endsWith(".xml")) {
                    persons = xmlMapper.readValue(file, xmlMapper.getTypeFactory()
                            .constructCollectionType(List.class, Person.class));
                }
            } catch (IOException | ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        return persons;
    }

    public static void showAll(List<Person> persons){
        System.out.println("Список всех: ");
        for (int i = 0; i < persons.size(); i++) {
            Person person = persons.get(i);
            String name = person.getName();
            int age = person.getAge();
            System.out.println((i + 1) + ". " + name + ", " + age + " лет");
        }
    }
}
